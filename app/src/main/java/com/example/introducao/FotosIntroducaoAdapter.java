package com.example.introducao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FotosIntroducaoAdapter extends PagerAdapter {

    Context context;
    ArrayList<Integer> fotos;
    int[] titulos = {R.string.titulo1_introducao, R.string.titulo2_introducao, R.string.titulo3_introducao};

    public FotosIntroducaoAdapter(Context context, ArrayList<Integer>imagens){
        this.context = context;
        this.fotos = imagens;
    }


    @Override
    public int getCount() {
        return fotos.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_slides_introducao, null);
        ImageView imageView = view.findViewById(R.id.imagens_introducao);

        TextView titulo = view.findViewById(R.id.titulo_introducao);
        Button btnNavegacao = view.findViewById(R.id.btnProximo_introducao);
        TextView pularIntro = view.findViewById(R.id.pular_introducao);
        if(position == 2){
            btnNavegacao.setBackgroundResource(R.drawable.btn_iniciar);
            pularIntro.setVisibility(View.INVISIBLE);
        }
        titulo.setText(titulos[position]);
        Glide.with(context).asBitmap().load(fotos.get(position)).into(imageView);

        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
