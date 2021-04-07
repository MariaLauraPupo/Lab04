package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	
	@FXML
    private ComboBox<Corso> comboBox;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextArea txtResult;

    @FXML
    void cercaCorsi(ActionEvent event) {
    	
    	String matricola = txtMatricola.getText();
    	int matr;/* = Integer.parseInt(matricola);*/
    	/*for(Studente s : model.getStudenti()) {
    		if(matr!=s.getMatricola())
    			txtResult.setText("Lo studente non esiste");	
    	}*/
    	try {
    		matr = Integer.parseInt(matricola); 		
    	}catch(NumberFormatException e) {
    		txtResult.setText("Devi inserire un numero");
    	    return;//non posso andare avanti con il metodo
    	}
    	if(model.esisteStudente(matr) == false) { /*???*/
    		txtResult.setText("lo studente non esiste");
    	}
    	/*if(model.getStudenti().contains(new Studente(matr,null,null,null))==false) {
    		txtResult.setText("lo studente non esiste");

    	}*/
    	txtResult.setStyle("-fx-font-family: monospace");
    	StringBuilder sb = new StringBuilder();
    	
           for (Corso c : model.getStudenteDAO().getCorsiStudente(matricola)){
    		
    		sb.append(String.format("%-8s ",c.getCondins()));
    		sb.append(String.format("%-50s ",c.getNome()));
    		sb.append(String.format("%-4d ",c.getCrediti()));
    		sb.append(String.format("%-4d\n",c.getPd()));
    	}
           txtResult.setText(sb.toString());
    	/*String elencoCorsi="";   		
    	for(Corso c : model.getStudenteDAO().getCorsiStudente(matricola)) {
    		elencoCorsi= elencoCorsi + c.getNome() +"\n";
    		txtResult.setText(elencoCorsi);
    	}*/
    	

    }

    @FXML
    void completamentoAutomatico(ActionEvent event) {
    	String matr = txtMatricola.getText();
    	Integer matrNum; /*= Integer.parseInt(matr);*/
    	try {
    		matrNum = Integer.parseInt(matr); 		
    	}catch(NumberFormatException e) {
    		txtResult.setText("Devi inserire un numero");
    	    return;//non posso andare avanti con il metodo
    	}
    	if(model.esisteStudente(matrNum) == false) {
    		txtResult.setText("lo studente non esiste");
    	}
    
    	for(Studente s:model.getStudenteDAO().getTuttiStudenti()) {
    		if(s.getMatricola().equals(matrNum)) {
    			txtNome.setText(s.getNome());
    			txtCognome.setText(s.getCognome());
    		}
    	}

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    }

    @FXML
    void iscrittiCorso(ActionEvent event) {
    	
    	Corso corso = comboBox.getValue();
    	
    //controllo corso = null
    	/*if(corso == null) {
    		txtResult.appendText("il corso non è stato inserito");
    	}*/
    
    	/*String elencoStudenti="";
    	for(Studente s : model.getCorsoDAO().getStudentiIscrittiAlCorso(corso.getNome())) {
    		elencoStudenti = elencoStudenti + s.toString()+ "\n";
    		txtResult.setText(elencoStudenti);
    	}*/
    	txtResult.setStyle("-fx-font-family: monospace"); //per allineare tutto nel'area di testo
    	StringBuilder sb = new StringBuilder();
    	if(corso!=null) {
    	for (Studente s : model.getCorsoDAO().getStudentiIscrittiAlCorso(corso.getNome())){
    		
    		sb.append(String.format("%-6d ",s.getMatricola()));
    		sb.append(String.format("%-50s ",s.getNome()));
    		sb.append(String.format("%-50s ",s.getCognome()));
    		sb.append(String.format("%-20s\n",s.getCds()));
    	}
    	txtResult.appendText(sb.toString());
    	}
    	else {
    		txtResult.appendText("il corso non è stato inserito");
    	}
    	
    	//txtResult.appendText(sb.toString());
    	//txtResult.appendText("il corso non è stato inserito");
    	
    }
    @FXML
    void cerca(ActionEvent event) {
    	Corso corso = comboBox.getValue();
    	String matricola = txtMatricola.getText();
    	int matr;
    	try {
    		matr = Integer.parseInt(matricola); 		
    	}catch(NumberFormatException e) {
    		txtResult.setText("Devi inserire un numero");
    	    return;//non posso andare avanti con il metodo
    	}
    	if(model.esisteStudente(matr) == false) {
    		txtResult.setText("lo studente non esiste");
    	}
    	if(corso==null ) {
    		txtResult.appendText("devi selezionare un corso");
    		return;
    	}
    	

    }

    @FXML
    void iscrivi(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        
    }
    public void setModel(Model model) {
    	this.model=model;
    	comboBox.getItems().addAll(model.getTutto());
    	comboBox.getItems().add(null);
    }
}
