package com.jin.cat.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.jin.cat.R;
import com.jin.cat.dialogs.MyCatDialog;
import com.jin.cat.models.MyCat;
import com.jin.cat.utils.FirebaseUtils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CatInfoFragment extends Fragment {

    private MyCat myCat;

    public CatInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cat_info, container, false);

        final String catId = getArguments().getString("catId");

        final ImageView image = (ImageView)view.findViewById(R.id.imageView_cat);
        final TextView name = (TextView)view.findViewById(R.id.textView_catName);
        final TextView sex = (TextView)view.findViewById(R.id.textView_catSex);
        final TextView birthday = (TextView)view.findViewById(R.id.textView_catBirthDay);
        final TextView age = (TextView)view.findViewById(R.id.textView_catAge);
        final TextView humanAge = (TextView)view.findViewById(R.id.textView_catHumanAge);
        final TextView type = (TextView)view.findViewById(R.id.textView_catType);
        final TextView weight = (TextView)view.findViewById(R.id.textView_weight);

        //final Button fabButton = (Button)view.findViewById(R.id.fab_cat_update);
        final FloatingActionButton fabButton = (FloatingActionButton)view.findViewById(R.id.fab_cat_update);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final Calendar todayCal = Calendar.getInstance();
        final Calendar birthCal = Calendar.getInstance();

        FirebaseUtils.getMyCatRef(FirebaseUtils.getCurrentUser().getUid(), catId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        myCat = dataSnapshot.getValue(MyCat.class);

                        Context context = image.getContext();
                        Picasso.with(context).load(myCat.getImage()).into(image);
                        name.setText(myCat.getName());
                        sex.setText(myCat.getSex());
                        birthday.setText(myCat.getYear()+"년 "+myCat.getMonth()+"월 "+myCat.getDay()+"일");
                        //age.setText();

                        birthCal.set(Integer.parseInt(myCat.getYear()),Integer.parseInt(myCat.getMonth())-1,Integer.parseInt(myCat.getDay()));
                        long today = todayCal.getTimeInMillis()/86400000;
                        long birthday = birthCal.getTimeInMillis()/86400000;
                        long count = today - birthday;

                        age.setText(String.valueOf(count/30)+"개월");

                        if(count/30<30){
                            if((count/30)>=27) humanAge.setText("사람나이로 "+"25세");
                            else if((count/30)>=24) humanAge.setText("사람나이로 "+"24세");
                            else if((count/30)>=21) humanAge.setText("사람나이로 "+"23세");
                            else if((count/30)>=18) humanAge.setText("사람나이로 "+"22세");
                            else if((count/30)>=15) humanAge.setText("사람나이로 "+"20세");
                            else if((count/30)>=12) humanAge.setText("사람나이로 "+"18세");
                            else if((count/30)>=9) humanAge.setText("사람나이로 "+"15세");
                            else if((count/30)>=6) humanAge.setText("사람나이로 "+"12세");
                            else if((count/30)>=3) humanAge.setText("사람나이로 "+"6세");
                            else if((count/30)>=2) humanAge.setText("사람나이로 "+"4세");
                            else if(count>=45) humanAge.setText("사람나이로 "+"3세");
                            else if((count/30)>=1) humanAge.setText("사람나이로 "+"2세");
                            else if(count>=14) humanAge.setText("사람나이로 "+"6개월");
                            else humanAge.setText("사람나이로 "+"6개월이하");
                        }else{
                            humanAge.setText("사람나이로 "+String.valueOf(16+(count/30)/3)+"세");
                        }

                        type.setText(myCat.getType());
                        weight.setText(myCat.getWeight()+"g");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("catId", catId);
                MyCatDialog dialogFragment = new MyCatDialog();
                dialogFragment.setArguments(args);
                dialogFragment.show(getFragmentManager(), "Sample Dialog Fragment");
            }
        });

        return view;
    }

}
