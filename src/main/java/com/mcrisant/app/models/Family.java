package com.mcrisant.app.models;

public class Family {

	private String nomFamilia;
	private Member padres;
	private Member hermanos;
	private Member conyuge;

	public Family() {
	}

	public String getNomFamilia() {
		return nomFamilia;
	}

	public void setNomFamilia(String nomFamilia) {
		this.nomFamilia = nomFamilia;
	}

	public Member getPadres() {
		return padres;
	}

	public void setPadres(Member padres) {
		this.padres = padres;
	}

	public Member getHermanos() {
		return hermanos;
	}

	public void setHermanos(Member hermanos) {
		this.hermanos = hermanos;
	}

	public Member getConyuge() {
		return conyuge;
	}

	public void setConyuge(Member conyuge) {
		this.conyuge = conyuge;
	}

}
