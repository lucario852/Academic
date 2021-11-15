package com.example.academic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

enum ProviderType{
    BASIC,
    GOOGLE
}
public class Pantalla_Principal_Activity extends AppCompatActivity implements View.OnClickListener {

    public CardView notas,estudiante,curso,ajustes;

    String email;
    String provider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        Bundle parametros = this.getIntent().getExtras();
        TextView t1 =findViewById(R.id.bienvenido);
        if(parametros !=null){
            email = parametros.getString("email");
            provider = parametros.getString("provider");
            t1.setText(email);
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

        notas.setOnClickListener(this);
        estudiante.setOnClickListener(this);
        curso.setOnClickListener(this);
        ajustes.setOnClickListener(this);

    }
    public void logout(View view){
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
        FirebaseAuth.getInstance().signOut();
        Intent nuevo = new Intent(this,LoginActivity.class);
        nuevo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(nuevo);


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
    public void onClick(View v) {
        Intent i;

        switch (v.getId())
        {
            case R.id.n:
                i = new Intent(this,Activity_Notas.class);
                startActivity(i);
                break;

            case R.id.e:
                i = new Intent(this,Activity_Estudiante.class);
                startActivity(i);
                break;

            case R.id.c:
                i = new Intent(this,Activity_Curso.class);
                startActivity(i);
                break;

            case R.id.a:
                i = new Intent(this,Activity_Ajuste.class);
                startActivity(i);
                break;
        }
    }
}