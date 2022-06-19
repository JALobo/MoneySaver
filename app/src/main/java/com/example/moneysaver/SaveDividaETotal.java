package com.example.moneysaver;

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
}
