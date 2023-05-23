package kg.ash.hospital.services.impl.doctor;

import kg.ash.hospital.dao.repositories.doctor.WorkScheduleRepository;
import kg.ash.hospital.entities.doctor.Doctor;
import kg.ash.hospital.entities.doctor.WorkSchedule;
import kg.ash.hospital.services.interfaces.doctor.DoctorService;
import kg.ash.hospital.services.interfaces.doctor.WorkScheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkScheduleServiceImpl implements WorkScheduleService {

    private final WorkScheduleRepository workScheduleRepository;

    private final DoctorService doctorService;

    @Autowired
    public WorkScheduleServiceImpl(WorkScheduleRepository workScheduleRepository, DoctorService doctorService) {
        this.workScheduleRepository = workScheduleRepository;
        this.doctorService = doctorService;
    }

    @Override
    public WorkSchedule save(WorkSchedule workSchedule) {
        return workScheduleRepository.save(workSchedule);
    }

    @Override
    public WorkSchedule find(int id) {
        Optional<WorkSchedule> optionalWorkSchedule = workScheduleRepository.findById(id);

        if (optionalWorkSchedule.isPresent()) {
            return optionalWorkSchedule.get();
        }

        else {
            throw new RuntimeException("Not found"); //TODO
        }
    }

    @Override
    public Doctor findDoctorById(int doctorId) {
        return doctorService.find(doctorId);
    }

    @Override
    public List<WorkSchedule> findWorkScheduleByDoctorId(int doctorId) {
        return workScheduleRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<WorkSchedule> findAll() {
        return workScheduleRepository.findAll();
    }

    @Override
    public void delete(int id) {
        workScheduleRepository.deleteById(id);
    }

}
