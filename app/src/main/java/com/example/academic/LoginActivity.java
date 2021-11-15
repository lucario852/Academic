package com.example.academic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity  {
    private EditText email;
    private EditText pass;
    private FirebaseAuth mAuth;
    private int Google_Sign_In=100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();



    }
    @Override
    protected void onStart() {
        super.onStart();
        LinearLayout  linea = findViewById(R.id.authlayaut);
        linea.setVisibility(View.VISIBLE);
        sesion();



    }


    @Override
    protected void onResume() {
        super.onResume();
        LottieAnimationView imagen =findViewById(R.id.imageView);
        likeAnimation(imagen,R.raw.signin);


    }
    public void Registro(View view){

        email= findViewById(R.id.email);
        pass=findViewById(R.id.pass);

        if(email.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
            Toast toast1 = Toast.makeText(getApplicationContext(), "Rellene todos los datos", Toast.LENGTH_LONG);

            toast1.show();

        } else{
            if(pass.getText().length()>=6){
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        paso();
                    } else {
                        new AlertDialog.Builder(LoginActivity.this,R.style.Base_Theme_AppCompat_Dialog_Alert)
                                .setTitle("Error en registro")
                                .setMessage("Ha ocurriodo un errror con la autentificacion, la cuenta ya se encuentra registrada.")
                                .setPositiveButton("Aceptar",null)
                                .create()
                                .show();


                    }
                }
            });
            } else {

                Toast toast1 = Toast.makeText(getApplicationContext(), "La contraseña debe tener mas de 6 caracteres.", Toast.LENGTH_LONG);

                toast1.show();
            }

        }
    }
    public void Login(View view){

        email= findViewById(R.id.email);
        pass=findViewById(R.id.pass);

        if(email.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
            Toast toast1 = Toast.makeText(getApplicationContext(), "Rellene todos los datos", Toast.LENGTH_LONG);

            toast1.show();

        } else{
            if(pass.getText().length()>=6){
                mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            paso();
                        } else {
                            new AlertDialog.Builder(LoginActivity.this,R.style.Base_Theme_AppCompat_Dialog_Alert)
                                    .setTitle("Error en el inicio de sesion")
                                    .setMessage("Ha ocurriodo un errror con la autentificacion, la cuenta no se encuentra registrada o los datos son incorrectos.")
                                    .setPositiveButton("Aceptar",null)
                                    .create()
                                    .show();


                        }
                    }
                });
            } else {

                Toast toast1 = Toast.makeText(getApplicationContext(), "La contraseña debe tener mas de 6 caracteres.", Toast.LENGTH_LONG);

                toast1.show();
            }

        }
    }
    public void Login_google(View view){

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

       GoogleSignInClient google = GoogleSignIn.getClient(this,gso);
       startActivityForResult(google.getSignInIntent(),Google_Sign_In);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== Google_Sign_In) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if(account != null){
                    AuthCredential account1 = GoogleAuthProvider.getCredential(account.getIdToken(),null);
                mAuth.signInWithCredential(account1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                paso(account.getEmail(),ProviderType.GOOGLE);
                            } else {
                                new AlertDialog.Builder(LoginActivity.this,R.style.Base_Theme_AppCompat_Dialog_Alert)
                                        .setTitle("Error en el inicio de sesion")
                                        .setMessage("Ha ocurriodo un errror con la autentificacion ")
                                        .setPositiveButton("Aceptar",null)
                                        .create()
                                        .show();


                            }
                        }
                    });
                }

            } catch (ApiException e) {

                Toast toast1 = Toast.makeText(getApplicationContext(), "Error: "+e, Toast.LENGTH_LONG);

                toast1.show();

            }

        }
    }

    private void likeAnimation(LottieAnimationView imagenView, int animation){

        imagenView.setAnimation(animation);
        imagenView.playAnimation();

    }
    private void sesion(){
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
        String email= sharedPref.getString("email",null);
        String provider= sharedPref.getString("provider",null);
        if(email!=null && provider !=null){
            LinearLayout  linea = findViewById(R.id.authlayaut);
            linea.setVisibility(View.INVISIBLE);
            paso(email,ProviderType.valueOf(provider));

        }
    }

    private void paso(){
        Toast toast1 = Toast.makeText(getApplicationContext(), "Autentificacion exitosa", Toast.LENGTH_LONG);

        toast1.show();
        Intent nuevo = new Intent(this, Pantalla_Principal_Activity.class);nuevo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ProviderType provider = ProviderType.BASIC;
        nuevo.putExtra("email",  email.getText().toString());
        nuevo.putExtra("provider",provider.name());
        startActivity(nuevo);


    }
    public void  paso( String e, ProviderType p){

        Intent nuevo = new Intent(this, Pantalla_Principal_Activity.class);nuevo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        nuevo.putExtra("email",  e);
        nuevo.putExtra("provider",p.name());
        startActivity(nuevo);


    }








}