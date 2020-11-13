package be.ehb.herhaling.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties("patient")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime timestamp;
    @ManyToOne
    @JoinColumn(name = "rnr", nullable = false)
    private Patient patient;

    public Appointment() {
    }

    public Appointment(LocalDateTime timestamp, Patient patient) {
        this.timestamp = timestamp;
        this.patient = patient;
    }

    public Appointment(int id, LocalDateTime timestamp, Patient patient) {
        this.id = id;
        this.timestamp = timestamp;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
