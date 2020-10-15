package com.example.practi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Add extends ArrayAdapter<Users> {

    Context context;
    List<Users>arrayListUsers;
    public Add(@NonNull Context context,List<Users>arrayListUsers){
        super(context,R.layout.activity_favorite,arrayListUsers);

        this.context=context;
        this.arrayListUsers=arrayListUsers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvName = view.findViewById(R.id.txt_name);

        tvID.setText(arrayListUsers.get(position).getId());
        tvName.setText(arrayListUsers.get(position).getFullname());

        return view;
    }


}
