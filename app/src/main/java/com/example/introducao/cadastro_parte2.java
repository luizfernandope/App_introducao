package com.example.introducao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.santalu.maskara.widget.MaskEditText;

public class cadastro_parte2 extends AppCompatActivity {

    CheckBox termosEcondicoes;
    TextInputLayout cpf,telefone,email,dataNasci,senha;
    EditText etEmail, etSenha;
    MaskEditText etCPF, etTelefone, etDataNasci;
    String cpfTelaAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_parte2);

        termosEcondicoes = findViewById(R.id.checkBoxTermos_cadastro2);

        String texto = "eu aceito os Termos e Condições do app e Políticas de privacidade";
        SpannableString ss = new SpannableString(texto);
        ClickableSpan clickTermos = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                //quando o texto do span é clicado
                //irParaLogin();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                //permite estilizar o texto que recebe o span
                super.updateDrawState(ds);
                ds.setColor(Color.rgb(69,160,239));
                ds.setUnderlineText(false);
            }
        };
        ClickableSpan clickPoliticas = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                //quando o texto do span é clicado
                //irParaLogin();
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
        ss.setSpan(clickTermos, 13, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickPoliticas, 41, 65, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //aplica o span no TextView naoPossuiConta
        termosEcondicoes.setText(ss);
        //permite a leitura do click
        termosEcondicoes.setMovementMethod(LinkMovementMethod.getInstance());

        cpf = findViewById(R.id.cpfContainer_cadastro2);
        telefone = findViewById(R.id.telefoneContainer_cadastro2);
        email = findViewById(R.id.emailContainer_cadastro2);
        dataNasci = findViewById(R.id.dataNasciContainer_cadastro2);
        senha = findViewById(R.id.senhaContainer_cadastro2);

        etEmail = findViewById(R.id.etMaskEmail_cadastro2);
        etSenha = findViewById(R.id.etSenha_cadastro2);
        etCPF = findViewById(R.id.etMaskCPF_cadastro2);
        etTelefone = findViewById(R.id.etMaskTelefone_cadastro2);
        etDataNasci = findViewById(R.id.etMaskDataNasci_cadastro2);

        //Colocando o cpf automaticamente
        cpfTelaAnterior = getIntent().getStringExtra("cpf");
        etCPF.setText(cpfTelaAnterior);

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

        //validando email
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validacoesCadastro validar = new validacoesCadastro();
                String mensagem = validar.validarEmail(charSequence.toString());
                if(mensagem==null)
                    email.setError(null);
                else
                    email.setError(mensagem);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //validando dataNasimento
        etDataNasci.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(etDataNasci.getUnMasked().length()==8)
                {
                    validacoesCadastro validar = new validacoesCadastro();
                    String mensagem = validar.validarDataNasi(etDataNasci.getUnMasked());
                    if(mensagem == null)
                        dataNasci.setError(null);
                    else
                        dataNasci.setError(mensagem);
                }
                else
                    dataNasci.setError(null);
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
}