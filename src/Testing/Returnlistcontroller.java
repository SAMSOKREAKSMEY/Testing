package Testing;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import Testing.Book.Returnbook;
import Testing.Book.Returnbook;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Returnlistcontroller implements Initializable{
    Stage stage;
    Scene scene;

    @FXML
    private TableView<Returnbook> TableView;

    @FXML
    private TableColumn<Returnbook, String> authorname;

    @FXML
    private Button backbtn;

    @FXML
    private TableColumn<Returnbook, String> bookidtxt;

    @FXML
    private TableColumn<Returnbook, String> booktitle;

    @FXML
    private TableColumn<Returnbook, String> category;

    @FXML
    private TableColumn<Returnbook, String> loandate;

    @FXML
    private TableColumn<Returnbook, String> returndate;

    @FXML
    private TableColumn<Returnbook, String> studentid;

    @FXML
    private TableColumn<Returnbook, String> username;

    @FXML
    private TableColumn<Returnbook, String> year;

    @FXML
    void goback(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Returnbook.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    public void executeQuery(String query) {
    	Connection conn = getConnection();
    	Statement st;
    	try {
			st = conn.createStatement();
			((java.sql.Statement) st).executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

    public ObservableList<Returnbook> getBooksList(){
    	ObservableList<Returnbook> booksList1 = FXCollections.observableArrayList();
    	Connection connection = getConnection();
    	String query = "SELECT * FROM returnlist ";
    	Statement st;
    	ResultSet rs;
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Returnbook books;
			while(rs.next()) {
				books = new Returnbook(rs.getString("Id"),rs.getString("username"),rs.getString("password"),rs.getString("title"),rs.getString("author"),rs.getString("year"),rs.getString("loandate"),rs.getString("category"),rs.getString("returndate"));
				booksList1.add(books);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return booksList1;
    }
    
    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showBooks() {
    	ObservableList<Returnbook> list = getBooksList();

    	bookidtxt.setCellValueFactory(new PropertyValueFactory<Returnbook,String>("Id"));
    	username.setCellValueFactory(new PropertyValueFactory<Returnbook,String>("username"));
    	studentid.setCellValueFactory(new PropertyValueFactory<Returnbook,String>("password"));
    	booktitle.setCellValueFactory(new PropertyValueFactory<Returnbook,String>("title"));
    	authorname.setCellValueFactory(new PropertyValueFactory<Returnbook,String>("author"));
    	year.setCellValueFactory(new PropertyValueFactory<Returnbook,String>("year"));
        loandate.setCellValueFactory(new PropertyValueFactory<Returnbook,String>("loandate"));
		category.setCellValueFactory(new PropertyValueFactory<Returnbook,String>("category"));
		returndate.setCellValueFactory(new PropertyValueFactory<Returnbook,String>("returndate"));
    	
    	TableView.setItems(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        showBooks();  
    }
}