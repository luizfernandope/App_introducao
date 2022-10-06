package com.example.introducao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.introducao.R;

public class iniciar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);
    }

    public void irParaLogin(View v){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
    public void irParaCadastro(View v){
        Intent intent = new Intent(this, cadastroParte1.class);
        startActivity(intent);
    }
}