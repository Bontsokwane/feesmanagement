/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feesmanagement;


import com.jfoenix.controls.JFXTextField;
import feesmanagement.mysqldriver.MySQLHander;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


/**
 *
 * @author tbontsokwane
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private ComboBox payment_mode;
    @FXML
    private JFXTextField fee_name;
    @FXML
    private JFXTextField fee_description;
    MySQLHander dbhandler;
    
    @FXML
    private TableView<fee> feetable;
    @FXML
    private TableColumn<fee, String> namecolumn;
    @FXML
    private TableColumn<fee, String> paymentcolumn;
    @FXML
    private TableColumn<fee, String> descriptioncolumn;
    
   ObservableList<fee> list = FXCollections.observableArrayList();
    @FXML
    private AnchorPane rootPane;
    
    ObservableList<String> paymode = FXCollections.observableArrayList("Monthly","Term Wise", "Specific Day");
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        dbhandler = new MySQLHander();
        payment_mode.setValue("Term Wise");
        payment_mode.setItems(paymode);
        
         initCol();
        loadData();
    }   
    
    public void AddFee(ActionEvent event)
    {
    String name = fee_name.getText();
    String description = fee_description.getText();
    String pay = (String) payment_mode.getValue();
    
   String sq = "INSERT INTO FEE VALUES ("+
           "'"+name+"',"+
           "'"+description+"',"+
           "'"+pay+"'"+
   ")";
   System.out.println(sq);
   
   if(dbhandler.execAction(sq)){
       System.out.print("Inserting Successful....*");
   
   }else{
      System.out.print("Inserting failed");
   }
    }
    
    
     private void initCol() {
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        paymentcolumn.setCellValueFactory(new PropertyValueFactory<>("payment"));
        descriptioncolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        
    }
     
     
      private void loadData() {
        list.clear();

       
        String qu = "SELECT * FROM FEE";
        ResultSet rs = dbhandler.execQuery(qu);
        try {
            while (rs.next()) {
                String name = rs.getString("name");
                String payment = rs.getString("payment");
                String description = rs.getString("description");
                

                list.add(new fee(name, payment, description));

            }
        } catch (SQLException ex) {
            System.out.print("Error");
        }

        feetable.setItems(list);
    }
    
}
