package be.ehb.herhaling.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient {

    @Id
    private long rnr;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments = new ArrayList<>();

    public Patient() {
    }

    public Patient(long rnr, String firstName, String lastName, LocalDate dateOfBirth) {
        this.rnr = rnr;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public long getRnr() {
        return rnr;
    }

    public void setRnr(long rnr) {
        this.rnr = rnr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
