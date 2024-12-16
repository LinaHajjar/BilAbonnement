package com.example.bilabonnement.Model;
import java.text.DateFormatSymbols;
public class MonthlyIncome {
    int måned;
    double indtjening;

    public MonthlyIncome() {
    }

    public int getMåned() {
        return måned;
    }

    public void setMåned(int måned) {
        this.måned = måned;
    }

    public double getIndtjening() {
        return indtjening;
    }

    public void setIndtjening(double indtjening) {
        this.indtjening = indtjening;
    }

    public String getMonthName() {
        return new DateFormatSymbols().getMonths()[måned - 1];
    }
}

