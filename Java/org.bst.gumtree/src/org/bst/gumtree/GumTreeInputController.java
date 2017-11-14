package org.bst.gumtree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

	static ArrayList<Ad> data = new ArrayList<Ad>();
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
		try {
			readExcel(file.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void readExcel(String fileName) throws IOException {
		File excel = new File(fileName);
		FileInputStream fis = new FileInputStream(excel);
		XSSFWorkbook book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheetAt(0);
		Iterator<Row> itr = sheet.iterator();
		while (itr.hasNext()) {
			Row row = itr.next(); // Iterating over each column of Excel file
			Iterator<Cell> cellIterator = row.cellIterator();
			ArrayList<String> tempD = new ArrayList<String>();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue() + "\t");
					tempD.add(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue() + "\t");
					tempD.add(Double.toString(cell.getNumericCellValue()));
					break;
				default:
				}
			}
			Ad temp = new Ad("furniture", tempD.get(0), tempD.get(1), tempD.get(2), tempD.get(3), tempD.get(4),
					tempD.get(5), tempD.get(6), tempD.get(7), tempD.get(8), tempD.get(9), tempD.get(10), tempD.get(11),
					tempD.get(12), tempD.get(13));
			data.add(temp);
			System.out.println("");
		}

	}

	// public static void readCSV(String filename) {
	// try {
	// CsvReader products = new CsvReader(filename);
	//
	// products.readHeaders();
	//
	// while (products.readRecord()) {
	// String category = products.get("Category");
	// String Location = products.get("Location");
	// String Title = products.get("Title");
	// String Description = products.get("Description");
	// String Price = products.get("Price");
	// String phone = products.get("Phone");
	// ArrayList<String> Images = new ArrayList<String>();
	// for (int i = 1; i < 10; i++) {
	// String toPush = products.get("Image" + i);
	// System.out.println(toPush);
	// Images.add(toPush);
	// }
	// System.out.println("The size of the Image is " + Images.size());
	// Ad temp = new Ad("furniture", Location, Title, Description, Price, phone,
	// Images);
	// data.add(temp);
	// temp.toString();
	// System.out.println("Categotry is " + category);
	// System.out.println("Location is " + Location);
	// System.out.println("Price is " + Price);
	// }
	//
	// products.close();
	//
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	@FXML
	protected void postAd() {
		Thread addThread = new Thread(new Runnable() {
			public void run() {
				System.out.println("Calling Post Ad");
				if (lblFileName.getText().equals("")) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Load Data File");
					alert.setHeaderText("Load file");
					alert.setContentText("Please load file then continue");
					alert.showAndWait();
				} else {
					for (int l = 0; l < data.size(); l++) {
						final int parameter = l;
						Thread t = new Thread(new Runnable() {
							public void run() {
								int l = parameter;
								Ad temp = data.get(l);
								txtfieldCategory.clear();
								txtfieldDiscription.clear();
								txtfieldLocation.clear();
								txtfieldPhone.clear();
								txtfieldPrice.clear();
								txtfieldTitle.clear();

								txtfieldCategory.setText(temp.getCategory());
								txtfieldDiscription.setText(temp.getDescription());
								txtfieldLocation.setText(temp.getLocation());
								txtfieldPhone.setText(temp.getPhone());
								txtfieldPrice.setText(temp.getPrice());
								txtfieldTitle.setText(temp.getTitle());

								if (txtfieldTitle.getText().equals("") || txtfieldCategory.getText().equals("")
										|| txtfieldPrice.getText().equals("")
										|| txtfieldDiscription.getText().equals("")) {
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
								myLogicalParent.postADD(temp.getCategory(), temp.getLocation(), temp.getTitle(),
										txtfieldYoutube.getText(), temp.getDescription(), temp.getPrice(),
										temp.getPhone(), temp.Images);

							}
						});
						t.start();
						try {
							t.join();

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						System.out
								.println("\n\n\\n\n\n\n\n\\t\t\t\t\t\tThread RELEASES WIth L = " + l + "\n\n\n\n\n\n");
					} // for loop

				}
			}
		});
		addThread.start();
	}
}