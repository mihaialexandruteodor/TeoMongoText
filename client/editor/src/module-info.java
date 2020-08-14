module editor {
	exports utils;
	exports gui;
	exports main;
	exports model;

	requires transitive ini4j;
	requires transitive java.desktop;
	requires transitive javafx.base;
	requires transitive javafx.controls;
	requires transitive javafx.fxml;
	requires transitive javafx.graphics;
	requires transitive javafx.web;
	requires transitive mongo.java.driver;
	requires transitive org.json;
	
	opens gui to javafx.fxml;
}