package com.example.academic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

enum ProviderType{
    BASIC
}
public class MainActivity extends AppCompatActivity {

    TextView t1;
    TextView t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle parametros = this.getIntent().getExtras();
        t1= findViewById(R.id.textView);
        t2= findViewById(R.id.textView2);
        if(parametros !=null){
            String email= parametros.getString("email");
            String provider= parametros.getString("provider");
            t1.setText(email);
            t2.setText(provider);
        }

    }
    public  void LogOut(View view){
        FirebaseAuth.getInstance().signOut();
        Intent nuevo = new Intent(this,LoginActivity.class);
        startActivity(nuevo);
        finish();

    }

}