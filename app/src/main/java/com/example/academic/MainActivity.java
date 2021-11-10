package com.example.academic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView notas,estudiante,curso,ajustes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notas = (CardView) findViewById(R.id.n);
        estudiante = (CardView) findViewById(R.id.e);
        curso = (CardView) findViewById(R.id.c);
        ajustes = (CardView) findViewById(R.id.a);

        notas.setOnClickListener(this);
        estudiante.setOnClickListener(this);
        curso.setOnClickListener(this);
        ajustes.setOnClickListener(this);

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