package kg.ash.hospital.services.interfaces.doctor;

import kg.ash.hospital.entities.doctor.Doctor;
import kg.ash.hospital.entities.doctor.DutySchedule;
import kg.ash.hospital.services.interfaces.BaseService;

import java.util.List;

public interface DutyScheduleService extends BaseService<DutySchedule> {

    List<DutySchedule> findDutyScheduleByDoctorId(int doctorId);

    Doctor findByDoctorId(int doctorId);

}
