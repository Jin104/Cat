package com.jin.cat.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.jin.cat.models.Post;
import com.jin.cat.models.User;
import com.jin.cat.utils.Constants;
import com.jin.cat.utils.FirebaseUtils;

import java.util.ArrayList;

public class PostCreateActivity extends AppCompatActivity {

    private static final int GALLERY_REQ=1;
    private Post mPost;
    private Uri uri = null;
    private ImageView mImageView;
    private EditText editDesc;
    private EditText editTitle;
    private ImageButton mImageButton;
    private Button btnUpload;
    private Spinner mSpinner;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_create);

        ArrayList<String> list = new ArrayList<>();
        list.add("질문");
        list.add("기타");

        mStorageRef = FirebaseStorage.getInstance().getReference().child(Constants.POST_IMAGES);

        mSpinner = (Spinner)findViewById(R.id.post_create_type);
        SpinnerAdapter spinnerAdapter = new ArrayAdapter(PostCreateActivity.this, R.layout.support_simple_spinner_dropdown_item, list);
        mSpinner.setAdapter(spinnerAdapter);

        editTitle = (EditText)findViewById(R.id.edit_post_title);
        editDesc = (EditText)findViewById(R.id.edit_post_desc);
        mImageView = (ImageView) findViewById(R.id.post_image_view);
        mPost = new Post();

        mImageButton = (ImageButton)findViewById(R.id.post_image_add);
        btnUpload = (Button)findViewById(R.id.post_upload);

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButtonClicked(v);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadButtonClicked(v);
            }
        });
    }

    public void imageButtonClicked(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), GALLERY_REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQ) {
            if (resultCode == RESULT_OK) {
                uri = data.getData();
                mImageView.setImageURI(uri);
            }
        }
    }

    public void uploadButtonClicked(View view) {


        final String titleValue = editTitle.getText().toString().trim();
        final String descValue = editDesc.getText().toString().trim();
        final String typeValue = mSpinner.getSelectedItem().toString();
        final String postID = FirebaseUtils.getUid();

        if (!TextUtils.isEmpty(descValue) && !TextUtils.isEmpty(titleValue) && !TextUtils.isEmpty(typeValue)) {

            if (uri != null) {
                StorageReference filepath = mStorageRef.child(uri.getLastPathSegment());
                filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String downloadUrl = taskSnapshot.getDownloadUrl().toString();

                        mPost.setPostImageUrl(downloadUrl);

                        FirebaseUtils.getUserRef(FirebaseUtils.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                User user = dataSnapshot.getValue(User.class);

                                mPost.setUser(user);
                                mPost.setNumComments(0);
                                mPost.setTimeCreated(System.currentTimeMillis());
                                mPost.setPostId(postID);
                                mPost.setPostDesc(descValue);
                                mPost.setPostType(typeValue);
                                mPost.setPostTitle(titleValue);
                                addToMyPostList(postID);
                                Toast.makeText(PostCreateActivity.this, "업로드 성공", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Toast.makeText(PostCreateActivity.this, "업로드 실패", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        });
                    }
                });
            }
            else{
                FirebaseUtils.getUserRef(FirebaseUtils.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        User user = dataSnapshot.getValue(User.class);

                        mPost.setUser(user);
                        mPost.setNumComments(0);
                        mPost.setTimeCreated(System.currentTimeMillis());
                        mPost.setPostId(postID);
                        mPost.setPostDesc(descValue);
                        mPost.setPostType(typeValue);
                        mPost.setPostTitle(titleValue);
                        addToMyPostList(postID);
                        Toast.makeText(PostCreateActivity.this, "업로드 성공", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(PostCreateActivity.this, "업로드 실패", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                });
            }
        }
    }

    private void addToMyPostList(String postId) {
        FirebaseUtils.getPostRef().child("All").child(postId)
                .setValue(mPost);
        FirebaseUtils.getPostRef().child(mPost.getPostType()).child(postId)
                .setValue(mPost);
        FirebaseUtils.getMyPostRef().child(postId).setValue(mPost)
                .addOnCompleteListener(PostCreateActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });

    }
}
