package kg.ash.hospital.services.impl.doctor;

import kg.ash.hospital.dao.repositories.doctor.DutyScheduleRepository;
import kg.ash.hospital.entities.doctor.Doctor;
import kg.ash.hospital.entities.doctor.DutySchedule;
import kg.ash.hospital.services.interfaces.doctor.DoctorService;
import kg.ash.hospital.services.interfaces.doctor.DutyScheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DutyScheduleServiceImpl implements DutyScheduleService {

    private final DutyScheduleRepository dutyScheduleRepository;

    private final DoctorService doctorService;

    @Autowired
    public DutyScheduleServiceImpl(DutyScheduleRepository dutyScheduleRepository, DoctorService doctorService) {
        this.dutyScheduleRepository = dutyScheduleRepository;
        this.doctorService = doctorService;
    }

    @Override
    public DutySchedule save(DutySchedule dutySchedule) {
        return dutyScheduleRepository.save(dutySchedule);
    }

    @Override
    public DutySchedule find(int id) {
        Optional<DutySchedule> optionalDutySchedule = dutyScheduleRepository.findById(id);

        if (optionalDutySchedule.isPresent()) {
            return optionalDutySchedule.get();
        }

        else {
            throw new RuntimeException("Not found"); //TODO
        }
    }

    @Override
    public List<DutySchedule> findAll() {
        return dutyScheduleRepository.findAll();
    }

    @Override
    public void delete(int id) {
        dutyScheduleRepository.deleteById(id);
    }

    @Override
    public List<DutySchedule> findDutyScheduleByDoctorId(int doctorId) {
        return dutyScheduleRepository.findByDoctorId(doctorId);
    }

    @Override
    public Doctor findByDoctorId(int doctorId) {
        return doctorService.find(doctorId);
    }
}
