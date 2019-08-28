/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndhealthtracker;

import filewriter.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author rewil
 */
public class FXMLDocumentController implements Initializable {
    
  //~~~~Create~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    // <editor-fold>
    @FXML TextField enterName;
    @FXML TextField enterHealth;
    @FXML TextField enterB;
    @FXML TextField enterP;
    @FXML TextField enterS;
    @FXML TextField enterAcid;
    @FXML TextField enterCold;
    @FXML TextField enterFire;
    @FXML TextField enterLightning;
    @FXML TextField enterThunder;
    @FXML TextField enterPoison;
    @FXML TextField enterForce;
    @FXML TextField enterPsychic;
    @FXML TextField enterRadiant;
    @FXML TextField enterNecrotic;
    // </editor-fold>
    
    private ArrayList<TextField> enterArray = new ArrayList<>();
    
    public void saveCreate() {
        try {
            
            for(TextField t : enterArray) {
                if(t.getText().equals("")) {
                    t.setText("0");
                }
            }
            
            String name = enterName.getText();
            int health = Integer.parseInt(enterHealth.getText());
            int b = Integer.parseInt(enterB.getText());
            int p = Integer.parseInt(enterP.getText());
            int s = Integer.parseInt(enterS.getText());
            int acid = Integer.parseInt(enterAcid.getText());
            int cold = Integer.parseInt(enterCold.getText());
            int fire = Integer.parseInt(enterFire.getText());
            int lightning = Integer.parseInt(enterLightning.getText());
            int thunder = Integer.parseInt(enterThunder.getText());
            int poison = Integer.parseInt(enterPoison.getText());
            int force = Integer.parseInt(enterForce.getText());
            int psychic = Integer.parseInt(enterPsychic.getText());
            int radiant = Integer.parseInt(enterRadiant.getText());
            int necrotic = Integer.parseInt(enterNecrotic.getText());
            
            Creature c = new Creature(name, health, b, p, s, acid, cold, fire, lightning, thunder, poison, force, psychic, radiant, necrotic);
            creatureList.add(c);
            
            clearCreate();
            save();
        } catch (Exception e) {
            for(TextField t : enterArray) {
                if(t.getText().equals("0")) {
                    t.setText("");
                }
            }
        }
    }
    
    public void clearCreate() {
        for(TextField t : enterArray) {
            t.setText("");
        }
    }
    
  //~~~~Use~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    private ArrayList<Creature> creatureList = new ArrayList<>();
    @FXML ToggleGroup damage;
    private boolean autoRemove = true;
    private boolean saved = false;
    
    private Writer io = new Writer("Health Tracker");
    
    // <editor-fold>
    @FXML RadioButton useB;
    @FXML RadioButton useP;
    @FXML RadioButton useS;
    @FXML RadioButton useAcid;
    @FXML RadioButton useCold;
    @FXML RadioButton useFire;
    @FXML RadioButton useLightning;
    @FXML RadioButton useThunder;
    @FXML RadioButton usePoison;
    @FXML RadioButton useForce;
    @FXML RadioButton usePsychic;
    @FXML RadioButton useRadiant;
    @FXML RadioButton useNecrotic;
    @FXML RadioButton useTrue;
    
    @FXML TextField useHealth;
    @FXML TextField useTempHealth;
    @FXML TextField useDamage;
    @FXML TextField useHeal;
    @FXML TextField useTempHeal;
    
    @FXML ComboBox useList;
    
    @FXML Label useAutoRemove;
    @FXML Label useResult;
    @FXML Label useSave;
    // </editor-fold>
    
    public void toggleAutoRemove() {
        autoRemove = !autoRemove;
        String text = "";
        if(autoRemove) {
            text = "Removing automatically on death";
        } else {
            text = "Not removing automatically on death";
        }
        useAutoRemove.setText(text);
    }
    
    public void useRemove() {
        try {
            int index = useList.getSelectionModel().getSelectedIndex();
            creatureList.remove(index);
            updateUseList();
            useView();
            save();
        } catch (Exception e) {}
    }
    
    public void useView() {
        try {
            Creature c = getCurrentCreature();
            useHealth.setText(c.getCurrentHealth() + "");
            useTempHealth.setText(c.getTempHealth() + "");
            damage.selectToggle(null);
        } catch (Exception e) {
            useHealth.setText("");
            useTempHealth.setText("");
        }
    }
    
