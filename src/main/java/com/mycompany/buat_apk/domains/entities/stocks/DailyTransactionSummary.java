package com.mycompany.buat_apk.domains.entities.stocks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DailyTransactionSummary {
    private final LocalDate day;
    private final long moneyIn;
    private final long moneyOut;

    public DailyTransactionSummary(LocalDate day, long moneyIn, long moneyOut) {
        this.day = day;
        this.moneyIn = moneyIn;
        this.moneyOut = moneyOut;
    }

    public LocalDate getDay() {
        return day;
    }

    public long getMoneyIn() {
        return moneyIn;
    }

    public long getMoneyOut() {
        return moneyOut;
    }

    public long getNet() {
        return moneyIn - moneyOut;
    }

    public String getDayLabel() {
        return DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.of("id", "ID")).format(day);
    }
}
