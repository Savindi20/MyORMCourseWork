package lk.ijse.hibernate.coursework.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
public class MainMenuFormController {
    public AnchorPane MyPane;


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
}
