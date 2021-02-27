package com.indianapp.woodworks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {
    List<String> prices;
    List<String> names;
    List<String> urls;
    List<String> quantitys;
    Integer newquantity;
    Context mContext;
    TextView totalPrice;
    Integer total;
    Button checkout;

    public CartItemAdapter(Context mContext,List<String> prices, List<String> names, List<String> urls, List<String> quantitys,TextView totalPrice,Integer total,Button checkout) {
        this.mContext=mContext;
        this.names = names;
        this.urls = urls;
        this.prices = prices;
        this.quantitys=quantitys;
        this.totalPrice=totalPrice;
        this.total=total;
        this.checkout=checkout;
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
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("amount", String.valueOf(total));
            }
        });
        holder.webView.getSettings().setLoadsImagesAutomatically(true);
        holder.webView.getSettings().setJavaScriptEnabled(true);
        holder.webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        holder.webView.loadUrl(urls.get(position));
        holder.price.setText(prices.get(position));
        holder.name.setText(names.get(position));
        holder.quantity.setText(quantitys.get(position));

            //Log.i("quantot",quantitys.get(position));
            holder.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("quantot",quantitys.get(position));
                    if (Integer.parseInt(quantitys.get(position)) > 0) {
                        newquantity = Integer.parseInt(quantitys.get(position)) - 1;
                        total=total-Integer.parseInt(prices.get(position));
                        if(newquantity==0){
                            quantitys.remove(position);
                            names.remove(position);
                            urls.remove(position);
                            prices.remove(position);
                        }else {
                            holder.quantity.setText(String.valueOf(newquantity));
                            quantitys.set(position, String.valueOf(newquantity));
                        }
                        totalPrice.setText("Subtotal: Rs. "+String.valueOf(total));
                        notifyDataSetChanged();
                    }
                }
            });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newquantity=Integer.parseInt(quantitys.get(position))+1;
                holder.quantity.setText(String.valueOf(newquantity));
                quantitys.set(position, String.valueOf(newquantity));
                total=total+Integer.parseInt(prices.get(position));
                totalPrice.setText("Subtotal: Rs. "+String.valueOf(total));
                notifyDataSetChanged();
            }
        });
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
        TextView quantity;
        ImageView plus;
        ImageView minus;
        WebView webView;
        CardView cardVIew;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardVIew=itemView.findViewById(R.id.cartCart);
            quantity=itemView.findViewById(R.id.quantity);
            plus=itemView.findViewById(R.id.plus);
            minus=itemView.findViewById(R.id.minus);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);
            webView = itemView.findViewById(R.id.webView);
        }
    }
}
