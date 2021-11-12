package com.example.academic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

enum ProviderType{
    BASIC
}
public class Pantalla_Principal_Activity extends AppCompatActivity implements View.OnClickListener {

    public CardView notas,estudiante,curso,ajustes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

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
        FirebaseAuth.getInstance().signOut();
        Intent nuevo = new Intent(this,LoginActivity.class);
        startActivity(nuevo);


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