    public void useApply() {
        try {
            RadioButton r = (RadioButton) damage.getSelectedToggle();
            String text = r.getText();
            int value = Integer.parseInt(useDamage.getText());
            Creature c = getCurrentCreature();
            switch (text) {
                case "Bludgeoning": value -= c.getB();
                    break;
                case "Piercing": value -= c.getP();
                    break;
                case "Slashing": value -= c.getS();
                    break;
                case "Force": value -= c.getForce();
                    break;
                case "Psychic": value -= c.getPsychic();
                    break;
                case "Radiant": value -= c.getRadiant();
                    break;
                case "Necrotic": value -= c.getNecrotic();
                    break;
                case "Acid": value -= c.getAcid();
                    break;
                case "Cold": value -= c.getCold();
                    break;
                case "Fire": value -= c.getFire();
                    break;
                case "Lightning": value -= c.getLightning();
                    break;
                case "Thunder": value -= c.getThunder();
                    break;
                case "Poison": value -= c.getPoison();
                    break;
                case "True": ;
                    break;
            }
            value = value < 0 ? 0 : value;
            c.modifyCurrentHealth(-value);
            damage.selectToggle(null);
            useResult.setText(c.getName() + " lost " + value + " health");
            useDamage.setText("");
            updateHealth();
            
            if(autoRemove && c.getCurrentHealth() <= 0) {
                useRemove();
            }
        } catch (Exception e) {}
        
        
    }
    
    public void useHeal() {
        try {
            int index = useList.getSelectionModel().getSelectedIndex();
            int value = Integer.parseInt(useHeal.getText());
            Creature c = creatureList.get(index);
            c.modifyCurrentHealth(value);
            
            useResult.setText(c.getName() + " healed " + value);
            useHeal.setText("");
            updateHealth();
        } catch(Exception e) {}
    }
     
    public void useTempHeal() {
        try {
            Creature c = getCurrentCreature();
            int value = Integer.parseInt(useTempHeal.getText());
            c.addTempHealth(value);
            useResult.setText(c.getName() + " gained " + value + " temp hp");
            useTempHeal.setText("");
            updateHealth();
        } catch(Exception e) {}
    }
    
    public void updateHealth() {
        try {
            Creature c = getCurrentCreature();
            useHealth.setText("" + c.getCurrentHealth());
            useTempHealth.setText("" + c.getTempHealth());
            save();
        } catch(Exception e) {}
    }
    
    public void updateUseList() {
        ObservableList<String> noList = FXCollections.observableArrayList(new ArrayList<>());
        useList.setItems(noList);
        ObservableList<Creature> cupsList = FXCollections.observableArrayList(creatureList);
        useList.setItems(cupsList);
    }
    
    private Creature getCurrentCreature() {
        return creatureList.get(useList.getSelectionModel().getSelectedIndex());
    }
    
    public void saveList() {
        io.writeObject(creatureList, "Creatures");
        saved = true;
        useSave.setText("Saved");
    }
    
    public void save() {
        if(saved) {
            saveList();
        }
    }
    
    public void deleteList() {
        io.deleteFile("Creatures");
        saved = false;
        useSave.setText("Not Saved");
    }
    
  //~~~Popout~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    public final static ArrayList<Stage> popoutArray = new ArrayList<>();
    
    public void popoutCreature() {
        try {
            Creature c = creatureList.get(useList.getSelectionModel().getSelectedIndex());
            
            FXMLLoader loader = new FXMLLoader(popoutTracker.Popout.class.getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
            popoutTracker.FXMLDocumentController popoutController = (popoutTracker.FXMLDocumentController) loader.getController();
            
            Stage stage = new Stage();
            stage.setAlwaysOnTop(true);
            stage.setScene(new Scene(root));
            stage.setTitle(c.getName() + " - Health Tracker Popout");
            popoutArray.add(stage);
            
            popoutController.setFields(c, this);
            stage.show();
            
        } catch (Exception e) {
            System.out.println(e);
            for(StackTraceElement s : e.getStackTrace()) {
                System.out.println(s);
            }
        }
            
    }
    
    public void closePopouts() {
        for(Stage s : popoutArray) {
            s.close();
        }
    }
    
  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
// <editor-fold>
        enterArray.add(enterName);
        enterArray.add(enterHealth);
        enterArray.add(enterB);
        enterArray.add(enterP);
        enterArray.add(enterS);
        enterArray.add(enterAcid);
        enterArray.add(enterCold);
        enterArray.add(enterFire);
        enterArray.add(enterLightning);
        enterArray.add(enterThunder);
        enterArray.add(enterPoison);
        enterArray.add(enterForce);
        enterArray.add(enterPsychic);
        enterArray.add(enterRadiant);
        enterArray.add(enterNecrotic);
        // </editor-fold>
        
        if(io.listFiles().contains("Creatures")) {
            creatureList = (ArrayList<Creature>) io.readObject("Creatures");
            saveList();
        } else {
            creatureList = new ArrayList<>();
        }
    }
    
}
