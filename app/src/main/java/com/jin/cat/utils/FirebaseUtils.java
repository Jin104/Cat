package com.jin.cat.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by rakha on 2017-11-05.
 */

public class FirebaseUtils {

    public static StorageReference getMyCatImagesRef(){
        return FirebaseStorage.getInstance().getReference(Constants.USER_CAT_IMAGES);
    }

    public static DatabaseReference getMyCatRef(String userId, String catId){
        return FirebaseDatabase.getInstance().getReference("User_Cat").child(userId).child(catId);
    }

    public static DatabaseReference getMyCatListRef(String userId){
        return FirebaseDatabase.getInstance().getReference("User_Liked_Cat").child(userId);
    }

    public static DatabaseReference getLanguageRef(String contentId){
        return FirebaseDatabase.getInstance().getReference("Knowledge").child("Language").child(contentId);
    }

    public static DatabaseReference getCatCommentRef(String hairId, String catId){
        return FirebaseDatabase.getInstance().getReference(Constants.COMMENTS_KEY).child(hairId).child(catId);
    }

    public static DatabaseReference getPostCommentRef(String postId){
        return FirebaseDatabase.getInstance().getReference(Constants.POST_COMMENTS_KEY).child(postId);
    }

    public static DatabaseReference getCatRef(){
        return FirebaseDatabase.getInstance().getReference(Constants.CAT_KEY);
    }

    public static DatabaseReference getCatLongRef(){
        return FirebaseDatabase.getInstance().getReference(Constants.CAT_KEY).child(Constants.CAT_LONG);
    }

    public static DatabaseReference getUserRef(String uid){
        return FirebaseDatabase.getInstance().getReference(Constants.USERS_KEY).child(uid);
    }

    public static DatabaseReference getPostRef(){
        return FirebaseDatabase.getInstance().getReference(Constants.POST_KEY);
    }

    public static DatabaseReference getPostLikedRef(){
        return FirebaseDatabase.getInstance().getReference(Constants.POST_LIKED_KEY);
    }

    public static DatabaseReference getCatLikedRef(String hairId, String catId){
        return FirebaseDatabase.getInstance().getReference(Constants.CAT_LIKED_KEY).child(hairId).child(catId);
    }

    public static DatabaseReference getPostLikedRef(String postId){
        return getPostLikedRef().child(getCurrentUser().getUid()).child(postId);
    }

    public static FirebaseUser getCurrentUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public static String getUid(){
        String path = FirebaseDatabase.getInstance().getReference().push().toString();
        return path.substring(path.lastIndexOf("/")+ 1 );
    }

    public static StorageReference getImagesRef(){
        return FirebaseStorage.getInstance().getReference(Constants.POST_IMAGES);
    }

    public static DatabaseReference getMyPostRef(){
        return FirebaseDatabase.getInstance().getReference(Constants.MY_POSTS).child(getCurrentUser().getUid());
    }

    public static DatabaseReference getCommentRef(String postId){
        return FirebaseDatabase.getInstance().getReference(Constants.COMMENTS_KEY).child(postId);
    }

    public static DatabaseReference getMyRecordRef(){
        return FirebaseDatabase.getInstance().getReference(Constants.USER_RECORD).child(getCurrentUser().getUid());
    }

    public static void addToMyRecord(String node, final String id){
        getMyRecordRef().child(node).runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                ArrayList<String> myRecordCollection;
                if(mutableData.getValue() == null){
                    myRecordCollection = new ArrayList<String>(1);
                    myRecordCollection.add(id);
                }else{
                    myRecordCollection = (ArrayList<String>) mutableData.getValue();
                    myRecordCollection.add(id);
                }
                mutableData.setValue(myRecordCollection);
                return null;
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

            }
        });
    }
}
