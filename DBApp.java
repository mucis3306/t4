/** DBApp.java by Wayne T. Boka on April 24, 2011
*	Revised on April 26, 2011
*/

import java.sql.*;

// A simple class to help use a database
public class DBApp {
  private Connection connect = null;
  private String dbURL = "jdbc:mysql://192.168.0.44/johndoe";
  private String username = "johndoe";
  private String password = "johndoe";

  public DBApp() {
    getConnection();
  }

  private void getConnection() {
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      connect = DriverManager.getConnection(dbURL, username, password);
    }
    catch (ClassNotFoundException e) {
      System.out.println("Database driver not found.");
    }
    catch (Exception e) {
      System.out.println("Other Exception");
    }
  }

  // Use doQuery for your sql select queries
  public ResultSet doQuery(String q) {
    ResultSet rs = null;
    PreparedStatement ps = null;
    try {
      ps = connect.prepareStatement(q);
      rs = ps.executeQuery();
    }
    catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("SQLState: " + e.getSQLState());
      System.out.println("VendorError: " + e.getErrorCode());
    }
    catch (Exception e) {
      System.out.println("Other Exception");
    }
    return rs;
  }

  // Use doUpdate for sql insert, update, and delete statements
  public void doUpdate(String q) {
    PreparedStatement ps = null;
    try {
      ps = connect.prepareStatement(q);
      ps.executeUpdate();
    }
    catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("SQLState: " + e.getSQLState());
      System.out.println("VendorError: " + e.getErrorCode());
    }
    catch (Exception e) {
      System.out.println("Other Exception");
    }
  }

  // The main method is used to test this class
  public static void main(String[] args) throws Exception {
    DBApp mydb = new DBApp();
    String preparedSQL = "select * from orders";
    ResultSet rs = mydb.doQuery(preparedSQL);
    while(rs.next()) {
      System.out.printf("%5d %-15s %-15s %-15s %-30s %-30s %-30s %-30s %2s\n",
		rs.getInt(1), rs.getString(2), rs.getString(3),
		rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
    }
  }
}