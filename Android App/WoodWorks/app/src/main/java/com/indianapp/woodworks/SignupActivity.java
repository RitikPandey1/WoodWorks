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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {
    SharedPreferences preferences;
    EditText name;
    EditText email;
    EditText mobile;
    EditText address;
    EditText password;
    EditText avatarCode;
    String code;
    Button signUp;
    String token;
    public void setDefaults(String key, String value) {
        preferences = this.getSharedPreferences("com.indianapp.woodworks", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public void setDefInt(String key, Integer value){
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
        setContentView(R.layout.activity_signup);
        email=findViewById(R.id.emailS);
        name=findViewById(R.id.nameS);
        mobile=findViewById(R.id.mobileS);
        address=findViewById(R.id.addressS);
        password=findViewById(R.id.passwordS);
        avatarCode=findViewById(R.id.avatarCS);
        signUp=findViewById(R.id.signUp);
        if(getIntent().getExtras()==null){
            code="0";
        }else {
            code=getIntent().getExtras().getString("code");
        }


        Log.i("code on signup",code);

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://woodworksapi.herokuapp.com/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        final WebClient client = retrofit.create(WebClient.class);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                name
//                        email
//                mobileNo
//                        address
//                password
//                avatar Code
                Log.i("message","pressed");
                HashMap<String,String> map = new HashMap<>();
//                map.put("name", String.valueOf(name.getText()));
//                map.put("email", String.valueOf(email.getText()));
//                map.put("mobileNo", String.valueOf(mobile.getText()));
//                map.put("address", String.valueOf(address.getText()));
//                map.put("avatarCode", String.valueOf(avatarCode.getText()));
                //map.put("password",String.valueOf(password.getText()));


                map.put("name", "tushar");
                map.put("password","1234");
                map.put("email", "tjain210@gmail.com");
                map.put("mobileNo", "9873450282");
                map.put("address", "h.no.1 huh");
                map.put("avatarCode","2");

                Call<JsonObject> call =client.executeSignup(map);
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.i("message", String.valueOf(response.body().get("data")));
                        token=String.valueOf(response.body().get("data"));
                        setDefaults("_id",token);
                        Log.i("message", String.valueOf(response.isSuccessful()));
                        Log.i("message", String.valueOf(response.code()));
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });
    }
    public void signup(View View){
        Intent intent = new Intent(getApplicationContext(),FragmentActivity.class);
//        setDefaults("username","tush",this);
        startActivity(intent);
        overridePendingTransition(R.anim.left_to_right1,R.anim.righ_to_left1);
        finish();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//        setDefaults("username","tush",this);
        startActivity(intent);
        overridePendingTransition(R.anim.left_to_right1,R.anim.righ_to_left1);
        finish();
    }
}