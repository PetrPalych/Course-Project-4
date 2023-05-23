package kg.ash.hospital.services.interfaces.patient;

import kg.ash.hospital.entities.patient.Patient;
import kg.ash.hospital.services.interfaces.BaseService;

import java.util.List;

public interface PatientService extends BaseService<Patient> {

    List<Patient> findAllOrderByIdDesc(int limit, boolean active);

    List<Patient> findByFullName(String fullName);

}
