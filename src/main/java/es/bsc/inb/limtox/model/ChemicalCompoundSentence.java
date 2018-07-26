package es.bsc.inb.limtox.model;

import java.util.List;

public class ChemicalCompoundSentence extends ReletavantTermSentence{
	
	private ChemicalCompound chemicalCompound;
	
	public ChemicalCompoundSentence() {}
	
	public ChemicalCompoundSentence(ChemicalCompound chemicalCompound, Float score, Integer quantity, List<Ocurrence> ocurrences, Sentence sentence) {
		super(score, quantity, sentence, ocurrences);
		this.chemicalCompound = chemicalCompound;
	}

	public ChemicalCompound getChemicalCompound() {
		return chemicalCompound;
	}

	public void setChemicalCompound(ChemicalCompound chemicalCompound) {
		this.chemicalCompound = chemicalCompound;
	}

}
