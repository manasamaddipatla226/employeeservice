package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectionMainApp {

	public DbConnectionMainApp() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		DbConnectionMainApp dbConnc=new DbConnectionMainApp();
		dbConnc.selectMethod();
		dbConnc.insertMethod();
		dbConnc.updateMethod();
		dbConnc.deleteMethod();
		
		
		
		
		
	}
		
		public void selectMethod() {

		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/organization", "root", "root");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from organization.employee");

			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String name=resultSet.getString("name");
				System.out.println("id" + id);
				System.out.println("name"+name);
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		public void insertMethod() {
		
			String id="jklmop24";
			String name="Seetha";
			String email="seetha@gmail.com";
			
			Connection connection = null;

	        try {
	            // Load MySQL JDBC driver
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Establish connection to the MySQL database
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/organization", "root", "root");

	            // Prepare the INSERT query
	            String insertQuery = "INSERT INTO employee (id, name, email) VALUES (?, ?, ?)";

	            // Create a PreparedStatement object to insert data
	            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

	            // Set the values for the placeholders in the query
	            preparedStatement.setString(1, id);
	            preparedStatement.setString(2, name);
	            preparedStatement.setString(3, email);

	            // Execute the INSERT query
	            int rowsAffected = preparedStatement.executeUpdate();
	            System.out.println(rowsAffected + " row(s) inserted successfully!");

	            // Close resources
	            preparedStatement.close();
	            connection.close();

	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

public void updateMethod() {
    String id = "jklmn123";  // The ID to update
    String name = "Seetha";  // New name
    String email = "seetha_updated@gmail.com";  // New email

    Connection connection = null;

    try {
        // Load MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish connection to the MySQL database
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/organization", "root", "root");

        // Prepare the UPDATE query
        String updateQuery = "UPDATE employee SET name = ?, email = ? WHERE id = ?";

        // Create a PreparedStatement object to update data
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

        // Set the values for the placeholders in the query
        preparedStatement.setString(1, name);  // Set new name
        preparedStatement.setString(2, email);  // Set new email
        preparedStatement.setString(3, id);  // Set the ID of the record to update

        // Execute the UPDATE query
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Record updated successfully!");
        } else {
            System.out.println("No record found with the given ID.");
        }

        // Close resources
        preparedStatement.close();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


public void deleteMethod() {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        // Step 1: Load MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Step 2: Establish connection to the MySQL database
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/organization", "root", "root");

        // Step 3: Prepare the DELETE query
        String deleteQuery = "DELETE FROM employee WHERE id = ?";

        // Step 4: Create a PreparedStatement object
        preparedStatement = connection.prepareStatement(deleteQuery);

        // Step 5: Set the value for the placeholder in the DELETE query
        String id="jklmn1";
        preparedStatement.setString(1, id);

        // Step 6: Execute the DELETE query
        int rowsAffected = preparedStatement.executeUpdate();

        // Step 7: Check if the deletion was successful
        if (rowsAffected > 0) {
            System.out.println("Employee with ID " + id + " deleted successfully.");
        } else {
            System.out.println("No employee found with ID " + id);
        }

    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    } finally {
        // Step 8: Close resources
        try {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
}




	
	



	

