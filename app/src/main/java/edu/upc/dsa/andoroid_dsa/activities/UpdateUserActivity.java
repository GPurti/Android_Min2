package edu.upc.dsa.andoroid_dsa.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.List;

import edu.upc.dsa.andoroid_dsa.Api;
import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.RetrofitClient;
import edu.upc.dsa.andoroid_dsa.models.Gadget;
import edu.upc.dsa.andoroid_dsa.models.UpdatableUserInformation;
import edu.upc.dsa.andoroid_dsa.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUserActivity extends AppCompatActivity {
    Api APIservice;
    String idUser;
    TextInputEditText nameText, surnameText, birthdayText, emailText, passwordText, confirmationPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user_activity);
        this.getUserIdFromPreviousActivity();
    }

    public void getUserIdFromPreviousActivity(){
        SharedPreferences sharedPreferences = getSharedPreferences("userEditInfo", Context.MODE_PRIVATE);
        this.idUser = sharedPreferences.getString("userId", null).toString();
    }

    public void ReturnToProfile(View view){
        Intent intentRegister = new Intent(UpdateUserActivity.this, YourProfileActivity.class);
        UpdateUserActivity.this.startActivity(intentRegister);
    }
    public void editUserProfile(View view){
        nameText = findViewById(R.id.editTextName);
        surnameText=findViewById(R.id.editTextSurname);
        birthdayText=findViewById(R.id.editTextBirthday);
        emailText=findViewById(R.id.editTextEmail);
        passwordText=findViewById(R.id.editTextPassword);
        confirmationPasswordText=findViewById(R.id.editTextPasswordConfirmation);
        Log.i("info: ",passwordText.getText().toString());
        Log.i("info2: ",confirmationPasswordText.getText().toString());
        if(passwordText.getText().toString().equals(confirmationPasswordText.getText().toString())){
            UpdatableUserInformation u = new UpdatableUserInformation(idUser,nameText.getText().toString(), surnameText.getText().toString(), birthdayText.getText().toString(), emailText.getText().toString(), passwordText.getText().toString());
            APIservice = RetrofitClient.getInstance().getMyApi();
            Call<Void> call = APIservice.updateUser(u);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    switch (response.code()){
                        case 201:
                            Snackbar snaky201 = Snackbar.make(view, "User updated!", 3000);
                            snaky201.show();
                            Intent intentRegister = new Intent(UpdateUserActivity.this, YourProfileActivity.class);
                            UpdateUserActivity.this.startActivity(intentRegister);
                            break;
                        case 401:
                            Snackbar snaky401 = Snackbar.make(view, "This user does not exist!", 3000);
                            snaky401.show();
                            break;
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Snackbar snakyfail = Snackbar.make(view, "NETWORK FAILURE", 3000);
                    snakyfail.show();
                }
            });
        }
        else{
            Toast.makeText(this,"Please Sign In.", Toast.LENGTH_SHORT).show();
        }

    }

}
