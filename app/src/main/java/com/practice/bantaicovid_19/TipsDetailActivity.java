package com.practice.bantaicovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import com.practice.bantaicovid_19.dataclass.Tips;

public class TipsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_detail);

        WebView myWebView = (WebView) findViewById(R.id.tips_webview);

        Tips parcelableTips = getIntent().getParcelableExtra("tips parcelable");
        myWebView.loadUrl(parcelableTips.getLink());

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
