package com.jin.cat.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.jin.cat.R;
import com.jin.cat.models.Inoculation;
import com.jin.cat.utils.FirebaseUtils;

import java.util.ArrayList;

/**
 * Created by rakha on 2017-11-26.
 */

public class MyCatInoculationDialog extends DialogFragment implements View.OnClickListener{



    private View mRootView;
    private Spinner mSpinner;
    private EditText mEditYear;
    private EditText mEditMonth;
    private EditText mEditDay;

    private String catId;
    private String userId;
    private Inoculation inoculation;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        Bundle bundle = getArguments();
        catId = bundle.getString("catId");
        userId = bundle.getString("userId");

        mRootView = getActivity().getLayoutInflater().inflate(R.layout.dialog_mycat_inoculation, null);
        builder.setView(mRootView);

        ArrayList<String>list = new ArrayList<>();
        list.add("종합백신(FVRCP)");
        list.add("백혈병(FeLV)");
        list.add("광견병(Rabies)");

        mSpinner = (Spinner)mRootView.findViewById(R.id.spinner_type);
        SpinnerAdapter spinnerAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, list);
        mSpinner.setAdapter(spinnerAdapter);


        mEditYear = (EditText)mRootView.findViewById(R.id.edit_year);
        mEditMonth = (EditText)mRootView.findViewById(R.id.edit_month);
        mEditDay = (EditText)mRootView.findViewById(R.id.edit_day);

        mRootView.findViewById(R.id.doneBtn).setOnClickListener(this);



        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.doneBtn:
                sendMyCatInoculation();
                break;
        }
    }

    public void sendMyCatInoculation(){

        String year = mEditYear.getText().toString().trim();
        String month = mEditMonth.getText().toString().trim();
        String day = mEditDay.getText().toString().trim();
        String type = mSpinner.getSelectedItem().toString();

        if(!TextUtils.isEmpty(year) && !TextUtils.isEmpty(month) && !TextUtils.isEmpty(day)){

            String str = FirebaseUtils.getUid();
            inoculation = new Inoculation();
            inoculation.setDate(year+"."+month+"."+day);
            inoculation.setUid(str);
            FirebaseUtils.getMyCatRef(userId, catId)
                    .child("inoculation").child(type).child(str).setValue(inoculation);

            dismiss();
        }else{
            Toast.makeText(getActivity(), "입력을 완료해주세요.", Toast.LENGTH_SHORT).show();
        }

    }
}
