package kg.ash.hospital.services.interfaces.appointment;

import kg.ash.hospital.entities.appointment.Treatment;
import kg.ash.hospital.services.interfaces.BaseService;

import java.util.List;

public interface TreatmentService extends BaseService<Treatment> {

    List<Treatment> findByPatientId(int patientId);

}
