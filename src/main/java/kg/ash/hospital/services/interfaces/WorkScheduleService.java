package kg.ash.hospital.services.interfaces;

import kg.ash.hospital.entities.doctors.Doctor;
import kg.ash.hospital.entities.doctors.WorkSchedule;

import java.util.List;

public interface WorkScheduleService extends BaseService<WorkSchedule> {

    List<WorkSchedule> findWorkScheduleByDoctorId(int doctorId);

    Doctor findDoctorById(int doctorId);

}
