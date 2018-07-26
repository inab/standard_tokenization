package es.bsc.inb.limtox.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


public class PubMedDocument extends Document{

	public PubMedDocument() {
		super();
	}
	
	public PubMedDocument(String sourceId, String outputPath) {
		super(sourceId, outputPath);
	}
	
}
