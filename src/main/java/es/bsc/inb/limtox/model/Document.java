package es.bsc.inb.limtox.model;

import java.util.ArrayList;
import java.util.List;



public abstract class Document implements LimtoxEntity {

	private Integer id;
	
	private String sourceId;
	
	private List<Sentence> sentences = new ArrayList<Sentence>();
	
	private List<MeshChemicalCompound> meshChemicalCompounds = new ArrayList<MeshChemicalCompound>();
	
	public Document() {
		super();
	}
	public Document(String sourceId) {
		this.sourceId=sourceId;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public List<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public List<MeshChemicalCompound> getMeshChemicalCompounds() {
		return meshChemicalCompounds;
	}
	public void setMeshChemicalCompounds(List<MeshChemicalCompound> meshChemicalCompounds) {
		this.meshChemicalCompounds = meshChemicalCompounds;
	}
	
	
	
	
	
}
