package com.jin.cat.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.jin.cat.models.Cat;
import com.jin.cat.R;
import com.jin.cat.utils.FirebaseUtils;
import com.squareup.picasso.Picasso;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CatDescActivity extends AppCompatActivity {

    private String hairId;
    private String catId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_desc);

        Intent intent = getIntent();
        hairId = intent.getExtras().getString("hair");
        catId = intent.getExtras().getString("key");

        final ImageView image = (ImageView)findViewById(R.id.cat_image);
        final TextView name = (TextView)findViewById(R.id.cat_name);
        final TextView nameEng= (TextView)findViewById(R.id.cat_name_eng);
        final TextView country = (TextView)findViewById(R.id.cat_country);
        final TextView origin = (TextView)findViewById(R.id.cat_origin);
        final TextView looks = (TextView)findViewById(R.id.cat_looks);
        final TextView personality = (TextView)findViewById(R.id.cat_personality);
        final TextView manage = (TextView)findViewById(R.id.cat_manage);

        TextView comment = (TextView)findViewById(R.id.cat_go_comment);

        SpannableStringBuilder builder = new SpannableStringBuilder(">");
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0000")), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        comment.append(builder);


        FirebaseUtils.getCatRef().child(hairId).child(catId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Context context = image.getContext();
                Cat cat = dataSnapshot.getValue(Cat.class);

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

    public void backButtonClicked(View view){
        finish();
    }

    public void addCommentViewClicked(View view){
        Intent intent = new Intent(CatDescActivity.this, CommentActivity.class);
        intent.putExtra("hairId",hairId);
        intent.putExtra("catId",catId);
        startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
