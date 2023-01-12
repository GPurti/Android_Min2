package edu.upc.dsa.andoroid_dsa.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.models.Gadget;
import edu.upc.dsa.andoroid_dsa.models.User;

public class RecyclerViewAdapterRanking extends RecyclerView.Adapter<RecyclerViewAdapterRanking.ViewHolder>{
    private static RecycleClickViewListener listener;
    //private static final String URL_INT
    public List<User> users;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView username,coins,experience;
        ImageView photoUser;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=(TextView)itemView.findViewById(R.id.userName);
            coins=(TextView)itemView.findViewById(R.id.coinBox);
            experience=(TextView)itemView.findViewById(R.id.experienceBox);
            photoUser=(ImageView) itemView.findViewById(R.id.userAvatar);
        }
    }

    public RecyclerViewAdapterRanking(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_layout_activity,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.username.setText(users.get(position).getName());
        holder.coins.setText("User coins:"+Integer.toString(users.get(position).getCoins()));
        holder.experience.setText("User experience:"+Integer.toString(users.get(position).getExperience()));
        Picasso.get().load(users.get(position).getProfileAvatar()).into(holder.photoUser);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
