package es.bsc.inb.limtox.model;

public class CytochromeChemicalCompoundInductionPattern implements LimtoxEntity{

	private String cyp_induction_pattern_norm;
	
	private String cyp_induction_pattern_id;
	
	private Integer pattern_length;
	
	private String cyp_induction_pattern;

	public String getCyp_induction_pattern_norm() {
		return cyp_induction_pattern_norm;
	}

	public void setCyp_induction_pattern_norm(String cyp_induction_pattern_norm) {
		this.cyp_induction_pattern_norm = cyp_induction_pattern_norm;
	}

	public String getCyp_induction_pattern_id() {
		return cyp_induction_pattern_id;
	}

	public void setCyp_induction_pattern_id(String cyp_induction_pattern_id) {
		this.cyp_induction_pattern_id = cyp_induction_pattern_id;
	}

	public Integer getPattern_length() {
		return pattern_length;
	}

	public void setPattern_length(Integer pattern_length) {
		this.pattern_length = pattern_length;
	}

	public String getCyp_induction_pattern() {
		return cyp_induction_pattern;
	}



	public void setCyp_induction_pattern(String cyp_induction_pattern) {
		this.cyp_induction_pattern = cyp_induction_pattern;
	}



	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
