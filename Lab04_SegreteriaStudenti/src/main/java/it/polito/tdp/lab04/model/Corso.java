package it.polito.tdp.lab04.model;

public class Corso {
	private String condins;
	private Integer crediti;
	private String nome;
	private Integer pd;
	
	public Corso(String condins, Integer crediti, String nome, Integer pd) {
		super();
		this.condins = condins;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
	}
	public String getCondins() {
		return condins;
	}
	public void setCondins(String condins) {
		this.condins = condins;
	}
	public Integer getCrediti() {
		return crediti;
	}
	public void setCrediti(Integer crediti) {
		this.crediti = crediti;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPd() {
		return pd;
	}
	public void setPd(Integer pd) {
		this.pd = pd;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((condins == null) ? 0 : condins.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (condins == null) {
			if (other.condins != null)
				return false;
		} else if (!condins.equals(other.condins))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return nome;
	}

	
	
	
}
