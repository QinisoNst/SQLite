package com.dummyworks.sqlite.rva;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dummyworks.sqlite.R;

import java.util.List;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.MyViewHolder> {

    List<President> presidentList;
    Context contect;

    public recyclerViewAdapter(List<President> presidentList, Context contect) {
        this.presidentList = presidentList;
        this.contect = contect;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_president,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_presidentName.setText(presidentList.get(position).getName());
        holder.tv_addElection.setText(String.valueOf(presidentList.get(position).getDateOfElection()));
        holder.iv_presPic.setText(presidentList.get(position).getImageURL());
        holder.tv_phone.setText(String.valueOf(presidentList.get(position).getPhone()));


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(contect, AddEditOne.class);

                intent.putExtra("id", presidentList.get(position).getId());

                contect.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return presidentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

       TextView tv_presidentName,tv_addElection,iv_presPic,tv_phone;

       ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_presPic = itemView.findViewById(R.id.iv_presidentPicture);
            tv_presidentName = itemView.findViewById(R.id.tv_presidentName);
            tv_addElection = itemView.findViewById(R.id.tv_addElection);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            parentLayout = itemView.findViewById(R.id.oneLinePresidentLayout);

        }
    }
}
