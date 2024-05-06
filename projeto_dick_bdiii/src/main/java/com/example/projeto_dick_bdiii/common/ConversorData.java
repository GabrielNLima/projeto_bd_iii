package com.example.projeto_dick_bdiii.common;

public class ConversorData {
    public static String converterDateParaDataHora(Date data){
        SimpleDateFormat formatador = new SimpleDateFormat(pattern:"dd/MM/YYYY HH:mm:ss");
        return formatador.format(data);
    }
}
