package personal.learning.sovf.entity;

public class Tag {
	
	private String name;
	private String description;
	
	public Tag(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Topic [name=" + name + ", description=" + description + "]";
	}
	
}
