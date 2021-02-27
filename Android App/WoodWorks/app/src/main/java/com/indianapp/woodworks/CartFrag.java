package com.indianapp.woodworks;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartFrag extends Fragment {
    View rootView;
    List<String> prices;
    List<String> names;
    List<String> urls;
    TextView totalPrice;
    List<String> quantitys;
    Integer total;
    Button checkout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.frag_cart,container,false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        totalPrice=rootView.findViewById(R.id.toatalprice);
        checkout=rootView.findViewById(R.id.button5);
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
        total=0;
        for(int i=0;i<prices.size();i++){
            total+=Integer.parseInt(prices.get(i));
        }
        totalPrice.setText("Subtotal: Rs. "+String.valueOf(total));
        initRecylerView();
    }
    private void initRecylerView(){
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        CartItemAdapter adapter= new CartItemAdapter(getActivity(),prices,names,urls,quantitys,totalPrice,total,checkout);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}