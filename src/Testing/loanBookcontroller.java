package Testing;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class loanBookcontroller implements Initializable{
    private static final int MAX_BOOK_TO_ISSUE = 3;
    Connection con;
    private Stage stage;
    private Scene scene;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField authorname;

    @FXML
    private TextField booktitle;

    @FXML
    private Button clearformbtn;

    @FXML
    private Button gobackbtn;

    @FXML
    private DatePicker issueDate;

    @FXML
    private Button laonbtn;

    @FXML
    private Button loanlistbtn;

    @FXML
    private TextField password;

    @FXML
    private TextField usernameid;

    @FXML
    private TextField year;

    @FXML
    private TextField searchBookCode;

    @FXML
    private TextField searchStudentID;

    @FXML
    private TextField category;

    @FXML
    private TextField studentidtxt;



    public void clear() {
        searchBookCode.setText("");
        booktitle.setText("");
        authorname.setText("");
        year.setText("");
        category.setText("");

        searchStudentID.setText("");
        usernameid.setText("");
        password.setText("");
        studentidtxt.setText("");


    }

    @FXML
    void goback(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Library.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void goToLoanList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loanlist1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void searchBook(ActionEvent event) {
        ConnectToBook();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String id = searchBookCode.getText();
        String sql = "select *from books where id = '" + id + "' ";
        try {

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("Book Available");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                // alert.setTitle("Loan Alert");
                // alert.setHeaderText("Loan Management");
                alert.setContentText("Book Available");
                alert.showAndWait();
                // msgLabel.setText("Book Available");
                searchBookCode.setDisable(false);
                booktitle.setText(rs.getString(2));
                authorname.setText(rs.getString(3));
                year.setText(rs.getString(4));
                category.setText(rs.getString(5));
                rs.close();
                pst.close();
            } else {
                System.out.println("The Book does not exist!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                // alert.setTitle("Loan Alert");
                // alert.setHeaderText("Loan Management");
                alert.setContentText("The Book does not exist!");
                alert.showAndWait();
                // msgLabel.setText("Book Not Available");
                //JOptionPane.showMessageDialog(null,"The book does not exist, check the book ID well");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {

            }
        }
    }
    @FXML
    public void searchStudent(ActionEvent event) throws IOException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        ConnectToStudent();
        String student = searchStudentID.getText();

        String sql = "select *from accounts where Fullname ='" + student + "'";
        try {
            //Connection con = Connectivity.ConnectDb();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                usernameid.setText(rs.getString(5));
                studentidtxt.setText(rs.getString(2));
                password.setText(rs.getString(4));    
                rs.close();
                pst.close();
            } else {
                JOptionPane.showMessageDialog(null, "The student does not exist in the database");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {

            }
        }
    }  

    @FXML
    void issueBook(ActionEvent event) {
        conne();
        clear();
    }

    public void conne() {
        ResultSet rs = null;
        PreparedStatement pst = null;
        ConnectToStudent();

        String reg = searchStudentID.getText();
        String sql = "select *from accounts where fullname='" + reg + "'";
        String sql3 = "update accounts set noIssued = ? where fullname='" + reg + "' ";
        try {
            // Connection con = Connectivity.ConnectDb();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            int noIssued = rs.getInt(4);

            //int noIssued = Integer.parseInt(no);
            if (noIssued < MAX_BOOK_TO_ISSUE) {
                issue();
                PreparedStatement prepstm = con.prepareStatement(sql3);
                //String cnt = Integer.toString(counter);
                int counter = noIssued;
                counter++;
                prepstm.setInt(1, counter);
                prepstm.executeUpdate();
                pst.close();
                rs.close();
            } else {
                JOptionPane.showMessageDialog(null, "You cannot be issued with another book" + "\nYou already have 3 books");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void issue() {
        ResultSet rs = null, rs1, rs2 = null;
        PreparedStatement pst = null, pst1, pst2, pst3 = null;
        ConnectToBook();
        ConnectToStudent();

        String reg = searchStudentID.getText();
        String bId = searchBookCode.getText();

        String sql1 = "select *from accounts where fullname = '" + reg + "'";
        String sql2 = "select *from books where id = '" + bId + "'";
        String sql3 = "DELETE FROM books where id = '"+bId+"'";
        String sql = "insert into loanlist1 (id,username,password,title,author,year,loandate,category) value(?,?,?,?,?,?,?,?)";

        try {
            ConnectToStudent();
            pst1 = con.prepareStatement(sql1);
            rs1 = pst1.executeQuery();
            pst2 = con.prepareStatement(sql2);
            rs2 = pst2.executeQuery();
            if (rs1.next() && rs2.next()) {
                try {
                    pst = con.prepareStatement(sql);
                    {
                        pst.setString(2, usernameid.getText());
                        pst.setString(3, password.getText());
                        pst.setString(4, booktitle.getText());
                        pst.setString(5, authorname.getText());
                        pst.setString(6, year.getText());
                        LocalDate localDate = issueDate.getValue();
                        pst.setString(7, localDate.toString());
                        pst.setString(8, category.getText());
                        pst.setString(1, searchBookCode.getText());
                        pst.execute();
                        pst.close();
                        JOptionPane.showMessageDialog(null, "Book issued Successfully");
                        
                        pst3 = con.prepareStatement(sql3);
                        pst3.executeUpdate(sql3);
                        pst3.close();
                        JOptionPane.showMessageDialog(null, "Removed book!");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                    System.out.println("error");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Either the book ID or the Student Registration number is incorrect");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sasa");
        }
    }
   
    public void ConnectToBook(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:/signup","root","");
            System.out.println("sucessfully");
        } catch (ClassNotFoundException ex) {
          System.out.println("");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void ConnectToStudent(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:/signup","root","");
            System.out.println("sucessfully");
        } catch (ClassNotFoundException ex) {
          System.out.println("");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}

