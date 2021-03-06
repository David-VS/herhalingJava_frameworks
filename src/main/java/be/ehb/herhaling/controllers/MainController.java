package be.ehb.herhaling.controllers;

import be.ehb.herhaling.dao.AppointmentDAO;
import be.ehb.herhaling.dao.PatientDAO;
import be.ehb.herhaling.entities.Appointment;
import be.ehb.herhaling.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class MainController {

    private final PatientDAO patientDAO;
    private final AppointmentDAO appointmentDAO;

    @Autowired
    public MainController(PatientDAO patientDAO, AppointmentDAO appointmentDAO) {
        this.patientDAO = patientDAO;
        this.appointmentDAO = appointmentDAO;
    }

    @GetMapping("/patient/all")
    @ResponseBody
    public Iterable<Patient> allPatients(){
        return patientDAO.findAll();
    }

    @RequestMapping(value = "/patient/new", method = RequestMethod.POST)
    @ResponseBody
    public void newPatient(@RequestParam(name = "rnr")long rnr,
                          @RequestParam(name = "firstName") String firstName,
                          @RequestParam(name = "lastName") String lastname,
                          @RequestParam(name = "dateOfBirth") String dateOfBirth){

        LocalDate dob = LocalDate.parse(dateOfBirth);
        Patient newPatient = new Patient(rnr, firstName, lastname, dob);
        patientDAO.save(newPatient);
    }

    @RequestMapping(value = "/appointment/new", method = RequestMethod.POST)
    @ResponseBody
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
    @ResponseBody
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
    @ResponseBody
    public void deleteAppointment(@RequestParam(name = "id") int id){
        Optional<Appointment> optionalAppointment = appointmentDAO.findById(id);

        //of lambda i.d.p.v. method reference
        //appointment -> appointmentDAO.delete(appointment)
        optionalAppointment.ifPresent(appointmentDAO::delete);
    }
}
