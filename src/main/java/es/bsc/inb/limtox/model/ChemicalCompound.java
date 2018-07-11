package es.bsc.inb.limtox.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChemicalCompound implements LimtoxEntity {
	
	private Integer id;
	
	private Integer keyId;
	
	@JsonProperty("Chemical_name")
	private String name;
	
	private Integer nameToStruct;
	
	@JsonProperty("ChemIDplus_id")
	private String chemPlusId;
	
	@JsonProperty("ChEBI_id")
	private String chebi;
	
	@JsonProperty("CAS_registry")
	private String casRegistryNumber;
	
	@JsonProperty("PubChem_compound_id")
	private String pubChemCompundId;
	
	@JsonProperty("PubChem_substance_id")
	private String pubChemSubstance;
	
	@JsonProperty("IUPAC_International_Chemical_id")
	private String inchi;
	
	@JsonProperty("Drugback_id")
	private String drugBankId;
	
	@JsonProperty("Human_metabolome_id")
	private String humanMetabolome;
	
	@JsonProperty("KEGG_compound_id")
	private String keggCompoundId; 
	
	@JsonProperty("MeSH_substance_id")
	private String meshSubstanceId; 
	
	@JsonProperty("nrDBIds")
	private String nrDBIds;
	
	@JsonProperty("SMILES")
	private String smile;
	
	
	public boolean equals(Object word_token) {
		if(word_token==null) {
			return false;
		}else {
			if(word_token.equals(this.name)) {
				return true;
			}
		}
		return false;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getNameToStruct() {
		return nameToStruct;
	}


	public void setNameToStruct(Integer nameToStruct) {
		this.nameToStruct = nameToStruct;
	}


	public String getChemPlusId() {
		return chemPlusId;
	}


	public void setChemPlusId(String chemPlusId) {
		this.chemPlusId = chemPlusId;
	}


	public String getChebi() {
		return chebi;
	}


	public void setChebi(String chebi) {
		this.chebi = chebi;
	}


	public String getCasRegistryNumber() {
		return casRegistryNumber;
	}


	public void setCasRegistryNumber(String casRegistryNumber) {
		this.casRegistryNumber = casRegistryNumber;
	}


	public String getPubChemCompundId() {
		return pubChemCompundId;
	}


	public void setPubChemCompundId(String pubChemCompundId) {
		this.pubChemCompundId = pubChemCompundId;
	}


	public String getPubChemSubstance() {
		return pubChemSubstance;
	}


	public void setPubChemSubstance(String pubChemSubstance) {
		this.pubChemSubstance = pubChemSubstance;
	}


	public String getInchi() {
		return inchi;
	}


	public void setInchi(String inchi) {
		this.inchi = inchi;
	}


	public String getDrugBankId() {
		return drugBankId;
	}


	public void setDrugBankId(String drugBankId) {
		this.drugBankId = drugBankId;
	}


	public String getHumanMetabolome() {
		return humanMetabolome;
	}


	public void setHumanMetabolome(String humanMetabolome) {
		this.humanMetabolome = humanMetabolome;
	}


	public String getKeggCompoundId() {
		return keggCompoundId;
	}


	public void setKeggCompoundId(String keggCompoundId) {
		this.keggCompoundId = keggCompoundId;
	}


	public String getMeshSubstanceId() {
		return meshSubstanceId;
	}


	public void setMeshSubstanceId(String meshSubstanceId) {
		this.meshSubstanceId = meshSubstanceId;
	}


	public String getNrDBIds() {
		return nrDBIds;
	}


	public void setNrDBIds(String nrDBIds) {
		this.nrDBIds = nrDBIds;
	}


	public String getSmile() {
		return smile;
	}


	public void setSmile(String smile) {
		this.smile = smile;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public Integer getId() {
		return id;
	}


	public Integer getKeyId() {
		return keyId;
	}


	public void setKeyId(Integer keyId) {
		this.keyId = keyId;
	}


	public void toLowerCase() {
		if(name!=null && !name.equals(name.toLowerCase())) {
			name=name.toLowerCase();
		}
	}
	
	@Override
	public int hashCode() {
	    return name.hashCode();
	}
	
	
}
