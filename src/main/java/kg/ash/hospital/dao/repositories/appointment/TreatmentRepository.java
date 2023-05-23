package kg.ash.hospital.dao.repositories.appointment;

import kg.ash.hospital.entities.appointment.Treatment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {

    List<Treatment> findByPatientId(int patientId);

}
