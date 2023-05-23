package kg.ash.hospital.services.interfaces.doctor;

import kg.ash.hospital.entities.doctor.Doctor;
import kg.ash.hospital.services.interfaces.BaseService;

import java.util.List;

public interface DoctorService extends BaseService<Doctor> {

    List<Doctor> findAllOrderByIdDesc(int limit, boolean active);

    List<Doctor> findByFullName(String fullName);

}
