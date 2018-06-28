package es.bsc.inb.limtox.model;

public class CytochromeChemicalCompoundPattern implements LimtoxEntity{

	private String substrate_pattern_norm;
	
	private String substrate_pattern_id;
	
	private Integer pattern_length;
	
	private String substrate_pattern;

	public String getSubstrate_pattern_norm() {
		return substrate_pattern_norm;
	}

	public void setSubstrate_pattern_norm(String substrate_pattern_norm) {
		this.substrate_pattern_norm = substrate_pattern_norm;
	}

	public String getSubstrate_pattern_id() {
		return substrate_pattern_id;
	}

	public void setSubstrate_pattern_id(String substrate_pattern_id) {
		this.substrate_pattern_id = substrate_pattern_id;
	}

	public Integer getPattern_length() {
		return pattern_length;
	}

	public void setPattern_length(Integer pattern_length) {
		this.pattern_length = pattern_length;
	}

	public String getSubstrate_pattern() {
		return substrate_pattern;
	}

	public void setSubstrate_pattern(String substrate_pattern) {
		this.substrate_pattern = substrate_pattern;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
