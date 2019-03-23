package com.example.osvaldo.assingment1;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

public class NewsPage extends AppCompatActivity {

    AppCompatButton back;
    JSONObject newsdata;
    ImageView imageview;
    TextView title;
    TextView description;
    Button gotourl ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);
        this.initVariables();
        this.setNewsdata();
    }
    private void initVariables() {
        //Init Back btn
        this.back = findViewById(R.id.backBtn);
        this.backAction();
        //init image
        this.imageview = findViewById(R.id.NewsImage);
        //init newdata
        this.newsdata = new JSONObject();
        //
        this.title = findViewById(R.id.newsTitle);
        //
        this.description = (TextView)findViewById(R.id.description);
        //
        this.gotourl = (Button)findViewById(R.id.goToUrl);
    }

    private void backAction() {
        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private String formatedDate(String date) {
        SimpleDateFormat simpeldateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat formatterOut = new SimpleDateFormat("MMM dd yyyy");
        try {
            Date d = simpeldateformat.parse(date);
            return formatterOut.format(d);
        }
        catch (ParseException e) {
            return "";
        }

    }
    private  void gotourlAction(final String url) {
        this.gotourl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {

                }
            }
        });
    }

    private void setNewsdata() {
        try {
            this.newsdata = new JSONObject(getIntent().getStringExtra("newsData"));
            //load image
            String url = this.newsdata.getString("urlToImage");
            int height = this.imageview.getHeight();
            Picasso.get()
                    .load(url)
                    .resize(this.imageview.getWidth(), 500)
                    .centerCrop()
                    .into(this.imageview);
            //load ttle
            String gettitle = this.newsdata.getString("title");
            this.title.setText(gettitle);
            //load description
            String getDescription = this.newsdata.getString("description");
            String getAuthor = this.newsdata.getString("author");
            String getPublishedAt = this.newsdata.getString("publishedAt");
            String toDescription = "by: "+ getAuthor + " \npublished: "+ this.formatedDate(getPublishedAt)+ " \n \n" +getDescription;
            this.description.setText(toDescription);
            //load gotourl button
            String getUrl = this.newsdata.getString("url");
            this.gotourlAction(getUrl);

        }catch (JSONException e) {
            Log.e("errro", e.getMessage());
        }
    }
}
