package kg.ash.hospital.dao.repositories.appointments;

import kg.ash.hospital.entities.appointments.Treatment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {}
