package com.zee.mavericksampleapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Name List Adapter is an Adapter class to display names in a RecyclerView
 */
public class NameListAdapter extends RecyclerView.Adapter<NameListAdapter.NameViewHolder> {

    private List<String> nameList;

    public NameListAdapter(List<String> nameList) {
        this.nameList = nameList;
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    @Override
    public void onBindViewHolder(final NameViewHolder holder, int i) {
        String name = nameList.get(i).trim();
        String[] namesArray = name.split(" ");

        if(namesArray.length > 1) {
            name = capitalizeWord(namesArray[namesArray.length - 1]) + ",";
            for(int j = 0; j < namesArray.length - 1; j++ ){
                name += " " + capitalizeWord(namesArray[j]);
            }
        }else{
           name = capitalizeWord(name);
        }

        holder.mTextView.setText(name);

    }

    @Override
    public NameViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_name, viewGroup, false);
        return new NameViewHolder(itemView);
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {

        protected TextView mTextView;

        public NameViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.text_view);
        }
    }

    private String capitalizeWord(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}