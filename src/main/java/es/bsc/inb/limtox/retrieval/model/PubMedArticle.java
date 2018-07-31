package es.bsc.inb.limtox.retrieval.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pubmed_articles")
public class PubMedArticle {
	@Id
	private Integer id;
	
	private String pmid;
	
	@Column(name="txt_path")
	private String filePath;
	
	@Column(name="filename")
	private String fileName;
	
	@Column(name="txt")
	private Boolean plainTextGenerated;
	
	@Column(name="json")
	private Boolean jsonFindingGenerated;
	
	@Column(name="relevant")
	private Boolean relevant;
	
	@Column(name="tokenization_processed")
	private Boolean tokenizationProcessed;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPmid() {
		return pmid;
	}

	public void setPmid(String pmid) {
		this.pmid = pmid;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Boolean getPlainTextGenerated() {
		return plainTextGenerated;
	}

	public void setPlainTextGenerated(Boolean plainTextGenerated) {
		this.plainTextGenerated = plainTextGenerated;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Boolean getJsonFindingGenerated() {
		return jsonFindingGenerated;
	}

	public void setJsonFindingGenerated(Boolean jsonFindingGenerated) {
		this.jsonFindingGenerated = jsonFindingGenerated;
	}

	public Boolean getRelevant() {
		return relevant;
	}

	public void setRelevant(Boolean relevant) {
		this.relevant = relevant;
	}

	public Boolean getTokenizationProcessed() {
		return tokenizationProcessed;
	}

	public void setTokenizationProcessed(Boolean tokenizationProcessed) {
		this.tokenizationProcessed = tokenizationProcessed;
	}
	
	
	
}
