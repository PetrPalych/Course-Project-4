package kg.ash.hospital.services.interfaces;

import kg.ash.hospital.entities.doctors.Doctor;
import kg.ash.hospital.entities.doctors.DutySchedule;

import java.util.List;

public interface DutyScheduleService extends BaseService<DutySchedule> {

    List<DutySchedule> findDutyScheduleByDoctorId(int doctorId);

    Doctor findDoctorById(int doctorId);

}
