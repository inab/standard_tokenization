package es.bsc.inb.limtox.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;



@Entity
@Table(name="chemicalcompound_hepatotoxicityterm_sentence")
public class HepatotoxicityTermChemicalCompoundSentence {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private ChemicalCompound chemicalCompound;
	
	@ManyToOne
	private HepatotoxicityTerm hepatotoxicityTerm;
	
	private RelationRule relationRule;
	
	private Float score;
	
	private Integer quantity;
	
	@ManyToOne
	@JsonIgnore
	private Sentence sentence;
		
	public HepatotoxicityTermChemicalCompoundSentence() {}
	
	public HepatotoxicityTermChemicalCompoundSentence(ChemicalCompound chemicalCompound, HepatotoxicityTerm hepatotoxicityTerm,Float score, Integer quantity, Sentence sentence) {
		this.chemicalCompound = chemicalCompound;
		this.hepatotoxicityTerm = hepatotoxicityTerm;
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

	
	public HepatotoxicityTerm getHepatotoxicityTerm() {
		return hepatotoxicityTerm;
	}

	public void setHepatotoxicityTerm(HepatotoxicityTerm hepatotoxicityTerm) {
		this.hepatotoxicityTerm = hepatotoxicityTerm;
	}

	public RelationRule getRelationRule() {
		return relationRule;
	}

	public void setRelationRule(RelationRule relationRule) {
		this.relationRule = relationRule;
	}

	
	
}
