package br.com.senior.server.checkin;

import br.com.senior.server.converter.BooleanNotNullConverter;
import br.com.senior.server.person.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "CHECKIN")
public class Checkin implements Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ENTRY_TIME", nullable = false)
    private LocalDateTime entryTime;

    @Column(name = "DEPARTURE_TIME")
    private LocalDateTime departureTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person person;

    @Convert(converter = BooleanNotNullConverter.class)
    @Column(name = "HAS_VEHICLE", nullable = false)
    private Boolean hasVehicle;

    @Column(name = "FINAL_VALUE")
    private BigDecimal finalValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checkin checkin = (Checkin) o;
        return Objects.equals(id, checkin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Boolean getHasVehicle() {
        return hasVehicle != null && hasVehicle;
    }

    public void setHasVehicle(Boolean hasVehicle) {
        this.hasVehicle = hasVehicle != null && hasVehicle;
    }

    public Long getPersonId() {
        return person != null ? person.getId() : null;
    }

    public BigDecimal getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(BigDecimal finalValue) {
        this.finalValue = finalValue;
    }
}
