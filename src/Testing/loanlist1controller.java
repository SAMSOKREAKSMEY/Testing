
package Testing;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Testing.Book.Loanbook1;
import javafx.scene.Node;

public class loanlist1controller implements Initializable{

    Stage stage;
    Scene scene;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Loanbook1> TableView;

    @FXML
    private TableColumn<Loanbook1, String> authorname;

    @FXML
    private TableColumn<Loanbook1, String> bookidtxt;

    @FXML
    private TableColumn<Loanbook1, String> booktitle;

    @FXML
    private TableColumn<Loanbook1, String> category;

    @FXML
    private TableColumn<Loanbook1, String> loandate;

    @FXML
    private TableColumn<Loanbook1, String> studentid;

    @FXML
    private TableColumn<Loanbook1, String> username;

    @FXML
    private TableColumn<Loanbook1, String> year;

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
    
    public ObservableList<Loanbook1> getBooksList(){
    	ObservableList<Loanbook1> booksList1 = FXCollections.observableArrayList();
    	Connection connection = getConnection();
    	String query = "SELECT * FROM loanlist1 ";
    	Statement st;
    	ResultSet rs;
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Loanbook1 books;
			while(rs.next()) {
				books = new Loanbook1(rs.getString("Id"),rs.getString("username"),rs.getString("password"),rs.getString("title"),rs.getString("author"),rs.getString("year"),rs.getString("loandate"),rs.getString("category"));
				booksList1.add(books);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return booksList1;
    }
    
    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showBooks() {
    	ObservableList<Loanbook1> list = getBooksList();

    	bookidtxt.setCellValueFactory(new PropertyValueFactory<Loanbook1,String>("Id"));
    	username.setCellValueFactory(new PropertyValueFactory<Loanbook1,String>("username"));
    	studentid.setCellValueFactory(new PropertyValueFactory<Loanbook1,String>("password"));
    	booktitle.setCellValueFactory(new PropertyValueFactory<Loanbook1,String>("title"));
    	authorname.setCellValueFactory(new PropertyValueFactory<Loanbook1,String>("author"));
    	year.setCellValueFactory(new PropertyValueFactory<Loanbook1,String>("year"));
        loandate.setCellValueFactory(new PropertyValueFactory<Loanbook1,String>("loandate"));
		category.setCellValueFactory(new PropertyValueFactory<Loanbook1,String>("category"));
		
    	
    	TableView.setItems(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        showBooks();  
    }

}