package com.jin.cat.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jin.cat.R;
import com.jin.cat.models.Cat;
import com.jin.cat.models.MyCat;
import com.jin.cat.utils.FirebaseUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CatHealthFragment extends Fragment {

    private Spinner mSpinner;
    private TextView mTextWater;
    private TextView mTextKcal;
    private TextView mTextRDA;

    private MyCat myCat;
    private String weight;
    private Double tmp;
    private String catId;

    //private double[] mArrType={1.8, };

    public CatHealthFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cat_health, container, false);

        catId = getArguments().getString("catId");

        mSpinner = (Spinner)view.findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.type, android.R.layout.simple_spinner_item);
        mSpinner.setAdapter(adapter);
        mTextWater = (TextView)view.findViewById(R.id.water);
        mTextKcal = (TextView)view.findViewById(R.id.kcal);
        mTextRDA = (TextView)view.findViewById(R.id.rda);

        FirebaseDatabase.getInstance().getReference("User_Cat").child(FirebaseUtils.getCurrentUser().getUid())
                .child(catId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myCat = dataSnapshot.getValue(MyCat.class);

                weight = myCat.getWeight();
                tmp = Double.parseDouble(weight);

                mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        switch (position){
                            case 0:

                                mTextRDA.setText(Double.toString((tmp*30/1000+70)*1.8) + "~" + Double.toString((tmp*30/1000+70)*2.5));
                                break;
                            case 1:
                                mTextRDA.setText(Double.toString((tmp*30/1000+70)*1.4) + "~" + Double.toString((tmp*30/1000+70)*1.6));
                                break;
                            case 2:
                                mTextRDA.setText(Double.toString((tmp*30/1000+70)*1.2) + "~" + Double.toString((tmp*30/1000+70)*1.4));
                                break;
                            case 3:
                                mTextRDA.setText(Double.toString((tmp*30/1000+70)*1));
                                break;
                            case 4:
                                mTextRDA.setText(Double.toString((tmp*30/1000+70)*0.8));
                                break;
                            case 5:
                                mTextRDA.setText(Double.toString((tmp*30/1000+70)*1.1) + "~" + Double.toString((tmp*30/1000+70)*1.4));
                                break;
                            case 6:
                                mTextRDA.setText(Double.toString((tmp*30/1000+70)*1.1) + "~" + Double.toString((tmp*30/1000+70)*1.6));
                                break;
                            case 7:
                                mTextRDA.setText(Double.toString((tmp*30/1000+70)*1.6) + "~" + Double.toString((tmp*30/1000+70)*2));
                                break;
                            case 8:
                                mTextRDA.setText(Double.toString((tmp*30/1000+70)*2) + "~" + Double.toString((tmp*30/1000+70)*6));
                                break;
                            case 9:
                                mTextRDA.setText(Double.toString((tmp*30/1000+70)*3));
                                break;
                            case 10:
                                mTextRDA.setText(Double.toString((tmp*30/1000+70)*2.5));
                                break;
                            case 11:
                                mTextRDA.setText(Double.toString((tmp*30/1000+70)*2));
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                mTextWater.setText(Double.toString(tmp*50/1000));
                mTextKcal.setText(Double.toString(tmp*30/1000+70));
                mTextRDA.setText("0");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
