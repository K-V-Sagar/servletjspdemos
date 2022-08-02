package jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {
public static void main(String[] args) {
         readfromDB();
		//insertIntoDB();
		//updateIntoDB();
         //delFromDB();
	}

private static void delFromDB() {
	try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "admin");
			Statement	statement = connection.createStatement();) {
		int rowsUpdated = statement.executeUpdate("delete from account where accno=3;");
		System.out.println("Number of rows Updated: "+rowsUpdated);
	} catch (SQLException e) {
		e.printStackTrace();
	} 
}


private static void updateIntoDB() {
	// TODO Auto-generated method stub
	try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "admin");
			Statement	statement = connection.createStatement();) {
			
			int rowsUpdated = statement.executeUpdate("update account set balance=15000 where accno=3;");
			System.out.println("Number of rows Updated: "+rowsUpdated);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	
}
private static void insertIntoDB() {
	// TODO Auto-generated method stub
	try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "admin");
			Statement	statement = connection.createStatement();) {
			
			int rowsInserted = statement.executeUpdate("insert into account values (3, 'Mary', 'Lucy', 150000);");
			System.out.println("Number of rows Inserted: "+rowsInserted);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	
}
private static void readfromDB() {
	try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "admin");
			Statement	statement = connection.createStatement();) {
			
			ResultSet resultSet = statement.executeQuery("select * from account");
			while (resultSet.next()) {				
				System.out.println(resultSet.getInt(1) + ", " + resultSet.getString(2) + ", " + resultSet.getString(3) + ", " + resultSet.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
}

}