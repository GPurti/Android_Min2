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
import java.util.Objects;

import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.models.Gadget;
import edu.upc.dsa.andoroid_dsa.models.User;

public class RecyclerViewAdapterRanking extends RecyclerView.Adapter<RecyclerViewAdapterRanking.ViewHolder>{
    private static RecycleClickViewListener listener;
    //private static final String URL_INT
    public List<User> users;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView username,coins,experience;
        ImageView photoUser, photoRanking;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=(TextView)itemView.findViewById(R.id.userName);
            coins=(TextView)itemView.findViewById(R.id.coinBox);
            experience=(TextView)itemView.findViewById(R.id.experienceBox);
            photoUser=(ImageView) itemView.findViewById(R.id.userAvatar);
            photoRanking=(ImageView) itemView.findViewById(R.id.compartir);
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
        holder.coins.setText("User coins: "+Integer.toString(users.get(position).getCoins()));
        holder.experience.setText("User experience: "+Integer.toString(users.get(position).getExperience()));

        if (Objects.equals(users.get(position).getName(), "Guillem")) {
            Picasso.get().load("https://lh3.googleusercontent.com/-g52zj2sStJI/AAAAAAAAAAI/AAAAAAAAAAA/AFi9ZuH7K6Y4JfTuh6NxvMHVugOoDaBBIA/photo.jpg?sz=46").into(holder.photoUser);
        }
        if (Objects.equals(users.get(position).getName(), "Alba")) {
            Picasso.get().load("https://media-exp1.licdn.com/dms/image/C4E03AQH9d9LHOD31vQ/profile-displayphoto-shrink_200_200/0/1657097482744?e=1675296000&v=beta&t=HHwkzAhMs6EGIG9E7wlo4HMyIE1tWQ8SuciFwq19ABs").into(holder.photoUser);
        }
        if (Objects.equals(users.get(position).getName(), "Maria")) {
            Picasso.get().load("https://media.licdn.com/dms/image/C4D03AQEzsbs50kI6vw/profile-displayphoto-shrink_200_200/0/1640174795289?e=2147483647&v=beta&t=pI-8r5G-cGUg368a4SyNa6KyxZsgWKA0sHhtt4P1McY").into(holder.photoUser);
        }
        if(position==0){
            Picasso.get().load("https://cdn-icons-png.flaticon.com/128/2583/2583381.png").into(holder.photoRanking);
        }
        if(position==1) {
            Picasso.get().load("https://cdn3.iconfinder.com/data/icons/study-education-9/96/medal_winner_win_award_second_place-512.png").into(holder.photoRanking);
        }
        if(position==2) {
            Picasso.get().load("https://cdn-icons-png.flaticon.com/512/5406/5406811.png").into(holder.photoRanking);
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
