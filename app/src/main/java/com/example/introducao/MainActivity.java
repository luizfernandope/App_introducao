package com.example.introducao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<Integer> imagens = new ArrayList<>();
    FotosIntroducaoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = findViewById(R.id.viewPager_introducao);
        imagens.add(R.drawable.intro1);
        imagens.add(R.drawable.intro2);
        imagens.add(R.drawable.intro3);

        adapter = new FotosIntroducaoAdapter(this, imagens);
        viewPager.setAdapter(adapter);


    }

    public void proximo_introducao(View view){
        int position = viewPager.getCurrentItem();

        if(position<2)
        {
            viewPager.setCurrentItem(position+1, true);
        }
        else
        {
            Intent intent = new Intent(this, iniciar.class);
            startActivity(intent);
        }
    }
}