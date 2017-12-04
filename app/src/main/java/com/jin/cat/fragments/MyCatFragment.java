package com.jin.cat.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jin.cat.R;
import com.jin.cat.activities.LoginActivity;
import com.jin.cat.dialogs.MyCatDialog;
import com.jin.cat.models.MyCat;
import com.jin.cat.utils.FirebaseUtils;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyCatFragment extends Fragment {

    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference mDatabase;

    private RecyclerView recyclerView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;

    private String catId;
    private Boolean isChecked = false;

    public MyCatFragment() {
    }

    @Override
    public void onAttach(Context context) {
        if(FirebaseUtils.getCurrentUser() == null ){
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_cat, container, false);

        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NanumBarunGothic.otf");

        //TextView textView = (TextView)view.findViewById(R.id.textView1);
        //textView.setTypeface(typeFace);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        if(FirebaseUtils.getCurrentUser() != null){

            mDatabase = FirebaseDatabase.getInstance().getReference("User_Cat").child(FirebaseUtils.getCurrentUser().getUid());
        }

        CircleImageView circleImageView = (CircleImageView)view.findViewById(R.id.add_mycat);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCatDialog dialog = new MyCatDialog();
                dialog.show(getFragmentManager(), null);
            }
        });

        textView1 = (TextView)view.findViewById(R.id.textView_tap1);
        textView2 = (TextView)view.findViewById(R.id.textView_tap2);
        textView3= (TextView)view.findViewById(R.id.textView_tap3);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTap1();
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTap2();
            }
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(FirebaseUtils.getCurrentUser() != null) {
            FirebaseRecyclerAdapter<MyCat, MyCatViewHolder> FBRA = new FirebaseRecyclerAdapter<MyCat, MyCatViewHolder>(

                    MyCat.class,
                    R.layout.row_mycat_list,
                    MyCatViewHolder.class,
                    mDatabase
            ) {

                @Override
                protected void populateViewHolder(MyCatViewHolder viewHolder, final MyCat model, final int position) {

                    Glide.with(getActivity())
                            .load(model.getImage())
                            .into(viewHolder.catImage);

                    viewHolder.setCatName(model.getName());

                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isChecked = true;
                            Bundle bundle = new Bundle();
                            bundle.putString("catId", model.getUid());
                            catId = model.getUid();

                            Fragment fragment;
                            fragment = new MyCatDescFragment();
                            fragment.setArguments(bundle);
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_mycat_basic, fragment);
                            fragmentTransaction.commit();

                            Fragment fragment1;
                            fragment1 = new MyCatTodayFragment();
                            fragment1.setArguments(bundle);
                            FragmentManager fragmentManager1 = getFragmentManager();
                            FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                            fragmentTransaction1.replace(R.id.fragment_mycat_tap, fragment1);
                            fragmentTransaction1.commit();
                            //Toast.makeText(getActivity(),model.getName(),Toast.LENGTH_SHORT).show();
                        }
                    });

                    viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {

                            AlertDialog.Builder ab = new AlertDialog.Builder(getContext());
                            ab.setTitle("주의");
                            ab.setMessage("삭제하시겠습니까?");

                            ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mDatabase.child(model.getUid()).removeValue();

                                    Fragment fragment;
                                    fragment = new MyCatBasicFragment();
                                    FragmentManager fragmentManager = getFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_mycat_basic, fragment);
                                    fragmentTransaction.commit();

                                    Fragment fragment1;
                                    fragment1 = new MyCatBasicFragment();
                                    FragmentManager fragmentManager1 = getFragmentManager();
                                    FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                                    fragmentTransaction1.replace(R.id.fragment_mycat_tap, fragment1);
                                    fragmentTransaction1.commit();
                                }
                            });

                            ab.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            ab.show();

                            return true;


                        }
                    });

                }
            };
            recyclerView.setAdapter(FBRA);
        }

    }

    public static class MyCatViewHolder extends RecyclerView.ViewHolder{

        ImageView catImage;
        TextView catName;

        public MyCatViewHolder(View itemView) {
            super(itemView);

            catImage = (ImageView) itemView.findViewById(R.id.mycat_list_image);
            catName = (TextView) itemView.findViewById(R.id.mycat_list_name);
        }

        public void setCatName(String name){
            catName.setText(name);
        }


    }

    public void fragmentTap1(){

        if(isChecked) {

            Bundle bundle = new Bundle();
            bundle.putString("catId", catId);

            Fragment fragment;
            fragment = new MyCatTodayFragment();
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_mycat_tap, fragment);
            fragmentTransaction.commit();
        }
    }

    public void fragmentTap2(){

        if(isChecked) {

            Bundle bundle = new Bundle();
            bundle.putString("catId", catId);

            Fragment fragment;
            fragment = new MyCatInoculationFragment();
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_mycat_tap, fragment);
            fragmentTransaction.commit();

        }
    }


}
