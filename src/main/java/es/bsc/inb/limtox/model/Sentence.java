package es.bsc.inb.limtox.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Sentence implements LimtoxEntity {
	private Integer id;
	@JsonIgnore
	private Document document;
	
	private String text;
	
	private Integer order;
	
	private Section section;
	
	private List<ChemicalCompoundSentence> chemicalCompoundSentences = new ArrayList<ChemicalCompoundSentence>();
	
	private List<HepatotoxicityTermSentence> hepatotoxicityTermSentences = new ArrayList<HepatotoxicityTermSentence>();
	
	private List<CytochromeSentence> cytochromeSentences = new ArrayList<CytochromeSentence>();
	
	private List<MarkerSentence> markerSentences = new ArrayList<MarkerSentence>();
	
	private List<ChemicalCompoundCytochromeSentence> chemicalCompoundCytochromeSentences = new ArrayList<ChemicalCompoundCytochromeSentence>();

	private List<HepatotoxicityTermChemicalCompoundSentence> hepatotoxicityTermChemicalCompoundSentences = new ArrayList<HepatotoxicityTermChemicalCompoundSentence>();
	
	private List<MarkerChemicalCompoundSentence> markerChemicalCompoundSentences = new ArrayList<MarkerChemicalCompoundSentence>();
	
	private List<TaxonomySentence> taxonomySentences = new ArrayList<TaxonomySentence>();
	
	public Sentence() {}
	
	public Sentence(Document document, String text, Section section, int order) {
		super();
		this.document = document;
		this.text = text;
		this.section = section;
		this.order=order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public List<ChemicalCompoundSentence> getChemicalCompoundSentences() {
		return chemicalCompoundSentences;
	}

	public void setChemicalCompoundSentences(List<ChemicalCompoundSentence> chemicalCompoundSentences) {
		this.chemicalCompoundSentences = chemicalCompoundSentences;
	}

	public List<HepatotoxicityTermSentence> getHepatotoxicityTermSentences() {
		return hepatotoxicityTermSentences;
	}

	public void setHepatotoxicityTermSentences(List<HepatotoxicityTermSentence> hepatotoxicityTermSentences) {
		this.hepatotoxicityTermSentences = hepatotoxicityTermSentences;
	}

	public List<CytochromeSentence> getCytochromeSentences() {
		return cytochromeSentences;
	}

	public void setCytochromeSentences(List<CytochromeSentence> cytochromeSentences) {
		this.cytochromeSentences = cytochromeSentences;
	}

	public List<ChemicalCompoundCytochromeSentence> getChemicalCompoundCytochromeSentences() {
		return chemicalCompoundCytochromeSentences;
	}

	public void setChemicalCompoundCytochromeSentences(
			List<ChemicalCompoundCytochromeSentence> chemicalCompoundCytochromeSentences) {
		this.chemicalCompoundCytochromeSentences = chemicalCompoundCytochromeSentences;
	}

	public List<MarkerSentence> getMarkerSentences() {
		return markerSentences;
	}

	public void setMarkerSentences(List<MarkerSentence> markerSentences) {
		this.markerSentences = markerSentences;
	}

	public List<MarkerChemicalCompoundSentence> getMarkerChemicalCompoundSentences() {
		return markerChemicalCompoundSentences;
	}

	public void setMarkerChemicalCompoundSentences(List<MarkerChemicalCompoundSentence> markerChemicalCompoundSentences) {
		this.markerChemicalCompoundSentences = markerChemicalCompoundSentences;
	}

	public List<HepatotoxicityTermChemicalCompoundSentence> getHepatotoxicityTermChemicalCompoundSentences() {
		return hepatotoxicityTermChemicalCompoundSentences;
	}

	public void setHepatotoxicityTermChemicalCompoundSentences(
			List<HepatotoxicityTermChemicalCompoundSentence> hepatotoxicityTermChemicalCompoundSentences) {
		this.hepatotoxicityTermChemicalCompoundSentences = hepatotoxicityTermChemicalCompoundSentences;
	}

	
	public List<TaxonomySentence> getTaxonomySentences() {
		return taxonomySentences;
	}

	public void setTaxonomySentences(List<TaxonomySentence> taxonomySentences) {
		this.taxonomySentences = taxonomySentences;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	
	
}
