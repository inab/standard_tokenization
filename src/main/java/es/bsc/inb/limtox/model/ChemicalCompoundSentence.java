package es.bsc.inb.limtox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ChemicalCompoundSentence {
	private Integer id;
	
	private ChemicalCompound chemicalCompound;
	
	private Float score;
	
	private Integer quantity;
	
	@JsonIgnore
	private Sentence sentence;
		

	public ChemicalCompoundSentence() {}
	
	public ChemicalCompoundSentence(ChemicalCompound chemicalCompound, Float score, Integer quantity, Sentence sentence) {
		this.chemicalCompound = chemicalCompound;
		this.score = score;
		this.quantity = quantity;
		this.sentence = sentence;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ChemicalCompound getChemicalCompound() {
		return chemicalCompound;
	}

	public void setChemicalCompound(ChemicalCompound chemicalCompound) {
		this.chemicalCompound = chemicalCompound;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Sentence getSentence() {
		return sentence;
	}

	public void setSentence(Sentence sentence) {
		this.sentence = sentence;
	}

}
