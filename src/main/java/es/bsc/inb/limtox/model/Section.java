package es.bsc.inb.limtox.model;

public class Section implements LimtoxEntity {
	private Integer id;
	
	private String name;
	private String internalName;
	
	
	
	public Section(String name, String internalName) {
		super();
		this.name = name;
		this.internalName = internalName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInternalName() {
		return internalName;
	}

	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}
	
	
	
}
