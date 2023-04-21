package lk.ijse.hibernate.coursework.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import lk.ijse.hibernate.coursework.bo.BOFactory;
import lk.ijse.hibernate.coursework.bo.custom.UserBO;
import lk.ijse.hibernate.coursework.dto.UserDTO;
import lk.ijse.hibernate.coursework.entity.User;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserFormController implements Initializable {

    public JFXTextField txtUserID;
    public JFXTextField txtPassword;
    public JFXTextField txtUserName;
    public JFXButton btnSave;
    public Label lblUserName;
    public Label lblPassword;

    private Matcher userNameMatcher;
    private  Matcher passwordMatcher;


    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void saveUserOnAction(ActionEvent actionEvent) {
        String userID = txtUserID.getText();
        String password = txtPassword.getText();
        String userName = txtUserName.getText();
        if (userNameMatcher.matches()) {
            if (passwordMatcher.matches()) {
                try {
                    if (userBO.saveUser(new UserDTO(userID, userName, password))) {
                        Clear();
                        new Alert(Alert.AlertType.CONFIRMATION, "Saved..!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                txtPassword.requestFocus();
                txtPassword.setFocusColor(Paint.valueOf("Red"));
                lblPassword.setText("Invalid Password");
            }
        }else {
            txtUserName.requestFocus();
            txtUserName.setFocusColor(Paint.valueOf("Red"));
            lblUserName.setText("Invalid User Name");
        }

    }

    //Delete
    public void DeleteUserOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are Your Sure ! ", ButtonType.NO, ButtonType.YES);
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            try {
                if (userBO.deleteUser(txtUserID.getText())) {
                    Clear();
                    new Alert(Alert.AlertType.CONFIRMATION, "Ok").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Update
    public void UpdateUserOnAction(ActionEvent actionEvent) {
        UserDTO user = new UserDTO(
                txtUserID.getText(),
                txtUserName.getText(),
                txtPassword.getText());
        try {
            if (userBO.updateUser(user)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Ok").show();

                Clear();
            }else {
                new Alert(Alert.AlertType.ERROR, "Try Again..!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Search
    public void onActionSearchUser(ActionEvent actionEvent) {
        UserDTO user;
        try {
            user = userBO.searchUser(txtUserID.getText());
            if (user != null) {
                fillData(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillData(UserDTO user) {
        txtPassword.setText(user.getPassword());
        txtUserName.setText(user.getUser_name());
    }

    private void Clear() {
        txtUserID.clear();
        txtUserName.clear();
        txtPassword.clear();
    }
   //============= rejex


    private  void setPatterns(){
        Pattern userNamePattern = Pattern.compile("^[a-zA-Z_-]{4,10}$");
        userNameMatcher = userNamePattern.matcher(txtUserName.getText());
        Pattern PasswordPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
        passwordMatcher = PasswordPattern.matcher(txtPassword.getText());
    }

    public void txtUserNameOnKeyType(KeyEvent keyEvent) {
        lblUserName.setText("");
        txtUserName.setFocusColor(Paint.valueOf("Blue"));
        Pattern userNamePattern=Pattern.compile("^[a-zA-Z_-]{4,10}$");
        userNameMatcher=userNamePattern.matcher(txtUserName.getText());

        if (!userNameMatcher.matches()){
            txtUserName.requestFocus();
            txtUserName.setFocusColor(Paint.valueOf("Red"));
            lblUserName.setText("Invalid User Name");
        }
    }

    public void txtPasswordOnKeyType(KeyEvent keyEvent) {
        lblPassword.setText("");
        txtPassword.setFocusColor(Paint.valueOf("Blue"));
        Pattern PasswordPattern=Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
        passwordMatcher=PasswordPattern.matcher(txtPassword.getText());

        if (!passwordMatcher.matches()){
            txtPassword.requestFocus();
            txtPassword.setFocusColor(Paint.valueOf("Red"));
            lblPassword.setText("Invalid Password");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPatterns();
    }


}

