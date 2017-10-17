package org.bst.gumtree;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	WebDriver driver;
	ScreensController mainContainer;

	public Main() {
		mainContainer = new ScreensController(this);
		mainContainer.loadScreen("GoogleLogin", "GoogleLogin.fxml");
		// mainContainer.loadScreen("check","check.fxml");
		mainContainer.loadScreen("GumTree", "GumTree.fxml");
		mainContainer.setScreen("GoogleLogin");

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		AnchorPane root = new AnchorPane();
		root.getChildren().addAll(mainContainer);
		AnchorPane.setTopAnchor(mainContainer, 0.0);
		AnchorPane.setBottomAnchor(mainContainer, 0.0);
		AnchorPane.setLeftAnchor(mainContainer, 0.0);
		AnchorPane.setRightAnchor(mainContainer, 0.0);

		Scene scene = new Scene(root, 600, 600);
		primaryStage.setTitle("Gumtree Ad post");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void loadChromDriver(String username, String password) {
		System.out.println("Start");
		mainContainer.setScreen("GumTree");

		new Thread(new Runnable() {
			@Override
			public void run() {
				String exePath = "dependencies/chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", exePath);
//				driver = new ChromeDriver();
//	    		driver.get("http://google.com");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("user-data-dir=C:/Users/hamza/AppData/Local/Google/Chrome/User Data");
				driver = new ChromeDriver(options);
				driver.navigate().to("http://www.google.com");
//				loginGumtree();
				// loginGoogle(username, password); // start the google login
			}
		}).start();
	}

	public void loginGumtree() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Login Gumtree");
				driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/button")).click();
			}
		}).start();
	}

	public void loginGoogle(String username, String Password) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				driver.get(
						"https://accounts.google.com/signin/v2/identifier?flowName=GlifWebSignIn&flowEntry=ServiceLogin");
			}
		}).start();
	}
}
