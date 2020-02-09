package com.example.emsapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeAdapter extends RecyclerView.Adapter<EmployeAdapter.EmployeViewHolder> {

    Context mContext;
    List<EmployeModel> mEmployeModels;

    public EmployeAdapter(Context context, List<EmployeModel> employelist) {
        this.mContext = context;
        this.mEmployeModels = employelist;
    }


    @NonNull
    @Override
    public EmployeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);
        return new EmployeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeViewHolder holder, int position) {
        final EmployeModel data = mEmployeModels.get(position);
        holder.name.setText(data.getName());
        holder.email.setText(data.getEmailID());
        Picasso.get().load(data.getImageURL()).into(holder.profilepic);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(mContext,home.class);
               intent.putExtra("name",data.getName());
               intent.putExtra("email",data.getEmailID());
               intent.putExtra("mobile",data.getMobile());
               intent.putExtra("image",data.getImageURL());
                intent.putExtra("gender",data.getGender());
                intent.putExtra("birthday",data.getBirthday());

                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mEmployeModels.size();
    }

    public class EmployeViewHolder extends RecyclerView.ViewHolder {

        CircleImageView profilepic;
        TextView name, email;

        public EmployeViewHolder(@NonNull View itemView) {
            super(itemView);
            profilepic = itemView.findViewById(R.id.imageview);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
        }
    }
}