package Testing;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import Database.InsertData;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.Node;

public class addproductcontroller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    String URL = "jdbc:mysql://localhost/signup";
    String user = "root";
    String password = "";
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField authorField;

    @FXML
    private TextField idField;

    @FXML
    private Button insertButton;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField yearField;

    public void clear() {
        idField.setText("");
        authorField.setText("");
        titleField.setText("");
        yearField.setText("");
        categoryField.setText("");
    }


    
    @FXML
    private void insertButton(ActionEvent event){

        //int id = Integer.parseInt(idField.getText());
        String id = idField.getText();
        String title = titleField.getText();
        String author = authorField.getText();
        //int year = Integer.parseInt(yearField.getText());
        String year = yearField.getText();
        String category = categoryField.getText();
        InsertData db1 = new InsertData();
        if(id.equals("") || title.equals("") || author.equals("") || year.equals("") || category.equals("")){
            JOptionPane.showMessageDialog(null, "Please complete all the form");
        }else{
        try{
                //String sql = "Insert into book(name,bookname,date,status)values(?,?,?,?)";
                //JDBCDemo db = new JDBCDemo();
                //Connection conn= DriverManager.getConnection(URL, user, password);
                //db.connect();
                //PreparedStatement pst = conn.prepareStatement(sql);
                //pst.setString(1,name);
                //pst.setString(2,bookname);
               // pst.setString(3, date);
                //pst.setString(4, status);
                //pst.execute();
            db1.insert(id,title,author,year,category);
            JOptionPane.showMessageDialog(null,"Book is added successfully");
            System.out.println("Data Inserted");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Please complete all the form");
        }
    }
    }
        
        @FXML
        public void backField(ActionEvent event) throws IOException{
            root = FXMLLoader.load(getClass().getResource("Library.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
        }
        
     
    }
    

