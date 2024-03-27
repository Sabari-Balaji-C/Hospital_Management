package mainmod;

import dao.HospitalServiceImpl;

import dao.IHospitalService;
import entity.Appointment;
import java.util.List;
import java.util.Scanner;

import Util.DBUtil;

public class MainModule {
	public static void main(String[] args) {
		HospitalServiceImpl hospitalService = new HospitalServiceImpl(DBUtil.getDBConn());
        Scanner scanner = new Scanner(System.in);
        AppointmentService service=new  AppointmentService();
        
        int choice;
        
        do {
            System.out.println("Select option:");
            System.out.println("1. Get appointment by id");
            System.out.println("2. Get appointments for patient");
            System.out.println("3. Get appointments for doctor");
            System.out.println("4. Schedule appointment");
            System.out.println("5. Update appointment");
            System.out.println("6. Cancel appointment");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                	service.appoimentByid(hospitalService);
                    break;
                case 2:
                	service.appointmentsForPatient(hospitalService);
                    break;
                case 3:
                	service.appointmentsForDoctor(hospitalService);
                    break;
                case 4:
                	service.appointmentSchedule(hospitalService);
                    break;
                case 5:
                	service.appointmentUpdate(hospitalService);
                    break;
                case 6:
                	service.appointmentCancel(hospitalService);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 7);
	}
	

}
