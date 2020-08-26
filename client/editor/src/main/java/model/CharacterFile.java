package model;

import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

public class CharacterFile extends TitledPane {

	private String name, details, objId;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

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
