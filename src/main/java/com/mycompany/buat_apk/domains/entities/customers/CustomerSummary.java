package com.mycompany.buat_apk.domains.entities.customers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CustomerSummary {

    private static final DateTimeFormatter DATE_FORMATTER =
        DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.of("id", "ID"));

    private static final DateTimeFormatter DATETIME_FORMATTER =
        DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm", Locale.of("id", "ID"));

    private final Long id;
    private final String name;
    private final String subdistrict;
    private final LocalDate dob;
    private final LocalDateTime joinedAt;

    public CustomerSummary(Long id, String name, String subdistrict, LocalDate dob, LocalDateTime joinedAt) {
        this.id = id;
        this.name = name;
        this.subdistrict = subdistrict;
        this.dob = dob;
        this.joinedAt = joinedAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubdistrict() {
        return subdistrict;
    }

    public LocalDate getDob() {
        return dob;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public String getDobLabel() {
        int years = Period.between(dob, LocalDate.now()).getYears();
        return DATE_FORMATTER.format(dob) + " (" + years + " tahun)";
    }

    public String getJoinedLabel() {
        return DATETIME_FORMATTER.format(joinedAt);
    }
}