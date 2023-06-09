package lk.ijse.hibernate.coursework.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import lk.ijse.hibernate.coursework.bo.BOFactory;
import lk.ijse.hibernate.coursework.bo.custom.StudentBO;
import lk.ijse.hibernate.coursework.dto.StudentDTO;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentFormController implements Initializable {

    public JFXComboBox cmbGender;
    public JFXTextField txtStudentID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;

    public TableView tblStudent;
    public TableColumn colStudentID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colGender;

    public TableColumn colDBO;
    public JFXDatePicker dobPicker;
    public Label lblStudentName;
    public Label lblStudentContact;
    public Label lblStudentAddress;

    private Matcher studentNameMatcher;
    private  Matcher studentAddressMatcher;
    private Matcher studentContactMatcher;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    private void setDataTable() {
        tblStudent.getItems().clear();
        try {
            for (StudentDTO s : studentBO.getAllStudent()) {
                tblStudent.getItems().add(new StudentDTO(
                        s.getStudent_id(),
                        s.getName(),
                        s.getAddress(),
                        s.getContact_no(),
                        s.getDob(),
                        s.getGender()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SaveStudentOnAction(ActionEvent actionEvent) {

        String studentID = txtStudentID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String date = String.valueOf(dobPicker.getValue());
        String gender = String.valueOf(cmbGender.getValue());

        if (studentNameMatcher.matches()) {
            if (studentAddressMatcher.matches()) {
                if (studentContactMatcher.matches()) {
                    try {
                        if (studentBO.saveStudent(new StudentDTO(studentID, name, address, contact, date, gender))) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Saved..!").show();
                            setDataTable();
                            Clear();
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    txtContact.requestFocus();
                    txtContact.setFocusColor(Paint.valueOf("Red"));
                    lblStudentContact.setText("Invalid Student Contact");
                }
            }else {
                txtAddress.requestFocus();
                txtAddress.setFocusColor(Paint.valueOf("Red"));
                lblStudentAddress.setText("Invalid Student Address");
            }
        }else {
            txtName.requestFocus();
            txtName.setFocusColor(Paint.valueOf("Red"));
            lblStudentName.setText("Invalid Student Name");
        }
    }

    public void UpdateStudentOnAction(ActionEvent actionEvent) {
        StudentDTO student = new StudentDTO(
                txtStudentID.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtContact.getText(),
                String.valueOf(dobPicker.getValue()),
                String.valueOf(cmbGender.getValue()));

        try {
            if (studentBO.updateStudent(student)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Ok").show();
                setDataTable();
                Clear();
            } else {
                new Alert(Alert.AlertType.ERROR, "Try Again..!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteStudentOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are Your Sure ! ", ButtonType.NO, ButtonType.YES);
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            try {
                if (studentBO.deleteStudent(txtStudentID.getText())) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Ok").show();
                    setDataTable();
                    Clear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void SearchStudentOnAction(ActionEvent actionEvent) {
        StudentDTO student;
        try {
            student = studentBO.searchStudent(txtStudentID.getText());
            if (student != null) {
                fillData(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillData(StudentDTO student) {
        txtStudentID.setText(student.getStudent_id());
        txtName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtContact.setText(student.getContact_no());
       dobPicker.setValue(LocalDate.parse(student.getDob()));
        cmbGender.setValue(student.getGender());
    }

    public void setTblValues() {
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colDBO.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    private void setGender() {
        String[] gender = {"Male", "Female"};
        cmbGender.getItems().addAll(gender);
    }

    public void txtStudentNameOnKeyType(KeyEvent keyEvent) {
        lblStudentName.setText("");
        txtName.setFocusColor(Paint.valueOf("Blue"));
        Pattern studentNamePattern=Pattern.compile("^[a-zA-Z]{4,}$");
        studentNameMatcher=studentNamePattern.matcher(txtName.getText());

        if (!studentNameMatcher.matches()){
            txtName.requestFocus();
            txtName.setFocusColor(Paint.valueOf("Red"));
            lblStudentName.setText("Invalid User Name");
        }
    }

    public void txtStudentAddressOnKeyType(KeyEvent keyEvent) {
        lblStudentAddress.setText("");
        txtAddress.setFocusColor(Paint.valueOf("Blue"));
        Pattern studentAddressPattern=Pattern.compile("^[a-zA-Z0-9]{4,}$");
        studentAddressMatcher=studentAddressPattern.matcher(txtAddress.getText());

        if (!studentAddressMatcher.matches()){
            txtAddress.requestFocus();
            txtAddress.setFocusColor(Paint.valueOf("Red"));
            lblStudentAddress.setText("Invalid User Name");
        }
    }

    public void txtStudentContacOnKeyType(KeyEvent keyEvent) {
        lblStudentContact.setText("");
        txtContact.setFocusColor(Paint.valueOf("Blue"));
        Pattern studentContactPattern=Pattern.compile("^\\+?(94)?(0)?\\d{9}$");
        studentContactMatcher=studentContactPattern.matcher(txtContact.getText());

        if (!studentContactMatcher.matches()){
            txtContact.requestFocus();
            txtContact.setFocusColor(Paint.valueOf("Red"));
            lblStudentContact.setText("Invalid User Name");
        }
    }

    private  void setPatterns(){
        Pattern studentNamePattern = Pattern.compile("^[a-zA-Z]{4,}$");
        studentNameMatcher = studentNamePattern.matcher(txtName.getText());
        Pattern studentAddressPattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        studentAddressMatcher = studentAddressPattern.matcher(txtAddress.getText());
        Pattern studentContactPattern = Pattern.compile("^\\+?(94)?(0)?\\d{9}$");
        studentContactMatcher = studentContactPattern.matcher(txtContact.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setGender();
        setDataTable();
        setTblValues();
        setPatterns();
    }


    private void Clear() {
        txtStudentID.clear();
        txtAddress.clear();
        txtContact.clear();
        txtName.clear();
        dobPicker.setValue(null);
        cmbGender.setValue(null);
    }

}
