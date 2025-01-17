package com.indianapp.woodworks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.view.View.INVISIBLE;


public class AvatarActivity extends AppCompatActivity {
    CircleImageView av1;
    CircleImageView av2;
    CircleImageView av3;
    CircleImageView av4;
    CircleImageView av5;
    CircleImageView av6;
    CircleImageView av7;
    CircleImageView av8;
    CircleImageView center;
    TextView textView;
    SharedPreferences preferences;
    String auth_token;
    String name;
    String username;
    Integer avatarCode;
    public void setDefaults(String key, String value) {
        preferences = this.getSharedPreferences("com.indianapp.quizapp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public void setDefInt(String key, Integer value){
        preferences = this.getSharedPreferences("com.indianapp.quizapp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    public String getDefaults(String key) {
        SharedPreferences preferences = this.getSharedPreferences("com.indianapp.quizapp", Context.MODE_PRIVATE);
        return preferences.getString(key, "null");
    }
    public Integer getDefInt(String key) {
        SharedPreferences preferences = this.getSharedPreferences("com.indianapp.quizapp", Context.MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        av1=findViewById(R.id.avatar1);
        av2=findViewById(R.id.avatar2);
        av3=findViewById(R.id.avatar3);
        av4=findViewById(R.id.avatar4);
        av5=findViewById(R.id.avatar5);
        av6=findViewById(R.id.avatar6);
        av7=findViewById(R.id.avatar7);
        av8=findViewById(R.id.avatar8);
        center=findViewById(R.id.center_av);
        final Animation sgAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.avatar_anim);
        av1.startAnimation(sgAnimation);
        av2.startAnimation(sgAnimation);
        av3.startAnimation(sgAnimation);
        av4.startAnimation(sgAnimation);
        av5.startAnimation(sgAnimation);
        av6.startAnimation(sgAnimation);
        av7.startAnimation(sgAnimation);
        av8.startAnimation(sgAnimation);
        av1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animAte(1,R.drawable.avatar1);
            }
        });
        av2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animAte(2,R.drawable.avatar2);
            }
        });
        av3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animAte(3,R.drawable.ladka);
            }
        });
        av4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animAte(4,R.drawable.avatar4);
            }
        });
        av5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animAte(5,R.drawable.avatar5);
            }
        });
        av6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animAte(6,R.drawable.avatar6);
            }
        });
        av7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animAte(7,R.drawable.avatar7);
            }
        });
        av8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animAte(8,R.drawable.avatar8);
            }
        });

         textView = (TextView) findViewById(R.id.textView21);
        textView.setText("Pick your Avatar");

        TextPaint paint = textView.getPaint();
        float width = paint.measureText("Pick your Avatar");

        Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        Color.parseColor("#253A48"),
                        Color.parseColor("#253A00"),
                        Color.parseColor("#253A26"),
                        Color.parseColor("#253A49"),
                        Color.parseColor("#253A43"),
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);
        textView.setTextColor(Color.parseColor("#F97C3C"));
    }

    public void rip(View view){
        Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
        av1.setVisibility(INVISIBLE);
        av2.setVisibility(INVISIBLE);
        av3.setVisibility(INVISIBLE);
        av4.setVisibility(INVISIBLE);
        av5.setVisibility(INVISIBLE);
        av6.setVisibility(INVISIBLE);
        av7.setVisibility(INVISIBLE);
        av8.setVisibility(INVISIBLE);
    }
    public void animAte(int code, Integer id){
        Log.i("code", String.valueOf(code));
        sendCode(code);
        av1.clearAnimation();
        av2.clearAnimation();
        av3.clearAnimation();
        av4.clearAnimation();
        av5.clearAnimation();
        av6.clearAnimation();
        av7.clearAnimation();
        av8.clearAnimation();
        center.setVisibility(View.VISIBLE);
        center.setImageResource(id);
        final Animation cAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.center_zoom);
        cAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("suc","suc");
                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                intent.putExtra("code",String.valueOf(code));
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        center.startAnimation(cAnimation);
        av1.setVisibility(INVISIBLE);
        av2.setVisibility(INVISIBLE);
        av3.setVisibility(INVISIBLE);
        av4.setVisibility(INVISIBLE);
        av5.setVisibility(INVISIBLE);
        av6.setVisibility(INVISIBLE);
        av7.setVisibility(INVISIBLE);
        av8.setVisibility(INVISIBLE);
        textView.setVisibility(INVISIBLE);
    }

    public void sendCode(Integer code){

    }
}