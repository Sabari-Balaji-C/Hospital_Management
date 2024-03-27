package dao;

import java.util.List;

import entity.Appointment;

public interface IHospitalService {
	
	Appointment getAppointmentById(int appointmentId);
    
    List<Appointment> getAppointmentsForPatient(int patientId);
    
    List<Appointment> getAppointmentsForDoctor(int doctorId);
    
    boolean scheduleAppointment(Appointment appointment);
    
    boolean updateAppointment(Appointment appointment);
    
    boolean cancelAppointment(int appointmentId);

}
