package be.ehb.herhaling.dao;

import be.ehb.herhaling.entities.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientDAO extends CrudRepository<Patient, Long> {

}
