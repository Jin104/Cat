package com.jin.cat.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.jin.cat.R;
import com.jin.cat.dialogs.MyCatInoculationDialog;
import com.jin.cat.models.Inoculation;
import com.jin.cat.models.MyCat;
import com.jin.cat.utils.FirebaseUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCatInoculationFragment extends Fragment {

    //private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;


    private FirebaseDatabase database;

    private DatabaseReference mFVRCPReference;
    private DatabaseReference mFeLVReference;
    private DatabaseReference mRabiesReference;
    private InoculationAdapter mAdapter1;
    private InoculationAdapter mAdapter2;
    private InoculationAdapter mAdapter3;

    public MyCatInoculationFragment() {

    }

    private void refresh(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_my_cat_inoculation, container, false);

        final String catId = getArguments().getString("catId");


        ImageButton imageButton = (ImageButton)view.findViewById(R.id.add_inoculation);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("catId", catId);
                bundle.putString("userId", FirebaseUtils.getCurrentUser().getUid());
                MyCatInoculationDialog dialog = new MyCatInoculationDialog();
                dialog.setArguments(bundle);
                dialog.show(getFragmentManager(), null);
            }
        });

        database = FirebaseDatabase.getInstance();
        mFVRCPReference = database.getReference("User_Cat").child(FirebaseUtils.getCurrentUser().getUid()).child(catId).child("inoculation").child("종합백신(FVRCP)");
        mFeLVReference = database.getReference("User_Cat").child(FirebaseUtils.getCurrentUser().getUid()).child(catId).child("inoculation").child("백혈병(FeLV)");
        mRabiesReference = database.getReference("User_Cat").child(FirebaseUtils.getCurrentUser().getUid()).child(catId).child("inoculation").child("광견병(Rabies)");

        recyclerView1 = (RecyclerView)view.findViewById(R.id.recycler1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView2 = (RecyclerView)view.findViewById(R.id.recycler2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView3 = (RecyclerView)view.findViewById(R.id.recycler3);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        mAdapter1 = new InoculationAdapter(getActivity(), mFVRCPReference);
        recyclerView1.setAdapter(mAdapter1);

        mAdapter2 = new InoculationAdapter(getActivity(), mFeLVReference);
        recyclerView2.setAdapter(mAdapter2);

        mAdapter3 = new InoculationAdapter(getActivity(), mRabiesReference);
        recyclerView3.setAdapter(mAdapter3);

    }

    @Override
    public void onStop() {
        super.onStop();

        mAdapter1.cleanupListener();
        mAdapter2.cleanupListener();
        mAdapter3.cleanupListener();
    }

    private static class InoculationViewHolder extends RecyclerView.ViewHolder{

        public TextView inocView;


        public InoculationViewHolder(View itemView) {
            super(itemView);

            inocView = itemView.findViewById(R.id.textView_inoculation);
        }
    }

    private class InoculationAdapter extends RecyclerView.Adapter<InoculationViewHolder>{

        private Context mContext;
        private DatabaseReference mDatabaseReference;
        private ChildEventListener mChildEventListener;

        private List<String> mInoculationId = new ArrayList<>();
        private List<Inoculation> mInoculation = new ArrayList<>();

        public InoculationAdapter(final Context context, DatabaseReference ref){

            mContext = context;
            mDatabaseReference = ref;
            Query query = mDatabaseReference.orderByChild("date");

            ChildEventListener childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Inoculation inoculation = dataSnapshot.getValue(Inoculation.class);

                    mInoculationId.add(dataSnapshot.getKey());
                    mInoculation.add(inoculation);
                    notifyItemInserted(mInoculation.size()-1);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Inoculation newInoculation = dataSnapshot.getValue(Inoculation.class);
                    String inoculationKey = dataSnapshot.getKey();

                    int inoculationIndex = mInoculationId.indexOf(inoculationKey);
                    if(inoculationIndex>-1){
                        mInoculation.set(inoculationIndex, newInoculation);
                        notifyItemChanged(inoculationIndex);
                    }else{

                    }
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    String inoculationKey = dataSnapshot.getKey();

                    int inoculationIndex = mInoculationId.indexOf(inoculationKey);
                    if(inoculationIndex>-1){
                        mInoculationId.remove(inoculationIndex);
                        mInoculation.remove(inoculationIndex);
                        notifyItemRemoved(inoculationIndex);
                    }else{

                    }
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            query.addChildEventListener(childEventListener);

            mChildEventListener = childEventListener;



        }

        @Override
        public InoculationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.row_inoculation, parent, false);
            return new InoculationViewHolder(view);
        }

        @Override
        public void onBindViewHolder(InoculationViewHolder holder, int position) {
            final Inoculation inoculation = mInoculation.get(position);
            holder.inocView.setText(inoculation.getDate());

            holder.inocView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                    ab.setTitle("알림");
                    ab.setMessage("삭제하시겠습니까?");

                    ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mDatabaseReference.child(inoculation.getUid()).removeValue();
                        }
                    });

                    ab.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    ab.show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mInoculation.size();
        }

        public void cleanupListener(){
            if(mChildEventListener!=null){
                mDatabaseReference.removeEventListener(mChildEventListener);
            }
        }
    }

}
