package org.bst.gumtree;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class GoogleLoginController implements ControlledScreen {
	ScreensController myScreenController;
	Main myLogicalParent;
	@FXML
	private TextField userIdField;

	@FXML
	private PasswordField passwordField;


    @Override
    public void setParents(ScreensController screenPage,Main logical) {
        myLogicalParent = logical;
        myScreenController = screenPage;
    }
    
	@FXML
	private void login() {
		String userId = userIdField.getText();
		String password = passwordField.getText();
		System.out.println(userId + "   " + password);
		// etc...
	}
}
