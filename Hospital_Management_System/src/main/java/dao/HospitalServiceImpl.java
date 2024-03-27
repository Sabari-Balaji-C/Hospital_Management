package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import entity.Appointment;

public class HospitalServiceImpl implements IHospitalService {
	    private Connection conn;
		private int patientId;
		private int doctorId;
		private String appointmentDate;
		private String description;
		private List<Appointment> appointment;	    
	    public HospitalServiceImpl(Connection conn) {
	        this.conn = conn;
	    }

	@Override
	public  Appointment getAppointmentById(int appointmentId) {
		 try {
	            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM Appointment WHERE appointmentId = ?");
	            pstmt.setInt(1, appointmentId);
	            ResultSet rs = pstmt.executeQuery();

	            if (rs.next()) {
	                Appointment appointment = new Appointment(appointmentId,patientId, doctorId,appointmentDate,description);
	                appointment.setPatientId(rs.getInt("patientId"));
	                appointment.setDoctorId(rs.getInt("doctorId"));
	                appointment.setAppointmentDate(rs.getString("appointmentDate"));
	                appointment.setDescription(rs.getString("description"));
	                return appointment;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	}

	@Override
	public List<Appointment> getAppointmentsForPatient(int patientId) {
		 List<Appointment> appointments = new ArrayList<>();
		 try {
	            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM Appointment WHERE patientId = ?");
	            pstmt.setInt(1, patientId);
	            ResultSet rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	 int appointmentId = rs.getInt("appointmentId");
	                 int doctorId = rs.getInt("doctorId");
	                 String appointmentDate = rs.getString("appointmentDate");
	                 String description = rs.getString("description");
	                 Appointment appointment = new Appointment(appointmentId, patientId, doctorId, appointmentDate, description);
	                 appointments.add(appointment);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }		
		 return appointments;
	}

	@Override
	public List<Appointment> getAppointmentsForDoctor(int doctorId) {
		List<Appointment> appointments = new ArrayList<>();
		 try {
	            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM Appointment WHERE doctorId = ?");
	            pstmt.setInt(1, doctorId);
	            ResultSet rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	 int appointmentId = rs.getInt("appointmentId");
	                 int patientId = rs.getInt("patientId");
	                 String appointmentDate = rs.getString("appointmentDate");
	                 String description = rs.getString("description");
	                 Appointment appointment = new Appointment(appointmentId, patientId, doctorId, appointmentDate, description);
	                 appointments.add(appointment);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }		
		 return appointments;
	}

	@Override
	public boolean scheduleAppointment(Appointment appointment) {
		 try {
	            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("INSERT INTO Appointment (appointmentId, patientId, doctorId, appointmentDate, description) VALUES (?, ?, ?, ?, ?)");
	            pstmt.setInt(1, appointment.getAppointmentId());
	            pstmt.setInt(2, appointment.getPatientId());
	            pstmt.setInt(3, appointment.getDoctorId());
	            pstmt.setString(4, appointment.getAppointmentDate());
	            pstmt.setString(5, appointment.getDescription());
	            
	            int rowsAffected = pstmt.executeUpdate();
	            
	         return rowsAffected > 0; 
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	}

	@Override
	public boolean updateAppointment(Appointment appointment) {
		 try {
	            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("UPDATE Appointment SET patientId = ?, doctorId = ?, appointmentDate = ?, description = ? WHERE appointmentId = ?");
	           
	            pstmt.setInt(1, appointment.getPatientId());
	            pstmt.setInt(2, appointment.getDoctorId());
	            pstmt.setString(3, appointment.getAppointmentDate());
	            pstmt.setString(4, appointment.getDescription());
	            pstmt.setInt(5, appointment.getAppointmentId());
	            
	            int rowsAffected = pstmt.executeUpdate();
	            
	         return rowsAffected > 0; 
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	}

	@Override
	public boolean cancelAppointment(int appointmentId) {
		try {
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("DELETE FROM Appointment WHERE appointmentId = ?");
            pstmt.setInt(1, appointmentId);
            int rowsAffected = pstmt.executeUpdate();
            
	         return rowsAffected > 0; 
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	}
}
