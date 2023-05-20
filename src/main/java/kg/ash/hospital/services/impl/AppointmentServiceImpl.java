package kg.ash.hospital.services.impl;

import kg.ash.hospital.dao.repositories.appointments.AppointmentRepository;
import kg.ash.hospital.entities.appointments.Appointment;
import kg.ash.hospital.services.interfaces.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
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
            throw new RuntimeException("Not found");
        }
    }

    @Override
    public List<Appointment> findAllOrderByIdDesk(int limit) {
        return appointmentRepository.findAllOrderByIdDesk(limit);
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
