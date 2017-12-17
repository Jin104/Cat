package com.jin.cat.dialogs;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.jin.cat.R;

import java.util.ArrayList;

public class PostSearchDialog extends DialogFragment implements View.OnClickListener {


    private View mRootView;
    private Spinner mSpinner;
    private EditText mSearchEdit;

    private String postType;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        Bundle bundle = getArguments();
        postType = bundle.getString("postType");
//        userId = bundle.getString("userId");

        mRootView = getActivity().getLayoutInflater().inflate(R.layout.dialog_post_search, null);
        builder.setView(mRootView);

        ArrayList<String> list = new ArrayList<>();
        list.add("제목");
        list.add("내용");
//        list.add("아이디");

        mSpinner = (Spinner)mRootView.findViewById(R.id.spinner_post_type);
        SpinnerAdapter spinnerAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, list);
        mSpinner.setAdapter(spinnerAdapter);

        mSearchEdit = (EditText)mRootView.findViewById(R.id.edit_post_type);

        mRootView.findViewById(R.id.search_btn).setOnClickListener(this);



        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_btn:
                sendSearch();
                break;
        }
    }

    public void sendSearch(){
        String type = mSpinner.getSelectedItem().toString();
        String search = mSearchEdit.getText().toString().trim();
        String str="";
        if(type.equals("제목")){
            str="postTitle";
        }else if(type.equals("내용")){
            str="postDesc";
        }

        Intent data = new Intent();
        data.putExtra("search", search);
        data.putExtra("searchType", str);

        getTargetFragment().onActivityResult(0, 0, data);

        dismiss();
    }
}
