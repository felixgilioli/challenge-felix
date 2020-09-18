package br.com.senior.server.checkin;

import java.time.LocalDateTime;
import java.util.Date;

import static com.felix.common.date.Dates.toLocalDateTime;

public class CheckinDTO {

    private Number id;
    private String name;
    private String document;
    private Number amount;
    private Date entry;
    private Boolean vehicle;

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Number getAmount() {
        return amount;
    }

    public void setAmount(Number amount) {
        this.amount = amount;
    }

    public LocalDateTime getEntry() {
        return toLocalDateTime(entry);
    }

    public void setEntry(Date entry) {
        this.entry = entry;
    }

    public Boolean getVehicle() {
        return vehicle;
    }

    public void setVehicle(Boolean vehicle) {
        this.vehicle = vehicle;
    }
}
