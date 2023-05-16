package kg.ash.hospital.services.interfaces;

import kg.ash.hospital.entities.patients.Patient;

import java.util.List;

public interface PatientService extends BaseService<Patient> {

    List<Patient> findAllOrderByIdDesc(int limit, boolean active);

    List<Patient> findByFullName(String fullName);

}
