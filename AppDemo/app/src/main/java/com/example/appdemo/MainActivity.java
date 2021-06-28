package com.example.appdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    EditText enternumber;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enternumber = findViewById(R.id.username);
        progressBar = findViewById(R.id.progress_circular);

    }

    public void doLogin(View view) {

        if(!enternumber.getText().toString().trim().isEmpty()){
            if((enternumber.getText().toString().trim()).length() == 10){
                progressBar.setVisibility(View.VISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+27" + enternumber.getText().toString(),
                        60, TimeUnit.SECONDS,
                        MainActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);

                            }

                            @Override
                            public void onCodeSent(@NonNull @NotNull String s, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(MainActivity.this, AppDemoScreen2.class);
                                intent.putExtra("otpcode",s);
                                intent.putExtra("number",enternumber.getText().toString());
                                startActivity(intent);
                                super.onCodeSent(s, forceResendingToken);
                            }
                        }
                );
            }

        }


    }
}