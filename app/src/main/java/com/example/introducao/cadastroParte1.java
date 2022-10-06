package com.example.introducao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.santalu.maskara.widget.MaskEditText;

public class cadastroParte1 extends AppCompatActivity {

    TextView jaPossuiConta;
    TextInputLayout cpf;
    MaskEditText etCPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_parte1);

        jaPossuiConta = findViewById(R.id.jaPossuiConta_cadastro1);

        String texto = "Já possui conta? Faça login aqui";
        SpannableString ss = new SpannableString(texto);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                //quando o texto do span é clicado
                irParaLogin();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                //permite estilizar o texto que recebe o span
                super.updateDrawState(ds);
                ds.setColor(Color.rgb(69,160,239));
                ds.setUnderlineText(false);
            }
        };
        //seta o span para a coordenada do texto desejado
        ss.setSpan(clickableSpan, 16, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //aplica o span no TextView naoPossuiConta
        jaPossuiConta.setText(ss);
        //permite a leitura do click
        jaPossuiConta.setMovementMethod(LinkMovementMethod.getInstance());


        cpf = findViewById(R.id.cpfContainer_cadastro1);
        etCPF = findViewById(R.id.etMaskCPF_cadastro1);

        etCPF.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(etCPF.getUnMasked().length()==11)
                {
                    ValidarCPF validacao = new ValidarCPF();
                    validacao.cpf(etCPF.getUnMasked());
                    boolean ehValido = validacao.isCPF();
                    if(ehValido==true)
                        cpf.setError(null);
                    else
                        cpf.setError("CPF invalido");
                }
                else
                    cpf.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void irParaLogin(){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
    public void irParaCadastro2(View v){
        if(etCPF.getUnMasked().length()==11 && cpf.getError()==null)
        {
            Intent intent = new Intent(this, cadastro_parte2.class);
            intent.putExtra("cpf", etCPF.getMasked());
            startActivity(intent);
        }
        else
            Toast.makeText(this, "cpf invalido", Toast.LENGTH_SHORT).show();
        
    }
}