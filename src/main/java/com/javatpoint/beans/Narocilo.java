package com.javatpoint.beans;

import java.sql.Timestamp;


import javax.xml.bind.annotation.*;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



public class Narocilo {
	
	
	
	public Narocilo(int sobaId, String opis, Timestamp datumPopravila) {
		super();
		this.sobaId = sobaId;
		this.opis = opis;
		this.datumPopravila = datumPopravila;
	}
	
	public int narociloId;
	public int sobaId;
	public String opis;
	public Timestamp datumPopravila;
	
	public Narocilo() {
		// TODO Auto-generated constructor stub
	}
	
	public int getNarociloId() {
		return narociloId;
	}

	public void setNarociloId(int narociloId) {
		this.narociloId = narociloId;
	}

	public int getSobaId() {
		return sobaId;
	}

	public void setSobaId(int sobaId) {
		this.sobaId = sobaId;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Timestamp getDatumPopravila() {
		return datumPopravila;
	}

	//@JsonSerialize(using = MyDateSerializer.class)
	public void setDatumPopravila(Timestamp datumPopravila) {
		this.datumPopravila = datumPopravila;
	}
	
	
}