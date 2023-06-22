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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Testing.Book.Books;
import javafx.scene.Node;
import javafx.scene.Parent;

public class Updatecontroller implements Initializable{
    Stage stage;
    Scene scene;
    
    @FXML
    private TableView<Books> TableView;
    @FXML
    private TableColumn<Books, String> authorColumn;
    @FXML
    private TextField idField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField categoryField;    
    @FXML
    private TableColumn<Books, Integer> idColumn;
    @FXML
    private TableColumn<Books, String> CategoryColumn;
    @FXML
    private TableColumn<Books, String> titleColumn;
    @FXML
    private TableColumn<Books, String> yearColumn;
    @FXML
    private Button updatebtn;
    @FXML
    private Button deletebtn;
    @FXML 
    private void updateButton() {
        String query = "UPDATE books SET Title='"+titleField.getText()+"',Author='"+authorField.getText()+"',Year='"+yearField.getText()+"',Category='"+categoryField.getText()+"' WHERE ID="+idField.getText()+"";
        executeQuery(query);
	    showBooks();
        }    
    @FXML
    private void deleteButton() {
    	String query = "DELETE FROM books WHERE ID="+idField.getText()+"";
    	executeQuery(query);
    	showBooks();
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
    	showBooks();
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
    
    public ObservableList<Books> getBooksList(){
    	ObservableList<Books> booksList = FXCollections.observableArrayList();
    	Connection connection = getConnection();
    	String query = "SELECT * FROM books ";
    	Statement st;
    	ResultSet rs;
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Books books;
			while(rs.next()) {
				books = new Books(rs.getString("Id"),rs.getString("Title"),rs.getString("Author"),rs.getString("Year"),rs.getString("Category"));
				booksList.add(books);
				}
		} catch (Exception e) {
			e.printStackTrace();
		} 	
        return booksList;
    }
    public void showBooks() {
    	ObservableList<Books> list = getBooksList();
    	
    	idColumn.setCellValueFactory(new PropertyValueFactory<Books,Integer>("id"));
    	titleColumn.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
    	authorColumn.setCellValueFactory(new PropertyValueFactory<Books,String>("author"));
    	yearColumn.setCellValueFactory(new PropertyValueFactory<Books,String>("year"));
    	CategoryColumn.setCellValueFactory(new PropertyValueFactory<Books,String>("category"));
    	
    	TableView.setItems(list);
    }
    @FXML
    public void goback (ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Library.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
