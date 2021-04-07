package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);
 
				// Crea un nuovo JAVA Bean Corso
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(c);
			}
			
            rs.close();
            st.close();
			conn.close();
			
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(String nome) {
		final String sql = "SELECT s.matricola, s.nome, s.cognome, s.CDS "
				+ " FROM studente s, corso c, iscrizione i "
				+ " WHERE s.matricola = i.matricola AND c.codins = i.codins AND c.nome = ? ";
	
			//mi costruisco la struttura dati in cui saverò i miei risultati e che poi ritirnrò al chiamante 
			List<Studente> studentiCorso = new ArrayList<Studente>();
			
			//operazioni che vado ad effettuare sul database da inserire dentro un try catch
			
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1,nome);
				ResultSet rs = st.executeQuery();//risultato della query
				
				while(rs.next()) {
					//per ogni riga creo un nuovo corso
					Studente s = new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("CDS"));
					//questo corso lo vado ad aggiungere a result
					studentiCorso.add(s);
				}
				//chiudo le connessioni
				rs.close();
				st.close();
			    conn.close();
			
		          }catch(SQLException e){
		        	  throw new RuntimeException(e);
			
		} 
			return studentiCorso;
	}
	/*
	 * Data una matricola ed il codice insegnamento, vedere se lo studente è iscritto al corso.
	 */
	public Map<Integer,Corso> cercaStudenteCorso(Integer matricola, Corso corso) {
		final String sql = "SELECT c.nome s.matricola "
		+"FROM studente s, corso c, iscrizione i "
		+"WHERE s.matricola = i.matricola AND c.codins = i.codins AND c.nome = ? AND s.nome=? ";
		
		Map<Integer,Corso> mappa = new HashMap<Integer,Corso>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,corso.getNome());
			st.setInt(2, matricola);
			ResultSet rs = st.executeQuery();//risultato della query
			
			while(rs.next()) {
				//per ogni riga creo un nuovo corso
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("CDS"));
				//questo corso lo vado ad aggiungere a result
				mappa.put(s.getMatricola(),c);
			}
			//chiudo le connessioni
			rs.close();
			st.close();
		    conn.close();
		
	          }catch(SQLException e){
	        	  throw new RuntimeException(e);
		
	} 
		return mappa ;
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
