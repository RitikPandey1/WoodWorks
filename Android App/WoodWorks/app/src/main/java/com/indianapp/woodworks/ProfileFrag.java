package com.indianapp.woodworks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFrag extends Fragment {
    View rootView;
    CircleImageView imgP;
    SharedPreferences preferences;
    Button logout;
    TextView name;
    TextView email;
    TextView address;
    TextView phone;
    public void setDefaults(String key, String value) {
        preferences = getActivity().getSharedPreferences("com.indianapp.woodworks", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public void setDefInt(String key, Integer value){
        preferences = getActivity().getSharedPreferences("com.indianapp.woodworks", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    public Integer getDefInt(String key) {
        SharedPreferences preferences = getActivity().getSharedPreferences("com.indianapp.woodworks", Context.MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }
    public String getDefaults(String key) {
        SharedPreferences preferences = getActivity().getSharedPreferences("com.indianapp.woodworks", Context.MODE_PRIVATE);
        return preferences.getString(key, "null");
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.frag_profile,container,false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        imgP=getActivity().findViewById(R.id.imgP);
        logout=getActivity().findViewById(R.id.button2);
        name=getActivity().findViewById(R.id.namepro);
        email=getActivity().findViewById(R.id.emailHome);
        address=getActivity().findViewById(R.id.addreshome);
        phone=getActivity().findViewById(R.id.numberHme);
        name.setText(getDefaults("name"));
        email.setText(getDefaults("email"));
        phone.setText(getDefaults("mobileNo"));
        address.setText(getDefaults("address"));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDefaults("_id","null");
                setDefaults("name","null");
                setDefaults("address","null");
                setDefaults("mobileNo","null");

                setDefaults("email","null");
                setDefInt("avatarCode",0);
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0,0);
                getActivity().finish();
            }
        });
        if(getDefInt("avatarCode")!=0) {
            imgP.setBackgroundResource(R.drawable.circle);
            switch (getDefInt("avatarCode")) {
                case 1:
                    imgP.setImageResource(R.drawable.avatar1);
                    break;
                case 2:
                    imgP.setImageResource(R.drawable.avatar2);
                    break;
                case 3:
                    imgP.setImageResource(R.drawable.ladka);
                    break;
                case 4:
                    imgP.setImageResource(R.drawable.avatar4);
                    break;
                case 5:
                    imgP.setImageResource(R.drawable.avatar5);
                    break;
                case 6:
                    imgP.setImageResource(R.drawable.avatar6);
                    break;
                case 7:
                    imgP.setImageResource(R.drawable.avatar7);
                    break;
                case 8:
                    imgP.setImageResource(R.drawable.avatar8);
                    break;
            }
        }

    }
}
