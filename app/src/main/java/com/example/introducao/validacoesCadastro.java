package com.example.introducao;

public class validacoesCadastro {

    public String validarSenha(String senha){
        if(senha.length()<6){
            return ("minimo de 6 caracteres");
        }
        else if(!senha.matches(".*[A-Z].*")){
            return ("deve conter ao menos 1 letra maiuscula");
        }
        else if(!senha.matches(".*[a-z].*")){
            return ("deve conter ao menos 1 letra minuscula");
        }
        else if(senha.length()>16)
        {
            return ("maximo de 16 caracteres");
        }
        else if(!senha.matches(".*[@#/@?$%&+=~^.,*!].*"))
        {
            return ("deve conter ao menos 1 caracter especial");
        }
        else if(!senha.toString().matches(".*[0-9].*"))
        {
            return ("deve conter ao menos 1 número");
        }
        else{
            return (null);
        }
    }

    public String validarEmail(String email){
        if(!email.matches(".*[@].*"))
            return "email invalido";
        if(!email.matches(".*[.].*"))
            return "email invalido";
        else
            return null;
    }

    public String validarDataNasi(String data){
        try
        {
            Integer dia = Integer.parseInt(data.substring(0,2)) ;
            Integer mes = Integer.parseInt(data.substring(2,4)) ;
            Integer ano = Integer.parseInt(data.substring(4,8)) ;

            if(dia<1 || dia>31){
                return ("dia invalido");
            }
            else if(mes<1 || mes>12){
                return ("mes invalido");
            }
            else if(ano<1920 || ano>2020){
                return ("ano invalido");
            }
            else{
                return (null);
            }
        } catch (NumberFormatException e) {
            return "data inválida";
        }
    }

}
