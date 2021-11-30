package com.example.academic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class Activity_Notas extends AppCompatActivity {
    private DocumentReference db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        LottieAnimationView imagen =findViewById(R.id.history);
        LottieAnimationView imagen1 =findViewById(R.id.geografia);
        LottieAnimationView imagen2 =findViewById(R.id.musica);
        LottieAnimationView imagen3 =findViewById(R.id.mate);
        LottieAnimationView imagen4 =findViewById(R.id.art);
        LottieAnimationView imagen5 =findViewById(R.id.idiomas);
        LottieAnimationView imagen6 =findViewById(R.id.ciencia);
        LottieAnimationView imagen7 =findViewById(R.id.efisica);
        likeAnimation(imagen,R.raw.history);
        likeAnimation(imagen1,R.raw.globe);
        likeAnimation(imagen2,R.raw.music);
        likeAnimation(imagen3,R.raw.math);
        likeAnimation(imagen4,R.raw.frame);
        likeAnimation(imagen5,R.raw.language);
        likeAnimation(imagen6,R.raw.science1);
        likeAnimation(imagen7,R.raw.work);
    }
    private void likeAnimation(LottieAnimationView imagenView, int animation){

        imagenView.setAnimation(animation);
        imagenView.playAnimation();

    }
    public void guardar(View view){
        CheckBox historia = findViewById(R.id.checkhistory);
        CheckBox geografia = findViewById(R.id.checkgeo);
        CheckBox musica = findViewById(R.id.checkmusica);
        CheckBox mate = findViewById(R.id.checkMath);
        CheckBox arte = findViewById(R.id.checkArt);
        CheckBox idioma = findViewById(R.id.checkIdms);
        CheckBox ciencia = findViewById(R.id.checkCien);
        CheckBox Efisica = findViewById(R.id.checkEfisica);

        if (historia.isChecked()){ agrearmateria("historia"); }
        if (geografia.isChecked()){ agrearmateria("geografia"); }
        if (musica.isChecked()){ agrearmateria("musica"); }
        if (mate.isChecked()){ agrearmateria("matematicas"); }
        if (arte.isChecked()){ agrearmateria("arte"); }
        if (idioma.isChecked()){ agrearmateria("idioma"); }
        if (ciencia.isChecked()){ agrearmateria("ciencia"); }
        if (Efisica.isChecked()){ agrearmateria("educacion fisica"); }
        Toast toast1 = Toast.makeText(getApplicationContext(), "Proceso exitoso", Toast.LENGTH_LONG);
        toast1.show();

    }
    private void agrearmateria(String materia){
    Map<String, Object> notas = new HashMap<>();
    SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
    String email= sharedPref.getString("email",null);
    db= FirebaseFirestore.getInstance().collection("usuarios").document(email);
    db.collection("materia").document(materia).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            if (task.isSuccessful()) {
                try {
                    DocumentSnapshot document= task.getResult();
                    document.getData().toString();
                }
                catch(Exception e) {
                    db.collection("materia").document(materia).set(notas);
                }



            }

        }
    });
    }

}