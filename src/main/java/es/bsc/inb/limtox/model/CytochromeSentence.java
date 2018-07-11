package es.bsc.inb.limtox.model;

import org.codehaus.jackson.annotate.JsonIgnore;



public class CytochromeSentence {
	private Integer id;
	
	private Cytochrome cytochrome;
	
	private Float score;
	
	private Integer quantity;
	
	@JsonIgnore
	private Sentence sentence;
		

	public CytochromeSentence() {}
	
	public CytochromeSentence(Cytochrome cytochrome, Float score, Integer quantity, Sentence sentence) {
		this.cytochrome = cytochrome;
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
	
	

	public Cytochrome getCytochrome() {
		return cytochrome;
	}

	public void setCytochrome(Cytochrome cytochrome) {
		this.cytochrome = cytochrome;
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
