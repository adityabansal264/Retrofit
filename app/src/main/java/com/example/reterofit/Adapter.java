package com.example.reterofit;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Callback;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder>{

    Context context;
    ArrayList<Articles> result;

    public Adapter(Context context, ArrayList<Articles> result){
        this.context=context;
        this.result=result;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cell,parent,false);
        Holder holder=new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Articles current =result.get(position);
        holder.mTvDesc.setText(current.description);
        holder.mTvTitle.setText(current.title);
        Glide.with(context).load(current.urlToImage).into(holder.mImage);


    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        private ImageView mImage;
        private TextView mTvTitle;
        private TextView mTvDesc;

        public Holder(@NonNull View itemView) {
            super(itemView);
            mImage=itemView.findViewById(R.id.iv_img);
            mTvTitle=itemView.findViewById(R.id.tv_title);
            mTvDesc=itemView.findViewById(R.id.tv_desc);
        }
    }
}
