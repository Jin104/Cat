package com.jin.cat.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jin.cat.R;
import com.jin.cat.interfaces.MyXAxisValueFormatter;
import com.jin.cat.models.Cat;
import com.jin.cat.models.MyCat;
import com.jin.cat.models.Weight;
import com.jin.cat.utils.FirebaseUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CatHealthFragment extends Fragment {

    private LineChart lineChart;

    private Spinner mSpinner;
    private TextView mTextWater;
    //private TextView mTextKcal;
    private TextView mTextRDA;
    private TextView mTextMax;
    private TextView mTextCount;
    private TextView mTextPercent;

    private ImageView mImagePlus;
    private ImageView mImageMinus;
    private ProgressBar mProgressWater;

    private MyCat myCat;
    private String weight;
    private Double tmp;
    private String catId;

    private List<Entry> entries;
    //private List<Weight> weightList;

    private  List<String> weightList;
    private  int max;
    private  int min;

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
        //mTextKcal = (TextView)view.findViewById(R.id.kcal);
        mTextRDA = (TextView)view.findViewById(R.id.rda);
        mImagePlus = (ImageView)view.findViewById(R.id.water_plus);
        mImageMinus = (ImageView)view.findViewById(R.id.water_minus);
        mTextMax = (TextView)view.findViewById(R.id.water_max);
        mTextCount = (TextView)view.findViewById(R.id.water_count);
        mProgressWater = (ProgressBar)view.findViewById(R.id.circularProgressbar);
        mTextPercent = (TextView)view.findViewById(R.id.water_percent);

        weightList = new ArrayList<>();

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
                                mTextRDA.setText(Double.toString(Math.round((tmp*30/1000+70)*1.8)) + "~" + Double.toString(Math.round((tmp*30/1000+70)*2.5)));
                                break;
                            case 1:
                                mTextRDA.setText(Double.toString(Math.round((tmp*30/1000+70)*1.4)) + "~" + Double.toString(Math.round((tmp*30/1000+70)*1.6)));
                                break;
                            case 2:
                                mTextRDA.setText(Double.toString(Math.round((tmp*30/1000+70)*1.2)) + "~" + Double.toString(Math.round((tmp*30/1000+70)*1.4)));
                                break;
                            case 3:
                                mTextRDA.setText(Double.toString(Math.round((tmp*30/1000+70)*1)));
                                break;
                            case 4:
                                mTextRDA.setText(Double.toString(Math.round((tmp*30/1000+70)*0.8)));
                                break;
                            case 5:
                                mTextRDA.setText(Double.toString(Math.round((tmp*30/1000+70)*1.1)) + "~" + Double.toString(Math.round((tmp*30/1000+70)*1.4)));
                                break;
                            case 6:
                                mTextRDA.setText(Double.toString(Math.round((tmp*30/1000+70)*1.1)) + "~" + Double.toString(Math.round((tmp*30/1000+70)*1.6)));
                                break;
                            case 7:
                                mTextRDA.setText(Double.toString(Math.round((tmp*30/1000+70)*1.6)) + "~" + Double.toString(Math.round((tmp*30/1000+70)*2)));
                                break;
                            case 8:
                                mTextRDA.setText(Double.toString(Math.round((tmp*30/1000+70)*2)) + "~" + Double.toString(Math.round((tmp*30/1000+70)*6)));
                                break;
                            case 9:
                                mTextRDA.setText(Double.toString(Math.round((tmp*30/1000+70)*3)));
                                break;
                            case 10:
                                mTextRDA.setText(Double.toString(Math.round((tmp*30/1000+70)*2.5)));
                                break;
                            case 11:
                                mTextRDA.setText(Double.toString(Math.round((tmp*30/1000+70)*2)));
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                mTextWater.setText(Integer.toString(Math.round(Math.round(tmp*50/1000)/5)*5));
                //mTextKcal.setText(Double.toString(tmp*30/1000+70));
                mTextRDA.setText("0");
                mTextMax.setText(Integer.toString(Math.round(Math.round(tmp*50/1000)/5)*5)+"ml");

                mProgressWater.setMax(Math.round(Math.round(tmp*50/1000)/5)*5);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat CurDayFormat = new SimpleDateFormat("dd");
        final String strCurYear = CurYearFormat.format(date);
        final String strCurMonth = CurMonthFormat.format(date);
        final String strCurDay = CurDayFormat.format(date);

        FirebaseDatabase.getInstance().getReference("User_Cat").child(FirebaseUtils.getCurrentUser().getUid())
                .child(catId).child("water_record").child(strCurYear+strCurMonth+strCurDay).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue()!=null){

                    int tmp = Integer.parseInt(dataSnapshot.getValue().toString());

                    mProgressWater.setProgress(tmp);
                    mTextCount.setText(Integer.toString(tmp));
                    mTextPercent.setText(Integer.toString(mProgressWater.getProgress()*100/mProgressWater.getMax())+"%");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mImagePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
                SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
                SimpleDateFormat CurDayFormat = new SimpleDateFormat("dd");
                final String strCurYear = CurYearFormat.format(date);
                final String strCurMonth = CurMonthFormat.format(date);
                final String strCurDay = CurDayFormat.format(date);

                //Toast.makeText(getActivity(), "플러스클릭", Toast.LENGTH_SHORT).show();

                FirebaseDatabase.getInstance().getReference("User_Cat").child(FirebaseUtils.getCurrentUser().getUid())
                        .child(catId).child("water_record").child(strCurYear+strCurMonth+strCurDay).addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.getValue()!=null){

                            int tmp = Integer.parseInt(dataSnapshot.getValue().toString());

                            if(mProgressWater.getProgress()<=mProgressWater.getMax()-5) {
                                tmp += 5;

                                FirebaseDatabase.getInstance().getReference("User_Cat").child(FirebaseUtils.getCurrentUser().getUid())
                                        .child(catId).child("water_record").child(strCurYear + strCurMonth + strCurDay).setValue(tmp);

                                mProgressWater.setProgress(mProgressWater.getProgress() + 5);
                                mTextCount.setText(Integer.toString(tmp));

                                mTextPercent.setText(Integer.toString(mProgressWater.getProgress()*100/mProgressWater.getMax())+"%");
                            }
                        }
                        else{
                            FirebaseDatabase.getInstance().getReference("User_Cat").child(FirebaseUtils.getCurrentUser().getUid())
                                    .child(catId).child("water_record").child(strCurYear+strCurMonth+strCurDay).setValue("5");

                            mProgressWater.setProgress(5);
                            mTextCount.setText("5");

                            mTextPercent.setText(Integer.toString(mProgressWater.getProgress()*100/mProgressWater.getMax())+"%");
                        }
                    }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
            }
        });

        mImageMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
                SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
                SimpleDateFormat CurDayFormat = new SimpleDateFormat("dd");
                final String strCurYear = CurYearFormat.format(date);
                final String strCurMonth = CurMonthFormat.format(date);
                final String strCurDay = CurDayFormat.format(date);

                FirebaseDatabase.getInstance().getReference("User_Cat").child(FirebaseUtils.getCurrentUser().getUid())
                        .child(catId).child("water_record").child(strCurYear+strCurMonth+strCurDay).addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.getValue()!=null){

                            int tmp = Integer.parseInt(dataSnapshot.getValue().toString());
                            if(mProgressWater.getProgress()>=5) {
                                tmp -= 5;

                                FirebaseDatabase.getInstance().getReference("User_Cat").child(FirebaseUtils.getCurrentUser().getUid())
                                        .child(catId).child("water_record").child(strCurYear + strCurMonth + strCurDay).setValue(tmp);

                                mProgressWater.setProgress(mProgressWater.getProgress() - 5);
                                mTextCount.setText(Integer.toString(tmp));
                                mTextPercent.setText(Integer.toString(mProgressWater.getProgress()*100/mProgressWater.getMax())+"%");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


        lineChart = (LineChart)view.findViewById(R.id.chart);

        final List<Entry> entries = new ArrayList<>();

        Query weightQuery = FirebaseDatabase.getInstance().getReference("User_Cat").child(FirebaseUtils.getCurrentUser().getUid())
                .child(catId).child("weight_record").orderByKey().limitToLast(5);


        weightQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int queryMax = (int) dataSnapshot.getChildrenCount();

                String[] xValues = new String[queryMax];
                int count = 0;
                for(DataSnapshot issue : dataSnapshot.getChildren()){
                    count++;

                    float tmp = Integer.parseInt(issue.getValue().toString());
                    tmp = tmp/1000;

                    int date = Integer.parseInt(issue.getKey().toString());
                    int month = (date/1000%10)*10 + (date/100%10);
                    int day = (date/10%10)*10 + (date%10);

                    xValues[count-1] = month +"월 "+ day +"일";

                    entries.add(new Entry(count, tmp));
                }
                LineDataSet lineDataSet = new LineDataSet(entries, "몸무게kg");
                lineDataSet.setLineWidth(2);
                lineDataSet.setCircleRadius(3);
                lineDataSet.setCircleColor(Color.parseColor("#77B3D4"));
                lineDataSet.setCircleColorHole(Color.BLUE);
                lineDataSet.setColor(Color.parseColor("#77B3D4"));
                lineDataSet.setDrawCircleHole(true);
                lineDataSet.setDrawCircles(true);
                lineDataSet.setDrawHorizontalHighlightIndicator(false);
                lineDataSet.setDrawHighlightIndicators(false);
                lineDataSet.setDrawValues(false);

                LineData lineData = new LineData(lineDataSet);
                lineChart.setData(lineData);

                XAxis xAxis = lineChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setTextColor(Color.BLACK);
                xAxis.enableGridDashedLine(8, 24, 0);
                xAxis.setValueFormatter(new MyXAxisValueFormatter(xValues));

                YAxis yLAxis = lineChart.getAxisLeft();
                yLAxis.setTextColor(Color.BLACK);

                YAxis yRAxis = lineChart.getAxisRight();
                yRAxis.setDrawLabels(false);
                yRAxis.setDrawAxisLine(false);
                yRAxis.setDrawGridLines(false);

                Description description = new Description();
                description.setText("");

                lineChart.setDoubleTapToZoomEnabled(false);
                lineChart.setDrawGridBackground(false);
                lineChart.setDescription(description);
                lineChart.animateY(1500, Easing.EasingOption.EaseInCubic);
                lineChart.invalidate();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
