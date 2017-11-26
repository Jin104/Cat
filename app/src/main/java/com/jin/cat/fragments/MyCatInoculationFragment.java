package com.jin.cat.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    private LinearLayoutManager linearLayoutManager;
    private MyCat myCat;
    private Inoculation inoculation;
    private List<Inoculation> result1;
    private RecyclerView recyclerView1;
    private InoculationAdapter adapter1;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    public MyCatInoculationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_cat_inoculation, container, false);

        final String catId = getArguments().getString("catId");

        //final TextView textView = (TextView)view.findViewById(R.id.textView);

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
        reference = database.getReference("User_Cat").child(FirebaseUtils.getCurrentUser().getUid()).child(catId).child("inoculation").child("종합백신(FVRCP)");

        result1 = new ArrayList<>();

        recyclerView1 = (RecyclerView)view.findViewById(R.id.recycler1);
        recyclerView1.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(linearLayoutManager);

        adapter1 = new InoculationAdapter(result1);
        recyclerView1.setAdapter(adapter1);

        updateList();

        return view;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        Toast.makeText(getActivity(), "삭제", Toast.LENGTH_SHORT).show();
        switch (item.getItemId()){
            case 0:
                Toast.makeText(getActivity(), "삭제?", Toast.LENGTH_SHORT).show();
                //removeInoculation(item.getGroupId());
                break;
            case 1:
                Toast.makeText(getActivity(), "삭제??", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }



    private void updateList() {

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                result1.add(dataSnapshot.getValue(Inoculation.class));
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Inoculation item = dataSnapshot.getValue(Inoculation.class);

                int index = getItemIndex(item);

                result1.set(index, item);
                adapter1.notifyItemChanged(index);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Inoculation item = dataSnapshot.getValue(Inoculation.class);

                int index = getItemIndex(item);

                result1.remove(index);
                adapter1.notifyItemRemoved(index);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private int getItemIndex(Inoculation item) {

        int index = -1;

        for (int i = 0; i < result1.size(); i++) {
            if (result1.get(i).getKey().equals(item.getKey())) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void removeInoculation(int position){
        reference.child(result1.get(position).getDate()).removeValue();
        //Toast.makeText(getContext(), result1.get(position).getDate(), Toast.LENGTH_SHORT).show();
    }

    public class InoculationAdapter extends RecyclerView.Adapter<MyCatInoculationFragment.InoculationViewHolder>{

        private List<Inoculation> list;
        private Context context;

        public InoculationAdapter(List<Inoculation> list) {
            this.list = list;
        }

        @Override
        public MyCatInoculationFragment.InoculationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            context = parent.getContext();

            return new MyCatInoculationFragment.InoculationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_inoculation, parent, false));
        }

        @Override
        public void onBindViewHolder(final MyCatInoculationFragment.InoculationViewHolder holder, final int position) {

            final Inoculation inoculation = list.get(position);

            holder.textView.setText(inoculation.getDate());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder ab = new AlertDialog.Builder(context);
                    ab.setTitle("알림");
                    ab.setMessage("삭제하시겠습니까?");

                    ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getContext(), "삭제~~", Toast.LENGTH_SHORT).show();
                            removeInoculation(position);
                        }
                    });

                    ab.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getContext(), "아뇨~~", Toast.LENGTH_SHORT).show();
                        }
                    });


                    ab.show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }



    }

    class InoculationViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public InoculationViewHolder(View itemView) {
            super(itemView);

            textView = (TextView)itemView.findViewById(R.id.textView_inoculation);
        }
    }
}
