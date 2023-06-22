package Database;
import java.sql. *;
import java.sql.PreparedStatement;

public class InsertData {
     public void insert(String a, String b, String c, String d, String e) {
        String sql = "INSERT INTO books(Id,Title,Author,Year,Category) VALUES(?,?,?,?,?)";
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/signup", "root", "");
        PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setString(1,a);
            pst.setString(2,b);
            pst.setString(3,c);
            pst.setString(4,d);
            pst.setString(5,e);
            pst.executeUpdate();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
   
}
