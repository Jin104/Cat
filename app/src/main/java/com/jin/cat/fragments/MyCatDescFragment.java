package com.jin.cat.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.jin.cat.R;
import com.jin.cat.models.MyCat;
import com.jin.cat.utils.FirebaseUtils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyCatDescFragment extends Fragment {

    private MyCat myCat;

    public MyCatDescFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cat_desc, container, false);

        String catId = getArguments().getString("catId");

        final ImageView image = (ImageView)view.findViewById(R.id.imageView_cat);
        final TextView name = (TextView)view.findViewById(R.id.textView_catName);
        final TextView sex = (TextView)view.findViewById(R.id.textView_catSex);
        final TextView birthday = (TextView)view.findViewById(R.id.textView_catBirthDay);
        final TextView age = (TextView)view.findViewById(R.id.textView_catAge);
        final TextView humanAge = (TextView)view.findViewById(R.id.textView_catHumanAge);
        final TextView type = (TextView)view.findViewById(R.id.textView_catType);

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

                        age.setText(String.valueOf(count/30));

                        if(count/30<30){
                            if((count/30)>=27) humanAge.setText("25세");
                            else if((count/30)>=24) humanAge.setText("24세");
                            else if((count/30)>=21) humanAge.setText("23세");
                            else if((count/30)>=18) humanAge.setText("22세");
                            else if((count/30)>=15) humanAge.setText("20세");
                            else if((count/30)>=12) humanAge.setText("18세");
                            else if((count/30)>=9) humanAge.setText("15세");
                            else if((count/30)>=6) humanAge.setText("12세");
                            else if((count/30)>=3) humanAge.setText("6세");
                            else if((count/30)>=2) humanAge.setText("4세");
                            else if(count>=45) humanAge.setText("3세");
                            else if((count/30)>=1) humanAge.setText("2세");
                            else if(count>=14) humanAge.setText("6개월");
                            else humanAge.setText("6개월이하");
                        }else{
                            humanAge.setText(String.valueOf(16+(count/30)/3)+"세");
                        }

                        type.setText(myCat.getType());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        return view;
    }

}
