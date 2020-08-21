package model;

import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

public class CharacterFile extends TitledPane {

	private String name, details;

	public CharacterFile(String title, Label label) {
		super(title, label);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
