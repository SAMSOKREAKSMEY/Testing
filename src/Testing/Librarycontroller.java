package Testing;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Librarycontroller {
    String URL = "jdbc:mysql://localhost/signup";
    String user = "root";
    String password = "";
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LBaddnook;

    @FXML
    private Button LBborrow;

    @FXML
    private Button LBlistbook;

    @FXML
    private Button LBlogout;

    @FXML
    private Button LBreturning;

    @FXML
    private Button LBsearch;

    @FXML
    private Button LBupdate;

    @FXML
    public void Listbook (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Listbook.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Loanbook (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("loanBook.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Addbook (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("addbook.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void Search (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Searchbook.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void Update (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("update.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void ReturnBook (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Returnbook.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Logout (ActionEvent event) throws IOException{
        JOptionPane.showMessageDialog(null, "Logout Successfully");
        root = FXMLLoader.load(getClass().getResource("Login1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Register (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}