package com.indianapp.woodworks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PrevItemAdapter extends RecyclerView.Adapter<PrevItemAdapter.ViewHolder> {
    List<String> prices;
    List<String> names;
    List<String> urls;
    List<String> quantity;

    public PrevItemAdapter(List<String> quantity,List<String> prices, List<String> names, List<String> urls) {
        this.names = names;
        this.quantity=quantity;
        this.urls = urls;
        this.prices = prices;
    }

    @NonNull
    @Override
    public PrevItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_prev_item, parent, false);
        PrevItemAdapter.ViewHolder holder = new PrevItemAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PrevItemAdapter.ViewHolder holder, int position) {
        holder.webView.getSettings().setLoadsImagesAutomatically(true);
        holder.webView.getSettings().setJavaScriptEnabled(true);
        holder.webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        holder.webView.loadUrl(urls.get(position));
        holder.price.setText(prices.get(position));
        holder.name.setText(names.get(position));
        holder.quantity.setText(quantity.get(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView price;
        TextView name;
        TextView quantity;

        WebView webView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quantity=itemView.findViewById(R.id.quantityprev);
            price = itemView.findViewById(R.id.priceprev);
            name = itemView.findViewById(R.id.namePrev);
            webView = itemView.findViewById(R.id.webView);
        }
    }
}

