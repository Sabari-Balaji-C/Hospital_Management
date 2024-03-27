package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	// Defined all DB variable

		private static Connection conn;

		public static Connection getDBConn() {
			String usernameDb = "root";
			String passwordDb = "";
			String urlDb = "jdbc:mysql://localhost:3306/Hospital_management";
			String driverName = "com.mysql.jdbc.Driver";

			/* Step 2: Load The driver */
			try {
				Class.forName(driverName);
//				System.out.println("Driver loaded successfully..");
			} catch (ClassNotFoundException e) {
				System.out.println("Driver could not be loaded");
				e.printStackTrace();
			}

			/* Step 3: Establish the connection */
			try {
				conn = DriverManager.getConnection(urlDb, usernameDb, passwordDb);
//			System.out.println("Connection established..");
			} catch (SQLException e) {
				System.out.println("Connection could not be established");
				e.printStackTrace();
			}

			return conn;
		}

		public static void dbClose() {
			try {
				conn.close();
//			System.out.println("Connection closed..");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		public static void main(String[] args) {
//			
//        Connection connection = DBUtil.getDBConn();
//        if (connection != null) {
//            // Perform database operations using the connection
//            // Example: Execute SQL queries, insert/update data, etc.
//
//            // Close the connection when done
//            DBUtil.dbClose();
//        } else {
//            System.out.println("Failed to establish a database connection.");
//        }
//		}
    }

		 
