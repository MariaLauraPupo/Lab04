package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getTuttiStudenti(){
		
		final String sql = "SELECT * FROM studente";
		
		List<Studente> studenti = new ArrayList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Integer matricola = rs.getInt("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String cds = rs.getString("cds");

				System.out.println(matricola + " " + nome + " " + cognome + " " + cds);
 
				// Crea un nuovo JAVA Bean Corso
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("cds"));
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				studenti.add(s);
			}
			
            rs.close();
            st.close();
			conn.close();
			
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	//Tutti i corsi a cui è iscritto lo studente
	public List<Corso> getCorsiStudente(String matricola) {
		
	final String sql = "SELECT c.codins, c.nome, c.crediti, c.pd "
			+" FROM corso c, iscrizione i "
			+" WHERE c.codins=i.codins AND i.matricola=? ";
	
		//mi costruisco la struttura dati in cui saverò i miei risultati e che poi ritirnrò al chiamante 
		List<Corso> corsiStudente = new ArrayList<Corso>();
		
		//operazioni che vado ad effettuare sul database da inserire dentro un try catch
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,matricola);
			ResultSet rs = st.executeQuery();//risultato della query
			
			while(rs.next()) {
				//per ogni riga creo un nuovo corso
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				//Integer n = rs.getInt("tot");
				//questo corso lo vado ad aggiungere a result
				corsiStudente.add(c);
			}
			//chiudo le connessioni
			rs.close();
			st.close();
		    conn.close();
		
	          }catch(SQLException e){
	        	  throw new RuntimeException(e);
		
	} 
		return corsiStudente;
		}
	public boolean esisteStudente(Studente studente) {
		 String sql = "SELECT * FROM studente WHERE matricola = ?";
		   try {
			   Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1,studente.getMatricola());
				ResultSet rs = st.executeQuery();
				if(rs.next()) {
					rs.close();
					st.close();
					conn.close();
					return true;
				}else {
					rs.close();
					st.close();
					conn.close();
					return false;
				}
		   }catch(SQLException e){
			   throw new RuntimeException(e);
		   }
				   
	}
	
		
	
	}


