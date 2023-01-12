package edu.upc.dsa.andoroid_dsa.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import edu.upc.dsa.andoroid_dsa.Api;
import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.RetrofitClient;
import edu.upc.dsa.andoroid_dsa.models.Gadget;
import edu.upc.dsa.andoroid_dsa.models.User;
import retrofit2.Call;

public class UsersRanking extends AppCompatActivity {
    Api APIservice;
    private RecyclerView recyclerViewUsers;
    private RecyclerViewAdapterRanking adapterUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_activity);
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerUserRanking);
        Log.d("DDDD", "" + recyclerViewUsers);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));
        APIservice = RetrofitClient.getInstance().getMyApi();
        Call<List<User>> call = APIservice.usersRanking();
        try {
            adapterUsers = new RecyclerViewAdapterRanking(call.execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerViewUsers.setAdapter(adapterUsers);
    }


    public void ReturnToProfile(View view){
        Intent intentRegister = new Intent(UsersRanking.this, YourProfileActivity.class);
        UsersRanking.this.startActivity(intentRegister);
    }
}
