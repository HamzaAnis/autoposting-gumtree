package org.bst.gumtree;

import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GumTreeInputController implements ControlledScreen {

	ScreensController myScreenController;
	Main myLogicalParent;

	@Override
	public void setParents(ScreensController screenPage, Main logical) {
		myLogicalParent = logical;
		myScreenController = screenPage;
	}

	@FXML
	protected void sendMassage() {
		System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
		System.out.println("Send");
	}
	
}
