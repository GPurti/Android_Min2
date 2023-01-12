package edu.upc.dsa.andoroid_dsa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upc.dsa.andoroid_dsa.Api;
import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.RetrofitClient;
import edu.upc.dsa.andoroid_dsa.models.UpdatableUserInformation;
import edu.upc.dsa.andoroid_dsa.models.ChatMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class ChatActivity extends AppCompatActivity {
    Api APIservice;
    String username;
    Integer number;
    TextInputEditText message;
    TableLayout tableScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        APIservice = RetrofitClient.getInstance().getMyApi();
        this.tableScreen = (TableLayout) findViewById(R.id.tableDisplay);
        this.message = (TextInputEditText) findViewById(R.id.messageInput);
        this.number = 0;
        Call<List<ChatMessage>> call = APIservice.getMessages(number);
        try {
            tableConstruct(call.execute().body());
        } catch (IOException e) {

        }
    }

    private void tableConstruct(List<ChatMessage> messages) {

    }

    public void sendMessage(View view) {
        if(!message.getText().toString().equals("")){
            ChatMessage m = new ChatMessage(this.username, message.getText().toString());
            Call<Void> call = APIservice.addMessage(m);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    ArrayList<ChatMessage> messages = new ArrayList<>();
                    ChatMessage me = new ChatMessage(username, message.getText().toString());
                    messages.add(me);
                    tableConstruct(messages);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Snackbar snakyfail = Snackbar.make(view, "NETWORK FAILURE", 3000);
                    snakyfail.show();
                }
            });

        }
    }

    public void reloadMessages(View view) {
        updateTable();
    }

    private void updateTable() {
        Call<List<ChatMessage>> call = APIservice.getMessages(this.number);
        try {
            tableConstruct(call.execute().body());
        } catch (IOException e) {

        }
    }

    public void Return(View view) {
        Intent intentDashBoard = new Intent(ChatActivity.this, DashBoardActivity.class);
        ChatActivity.this.startActivity(intentDashBoard);
    }
}
