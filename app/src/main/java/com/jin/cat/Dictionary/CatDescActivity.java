package com.jin.cat.Dictionary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jin.cat.Dictionary.Model.Cat;
import com.jin.cat.R;
import com.jin.cat.Utils.FirebaseUtils;
import com.squareup.picasso.Picasso;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CatDescActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_desc);

        Intent intent = getIntent();
        String hair = intent.getExtras().getString("hair");
        final String key = intent.getExtras().getString("key");

        final ImageView image = (ImageView)findViewById(R.id.cat_image);
        final TextView name = (TextView)findViewById(R.id.cat_name);
        final TextView nameEng= (TextView)findViewById(R.id.cat_name_eng);
        final TextView country = (TextView)findViewById(R.id.cat_country);
        final TextView origin = (TextView)findViewById(R.id.cat_origin);
        final TextView looks = (TextView)findViewById(R.id.cat_looks);
        final TextView personality = (TextView)findViewById(R.id.cat_personality);
        final TextView manage = (TextView)findViewById(R.id.cat_manage);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("Cats").child(hair);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Context context = image.getContext();
                Cat cat = dataSnapshot.child(key).getValue(Cat.class);

                Picasso.with(context).load(cat.getImage()).into(image);
                name.setText(cat.getName());
                nameEng.setText(cat.getKey());
                country.setText(cat.getCountry());
                origin.setText(cat.getOrigin());
                looks.setText(cat.getLooks());
                personality.setText(cat.getPersonality());
                manage.setText(cat.getManage());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void addFavoritesButtonClicked(View view){

    }

    public void addCommentButtonClicked(View view){

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
