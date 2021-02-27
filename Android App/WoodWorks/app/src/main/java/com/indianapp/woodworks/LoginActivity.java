package com.indianapp.woodworks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences preferences;
    EditText email;
    EditText password;
    String token;

    public void setDefaults(String key, String value) {
        preferences = this.getSharedPreferences("com.indianapp.woodworks", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void setDefInt(String key, Integer value) {
        preferences = this.getSharedPreferences("com.indianapp.woodworks", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public Integer getDefInt(String key) {
        SharedPreferences preferences = this.getSharedPreferences("com.indianapp.woodworks", Context.MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }

    public String getDefaults(String key) {
        SharedPreferences preferences = this.getSharedPreferences("com.indianapp.woodworks", Context.MODE_PRIVATE);
        return preferences.getString(key, "null");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.emailL);
        password = findViewById(R.id.passwordL);
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://woodworksapi.herokuapp.com/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        final WebClient client = retrofit.create(WebClient.class);
        Button loginL = findViewById(R.id.loginL);
        loginL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();
//                map.put("email", String.valueOf(email.getText()));
//                map.put("password",String.valueOf(password.getText()));

                map.put("password", "1234");
                map.put("email", "tjain210@gmail.com");

//                setDefaults("name",String.valueOf(name.getText()));
//                setDefaults("address",String.valueOf(address.getText()));
//                setDefaults("mobileNo",String.valueOf(mobile.getText()));
//
//                setDefaults("email",String.valueOf(email.getText()));
                Call<JsonObject> call = client.executeLogin(map);
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.i("message", String.valueOf(response.body().get("data")));
                        token = String.valueOf(response.body().get("data"));
                        Log.i("data", String.valueOf(response.body().get("user")));
                        setDefaults("_id", token);
                        setDefaults("name", String.valueOf(response.body().get("user").getAsJsonObject().get("name").getAsString()));
                        setDefaults("address", String.valueOf(response.body().get("user").getAsJsonObject().get("address").getAsString()));
                        setDefaults("mobileNo", String.valueOf(response.body().get("user").getAsJsonObject().get("mobileNo").getAsString()));
                        setDefaults("email", String.valueOf(response.body().get("user").getAsJsonObject().get("email").getAsString()));
                        Log.i("message", String.valueOf(response.isSuccessful()));
                        Log.i("message", String.valueOf(response.code()));
                        Intent intent = new Intent(getApplicationContext(),FragmentActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });
    }

    public void back(View view) {
        onBackPressed();
    }

    public void login(View view) {
        Intent intent = new Intent(getApplicationContext(), FragmentActivity.class);
//        setDefaults("username","tush",this);
        startActivity(intent);
        overridePendingTransition(R.anim.left_to_right1, R.anim.righ_to_left1);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        setDefaults("username","tush",this);
        startActivity(intent);
        overridePendingTransition(R.anim.left_to_right1, R.anim.righ_to_left1);
        finish();
    }
}