package com.practice.bantaicovid_19.adapter;

import android.content.Intent;
import android.graphics.Region;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.practice.bantaicovid_19.R;
import com.practice.bantaicovid_19.TipsDetailActivity;
import com.practice.bantaicovid_19.dataclass.Regional;
import com.practice.bantaicovid_19.dataclass.Tips;

import java.util.ArrayList;

public class RegionalSpreadAdapter extends RecyclerView.Adapter<RegionalSpreadAdapter.ListViewHolder>  {
    ArrayList mData = new ArrayList<Regional>();

    public void setData(ArrayList<Regional> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_listdaerah, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        final Regional data = (Regional) mData.get(position);
        holder.viewProvince.setText(data.getProvince());
        holder.viewPositive.setText(String.valueOf(data.getRegionalPositive()));
        holder.viewCured.setText(String.valueOf(data.getRegionalCured()));
        holder.viewDeath.setText(String.valueOf(data.getRegionalDeath()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        TextView viewProvince;
        TextView viewPositive;
        TextView viewCured;
        TextView viewDeath;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            viewProvince = itemView.findViewById(R.id.province);
            viewPositive = itemView.findViewById(R.id.regional_positive);
            viewCured = itemView.findViewById(R.id.regional_cured);
            viewDeath = itemView.findViewById(R.id.regional_death);
        }
    }
}
