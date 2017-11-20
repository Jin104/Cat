package com.jin.cat.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.jin.cat.R;
import com.jin.cat.adapter.ViewPagerAdapter;
import com.jin.cat.models.Cat;
import com.jin.cat.utils.FirebaseUtils;
import com.squareup.picasso.Picasso;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CatDescActivity extends AppCompatActivity {

    private String hairId;
    private String catId;
    private ImageButton favoritesBtn;
    private ViewPagerAdapter viewPagerAdapter;

    private Cat cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_desc);

        Intent intent = getIntent();
        hairId = intent.getExtras().getString("hair");
        catId = intent.getExtras().getString("key");

        //image
        final ViewPager viewPager = (ViewPager)findViewById(R.id.cat_view_pager);
        final TextView textView = (TextView)findViewById(R.id.textView);

        //final ImageView image = (ImageView)findViewById(R.id.cat_image);
        final TextView name = (TextView)findViewById(R.id.cat_name);
        final TextView nameEng= (TextView)findViewById(R.id.cat_name_eng);
        final TextView country = (TextView)findViewById(R.id.cat_country);
        final TextView origin = (TextView)findViewById(R.id.cat_origin);
        final TextView looks = (TextView)findViewById(R.id.cat_looks);
        final TextView personality = (TextView)findViewById(R.id.cat_personality);
        final TextView manage = (TextView)findViewById(R.id.cat_manage);
        favoritesBtn =(ImageButton)findViewById(R.id.cat_like_btn);

        TextView comment = (TextView)findViewById(R.id.cat_go_comment);

        SpannableStringBuilder builder = new SpannableStringBuilder(">");
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0000")), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        comment.append(builder);


        FirebaseUtils.getCatRef().child(hairId).child(catId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Context context = image.getContext();
                cat = dataSnapshot.getValue(Cat.class);

                //Picasso.with(context).load(cat.getImage()).into(image);
                name.setText(cat.getName());
                nameEng.setText(cat.getKey());
                country.setText(cat.getCountry());
                origin.setText(cat.getOrigin());
                looks.setText(cat.getLooks());
                personality.setText(cat.getPersonality());
                manage.setText(cat.getManage());

                String[] data={
                        cat.getImage(),
                        cat.getImage1(),
                        cat.getImage2()
                };

                viewPagerAdapter = new ViewPagerAdapter(getBaseContext(), data);
                viewPager.setOffscreenPageLimit(2);
                viewPager.setAdapter(viewPagerAdapter);

                favoritesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(FirebaseUtils.getCurrentUser()!=null){
                            addFavoritesButtonClicked(cat.getKey());
                        }else{
                            Snackbar.make(v,  Html.fromHtml("<font color=\"#ffffff\">로그인 하시겠습니까?</font>"), 2000).setActionTextColor(Color.parseColor("#FF0000")).setAction("YES", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(CatDescActivity.this, LoginActivity.class));
                                }
                            }).show();
                        }
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                textView.setText("");
                for (int i = 0; i < viewPagerAdapter.getCount(); i++) {
                    Spannable word = new SpannableString(" " + ".");
                    if (i == position) {
                        word.setSpan(new ForegroundColorSpan(Color.rgb(255,81,81)), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } else {
                        word.setSpan(new ForegroundColorSpan(Color.rgb(71,73,79)), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    textView.append(word);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    @Override
    protected void onResume() {

        if(FirebaseUtils.getCurrentUser()!=null) {
            FirebaseUtils.getCatLikedRef(hairId, catId)
                    .child(FirebaseUtils.getCurrentUser().getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if (dataSnapshot.getValue() != null) {
                                favoritesBtn.setImageResource(R.drawable.icon_like);
                            } else {
                                favoritesBtn.setImageResource(R.drawable.icon_dislike);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

        }
        super.onResume();
    }

    public void addFavoritesButtonClicked(final String catId){

        FirebaseUtils.getCatLikedRef(hairId, catId)
                .child(FirebaseUtils.getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.getValue()!=null){
                            FirebaseUtils.getCatLikedRef(hairId, catId).child(FirebaseUtils.getCurrentUser().getUid()).setValue(null);
                            favoritesBtn.setImageResource(R.drawable.icon_dislike);
                            FirebaseUtils.getMyCatListRef(FirebaseUtils.getCurrentUser().getUid()).child(catId).setValue(null);
                        }else{
                            FirebaseUtils.getCatLikedRef(hairId, catId).child(FirebaseUtils.getCurrentUser().getUid()).setValue(true);
                            favoritesBtn.setImageResource(R.drawable.icon_like);
                            FirebaseUtils.getMyCatListRef(FirebaseUtils.getCurrentUser().getUid()).child(catId).setValue(cat);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

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
