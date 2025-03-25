package com.br.foodconnect.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

    public static double arredondarParaDuasCasasDecimais(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
