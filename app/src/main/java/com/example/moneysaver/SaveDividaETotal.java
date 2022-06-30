package com.example.moneysaver;

import java.text.NumberFormat;
import java.util.Currency;

public class SaveDividaETotal {
    private static String divida = "0.0";
    private static String total = "0.0";

    public static String getDivida() {
        return divida;
    }

    public static void setDivida(String divida) {
        SaveDividaETotal.divida = divida;
    }

    public static String getTotal() {
        return total;
    }

    public static void setTotal(String total) {
        SaveDividaETotal.total = total;
    }

    public static String formatCurrency(Double valorAConverter){//TODO verificar se só vêm doubles
        Double temp = valorAConverter;

        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(2);
        format.setCurrency(Currency.getInstance("EUR"));


        return format.format(temp).toString();
    }

    public static String getTotalFormated(){
        return formatCurrency(Double.parseDouble(total));
    }
    public static String getDividaFormated(){
        return formatCurrency(Double.parseDouble(divida));
    }

    public static String getValorFinal(){
        Double valorFinal = Double.parseDouble(total) - Double.parseDouble(divida);
        return formatCurrency(valorFinal);
    }

}
