package com.techelevator.model;

import java.time.LocalDate;

public class Schedule {
    private int id;
    private int potholeId;
    private LocalDate dateCreated;
    private LocalDate dateInspected;
    private LocalDate dateRepaired;
    private String status;
    private int severity;

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPotholeId() {
        return potholeId;
    }

    public void setPotholeId(int potholeId) {
        this.potholeId = potholeId;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateInspected() {
        return dateInspected;
    }

    public void setDateInspected(LocalDate dateInspected) {
        this.dateInspected = dateInspected;
    }

    public LocalDate getDateRepaired() {
        return dateRepaired;
    }

    public void setDateRepaired(LocalDate dateRepaired) {
        this.dateRepaired = dateRepaired;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
