package com.practice.bantaicovid_19.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.practice.bantaicovid_19.R;
import com.practice.bantaicovid_19.TipsDetailActivity;
import com.practice.bantaicovid_19.dataclass.Tips;

import java.util.ArrayList;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ListViewHolder> {
    ArrayList mData = new ArrayList<Tips>();

    public void setData(ArrayList<Tips> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TipsAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_tips, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TipsAdapter.ListViewHolder holder, int position) {
        final Tips data = (Tips) mData.get(position);
        holder.viewTitle.setText(data.getTitle());
        holder.viewContent.setText(data.getContent());
        Glide.with(holder.itemView.getContext())
                .load(data.getPhoto())
                .into(holder.viewPhoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), TipsDetailActivity.class);
                intent.putExtra("tips parcelable", data);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        TextView viewTitle;
        TextView viewContent;
        ImageView viewPhoto;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            viewTitle = itemView.findViewById(R.id.tips_title);
            viewContent = itemView.findViewById(R.id.tips_content);
            viewPhoto = itemView.findViewById(R.id.tips_photo);
        }
    }
}
