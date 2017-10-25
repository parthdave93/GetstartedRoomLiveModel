package com.parthdave.room.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.parthdave.room.R;

public class ViewHolder extends RecyclerView.ViewHolder{
    public TextView tvTitle;
    public TextView tvCreatedAt;
    public TextView tvUpdatedAt;

    public ViewHolder(View view) {
        super(view);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvCreatedAt = (TextView) view.findViewById(R.id.tvCreatedAt);
        tvUpdatedAt = (TextView) view.findViewById(R.id.tvUpdatedAt);
    }
}