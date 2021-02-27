package com.indianapp.woodworks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFrag extends Fragment {
    View rootView;
    CircleImageView imgP;
    SharedPreferences preferences;
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
        setDefInt("avatarCode",2);
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
