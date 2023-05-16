package kg.ash.hospital.services.interfaces;

import kg.ash.hospital.entities.doctors.Doctor;

import java.util.List;

public interface DoctorService extends BaseService<Doctor>{

    List<Doctor> findAllOrderByIdDesc(int limit, boolean active);

    List<Doctor> findByFullName(String fullName);

}
