package kg.ash.hospital.services.interfaces;

import kg.ash.hospital.entities.appointments.Treatment;

import java.util.List;

public interface TreatmentService extends BaseService<Treatment> {

    List<Treatment> findByPatientId(int patientId);
}
