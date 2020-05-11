package com.practice.bantaicovid_19.ui.home;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.practice.bantaicovid_19.R;
import com.practice.bantaicovid_19.dataclass.CallCenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import static com.practice.bantaicovid_19.model.SpreadDataModel.PREFERENCE_MENINGGAL;
import static com.practice.bantaicovid_19.model.SpreadDataModel.PREFERENCE_POSITIF;
import static com.practice.bantaicovid_19.model.SpreadDataModel.PREFERENCE_SEMBUH;

public class HomeFragment extends Fragment {

    private TextView totalPositif, totalSembuh, totalMeninggal;
    private ProgressBar progressBar;
    private View callCenter;
    public Calendar updateDate = Calendar.getInstance();

    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // call view
        totalPositif = root.findViewById(R.id.id_positif);
        totalSembuh = root.findViewById(R.id.id_sembuh);
        totalMeninggal = root.findViewById(R.id.id_meninggal);
        progressBar = root.findViewById(R.id.progress_circular_home);
        callCenter = root.findViewById(R.id.callcenter);

        // call Volley
        getSpreadData();

        callCenter.findViewById(R.id.callcenter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),CallCenter.class);
                startActivity(intent);
            }
        });

        return root;
    }

    private void getSpreadData() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://corona.lmao.ninja/v2/countries/indonesia";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    if (getActivity().getSharedPreferences("spread data widget", Context.MODE_PRIVATE) != null) {
                        sharedPreferences = getActivity().getSharedPreferences("spread data widget", Context.MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString(PREFERENCE_POSITIF, jsonObject.getString("cases"));
                        editor.putString(PREFERENCE_SEMBUH, jsonObject.getString("recovered"));
                        editor.putString(PREFERENCE_MENINGGAL, jsonObject.getString("deaths"));
                        editor.apply();
                    }

                    // set data
                    setSpreadData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.d("Error Response", error.toString());
            }
        });
        queue.add(request);

    }

    private void setSpreadData() {
        sharedPreferences = getActivity().getSharedPreferences("spread data widget", Context.MODE_PRIVATE);
        totalPositif.setText(sharedPreferences.getString(PREFERENCE_POSITIF, "-"));
        totalSembuh.setText(sharedPreferences.getString(PREFERENCE_SEMBUH, "-"));
        totalMeninggal.setText(sharedPreferences.getString(PREFERENCE_MENINGGAL, "-"));
        Log.d("pos", sharedPreferences.getString(PREFERENCE_POSITIF, "-"));
    }
}
