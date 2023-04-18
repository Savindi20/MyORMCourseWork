package lk.ijse.hibernate.coursework.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javax.naming.Binding;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuFormController implements Initializable{
    public AnchorPane MyPane;
    public javafx.scene.chart.PieChart PieChart;
    public JFXButton btn;


    private void setNode(Node node) {
        MyPane.getChildren().clear();
        MyPane.getChildren().add(node);
    }
    public void onClikUser(MouseEvent mouseEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/UserForm.fxml"));
        setNode(node);
    }

    public void onClikStudent(MouseEvent mouseEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/StudentForm.fxml"));
        setNode(node);
    }

    public void onClikRoom(MouseEvent mouseEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/RoomForm.fxml"));
        setNode(node);
    }

    public void onClikReservation(MouseEvent mouseEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/ReservationForm.fxml"));
        setNode(node);
    }

    public void onClikMainManu(MouseEvent mouseEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/Dashboard.fxml"));
        setNode(node);
    }

    public void onActionDashboard(ActionEvent actionEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/Dashboard.fxml"));
        setNode(node);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn.fire();
    }
}
