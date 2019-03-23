package com.example.osvaldo.assingment1;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
// firebase imports
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {

    private GridView gridview;
    private TabLayout topTabs;
    private int currentTab;
    private DrawerLayout drawerLayout;
    private NavigationView nv;
    private FirebaseAuth auth;
    private Userinfo currentuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.initVariables();
        this.setToobar();
        this.pulltorefresh();
        this.tabLayout();
        this.fetchDataBaseOnSelection(0);
        this.menuData();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void initVariables() {
        this.gridview = findViewById(R.id.gridView);
        this.topTabs = findViewById(R.id.topTabs);
        this.drawerLayout = findViewById(R.id.drawers);
        this.currentTab = 0;
        this.auth =  FirebaseAuth.getInstance();
        this.currentuser = new Userinfo();
    }
    private void menuData() {
        View header = this.nv.getHeaderView(0);
        TextView title = header.findViewById(R.id.proUsername);
        title.setText(this.currentuser.getEmail());
    }
    private void tabLayout() {
        this.topTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTab = tab.getPosition();
                fetchDataBaseOnSelection(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                fetchDataBaseOnSelection(currentTab);
            }
        });
    }
    private String linkToApi(String category) {
        if(category.isEmpty()){
            return "https://newsapi.org/v2/top-headlines?country=us&pageSize=100&apiKey=6c37f351ae8f4c628db6deae9bb9cde4";
        } else {
            return "https://newsapi.org/v2/top-headlines?country=us&pageSize=100&category="+category+"&apiKey=6c37f351ae8f4c628db6deae9bb9cde4";
        }
    }
    private void fetchDataBaseOnSelection(int tab) {
        switch (tab) {
            case 0:
                String top = this.linkToApi("");
                this.fetchData(top);
                break;
            case 1:
                String business = this.linkToApi("business");
                this.fetchData(business);
                break;
            case 2:
                String health = this.linkToApi("health");
                this.fetchData(health);
                break;

            case 3:
                String science = this.linkToApi("science");
                this.fetchData(science);
                break;
            case 4:
                String technology = this.linkToApi("technology");
                this.fetchData(technology);
                break;
                default:
                    break;
        }
    }
    private void pulltorefresh() {
        final SwipeRefreshLayout pull = (SwipeRefreshLayout)findViewById(R.id.pullToRefresh);
        pull.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchDataBaseOnSelection(currentTab);
                pull.setRefreshing(false);
            }
        });
    }
    private void setToobar() {
        Toolbar toolbar = findViewById(R.id.Apptoolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, this.drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        this.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile:
                        Intent profile = new Intent(getApplicationContext(), Profile.class);
                        startActivity(profile);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.about:
                        Intent about = new Intent(getApplicationContext(), About.class);
                        startActivity(about);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.terms:
                        Intent terms = new Intent(getApplicationContext(), Terms.class);
                        startActivity(terms);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.logout:
                        logOutAction();
                        break;
                }
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return true;
    }

    private void logOutAction() {
        this.auth.signOut();
        FirebaseUser user = this.auth.getCurrentUser();
        if(user == null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void fetchData(String api) {
        new Httrequest().cancel(true);
        String url = api;
        new Httrequest().execute(url);
    }

    private void prepareGridView(final List<JSONObject> jsondata) {
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.data);
        Home_gridview arrayAdapter = new Home_gridview(getApplicationContext(), jsondata);
        gridview.setAdapter(arrayAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent newspage = new Intent(getApplicationContext(), NewsPage.class);
                newspage.putExtra("newsData", jsondata.get(position).toString());
                startActivity(newspage);
            }
        });
    }

    private class Httrequest extends AsyncTask<String, Integer, List<JSONObject>> {
        List<JSONObject> jsonArticles;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.jsonArticles = new ArrayList<>();
        }

        @Override
        protected List<JSONObject> doInBackground(String... strings) {
            try {
                String result = run(strings[0]);
                JSONObject json = new JSONObject(result);
                JSONArray articles = json.getJSONArray("articles");
                for(int i = 0; i < articles.length(); i++) {
                    JSONObject article = articles.getJSONObject(i);
                    this.jsonArticles.add(article);
                }
                return this.jsonArticles;

            } catch (IOException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            } catch (JSONException jsonerror) {
                Log.e("jsonError: ", jsonerror.getMessage());
                return null;
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<JSONObject> jsonObjects) {
            super.onPostExecute(jsonObjects);
            prepareGridView(jsonObjects);
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        String run(String url) throws IOException {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try(Response response = client.newCall(request).execute()) {
                return response.body().string();
            }
        }
    }
}
