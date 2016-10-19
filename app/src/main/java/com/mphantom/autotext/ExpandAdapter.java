package com.mphantom.autotext;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wushaorong on 16-10-14.
 */

public class ExpandAdapter extends RecyclerView.Adapter<ExpandAdapter.ViewHolder> {
    private List<ExpandModle> expandList;

    private OnItemClickListener onItemClickListener;

    public ExpandAdapter(List<ExpandModle> list) {
        this.expandList = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_expandtext, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvKey.setText(expandList.get(position).getKey());
        holder.tvValue.setText(expandList.get(position).getValue());
        holder.itemView.setTag(expandList.get(position));
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(v, position);
        });
    }

    @Override
    public int getItemCount() {
        return expandList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvKey, tvValue;

        public ViewHolder(View itemView) {
            super(itemView);
            tvKey = (TextView) itemView.findViewById(R.id.tv_key);
            tvValue = (TextView) itemView.findViewById(R.id.tv_value);
        }
    }
}
