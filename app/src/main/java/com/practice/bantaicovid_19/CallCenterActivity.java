package com.practice.bantaicovid_19;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.bantaicovid_19.adapter.CallCenterAdapter;
import com.practice.bantaicovid_19.viewmodel.CallCenterViewModel;

public class CallCenterActivity extends AppCompatActivity {

    private CallCenterViewModel callCenterViewModel;
    private RecyclerView rvCallCenter;
    private CallCenterAdapter adapterCallCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_center);

        setAdapter();
        setRecyclerView();

        callCenterViewModel = ViewModelProviders.of(this).get(CallCenterViewModel.class);
        callCenterViewModel.setCallCenterList(this);
        callCenterViewModel.getCallCenter().observe(this, callCenterList -> {
            if (callCenterList != null) {
                adapterCallCenter.setData(callCenterList);
            }
        });

        getSupportActionBar().setTitle(getResources().getString(R.string.call_center_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setAdapter() {
        adapterCallCenter = new CallCenterAdapter();
        adapterCallCenter.notifyDataSetChanged();
    }

    private void setRecyclerView() {
        rvCallCenter = findViewById(R.id.rv_callcenter);
        rvCallCenter.setLayoutManager(new LinearLayoutManager(this));
        rvCallCenter.setAdapter(adapterCallCenter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
