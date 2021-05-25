/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.extflightdelays.model.Model;
import it.polito.tdp.extflightdelays.model.Rotta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="distanzaMinima"
    private TextField distanzaMinima; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	
    	txtResult.clear();
    	int distanza = 0;
    	
    	try {
    		
    		distanza=Integer.parseInt(distanzaMinima.getText());
    		
    		
    	}catch(NumberFormatException e) {
    		txtResult.setText("Inserire una distanza minima in formato numerico");
    		
    		
    		
    	}
    	
    	
    	if(distanza==0) {
    		txtResult.setText("Inserire una distanza minima ");
    	}
    	
    	
    	model.creaGrafo(distanza);
    	txtResult.appendText("Grafo creato!\n\n");
    	txtResult.appendText("Numero di vertici: "+model.numVertici());
    	txtResult.appendText("\n\n");
    	txtResult.appendText("Numero di archi: "+model.numArchi());
    	txtResult.appendText("\n\n");
    	
    	
    	for(Rotta rotta: model.getRotte()) {
    		
    		
    		txtResult.appendText(rotta.toString()+"\n");
    		
    	}
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert distanzaMinima != null : "fx:id=\"distanzaMinima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
