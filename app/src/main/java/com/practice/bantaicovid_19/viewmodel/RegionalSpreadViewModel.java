package com.practice.bantaicovid_19.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.practice.bantaicovid_19.dataclass.Regional;
import com.practice.bantaicovid_19.dataclass.Tips;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.practice.bantaicovid_19.model.SpreadDataModel.PREFERENCE_MENINGGAL;
import static com.practice.bantaicovid_19.model.SpreadDataModel.PREFERENCE_POSITIF;
import static com.practice.bantaicovid_19.model.SpreadDataModel.PREFERENCE_SEMBUH;

public class RegionalSpreadViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Regional>> regionalList = new MutableLiveData<>();

    public void setRegionalList(Context context) {
        ArrayList<Regional> list = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://my-json-server.typicode.com/alfarelzaki/covid-daerah-indonesia/data-daerah";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("daerah1");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject regional = jsonArray.getJSONObject(i);
                        String province = regional.getString("provinsi");
                        int positif = regional.getInt("positif");
                        int sembuh = regional.getInt("sembuh");
                        int meninggal = regional.getInt("meninggal");
                        Regional itemRegional = new Regional(province, positif, sembuh, meninggal);
                        list.add(itemRegional);
                    }

                    jsonArray = jsonObject.getJSONArray("daerah2");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject regional = jsonArray.getJSONObject(i);
                        String province = regional.getString("provinsi");
                        int positif = regional.getInt("positif");
                        int sembuh = regional.getInt("sembuh");
                        int meninggal = regional.getInt("meninggal");
                        Regional itemRegional = new Regional(province, positif, sembuh, meninggal);
                        list.add(itemRegional);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                regionalList.postValue(list);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Response", error.toString());
            }
        });
        queue.add(request);
    }

    public MutableLiveData<ArrayList<Regional>> getRegionalList() {
        return regionalList;
    }
}