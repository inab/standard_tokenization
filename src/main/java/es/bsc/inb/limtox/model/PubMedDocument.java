package es.bsc.inb.limtox.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("pubmed")
public class PubMedDocument extends Document{

	public PubMedDocument() {
		super();
	}
	
	public PubMedDocument(String sourceId) {
		super(sourceId);
	}
	
}
