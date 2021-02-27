package com.indianapp.woodworks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BedFrag extends Fragment {
    View rootView;
    List<String> names;
    List<String> urls;
    List<String> prices;
    HomeItemAdapter adapter;
    RecyclerView tableData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.frag_bed,container,false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        tableData=rootView.findViewById(R.id.tableData);
        prices=new ArrayList<>();
        urls=new ArrayList<>();
        names=new ArrayList<>();
        prices.add("Rs 232");
        prices.add("Rs 265");
        urls.add("https://sid-3d-viewer.netlify.app/");
        urls.add("https://sid-3d-viewer.netlify.app/");
        names.add("bed");
        names.add("bed");

        adapter = new HomeItemAdapter(prices,names,urls);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        tableData.setLayoutManager(gridLayoutManager);
        tableData.setAdapter(adapter);
    }
}
