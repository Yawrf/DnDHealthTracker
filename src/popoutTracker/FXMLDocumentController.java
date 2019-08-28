/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popoutTracker;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author rewil
 */
public class FXMLDocumentController implements Initializable {
    
    private dndhealthtracker.Creature creature;
    private dndhealthtracker.FXMLDocumentController parent;
    
    public void setFields(dndhealthtracker.Creature c, dndhealthtracker.FXMLDocumentController parent) {
        creature = c;
        this.parent = parent;
        healthField.setText(c.getCurrentHealth() + "");
    }
    
  //~~~Use~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    @FXML TextField healthField;
    @FXML TextField damageField;
    
    public void applyDamage() {
        try {
            int dam = Integer.parseInt(damageField.getText());
            creature.modifyCurrentHealth(-dam);
            damageField.setText("");
            healthField.setText(creature.getCurrentHealth() + "");
            parent.save();
        } catch (Exception e) {}
    }
    
  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
