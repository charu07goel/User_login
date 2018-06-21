package com.example.charugoel.application;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charu Goel on 21-06-2018.
 */

public class ListAdapter extends ArrayAdapter {


    List list = new ArrayList();

    public ListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    static class Layout_handler{
        TextView username, First_name;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        Layout_handler layout_handler;
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            layout_handler = new Layout_handler();
            layout_handler.username = (TextView) row.findViewById(R.id.row_username);
            layout_handler.First_name = (TextView) row.findViewById(R.id.row_name);
            row.setTag(layout_handler);

        }
        else{
            layout_handler = (Layout_handler) row.getTag();
        }

        UserProvider userProvider = (UserProvider)this.getItem(position);
        layout_handler.username.setText(userProvider.getUsername());
        layout_handler.First_name.setText(userProvider.getFirst_name() +" "+ userProvider.getLast_name());

        return row;

    }
}
