package com.example.emsapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    EmployeAdapter mEmployeAdapter;
    List<EmployeModel> mEmployeModelList;
    RequestQueue queue;
    String url = "http://userapi.tk/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycleview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,1));
        mRecyclerView.setHasFixedSize(true);
        mEmployeModelList = new ArrayList<>();
        loadDataFromUrl();

    }
    private void loadDataFromUrl() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        setDataInRecyclerview(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue = Volley.newRequestQueue(this);
        queue.add(jsonArrayRequest);
    }
    private void setDataInRecyclerview(JSONArray response) {
        JSONObject jsonObject = null;
        for(int i = 0; i < response.length(); i++) {
            EmployeModel user = new EmployeModel();
            try {
                jsonObject = response.getJSONObject(i);
                user.setName(jsonObject.getString("Name"));
                user.setEmailID(jsonObject.getString("EmailID"));
                user.setImageURL(jsonObject.getString("ImageURL"));
                user.setMobile(jsonObject.getString("Mobile"));
                user.setGender(jsonObject.getString("Gender"));
                user.setBirthday(jsonObject.getString("Birthday"));


            } catch (JSONException e) {
                e.printStackTrace();
            }
            mEmployeModelList.add(user);

        }
        mEmployeAdapter = new EmployeAdapter(this,mEmployeModelList);
        mRecyclerView.setAdapter(mEmployeAdapter);
    }
}
