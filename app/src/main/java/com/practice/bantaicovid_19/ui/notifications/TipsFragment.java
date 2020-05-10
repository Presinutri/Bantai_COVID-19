package com.practice.bantaicovid_19.ui.notifications;

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
import com.practice.bantaicovid_19.adapter.TipsAdapter;

public class TipsFragment extends Fragment {

    private TipsViewModel mViewModel;
    private RecyclerView rvTips;
    private TipsAdapter adapterTips;
    private View root;

    public static TipsFragment newInstance() {
        return new TipsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_tips, container, false);
        setAdapter();
        setRecyclerView();
        return root;
    }

    private void setAdapter() {
        adapterTips = new TipsAdapter();
        adapterTips.notifyDataSetChanged();
    }

    private void setRecyclerView() {
        rvTips = root.findViewById(R.id.rv_tips);
        rvTips.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTips.setAdapter(adapterTips);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TipsViewModel.class);
        mViewModel.setTipsList(getActivity());
        mViewModel.getTipsList().observe(getActivity(), tipsList -> {
            if (tipsList != null) {
                adapterTips.setData(tipsList);
            }
        });
    }

}
