package com.indianapp.woodworks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.PaymentIntentResult;
import com.stripe.android.Stripe;
import com.stripe.android.model.Address;

import com.stripe.android.model.Card;
import com.stripe.android.model.ConfirmPaymentIntentParams;
import com.stripe.android.model.PaymentIntent;
import com.stripe.android.model.PaymentMethod;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.view.CardInputWidget;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PaymentActivity extends AppCompatActivity {
    EditText card;
    EditText cvv;
    EditText pincode;
    EditText date;
    Button pay;
    private static final String BACKEND_URL = "https://siddhant.run-ap-south1.goorm.io/";

    private OkHttpClient httpClient = new OkHttpClient();
    private String paymentIntentClientSecret;
    private Stripe stripe;
    String cardn,cvvn,pincoden,daten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
//        card=findViewById(R.id.cardNum);
//        cvv=findViewById(R.id.cvv);
//        pincode=findViewById(R.id.pincode);
//        date=findViewById(R.id.expdate);
        pay=findViewById(R.id.pay);
        stripe = new Stripe(
                getApplicationContext(),
                Objects.requireNonNull("pk_test_51IMurOCZfXoloCo5uhF86MzUMhOsTbWcgq0FfFTyNFycMd31qshkpzIYqIxUVVjfL58KbXiyCVQE1CS5moycEwUa000zapQHLE")
        );
    }
    private void startCheckout() {
//        card=findViewById(R.id.cardNum);
//        cvv=findViewById(R.id.cvv);
//        pincode=findViewById(R.id.pincode);
//        date=findViewById(R.id.expdate);
        // Create a PaymentIntent by calling the server's endpoint.
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        double amount = Double.valueOf(20) * 100;

        Map<String, Object> payMap = new HashMap<>();
        Map<String, Object> itemMap = new HashMap<>();
        List<Map<String, Object>> itemList = new ArrayList<>();
        payMap.put("currency", "inr"); //don't change currency in testing phase otherwise it won't work
        itemMap.put("id", "photo_subscription");
        itemMap.put("amount", amount);
        itemList.add(itemMap);
        payMap.put("items", itemList);
        String json = new Gson().toJson(payMap);



        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(BACKEND_URL + "create-payment-intent")
                .post(body)
                .build();
        httpClient.newCall(request)
                .enqueue(new PayCallback(this));

        // Hook up the pay button to the card widget and stripe instance
        Button payButton = findViewById(R.id.pay);
        payButton.setOnClickListener((View view) -> {
            //CardInputWidget cardInputWidget = findViewById(R.id.cardInputWidget);

            //PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();
            PaymentMethodCreateParams params=new PaymentMethodCreateParams(PaymentMethodCreateParams.Type.Card, new PaymentMethodCreateParams.Card(), null, null, null, null, null, null, new PaymentMethod.BillingDetails(new Address(null, null, null, null, "2222", null), null, null, null), null,null);
            Log.i("parameters", String.valueOf(params));
            if (params != null) {
                ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams
                        .createWithPaymentMethodCreateParams(params, paymentIntentClientSecret);
                stripe.confirmPayment(this, confirmParams);
            }
        });
    }
    private void displayAlert(@NonNull String title,
                              @Nullable String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message);

        builder.setPositiveButton("Ok", null);
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Handle the result of stripe.confirmPayment
        stripe.onPaymentResult(requestCode, data, new PaymentResultCallback(this));
    }

    private void onPaymentSuccess(@NonNull final Response response) throws IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> responseMap = gson.fromJson(
                Objects.requireNonNull(response.body()).string(),
                type
        );
        Log.i("res12", String.valueOf(responseMap));

        paymentIntentClientSecret = responseMap.get("clientSecret");
    }

    private static final class PayCallback implements Callback {
        @NonNull private final WeakReference<PaymentActivity> activityRef;

        PayCallback(@NonNull PaymentActivity activity) {
            activityRef = new WeakReference<>(activity);
        }

        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            final PaymentActivity activity = activityRef.get();
            if (activity == null) {
                return;
            }
            Log.i("onFaliure",e.toString());


//                    Toast.makeText(
//                            activity, "Error: " + e.toString(), Toast.LENGTH_LONG
//                    ).show();

        }

        @Override
        public void onResponse(@NonNull Call call, @NonNull final Response response)
                throws IOException {
            final PaymentActivity activity = activityRef.get();
            if (activity == null) {
                return;
            }

            if (!response.isSuccessful()) {
                Log.i("onFaliure",response.toString());

//                        Toast.makeText(
//                                activity, "Error: " + response.toString(), Toast.LENGTH_LONG
//                        ).show()
                ;
            } else {
                activity.onPaymentSuccess(response);
            }
        }
    }

    private static final class PaymentResultCallback
            implements ApiResultCallback<PaymentIntentResult> {
        @NonNull private final WeakReference<PaymentActivity> activityRef;

        PaymentResultCallback(@NonNull PaymentActivity activity) {
            activityRef = new WeakReference<>(activity);
        }

        @Override
        public void onSuccess(@NonNull PaymentIntentResult result) {
            final PaymentActivity activity = activityRef.get();
            if (activity == null) {
                return;
            }

            PaymentIntent paymentIntent = result.getIntent();
            PaymentIntent.Status status = paymentIntent.getStatus();
            if (status == PaymentIntent.Status.Succeeded) {
                // Payment completed successfully
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                activity.displayAlert(
                        "Payment completed",
                        gson.toJson(paymentIntent)
                );
            } else if (status == PaymentIntent.Status.RequiresPaymentMethod) {
                // Payment failed – allow retrying using a different payment method
                activity.displayAlert(
                        "Payment failed",
                        Objects.requireNonNull(paymentIntent.getLastPaymentError()).getMessage()
                );
            }
        }

        @Override
        public void onError(@NonNull Exception e) {
            final PaymentActivity activity = activityRef.get();
            if (activity == null) {
                return;
            }

            // Payment request failed – allow retrying using the same payment method
            activity.displayAlert("Error", e.toString());
        }
    }
}