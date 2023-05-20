package kg.ash.hospital.services.interfaces;

import kg.ash.hospital.entities.appointments.Appointment;

import java.util.List;

public interface AppointmentService extends BaseService<Appointment> {

    List<Appointment> findAllOrderByIdDesk(int limit);

    List<Appointment> findByPatientId(int patientId);
}
