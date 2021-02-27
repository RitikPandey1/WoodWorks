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

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {
    List<String> prices;
    List<String> names;
    List<String> urls;

    public CartItemAdapter(List<String> prices, List<String> names, List<String> urls) {
        this.names = names;
        this.urls = urls;
        this.prices = prices;
    }

    @NonNull
    @Override
    public CartItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cart_item, parent, false);
        CartItemAdapter.ViewHolder holder = new CartItemAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemAdapter.ViewHolder holder, int position) {
        holder.webView.getSettings().setLoadsImagesAutomatically(true);
        holder.webView.getSettings().setJavaScriptEnabled(true);
        holder.webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        holder.webView.loadUrl(urls.get(position));
        holder.price.setText(prices.get(position));
        holder.name.setText(names.get(position));
//        holder.quantity.setText(quantity.get(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView price;
        TextView name;
        TextView quantity;
        ImageView plus;
        ImageView minus;
        WebView webView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quantity=itemView.findViewById(R.id.quantity);
            plus=itemView.findViewById(R.id.plus);
            minus=itemView.findViewById(R.id.minus);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);
            webView = itemView.findViewById(R.id.webView);
        }
    }
}
