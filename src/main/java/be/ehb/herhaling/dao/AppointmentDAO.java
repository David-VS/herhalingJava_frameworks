package be.ehb.herhaling.dao;

import be.ehb.herhaling.entities.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentDAO extends CrudRepository<Appointment, Integer> {
}
