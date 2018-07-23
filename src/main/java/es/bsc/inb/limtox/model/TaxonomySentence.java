package es.bsc.inb.limtox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TaxonomySentence {
	private Integer id;
	
	private Taxonomy taxonomy;
	
	private Float score;
	
	private Integer quantity;
	
	@JsonIgnore
	private Sentence sentence;
		

	public TaxonomySentence() {}
	
	public TaxonomySentence(Taxonomy taxonomy, Float score, Integer quantity, Sentence sentence) {
		this.taxonomy = taxonomy;
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
	
	

	public Taxonomy getTaxonomy() {
		return taxonomy;
	}

	public void setTaxonomy(Taxonomy taxonomy) {
		this.taxonomy = taxonomy;
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
