package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ApplicationController {
	
	@FXML
	private Button button2;
	
	@FXML
	void initialize() {
		button2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Hello World2");
			}
		});
	}
	
	public void clickMe() {
		System.out.println("Hello World");
	}
}
