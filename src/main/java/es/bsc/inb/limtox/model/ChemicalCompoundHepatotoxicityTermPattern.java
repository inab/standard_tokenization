package es.bsc.inb.limtox.model;

public class ChemicalCompoundHepatotoxicityTermPattern implements LimtoxEntity{

	private String adverse_pattern;
	
	private String adverse_pattern_id;
	
	private Integer pattern_length;
	
	private String adverse_pattern_norm;

	

	public String getAdverse_pattern() {
		return adverse_pattern;
	}



	public void setAdverse_pattern(String adverse_pattern) {
		this.adverse_pattern = adverse_pattern;
	}



	public String getAdverse_pattern_id() {
		return adverse_pattern_id;
	}



	public void setAdverse_pattern_id(String adverse_pattern_id) {
		this.adverse_pattern_id = adverse_pattern_id;
	}



	public Integer getPattern_length() {
		return pattern_length;
	}



	public void setPattern_length(Integer pattern_length) {
		this.pattern_length = pattern_length;
	}



	public String getAdverse_pattern_norm() {
		return adverse_pattern_norm;
	}



	public void setAdverse_pattern_norm(String adverse_pattern_norm) {
		this.adverse_pattern_norm = adverse_pattern_norm;
	}



	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
