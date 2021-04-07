package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import it.polito.tdp.lab04.FXMLController;
import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Model {
	private CorsoDAO corsoDAO;
	private List<Corso> lista1;
	private List<Corso> listaCorsi;
	private ObservableList<Corso> lista2 = FXCollections.observableArrayList();
	private StudenteDAO studenteDAO;
	private List<Studente> listaStudenti;
	
	
	public Model() {
		corsoDAO = new CorsoDAO();
		studenteDAO = new StudenteDAO();
		lista1 = new ArrayList<Corso>();
		listaCorsi = new ArrayList<Corso>();
		listaStudenti = new ArrayList<Studente>();
	}
	
	
	
	public CorsoDAO getCorsoDAO() {
		return corsoDAO;
	}



	public StudenteDAO getStudenteDAO() {
		return studenteDAO;
	}



	public ObservableList<Corso> getTutto() {
		lista1.addAll(corsoDAO.getTuttiICorsi());
		for(Corso c : lista1) {
			lista2.add(c);
			
		}return lista2;
	}
	public List<Corso> getCorsi(){
		listaCorsi.addAll(corsoDAO.getTuttiICorsi());
		return listaCorsi;
	}
	public List<Studente> getStudenti(){
		listaStudenti.addAll(studenteDAO.getTuttiStudenti());
		return listaStudenti;
	}
	public boolean esisteStudente(Integer matricola) {
		return studenteDAO.esisteStudente(new Studente(matricola,null, null, null));
	}
	
	
	

}
