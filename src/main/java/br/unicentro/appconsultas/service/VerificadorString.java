package br.unicentro.appconsultas.service;

public class VerificadorString {
    public boolean numerico(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)<'0'||s.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }

    public boolean naoNumerico(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)>='0'&&s.charAt(i)<='9'){
                return false;
            }
        }
        return true;
    }
}
