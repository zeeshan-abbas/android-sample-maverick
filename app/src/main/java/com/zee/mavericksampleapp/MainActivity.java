package com.zee.mavericksampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NameDialogFragment.AddNameListener {

    public static final String DEFAULT_NAMES_JSON = "{\"names\":[\"Zeeshan Abbas\",\"Haseeb Ahmed\", \"Ali Raza\", \"Hammad Masood\", \"Mateen Saud\"]}";

    RecyclerView mRecyclerView;
    NameListAdapter mNameListAdapter;
    List namesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.add_button).setOnClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        namesList = parseNamesJson(DEFAULT_NAMES_JSON);

        mNameListAdapter = new NameListAdapter(namesList);
        mRecyclerView.setAdapter(mNameListAdapter);

    }

    private List parseNamesJson(String json){
        List namesList = new ArrayList();
        try{
            JSONObject obj = new JSONObject(json);
            JSONArray arr = obj.getJSONArray("names");
            for(int i = 0; i < arr.length(); i++){
                String name = arr.getString(i);
                namesList.add(name);
            }
        }catch (Exception e){
            Log.e("Main Activity", "Parsing error: " + e.getMessage());
        }
        return namesList;
    }

    @Override
    public void onNameEntered(String name) {
        namesList.add(name);
    }

    @Override
    public void onClick(View v) {
        NameDialogFragment editNameDialog = new NameDialogFragment();
        editNameDialog.show(getFragmentManager(), "fragment_add_name");
    }
}
