package com.example.academic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        private void likeAnimation(LottieAnimationView imagenView, int animation){

        imagenView.setAnimation(animation);
        imagenView.playAnimation();

    }

    private void paso(){
        Toast toast1 = Toast.makeText(getApplicationContext(), "pass"+pass.getText(), Toast.LENGTH_LONG);

        toast1.show();
        Intent nuevo = new Intent(this, Pantalla_Principal_Activity.class);
        ProviderType provider = ProviderType.BASIC;
        nuevo.putExtra("email",  email.getText().toString());
        nuevo.putExtra("provider",provider.name());
        startActivity(nuevo);


    }








}