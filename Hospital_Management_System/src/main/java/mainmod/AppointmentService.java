package mainmod;

import java.util.List;
import java.util.Scanner;

import dao.HospitalServiceImpl;
import entity.Appointment;
import exception.PatientNumberNotFoundException;

public class AppointmentService {

	   Scanner scanner = new Scanner(System.in);
	
	
	   public void appoimentByid(HospitalServiceImpl hospitalService) {
	        System.out.print("Enter appointment ID: ");
	        int appointmentId = scanner.nextInt();
	        
	        Appointment appointment = hospitalService.getAppointmentById(appointmentId);
	        
	        if (appointment != null) {
	            System.out.println("Appointment details:");
	            System.out.println(appointment);
	        } else {
	            try {
	                throw new PatientNumberNotFoundException("Appointment with appointment ID " + appointmentId + " not found.");
	            } catch (PatientNumberNotFoundException e) {
	                System.out.println(e.getMessage());
	            }
	        }
	    }
	   
	   public void appointmentsForPatient(HospitalServiceImpl hospitalService) {
	        System.out.print("Enter patient ID: ");
	        int patientId = scanner.nextInt();

	        List<Appointment> appointments = hospitalService.getAppointmentsForPatient(patientId);

	        if (!appointments.isEmpty()) {
	            System.out.println("Appointments for patient ID " + patientId + ":");
	            for (Appointment appointment : appointments) {
	                System.out.println(appointment);
	            }
	        } else {
	        	try {
	                throw new PatientNumberNotFoundException("Appointment for patient with patient ID " + patientId + " not found.");
	            } catch (PatientNumberNotFoundException e) {
	                System.out.println(e.getMessage());
	            }	        }
	    }
	   
	   public void appointmentsForDoctor(HospitalServiceImpl hospitalService) {
	        System.out.print("Enter doctor ID: ");
	        int doctorId = scanner.nextInt();

	        List<Appointment> appointments = hospitalService.getAppointmentsForPatient(doctorId);

	        if (!appointments.isEmpty()) {
	            System.out.println("Appointments for patient ID " + doctorId + ":");
	            for (Appointment appointment : appointments) {
	                System.out.println(appointment);
	            }
	        } else {
	        	try {
	                throw new PatientNumberNotFoundException("Appointment for doctor with doctor ID " + doctorId + " not found.");
	            } catch (PatientNumberNotFoundException e) {
	                System.out.println(e.getMessage());
	            }	        }
	    }
	   
	   public void appointmentSchedule(HospitalServiceImpl hospitalService) {
		   System.out.println("enter appointment id: ");
		   int appointmentId=scanner.nextInt();
		   System.out.println("enter patient id: ");
		   int patientId=scanner.nextInt();
		   System.out.println("enter doctor id: ");
		   int doctorId=scanner.nextInt();
		   System.out.println("enter appointment date: ");
		   String appointmentDate=scanner.next();
		   System.out.println("enter appointment description: ");
		   String description=scanner.next();
		   
		   Appointment appointment = new Appointment(appointmentId, patientId, doctorId, appointmentDate, description);
		    boolean scheduled = hospitalService.scheduleAppointment(appointment);

		    if (scheduled) {
		        System.out.println("Appointment scheduled successfully.");
		    } else {
		        System.out.println("Failed to schedule appointment.");
		    }
		}
	   
	   public void appointmentUpdate(HospitalServiceImpl hospitalService) {
		    try {
		        System.out.println("Enter appointment ID to update: ");
		        int appointmentId = scanner.nextInt();

		        Appointment existingAppointment = hospitalService.getAppointmentById(appointmentId);
		        if (existingAppointment != null) {
		            System.out.println("Enter new patient ID: ");
		            int newPatientId = scanner.nextInt();
		            System.out.println("Enter new doctor ID: ");
		            int newDoctorId = scanner.nextInt();
		            System.out.println("Enter new appointment date: ");
		            String newAppointmentDate = scanner.next();
		            System.out.println("Enter new appointment description: ");
		            String newDescription = scanner.next();

		            existingAppointment.setPatientId(newPatientId);
		            existingAppointment.setDoctorId(newDoctorId);
		            existingAppointment.setAppointmentDate(newAppointmentDate);
		            existingAppointment.setDescription(newDescription);

		            boolean updated = hospitalService.updateAppointment(existingAppointment);
		            if (updated) {
		                System.out.println("Appointment updated successfully.");
		            } else {
		                System.out.println("Failed to update appointment.");
		            }
		        } else {
		            throw new PatientNumberNotFoundException("Appointment not found.");
		        }
		    } catch (PatientNumberNotFoundException e) {
		        System.out.println(e.getMessage());
		    }
		}
	   
	   public void appointmentCancel(HospitalServiceImpl hospitalService){
		   System.out.println("Enter appointment ID to delete: ");
	        int appointmentId = scanner.nextInt();
	        Appointment existingAppointment = hospitalService.getAppointmentById(appointmentId);
	       try {
	    	   if (existingAppointment != null) {
	        	 boolean deleted = hospitalService.cancelAppointment(appointmentId);
	 	        if (deleted) {
	 	            System.out.println("Appointment deleted successfully.");
	 	        } else {
	 	            System.out.println("Failed to delete appointment.");
	 	        }
	        }else {
	            throw new PatientNumberNotFoundException("Appointment not found.");
	        }
	       }catch (PatientNumberNotFoundException e) {
		        System.out.println(e.getMessage());
		    }
	   }

}

