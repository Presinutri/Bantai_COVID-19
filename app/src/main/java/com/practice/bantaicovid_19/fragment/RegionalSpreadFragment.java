package com.practice.bantaicovid_19.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.bantaicovid_19.R;
import com.practice.bantaicovid_19.adapter.RegionalSpreadAdapter;
import com.practice.bantaicovid_19.viewmodel.RegionalSpreadViewModel;

public class RegionalSpreadFragment extends Fragment {

    private RegionalSpreadViewModel regionalSpreadViewModel;
    private RecyclerView rvRegionalSpread;
    private RegionalSpreadAdapter adapterRegional;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_regional_spread, container, false);
        setAdapter();
        setRecyclerView();
        return root;
    }

    private void setAdapter() {
        adapterRegional = new RegionalSpreadAdapter();
        adapterRegional.notifyDataSetChanged();
    }

    private void setRecyclerView() {
        rvRegionalSpread = root.findViewById(R.id.rv_listregional);
        rvRegionalSpread.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRegionalSpread.setAdapter(adapterRegional);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        regionalSpreadViewModel = ViewModelProviders.of(this).get(RegionalSpreadViewModel.class);
        regionalSpreadViewModel.setRegionalList(getActivity());
        regionalSpreadViewModel.getRegionalList().observe(getActivity(), regionalSpreadList -> {
            if (regionalSpreadList != null) {
                adapterRegional.setData(regionalSpreadList);
            }
        });
    }
}
