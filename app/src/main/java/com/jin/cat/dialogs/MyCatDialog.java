package com.jin.cat.dialogs;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jin.cat.R;
import com.jin.cat.models.MyCat;
import com.jin.cat.utils.Constants;
import com.jin.cat.utils.FirebaseUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * Created by rakha on 2017-11-25.
 */

public class MyCatDialog extends DialogFragment implements View.OnClickListener{

    private MyCat myCat;
    private static final int GALLERY_REQ=1;
    private Uri mSelectedUri = null;
    private View mRootView;
    private StorageReference mStorageRef;

    private ImageButton mImageButton;
    private EditText mEditTextName;
    private EditText mEditTextType;
    //private EditText mEditTextNumber;
    private RadioButton mRadioButtonMale;
    private RadioButton mRadioButtonFemale;
    private EditText mEditYear;
    private EditText mEditMonth;
    private EditText mEditDay;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        myCat = new MyCat();

        mRootView = getActivity().getLayoutInflater().inflate(R.layout.dialog_mycat, null);
        mImageButton = (ImageButton)mRootView.findViewById(R.id.image_btn_cat);
        mEditTextName = (EditText)mRootView.findViewById(R.id.edit_cat_name);
        mEditTextType = (EditText)mRootView.findViewById(R.id.edit_cat_type);
        //mEditTextNumber = (EditText)mRootView.findViewById(R.id.edit_cat_number);
        mRadioButtonMale = (RadioButton)mRootView.findViewById(R.id.radio_male);
        mRadioButtonFemale = (RadioButton)mRootView.findViewById(R.id.radio_female);
        //mButton = (Button)mRootView.findViewById(R.id.doneBtn);
        //mButton.setEnabled(true);
        mEditYear = (EditText)mRootView.findViewById(R.id.edit_year);
        mEditMonth = (EditText)mRootView.findViewById(R.id.edit_month);
        mEditDay = (EditText)mRootView.findViewById(R.id.edit_day);

        mStorageRef = FirebaseStorage.getInstance().getReference().child(Constants.USER_CAT_IMAGES);


        Bundle mArgs = getArguments();
        if(mArgs!=null)
        {
            String catId = mArgs.getString("catId");
            //mImageButton.setImageURI(FirebaseUtils.getMyCatRef(FirebaseUtils.getCurrentUser().getUid(), catId));
            FirebaseUtils.getMyCatRef(FirebaseUtils.getCurrentUser().getUid(), catId)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            myCat = dataSnapshot.getValue(MyCat.class);
                            mEditTextName.setText(myCat.getName());
                            mEditTextType.setText(myCat.getType());
                            mEditYear.setText(myCat.getYear());
                            mEditMonth.setText(myCat.getMonth());
                            mEditDay.setText(myCat.getDay());
                            if(myCat.getSex().equals("암컷")){
                                mRadioButtonFemale.setChecked(true);
                                mRadioButtonMale.setChecked(false);
                            }else if(myCat.getSex().equals("수컷")){
                                mRadioButtonMale.setChecked(true);
                                mRadioButtonFemale.setChecked(false);
                            }
                            String url = myCat.getImage();
                            //mImageButton.setImageURI(url);
                            mImageButton.setBackground(null);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


        }

        mRootView.findViewById(R.id.image_btn_cat).setOnClickListener(this);
        mRootView.findViewById(R.id.doneBtn).setOnClickListener(this);
        builder.setView(mRootView);

//        long time = System.currentTimeMillis();
//        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy");
//        String nowTime = dayTime.format(new Date(time));

//        final ArrayList<Integer> year = new ArrayList<>();
//        for(int i=1996;i<Integer.parseInt(nowTime)+1;i++){
//            year.add(i);
//        }
//
//        final ArrayList<Integer> month = new ArrayList<>();
//        for(int i=1;i<13;i++){
//            month.add(i);
//        }
//
//        final ArrayList<Integer> day = new ArrayList<>();
//        for(int i=1;i<32;i++){
//            day.add(i);
//        }
//
//        mSpinnerYear = (Spinner)mRootView.findViewById(R.id.spinner_year);
//        SpinnerAdapter spinnerYearAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, year);
//        mSpinnerYear.setAdapter(spinnerYearAdapter);
//
//        mSinnerMonth = (Spinner)mRootView.findViewById(R.id.spinner_month);
//        SpinnerAdapter spinnerMonthAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, month);
//        mSinnerMonth.setAdapter(spinnerMonthAdapter);
//
//        mSinnerDay = (Spinner)mRootView.findViewById(R.id.spinner_day);
//        SpinnerAdapter spinnerDayAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, day);
//        mSinnerDay.setAdapter(spinnerDayAdapter);

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_btn_cat:
                selectImage();
                break;
            case R.id.doneBtn:
                sendMyCat();
                break;
        }
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), GALLERY_REQ);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQ) {
            if (resultCode == RESULT_OK) {
                mSelectedUri = data.getData();
                mImageButton.setImageURI(mSelectedUri);
                mImageButton.setBackground(null);
            }
        }
    }

    public void sendMyCat(){


        final String name = mEditTextName.getText().toString().trim();
        final String type = mEditTextType.getText().toString().trim();
        final String year = mEditYear.getText().toString().trim();
        final String month = mEditMonth.getText().toString().trim();
        final String day = mEditDay.getText().toString().trim();
        final String uid = FirebaseUtils.getUid();

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(type) && mSelectedUri!=null){

            if(mRadioButtonMale.isChecked() || mRadioButtonFemale.isChecked()){


                StorageReference filepath = mStorageRef.child(mSelectedUri.getLastPathSegment());
                filepath.putFile(mSelectedUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String downloadUrl = taskSnapshot.getDownloadUrl().toString();
                        myCat.setImage(downloadUrl);
                        myCat.setName(name);

                        if(mRadioButtonMale.isChecked())
                            myCat.setSex("수컷");
                        else if(mRadioButtonFemale.isChecked())
                            myCat.setSex("암컷");

                        myCat.setType(type);
                        myCat.setYear(year);
                        myCat.setMonth(month);
                        myCat.setDay(day);
                        myCat.setUid(uid);

                        FirebaseUtils.getMyCatRef(FirebaseUtils.getCurrentUser().getUid(),myCat.getUid())
                                .setValue(myCat);


                    }
                });

                dismiss();

            }else{
                Toast.makeText(getActivity(),"성별을 선택해주세요",Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(getActivity(), "입력을 완료해주세요", Toast.LENGTH_SHORT).show();
        }



    }
}
