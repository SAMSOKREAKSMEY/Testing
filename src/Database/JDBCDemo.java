package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class JDBCDemo{
    Connection c;
    String URL = "jdbc:mysql://localhost/Signup";
    String user = "root";
    String password = "";
    public JDBCDemo(){

    }

    public void connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            c = (Connection) DriverManager.getConnection(URL, user, password);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public PreparedStatement prepareStatement(String sql) {
        return null;
    }

    public Connection getConnection() {
        return null;
    }

    public void executeQuery(String query) {
    	Connection conn = getConnection();
    	Statement st;
    	try {
			st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
