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

public class AddUserToExam extends ArrayAdapter<UsersToExam> {

    Context context;
    List<UsersToExam> usersToExams;
    public AddUserToExam(@NonNull Context context, List<UsersToExam>usersToExams){
        super(context,R.layout.activity_list_users_to_exam,usersToExams);

        this.context=context;
        this.usersToExams=usersToExams;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvName = view.findViewById(R.id.txt_name);

        tvID.setText(usersToExams.get(position).getId());
        tvName.setText(usersToExams.get(position).getUsername());

        return view;
    }

}
