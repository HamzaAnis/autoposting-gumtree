package org.bst.gumtree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

import com.csvreader.CsvReader;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
	@FXML
	Node node;
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
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(new Stage());
		System.out.println(file.getAbsolutePath());
		lblFileName.setText(file.getName());
		readCSV(file.getAbsolutePath());
	}

	public static void readCSV(String filename) {
		try {
			CsvReader products = new CsvReader(filename);

			products.readHeaders();

			while (products.readRecord()) {
				String category = products.get("Category");
				String Location = products.get("Location");
				String Title = products.get("Title");
				String Description = products.get("Description");
				String Price = products.get("Price");
				String phone = products.get("Phone");
				System.out.println(
						category + "  " + Location + "   " + Title + "  " + Description + "  " + Price + "  " + phone);
			}

			products.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	protected void postAd() {
		System.out.println("Calling Post Ad");
		if (lblFileName.getText().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Load Data File");
			alert.setHeaderText("Load file");
			alert.setContentText("Please load file then continue");
			alert.showAndWait();
		}
		else{
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

}
