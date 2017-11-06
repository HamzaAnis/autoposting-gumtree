package org.bst.gumtree;

import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GumTreeInputController implements ControlledScreen {

	@FXML
	TextField txtfieldCategory;
	@FXML
	TextField txtfieldLocation;
	@FXML
	TextField txtfieldTitle;
	@FXML
	TextField txtfieldYoutube;
	@FXML
	TextField txtfieldDiscription;
	@FXML
	TextField txtfieldPrice;
	@FXML
	TextField txtfieldPhone;
	@FXML
	Button buttonPost;
	@FXML
	Label lblFileName;
	@FXML
	Button buttonLoadFile;

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

	@FXML
	protected void loadFIleaction() {
	}

	@FXML
	protected void postAd() {
		System.out.println("Calling");
		if (txtfieldTitle.getText().equals("") || txtfieldCategory.getText().equals("")
				|| txtfieldPrice.getText().equals("") || txtfieldDiscription.getText().equals("")
				|| txtfieldPhone.getText().equals("") || txtfieldPrice.getText().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Enter required fields");
			alert.setContentText("Please enter all details that are necesary");
			alert.showAndWait();
			return;
		}
		String s = txtfieldDiscription.getText();
		String[] words = s.trim().split("\\s+");
		if (words.length <= 12) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Less Description");
			alert.setHeaderText("Description Field");
			alert.setContentText("12 words minimum.");
			alert.showAndWait();
			return;
		}
		myLogicalParent.postADD(txtfieldCategory.getText(), txtfieldLocation.getText(), txtfieldTitle.getText(),
				txtfieldYoutube.getText(), txtfieldDiscription.getText(), txtfieldPrice.getText(),
				txtfieldPhone.getText());
	}

}
