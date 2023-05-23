package kg.ash.hospital.services.interfaces.doctor;

import kg.ash.hospital.entities.doctor.Doctor;
import kg.ash.hospital.entities.doctor.WorkSchedule;
import kg.ash.hospital.services.interfaces.BaseService;

import java.util.List;

public interface WorkScheduleService extends BaseService<WorkSchedule> {

    List<WorkSchedule> findWorkScheduleByDoctorId(int doctorId);

    Doctor findDoctorById(int doctorId);

}
