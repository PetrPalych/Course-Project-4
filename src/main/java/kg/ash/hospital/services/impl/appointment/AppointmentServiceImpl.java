package kg.ash.hospital.services.impl.appointment;

import kg.ash.hospital.dao.repositories.appointment.AppointmentRepository;
import kg.ash.hospital.dao.repositories.doctor.AccountDataRepository;
import kg.ash.hospital.dao.repositories.doctor.DoctorRepository;
import kg.ash.hospital.entities.appointment.Appointment;
import kg.ash.hospital.entities.doctor.AccountData;
import kg.ash.hospital.entities.doctor.Doctor;
import kg.ash.hospital.enums.Role;
import kg.ash.hospital.errors.exceptions.NotFoundException;
import kg.ash.hospital.services.interfaces.appointment.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final AccountDataRepository accountDataRepository;

    private final DoctorRepository doctorRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AccountDataRepository accountDataRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.accountDataRepository = accountDataRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment find(int id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            return optionalAppointment.get();
        }

        else {
            throw new NotFoundException("Appointment is not found! ID: " + id);
        }
    }

    @Override
    public List<Appointment> findAllOrderByIdDesk(int limit) {
        return appointmentRepository.findAllOrderByIdDesk(limit);
    }

    @Override
    public List<Appointment> findByCurrentDoctor() {
        Optional<AccountData> accountData =
                accountDataRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if (accountData.isPresent()) {
            if (accountData.get().getRole().equals(Role.ROLE_DOCTOR)) {
                Doctor doctor = doctorRepository.findByAccountDataId(accountData.get().getId());

                return appointmentRepository.findByDoctorId(doctor.getId());
            }

            else {
                return appointmentRepository.findAllOrderByIdDesk(25);
            }
        }

        else {
            throw new NotFoundException("Something went wrong!");
        }
    }

    @Override
    public List<Appointment> findByPatientId(int patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public void delete(int id) {
        appointmentRepository.deleteById(id);
    }

}
