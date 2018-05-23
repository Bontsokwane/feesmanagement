/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feesmanagement;

import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class StudentManagement extends BorderPane{
    
    private JFXListView<Label> mainMenu;
    private AnchorPane dashboardUI, enrolmentUI;
    public static StackPane STUDENT_MAN_STACK;
    
    
    
    public StudentManagement() {
        
        getStyleClass().add("container");
        
        //-- Student Management Menu --
        mainMenu = new JFXListView<>();
        mainMenu.getStyleClass().add("main_menu");
        
        //-- Menu Items --
        Label dashboard = new Label("Dashboard");
        Label enrolment = new Label("Student Enrolment");
        Label attendance = new Label("Attendance");
        Label hostel = new Label("Hostel Management");
       

        mainMenu.getItems().addAll(dashboard, enrolment, attendance,
                                   hostel);
        
        //-- set the first item selected --
        mainMenu.getSelectionModel().select(0);
        
        mainMenu.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            switch (newValue.intValue()){
                case 0:
                   // SMS.setNode(STUDENT_MAN_STACK, dashboardUI);
                    break;
                case 1:
                    //SMS.setNode(STUDENT_MAN_STACK, enrolmentUI);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        });
        
        setLeft(mainMenu);
        
        try {
            //-- Student Management Views
            dashboardUI = FXMLLoader.load(getClass().getResource("/studentmanagement/view/dashboard.fxml"));
            
            enrolmentUI = FXMLLoader.load(getClass().getResource("/studentmanagement/view/studentEnrolment.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        STUDENT_MAN_STACK = new StackPane(enrolmentUI, dashboardUI);
        
        setCenter(STUDENT_MAN_STACK);
        
    }
    
}

