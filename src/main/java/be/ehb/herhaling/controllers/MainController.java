package be.ehb.herhaling.controllers;

import be.ehb.herhaling.dao.AppointmentDAO;
import be.ehb.herhaling.dao.PatientDAO;
import be.ehb.herhaling.entities.Appointment;
import be.ehb.herhaling.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class MainController {

    private PatientDAO patientDAO;
    private AppointmentDAO appointmentDAO;

    @Autowired
    public MainController(PatientDAO patientDAO, AppointmentDAO appointmentDAO) {
        this.patientDAO = patientDAO;
        this.appointmentDAO = appointmentDAO;
    }

    @RequestMapping(value = "/patient/new", method = RequestMethod.POST)
    public void newPatient(@RequestParam(name = "rnr")long rnr,
                          @RequestParam(name = "firstName") String firstName,
                          @RequestParam(name = "lastName") String lastname,
                          @RequestParam(name = "dateOfBirth") String dateOfBirth){

        LocalDate dob = LocalDate.parse(dateOfBirth);
        Patient newPatient = new Patient(rnr, firstName, lastname, dob);
        patientDAO.save(newPatient);
    }

    @RequestMapping(value = "/appointment/new", method = RequestMethod.POST)
    public void newAppointment(@RequestParam(name = "rnr") long rnr,
                               @RequestParam(name="time") String timestamp){
        Optional<Patient> optionalPatient = patientDAO.findById(rnr);

        if(optionalPatient.isPresent()) {
            LocalDateTime when = LocalDateTime.parse(timestamp);
            Patient who = optionalPatient.get();

            Appointment newAppointment = new Appointment(when, who);
            appointmentDAO.save(newAppointment);
        }
    }

    @RequestMapping(value = "/appointment/update", method = RequestMethod.PUT)
    public void updateAppointment(@RequestParam(name = "id") int id,
                                  @RequestParam(name = "rnr") long rnr,
                                  @RequestParam(name="timestamp") String timestamp){
        Optional<Patient> optionalPatient = patientDAO.findById(rnr);

        if(optionalPatient.isPresent()) {
            LocalDateTime when = LocalDateTime.parse(timestamp);
            Patient who = optionalPatient.get();

            Appointment newAppointment = new Appointment(id, when, who);
            appointmentDAO.save(newAppointment);
        }
    }

    @RequestMapping(value = "/appointment/delete", method = RequestMethod.DELETE)
    public void deleteAppointment(@RequestParam(name = "id") int id){
        Optional<Appointment> optionalAppointment = appointmentDAO.findById(id);

        optionalAppointment.ifPresent(appointment -> appointmentDAO.delete(appointment));
    }
}
