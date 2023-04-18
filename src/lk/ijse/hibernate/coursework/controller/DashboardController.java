package lk.ijse.hibernate.coursework.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public javafx.scene.chart.PieChart PieChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Students" , 70),
                        new PieChart.Data("available Rooms" , 30),
                        new PieChart.Data("Reserved Rooms" , 49),
                        new PieChart.Data("UnPaid Rooms" ,15));
//        pieChartData.forEach(data ->
//                data.nameProperty().bind(
//                        Bindings.concat(data.getName(),"amount", data.pieValueProperty()
//                        )
//                )
//        );
        PieChart.getData().addAll(pieChartData);
    
    }
}
