package daniel.brian.xpressapp.payments;

import static daniel.brian.xpressapp.payments.utils.Constants.BUSINESS_SHORT_CODE;
import static daniel.brian.xpressapp.payments.utils.Constants.CALLBACKURL;
import static daniel.brian.xpressapp.payments.utils.Constants.PARTYB;
import static daniel.brian.xpressapp.payments.utils.Constants.PASSKEY;
import static daniel.brian.xpressapp.payments.utils.Constants.TRANSACTION_TYPE;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import daniel.brian.xpressapp.auth.MainActivity;
import daniel.brian.xpressapp.databinding.ActivityMpesaPaymentBinding;
import daniel.brian.xpressapp.payments.model.AccessToken;
import daniel.brian.xpressapp.payments.model.STKPush;
import daniel.brian.xpressapp.payments.services.DarajaApiClient;
import daniel.brian.xpressapp.payments.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MpesaPaymentActivity extends AppCompatActivity {
    ActivityMpesaPaymentBinding binding;
    private DarajaApiClient mApiClient;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMpesaPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        int totalPrice = intent.getIntExtra("price",0);
        String phoneNumber = intent.getStringExtra("phone");

        progressDialog = new ProgressDialog(this);
        mApiClient = new DarajaApiClient();
        mApiClient.setIsDebug(true);

        getAccessToken();

        binding.btnPayCash.setOnClickListener(v -> {
            onBackPressed();
            Snackbar.make(this.getCurrentFocus(),"Thank you for Trusting our Services",Snackbar.LENGTH_SHORT).show();
        });

        binding.btnPay.setOnClickListener(v -> {
            performSTKPush(String.valueOf(totalPrice),phoneNumber);
        });
    }

    private void performSTKPush(String amount, String phoneNumber) {
        progressDialog.setMessage("Processing your request");
        progressDialog.setTitle("Please Wait..");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        String timestamp = Utils.getTimestamp();
        STKPush stkPush = new STKPush(
                BUSINESS_SHORT_CODE,
                Utils.getPassword(BUSINESS_SHORT_CODE,PASSKEY, timestamp),
                timestamp,
                TRANSACTION_TYPE,
                amount,
                Utils.validatePhoneNumber(phoneNumber),
                PARTYB,
                Utils.validatePhoneNumber(phoneNumber),
                CALLBACKURL,
                "AutoExpress",
                "Payment"
        );

        mApiClient.setGetAccessToken(false);

        mApiClient.mpesaService().sendPush(stkPush).enqueue(new Callback<STKPush>() {
            @Override
            public void onResponse(@NonNull Call<STKPush> call, @NonNull Response<STKPush> response) {
                progressDialog.dismiss();
                try{
                    if(response.isSuccessful()){
                        Timber.d("Post Submitted to API. %s",response.body());
                    }else{
                        assert response.errorBody() != null;
                        Timber.e("Response %s",response.errorBody().string());
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<STKPush> call, Throwable t) {

            }
        });
    }

    private void getAccessToken() {
        mApiClient.setGetAccessToken(true);

        mApiClient.mpesaService().getAccessToken().enqueue(new Callback<AccessToken>(){
            @Override
            public void onResponse(@NonNull Call<AccessToken> call, @NonNull Response<AccessToken> response) {
                if(response.isSuccessful()){
                    assert response.body() != null;
                    mApiClient.setAuthToken(response.body().accessToken);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AccessToken> call, Throwable t) {
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }
}