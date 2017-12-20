package com.example.rohit.stayclose;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView mListView;
    private ImageButton mAboutBtn;
    private DatabaseReference mUserDatabase;
    private EditText mSearchField;
    private ImageButton mSearchBtn;
    private ImageView mRefreshBtn;


    private Boolean isUserClickedBackButton = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mSearchBtn = (ImageButton) findViewById(R.id.search_icon);
        mSearchField = (EditText) findViewById(R.id.search_field);
        mAboutBtn = (ImageButton) findViewById(R.id.about_btn);
        mRefreshBtn = (ImageView) findViewById(R.id.refresh_btn);
        mListView = (RecyclerView) findViewById(R.id.recycle_view);
        mListView.setHasFixedSize(true);
        mListView.setLayoutManager(new LinearLayoutManager(this));

        mRefreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                overridePendingTransition(0,0);
                startActivity(getIntent());
                //overridePendingTransition(0,0);
            }
        });


        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        mAboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, "To back press logo again", Toast.LENGTH_SHORT).show();
                Intent about_intent = new Intent(SecondActivity.this,AboutActivity.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair(mAboutBtn , "image_trans");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SecondActivity.this, pairs);

                startActivity(about_intent, options.toBundle());
            }
        });

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = mSearchField.getText().toString();
                firebaseUserSearch(searchText);
            }
        });

    }


    //--Checking for network
    public boolean isNetworkAvailable(){
        ConnectivityManager connecivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo aciveNetworkInfo = connecivityManager.getActiveNetworkInfo();
        return aciveNetworkInfo != null && aciveNetworkInfo.isConnected();

    }

    private void firebaseUserSearch(String searchText) {

        Query firebaseSearchQuery = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText+"\uf8ff");

        FirebaseRecyclerAdapter<Users ,UsersViewHolder > firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(
                Users.class,
                R.layout.all_app_layout,
                UsersViewHolder.class,
                firebaseSearchQuery


        ) {
            //OnClick



            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, final Users model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setStatus(model.getStatus());
                viewHolder.setRate(model.getRate());
                viewHolder.setUserImage(model.getImage(),getApplicationContext());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String url = model.getLink();

                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);

                    }
                });

                viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = model.getLink();
                        Intent ix = new Intent(Intent.ACTION_VIEW);
                        ix.setData(Uri.parse(url));
                        startActivity(ix);

                    }
                });



            }
        };
        mListView.setAdapter(firebaseRecyclerAdapter);




    }

    @Override
    protected void onStart() {
        super.onStart();

        if(isNetworkAvailable()){

           //Toast.makeText(this, "elcome to Stay Close", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this, "No Network!! Connect To internet", Toast.LENGTH_LONG).show();

        FirebaseRecyclerAdapter<Users ,UsersViewHolder > firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(
                Users.class,
                R.layout.all_app_layout,
                UsersViewHolder.class,
                mUserDatabase


        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, final Users model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setStatus(model.getStatus());
                viewHolder.setRate(model.getRate());
                viewHolder.setUserImage(model.getImage(),getApplicationContext());

                viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = model.getLink();
                        Intent ix = new Intent(Intent.ACTION_VIEW);
                        ix.setData(Uri.parse(url));
                        startActivity(ix);

                    }
                });

                //Opeaning Link on click
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String url = model.getLink();

                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);

                    }
                });


            }
        };
        mListView.setAdapter(firebaseRecyclerAdapter);
    }


    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;
        CardView cardView;
        public UsersViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            cardView = (CardView) mView.findViewById(R.id.card_view);
        }


        public void setName(String name){

            TextView mUserNameView = (TextView) mView.findViewById(R.id.name_txt);
            mUserNameView.setText(name);
        }
        public void setRate(String rate){

            TextView mRateView = (TextView) mView.findViewById(R.id.rate_view);
            mRateView.setText(rate);
        }

        public void setStatus(String status){
            TextView mUserStatusView = (TextView) mView.findViewById(R.id.status_txt);
            mUserStatusView.setText(status);
        }


        public void setUserImage(String image, Context Ctx){

            ImageView mUserImage = (ImageView) mView.findViewById(R.id.user_img);
            Picasso.with(Ctx).load(image).placeholder(R.mipmap.video_app).into(mUserImage);
        }


    }


    //Double Back Exit
    @Override
    public void onBackPressed() {

        if(!isUserClickedBackButton){



            Toast.makeText(this, "press back again to exit!!", Toast.LENGTH_SHORT).show();
            isUserClickedBackButton = true;
        }
        else{
            super.onBackPressed();
        }
        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                isUserClickedBackButton = false;
            }
        }.start();
    }

}
