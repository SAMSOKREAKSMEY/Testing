package Testing;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import Database.JDBCDemo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Registercontroller {
    String URL = "jdbc:mysql://localhost/signup";
    String user = "root";
    String password = "";
    private Stage stage;
    private Scene scene;
    private Parent root;

    java.sql.Statement statement;


    @FXML
    private TextField emailIdField;

    @FXML
    private TextField fullnameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Button RESlogin;

    @FXML
    private Button RESback;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    public void clear() {
        fullnameField.setText("");
        emailIdField.setText("");
        passwordField.setText("");
        firstname.setText("");
        lastname.setText("");
    }

    @FXML
    void insert(ActionEvent event) {
        String fname = fullnameField.getText();
        String Id = emailIdField.getText();
        String pass = passwordField.getText();
        String firtn = firstname.getText();
        String lastn = lastname.getText();


        if(fname.equals("") || Id.equals("") || pass.equals("") || firtn.equals("") || lastn.equals("")){
            JOptionPane.showMessageDialog(null, "Please fill all the blank");
        }else{
        try{
            String sql = "Insert into accounts(Fullname,SchoolId,Password,firstname,lastname)values(?,?,?,?,?)";
            JDBCDemo db = new JDBCDemo();
            Connection conn= DriverManager.getConnection(URL, user, password);
            db.connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,fname);
            pst.setString(2,Id);
            pst.setString(3, pass);
            pst.setString(4,firtn);
            pst.setString(5,lastn);
            pst.execute();
            System.out.println("Data Inserted");
        

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Please complete all the form!");
            Logger.getLogger(Registercontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    @FXML
    public void Login (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Back (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Library.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void Registerlist (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Registerlist.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    



    
}
