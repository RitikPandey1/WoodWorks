package com.indianapp.woodworks;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PrevOrderFrag extends Fragment {
    View rootView;
    List<String> prices;
    List<String> names;
    List<String> urls;
    List<String> quantitys;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.frag_prev,container,false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        prices=new ArrayList<>();
        names = new ArrayList<>();
        urls =new ArrayList<>();
        quantitys=new ArrayList<>();
        prices.add("500");
        names.add("chair");
        urls.add("https://woodworksapi.herokuapp.com/3d/model");
        quantitys.add("1");

        prices.add("500");
        names.add("chair");
        urls.add("https://woodworksapi.herokuapp.com/3d/model");
        quantitys.add("1");
        prices.add("500");
        names.add("chair");
        urls.add("https://woodworksapi.herokuapp.com/3d/model");
        quantitys.add("1");
        prices.add("500");
        names.add("chair");
        urls.add("https://woodworksapi.herokuapp.com/3d/model");
        quantitys.add("1");
        initRecylerView();
    }
    private void initRecylerView(){
        RecyclerView recyclerView = rootView.findViewById(R.id.prevData);
        PrevItemAdapter adapter= new PrevItemAdapter(quantitys,prices,names,urls);
        recyclerView.setAdapter(adapter);
        Log.i("recycler","current");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.i("recycler","current");
    }
}
