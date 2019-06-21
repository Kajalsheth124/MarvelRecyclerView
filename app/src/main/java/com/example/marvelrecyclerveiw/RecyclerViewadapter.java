package com.example.marvelrecyclerveiw;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewadapter extends RecyclerView.Adapter<RecyclerViewadapter.ViewHolder>  {
    Context mContext;
    LayoutInflater mInflater;
    List<HeroActivity> HeroList;
    ItemClickListener mClickListener;

    public RecyclerViewadapter(Context mContext, List<HeroActivity> HeroList) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.HeroList = HeroList;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtHeroName.setText(HeroList.get(position).getName());
        holder.txtRealName.setText(HeroList.get(position).getRealname());
        holder.txtHeroCreated.setText(HeroList.get(position).getCreatedby());
        holder.txtHeroFirstApp.setText(HeroList.get(position).getFirstappearance());
        holder.txtHerobio.setText(HeroList.get(position).getBio());
        holder.txtHeroPublisher.setText(HeroList.get(position).getPublisher());
        holder.txtHeroteam.setText(HeroList.get(position).getTeam());

        Picasso.get().load(HeroList.get(position).getImageurl()).into(holder.imgHeroImage);
        // Picasso.with(mContext).load(mData.get(position).getImageUrl()).fit().into(holder.imgProductImage);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return HeroList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtHeroName;
        TextView txtHerobio;
        TextView txtRealName;
        TextView txtHeroteam;
        TextView txtHeroPublisher;
        TextView txtHeroFirstApp;
        TextView txtHeroCreated;
        ImageView imgHeroImage;

        ViewHolder(View itemView) {
            super(itemView);
            imgHeroImage = itemView.findViewById(R.id.imageView);
            txtHeroName = itemView.findViewById(R.id.Name);
            txtRealName = itemView.findViewById(R.id.RealName);
            txtHeroteam = itemView.findViewById(R.id.Team);
            txtHeroFirstApp = itemView.findViewById(R.id.Firstapp);
            txtHeroCreated = itemView.findViewById(R.id.Created);
            txtHeroPublisher = itemView.findViewById(R.id.publisher);
            txtHerobio = itemView.findViewById(R.id.bio);

            txtHeroName.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                if (view.getId()== txtHeroName.getId()){
                    mClickListener.onItemNameClick(view,getAdapterPosition());
                }else {
                    mClickListener.onItemClick(view, getAdapterPosition());
                }
            }
        }
    }

    // convenience method for getting data at click position
    HeroActivity getItem(int id) {
        return HeroList.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
        void onItemNameClick(View view, int position);
    }
}
