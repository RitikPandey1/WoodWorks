package com.indianapp.woodworks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeItemAdapter extends RecyclerView.Adapter<HomeItemAdapter.ViewHolder>{
    List<String> prices;List<String> names;List<String> urls;
    Context mContext;
    public HomeItemAdapter(Context mContext,List<String> prices,List<String> names,List<String> urls){
        this.names=names;
        this.mContext=mContext;
        this.urls=urls;
        this.prices=prices;
    }
    @NonNull
    @Override
    public HomeItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_home_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeItemAdapter.ViewHolder holder, int position) {
        holder.webView.getSettings().setLoadsImagesAutomatically(true);
        holder.webView.getSettings().setJavaScriptEnabled(true);
        holder.webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        holder.webView.loadUrl(urls.get(position));
        holder.price.setText(prices.get(position));
        holder.name.setText(names.get(position));
        holder.cardVIew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ProductViewerActivity.class);
                mContext.startActivity(intent);
                ((Activity)mContext).finish();

            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView price;
        TextView name;
        String url;
        WebView webView;
        CardView cardVIew;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            price=itemView.findViewById(R.id.pricecH);
            name=itemView.findViewById(R.id.namecH);
            webView=itemView.findViewById(R.id.webViewH);
            cardVIew=itemView.findViewById(R.id.itemCard);
        }
    }
}
