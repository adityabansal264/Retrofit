package com.example.reterofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRcView;
    ArrayList<Articles> result;
    Adapter adapter;
    RecyclerView.LayoutManager layoutManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRcView=findViewById(R.id.rc_view);

        result=new ArrayList<Articles>();
       // layoutManager=new LinearLayoutManager(this);
        mRcView.setLayoutManager( new LinearLayoutManager(MainActivity.this));
        mRcView.setHasFixedSize(true);
    }

    public void onGetNewsCalled(View view) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<String> getNews = apiInterface.getNews("google-news", "4c82d7e8131841f484c6cf169bb83ae4");

        getNews.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseValue=response.body();

                try {
                    JSONObject responseObject=new JSONObject(responseValue);
                    JSONArray articlesArray=responseObject.getJSONArray("articles");

                    ArrayList<Articles> result=new ArrayList<>();

                    for (int i=0;i<articlesArray.length();i++){
                        Articles newArticle=Articles.parseJSONObject(articlesArray.optJSONObject(i));
                        result.add(newArticle);
                    }





                }catch (Exception e){
                    e.printStackTrace();
                }

                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                adapter=new Adapter(MainActivity.this,result);
                mRcView.setAdapter(adapter);

            }


            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }


}