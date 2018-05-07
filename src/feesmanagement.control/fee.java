/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feesmanagement;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author tbontsokwane
 */
public class fee {
   

   SimpleStringProperty name;
   SimpleStringProperty payment;
   SimpleStringProperty description;
           
 

        public fee(String name, String payment, String description) {
            this.name = new SimpleStringProperty(name);
            this.payment = new SimpleStringProperty(payment);
            this.description = new SimpleStringProperty(description);
        }

        public String getName() {
            return name.get();
        }

        public String getPayment() {
            return payment.get();
        }

        public String getDescription() {
            return description.get();
        }

    
}
