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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.santalu.maskara.widget.MaskEditText;

public class login extends AppCompatActivity {

    TextInputLayout cpf, senha;
    MaskEditText etCPF;
    EditText etSenha;
    TextView naoPossuiConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        naoPossuiConta = findViewById(R.id.naoPossuiConta_login);

        String texto = "Não possui conta? Cadastre-se aqui";
        SpannableString ss = new SpannableString(texto);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                //quando o texto do span é clicado
                irParaCadastro();
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
        ss.setSpan(clickableSpan, 18, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //aplica o span no TextView naoPossuiConta
        naoPossuiConta.setText(ss);
        //permite a leitura do click
        naoPossuiConta.setMovementMethod(LinkMovementMethod.getInstance());

        cpf = findViewById(R.id.cpfContainer_login);
        senha = findViewById(R.id.senhaContainer_login);
        etCPF = findViewById(R.id.etMaskCPF_login);
        etSenha = findViewById(R.id.etSenha_login);

        //validando cpf
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

        //validando senha
        etSenha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                validacoesCadastro validar = new validacoesCadastro();
                String mensagem = validar.validarSenha(charSequence.toString());

                if(mensagem == null)
                    senha.setError(null);
                else
                    senha.setError(mensagem);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void irParaCadastro(){
        Intent intent = new Intent(this, cadastroParte1.class);
        startActivity(intent);
    }
}