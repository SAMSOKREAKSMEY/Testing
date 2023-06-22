package Testing.Other;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import Testing.Book.LoanBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class loanListcontroller implements Initializable {

    

    Connection con;
    PreparedStatement pst;
    int myIndex;
    int myDate;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<LoanBook, String> author;

    @FXML
    private TableColumn<LoanBook, String> loandate;

    @FXML
    private TableColumn<LoanBook, String> password;

    @FXML
    private TableColumn<LoanBook, String> title;

    @FXML
    private TableColumn<LoanBook, String> usernameid;

    @FXML
    private TableColumn<LoanBook, String> year;
    
    @FXML
    private TableView<LoanBook> TableView;

	@FXML
	private TableColumn<LoanBook, String> category;

	@FXML
    private TableColumn<LoanBook, String> bookid;

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
    
    public ObservableList<LoanBook> getBooksList(){
    	ObservableList<LoanBook> booksList = FXCollections.observableArrayList();
    	Connection connection = getConnection();
    	String query = "SELECT * FROM loanlist1 ";
    	Statement st;
    	ResultSet rs;
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			LoanBook books;
			while(rs.next()) {
				books = new LoanBook(rs.getString("username"),rs.getString("password"),rs.getString("title"),rs.getString("author"),rs.getString("year"),rs.getString("loandate"),rs.getString("category"),rs.getString("id"));
				booksList.add(books);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return booksList;
    }
    
    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showBooks() {
    	ObservableList<LoanBook> list = getBooksList();
    	
    	usernameid.setCellValueFactory(new PropertyValueFactory<LoanBook,String>("username"));
    	password.setCellValueFactory(new PropertyValueFactory<LoanBook,String>("password"));
    	title.setCellValueFactory(new PropertyValueFactory<LoanBook,String>("title"));
    	author.setCellValueFactory(new PropertyValueFactory<LoanBook,String>("author"));
    	year.setCellValueFactory(new PropertyValueFactory<LoanBook,String>("year"));
        loandate.setCellValueFactory(new PropertyValueFactory<LoanBook,String>("loandate"));
		category.setCellValueFactory(new PropertyValueFactory<LoanBook,String>("category"));
		bookid.setCellValueFactory(new PropertyValueFactory<LoanBook,String>("id"));
    	
    	TableView.setItems(list);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	showBooks();

    }
    
    
}
