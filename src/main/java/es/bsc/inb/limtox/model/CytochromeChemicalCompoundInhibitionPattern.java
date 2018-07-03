package es.bsc.inb.limtox.model;

public class CytochromeChemicalCompoundInhibitionPattern implements LimtoxEntity{

	private String cyp_inhibition_pattern_norm;
	
	private String cyp_inhibition_pattern_id;
	
	private Integer pattern_length;
	
	private String cyp_inhibition_pattern;

	

	public String getCyp_inhibition_pattern_norm() {
		return cyp_inhibition_pattern_norm;
	}



	public void setCyp_inhibition_pattern_norm(String cyp_inhibition_pattern_norm) {
		this.cyp_inhibition_pattern_norm = cyp_inhibition_pattern_norm;
	}



	public String getCyp_inhibition_pattern_id() {
		return cyp_inhibition_pattern_id;
	}



	public void setCyp_inhibition_pattern_id(String cyp_inhibition_pattern_id) {
		this.cyp_inhibition_pattern_id = cyp_inhibition_pattern_id;
	}



	public Integer getPattern_length() {
		return pattern_length;
	}



	public void setPattern_length(Integer pattern_length) {
		this.pattern_length = pattern_length;
	}



	public String getCyp_inhibition_pattern() {
		return cyp_inhibition_pattern;
	}



	public void setCyp_inhibition_pattern(String cyp_inhibition_pattern) {
		this.cyp_inhibition_pattern = cyp_inhibition_pattern;
	}



	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
