package Testing;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Logincontroller {
    String URL = "jdbc:mysql://localhost/signup";
    String user = "root";
    String password = "";
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button txtlogin;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private Button txtregister;

    @FXML
    private TextField txtusername;

    @FXML
    private void login(ActionEvent event) throws IOException{
        String username = txtusername.getText();
        String pass = txtpassword.getText();

        if(username.equals("") && pass.equals("")){
            JOptionPane.showMessageDialog(null, "Username or Password blank");
        }else{
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/signup", "root", "");
                PreparedStatement pst = con.prepareStatement("select * from account where Username=? and Password=?");
                pst.setString(1, username);
                pst.setString(2, pass);
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Login successful");
                        root = FXMLLoader.load(getClass().getResource("Library.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                }else{
                    JOptionPane.showMessageDialog(null, "Login failed");
                    txtusername.setText("");
                    txtpassword.setText("");
                    txtusername.requestFocus();
                }

            }catch(ClassNotFoundException ex){
                Logger.getLogger(Logincontroller.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(SQLException ex){
                Logger.getLogger(Logincontroller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @FXML
    public void Register (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
