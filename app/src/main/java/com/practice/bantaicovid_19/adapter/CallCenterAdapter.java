package com.practice.bantaicovid_19.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.practice.bantaicovid_19.R;
import com.practice.bantaicovid_19.dataclass.CallCenter;

import java.util.ArrayList;

public class CallCenterAdapter extends RecyclerView.Adapter<CallCenterAdapter.ListViewHolder> {
    ArrayList mData = new ArrayList<CallCenter>();

    @NonNull
    @Override
    public CallCenterAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_call_center, parent, false);
        return new CallCenterAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CallCenterAdapter.ListViewHolder holder, int position) {
        final CallCenter data = (CallCenter) mData.get(position);
        holder.viewProvinsi.setText(data.getProvinsi());
        holder.viewCallCenter.setText(data.getCallCenter());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", data.getCallCenter(), null));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return mData.size(); }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView viewProvinsi, viewCallCenter;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            viewProvinsi = itemView.findViewById(R.id.provinsi);
            viewCallCenter = itemView.findViewById(R.id.call_center_daerah);
        }
    }
}
