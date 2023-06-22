package Testing;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import Testing.Book.Registerlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Registerlistcontroller implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backbtn;
     @FXML
    private TableView<Registerlist> TableView;

    @FXML
    private TableColumn<Registerlist, String> firstname;

    @FXML
    private TableColumn<Registerlist, String> fullname;

    @FXML
    private TableColumn<Registerlist, String> lastname;

    @FXML
    private TableColumn<Registerlist, String> password;

    @FXML
    private TableColumn<Registerlist, String> schoolid;

    @FXML
    private TextField idField;
    @FXML
    private TextField fullnametxt;
    @FXML
    private TextField passwordtxt;
    @FXML
    private TextField firstnametxt;
    @FXML
    private TextField lastnametxt;

    Stage stage;
	Scene scene;

    @FXML 
    private void updateButton() {
        String query = "UPDATE accounts SET fullname='"+fullnametxt.getText()+"',password='"+passwordtxt.getText()+"',firstname='"+firstnametxt.getText()+"',lastname='"+lastnametxt.getText()+"' WHERE schoolid="+idField.getText()+"";
        executeQuery(query);
	    showRegisterlist();
        } 

	@FXML
    void goback(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	showRegisterlist();
    }
    
    public Connection getConnection() {
    	Connection conn;
    	try {
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/signup","root","");
    		return conn;
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
    public ObservableList<Registerlist> getBooksList(){
    	ObservableList<Registerlist> Registerlist = FXCollections.observableArrayList();
    	Connection connection = getConnection();
    	String query = "SELECT * FROM accounts ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Registerlist registerlist;
			while(rs.next()) {
				registerlist = new Registerlist(rs.getString("fullname"),rs.getString("schoolid"),rs.getString("password"),rs.getString("firstname"),rs.getString("lastname"));
				Registerlist.add(registerlist);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return Registerlist;
    }
    
    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showRegisterlist() {
    	ObservableList<Registerlist> list = getBooksList();
    	
    	fullname.setCellValueFactory(new PropertyValueFactory<Registerlist, String>("fullname"));
    	schoolid.setCellValueFactory(new PropertyValueFactory<Registerlist, String>("schoolid"));
    	password.setCellValueFactory(new PropertyValueFactory<Registerlist, String>("password"));
    	firstname.setCellValueFactory(new PropertyValueFactory<Registerlist, String>("firstname"));
    	lastname.setCellValueFactory(new PropertyValueFactory<Registerlist, String>("lastname"));
    	
    	TableView.setItems(list);
    }
}