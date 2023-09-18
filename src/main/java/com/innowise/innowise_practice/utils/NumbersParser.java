package com.innowise.innowise_practice.utils;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class NumbersParser {
    public static Number parseNumber(String textValue) {
        Number number = null;
        DecimalFormat decimalFormat = new DecimalFormat("#,##00;(#,##00)");
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(',');
        decimalFormatSymbols.setGroupingSeparator(' ');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        try {
            number = decimalFormat.parse(textValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return number;
    }

    public static Double parseDouble(String textValue) {
        return parseNumber(textValue).doubleValue();
    }
}
