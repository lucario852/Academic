package com.example.academic;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.academic.databinding.ActivityNavegationDrawableBinding;
import com.google.firebase.auth.FirebaseAuth;
enum ProviderType{
    BASIC,
    GOOGLE
}

public class NavegationDrawable extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavegationDrawableBinding binding;
    public CardView notas,estudiante,curso,ajustes;




    String email;
    String provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavegationDrawableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavegationDrawable.toolbar);
        binding.appBarNavegationDrawable.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                TextView t = findViewById(R.id.correo);


                datos();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();





        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navegation_drawable);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        Bundle parametros = this.getIntent().getExtras();


        if(parametros !=null){
            email = parametros.getString("email");
            provider = parametros.getString("provider");

            //core.setText(email);
        }
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("email",email);
        editor.putString("provider",provider);
        editor.apply();

        notas = (CardView) findViewById(R.id.n);
        estudiante = (CardView) findViewById(R.id.e);
        curso = (CardView) findViewById(R.id.c);
        ajustes = (CardView) findViewById(R.id.a);


    }
    public void notas(View view){
        Intent nuevo = new Intent(NavegationDrawable.this,Activity_Notas.class);
        nuevo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(nuevo);
        finish();
    }

    public void logout(View view){
        new AlertDialog.Builder(this)

                .setMessage("¿Desea cerrar sesion en academic?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        SharedPreferences sharedPref = NavegationDrawable.this.getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.clear();
                        editor.apply();
                        FirebaseAuth.getInstance().signOut();
                        Intent nuevo = new Intent(NavegationDrawable.this,LoginActivity.class);
                        nuevo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(nuevo);
                    }
                })
                .setNegativeButton("Cancelar",null)
                .create()
                .show();




    }
    public void datos(){
        Bundle parametros = this.getIntent().getExtras();
        TextView core= findViewById(R.id.correo);
        if(parametros !=null){
            email = parametros.getString("email");
            provider = parametros.getString("provider");

            core.setText(email);
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navegation_drawable, menu);
        TextView t = findViewById(R.id.correo);
        t.setText(email);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== event.KEYCODE_BACK){
            new AlertDialog.Builder(this)

                    .setMessage("¿Desea salir de academic?")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancelar",null)
                    .create()
                    .show();

        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navegation_drawable);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}