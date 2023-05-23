package kg.ash.hospital.services.interfaces.appointment;

import kg.ash.hospital.entities.appointment.Appointment;
import kg.ash.hospital.services.interfaces.BaseService;

import java.util.List;

public interface AppointmentService extends BaseService<Appointment> {

    List<Appointment> findAllOrderByIdDesk(int limit);

    List<Appointment> findByCurrentDoctor();

    List<Appointment> findByPatientId(int patientId);

}
