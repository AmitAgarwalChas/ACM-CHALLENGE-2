package com.dev5151.acmchallenge2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.dev5151.acmchallenge2.Activities.User;

import java.util.ArrayList;

public class AmitAdapter extends RecyclerView.Adapter<AmitAdapter.ViewHolder>{

    ArrayList<User> muser;
    Context mcontext;

    public AmitAdapter(Context context,ArrayList<User> user){
        mcontext = context;
        muser = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_list_item , parent ,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        holder.DisplayName.setText(muser.get(position).getUserName());
        holder.DisplayAge.setText(muser.get(position).getAge()+" yrs");
        holder.DisplayEmail.setText(muser.get(position).getEmail());
        holder.DisplayPhone.setText(muser.get(position).getContact());


    }

    @Override
    public int getItemCount() {

        return muser.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView DisplayName;
        TextView DisplayAge;
        TextView DisplayEmail;
        TextView DisplayPhone;
        ConstraintLayout lay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            DisplayName = (TextView) itemView.findViewById(R.id.text_name);
            DisplayAge = (TextView)itemView.findViewById(R.id.text_age);
            DisplayEmail = (TextView)itemView.findViewById(R.id.text_email);
            DisplayPhone = (TextView)itemView.findViewById(R.id.text_contact);
            lay = (ConstraintLayout)itemView.findViewById(R.id.item_layout);
        }
    }

}
