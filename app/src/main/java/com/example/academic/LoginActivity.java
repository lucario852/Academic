package com.example.academic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.Authenticator;

public class LoginActivity extends AppCompatActivity  {
    private EditText email;
    private EditText pass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();


    }

// ...
// Initialize Firebase Auth

    @Override
    protected void onResume() {
        super.onResume();
        LottieAnimationView imagen =findViewById(R.id.imageView);
        likeAnimation(imagen,R.raw.signin);

    }
    public void trasladar(View view){

        email= findViewById(R.id.email);
        pass=findViewById(R.id.pass);

        if(email.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
            Toast toast1 = Toast.makeText(getApplicationContext(), "Rellene todos los datos", Toast.LENGTH_LONG);

            toast1.show();

        } else{
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString());
             Toast toast1 = Toast.makeText(getApplicationContext(), "pass"+pass.getText(), Toast.LENGTH_LONG);

            toast1.show();
            Intent nuevo = new Intent(this,MainActivity.class);
            ProviderType provider = ProviderType.BASIC;
            nuevo.putExtra("email",  email.getText().toString());
            nuevo.putExtra("provider",provider.name());
            startActivity(nuevo);


        }
    }



        private void likeAnimation(LottieAnimationView imagenView, int animation){

        imagenView.setAnimation(animation);
        imagenView.playAnimation();

    }








}