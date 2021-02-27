package com.indianapp.woodworks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class HomeFrag extends Fragment {
    View rootView;
    TextView name;

    ConstraintLayout tablec;
    ImageView tableH;
    ImageView tablebH;

    ConstraintLayout bedc;
    ImageView bedH;
    ImageView bedbH;

    ConstraintLayout sofac;
    ImageView sofaH;
    ImageView sofabH;

    ConstraintLayout chairc;
    ImageView chairH;
    ImageView chairbH;

    ConstraintLayout lampc;
    ImageView lampH;
    ImageView lampbH;
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
        rootView= inflater.inflate(R.layout.frag_home,container,false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        name=getActivity().findViewById(R.id.nameHome);
        name.setText(getDefaults("name"));

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.catfrag, new TableFrag()).commit();
        tablec=getActivity().findViewById(R.id.tablec);
        tablebH=getActivity().findViewById(R.id.tablebH);
        tableH=getActivity().findViewById(R.id.tableH);

        bedc=getActivity().findViewById(R.id.bedc);
        bedbH=getActivity().findViewById(R.id.bedbH);
        bedH=getActivity().findViewById(R.id.bedH);

        sofac=getActivity().findViewById(R.id.sofac);
        sofabH=getActivity().findViewById(R.id.sofabH);
        sofaH=getActivity().findViewById(R.id.sofaH);

        chairc=getActivity().findViewById(R.id.chairc);
        chairbH=getActivity().findViewById(R.id.chairbH);
        chairH=getActivity().findViewById(R.id.chairH);

        lampc=getActivity().findViewById(R.id.lampc);
        lampbH=getActivity().findViewById(R.id.lampbH);
        lampH=getActivity().findViewById(R.id.lampH);

        tablebH.setImageResource(R.drawable.ic_colorcategory);
        tableH.setImageResource(R.drawable.ic_table_);

        lampc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.catfrag, new LampFrag()).commit();
                tablebH.setImageResource(R.drawable.ic_whitecategory);
                tableH.setImageResource(R.drawable.ic_table);
                bedbH.setImageResource(R.drawable.ic_whitecategory);
                bedH.setImageResource(R.drawable.ic_bed);
                sofabH.setImageResource(R.drawable.ic_whitecategory);
                sofaH.setImageResource(R.drawable.ic_couch);
                chairbH.setImageResource(R.drawable.ic_whitecategory);
                chairH.setImageResource(R.drawable.ic_chair__1_);

                lampbH.setImageResource(R.drawable.ic_colorcategory);
                lampH.setImageResource(R.drawable.ic_lamp_);
            }
        });

        bedc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.catfrag, new BedFrag()).commit();
                tablebH.setImageResource(R.drawable.ic_whitecategory);
                tableH.setImageResource(R.drawable.ic_table);
                bedbH.setImageResource(R.drawable.ic_whitecategory);
                bedH.setImageResource(R.drawable.ic_bed);
                sofabH.setImageResource(R.drawable.ic_whitecategory);
                sofaH.setImageResource(R.drawable.ic_couch);
                chairbH.setImageResource(R.drawable.ic_whitecategory);
                chairH.setImageResource(R.drawable.ic_chair__1_);
                lampbH.setImageResource(R.drawable.ic_whitecategory);
                lampH.setImageResource(R.drawable.ic_lamp);

                bedbH.setImageResource(R.drawable.ic_colorcategory);
                bedH.setImageResource(R.drawable.ic_bed_);
            }
        });
        tablec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.catfrag, new TableFrag()).commit();

                tablebH.setImageResource(R.drawable.ic_whitecategory);
                tableH.setImageResource(R.drawable.ic_table);
                bedbH.setImageResource(R.drawable.ic_whitecategory);
                bedH.setImageResource(R.drawable.ic_bed);
                sofabH.setImageResource(R.drawable.ic_whitecategory);
                sofaH.setImageResource(R.drawable.ic_couch);
                chairbH.setImageResource(R.drawable.ic_whitecategory);
                chairH.setImageResource(R.drawable.ic_chair__1_);
                lampbH.setImageResource(R.drawable.ic_whitecategory);
                lampH.setImageResource(R.drawable.ic_lamp);

                tablebH.setImageResource(R.drawable.ic_colorcategory);
                tableH.setImageResource(R.drawable.ic_table_);
            }
        });

        sofac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.catfrag, new SofaFrag()).commit();
                tablebH.setImageResource(R.drawable.ic_whitecategory);
                tableH.setImageResource(R.drawable.ic_table);
                bedbH.setImageResource(R.drawable.ic_whitecategory);
                bedH.setImageResource(R.drawable.ic_bed);
                sofabH.setImageResource(R.drawable.ic_whitecategory);
                sofaH.setImageResource(R.drawable.ic_couch);
                chairbH.setImageResource(R.drawable.ic_whitecategory);
                chairH.setImageResource(R.drawable.ic_chair__1_);
                lampbH.setImageResource(R.drawable.ic_whitecategory);
                lampH.setImageResource(R.drawable.ic_lamp);

                sofabH.setImageResource(R.drawable.ic_colorcategory);
                sofaH.setImageResource(R.drawable.ic_couch_);
            }
        });
        chairc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.catfrag, new ChairFrag()).commit();
                tablebH.setImageResource(R.drawable.ic_whitecategory);
                tableH.setImageResource(R.drawable.ic_table);
                bedbH.setImageResource(R.drawable.ic_whitecategory);
                bedH.setImageResource(R.drawable.ic_bed);
                sofabH.setImageResource(R.drawable.ic_whitecategory);
                sofaH.setImageResource(R.drawable.ic_couch);
                chairbH.setImageResource(R.drawable.ic_whitecategory);
                chairH.setImageResource(R.drawable.ic_chair__1_);
                lampbH.setImageResource(R.drawable.ic_whitecategory);
                lampH.setImageResource(R.drawable.ic_lamp);

                chairbH.setImageResource(R.drawable.ic_colorcategory);
                chairH.setImageResource(R.drawable.ic_chair__1__);
            }
        });
        




    }

}
