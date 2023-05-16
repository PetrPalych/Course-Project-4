package kg.ash.hospital.dao.repositories.appointments;

import kg.ash.hospital.entities.appointments.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {}
