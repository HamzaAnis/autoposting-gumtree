package org.bst.gumtree;

import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.sun.glass.ui.Robot;
import com.sun.javafx.tk.Toolkit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	WebDriver driver;
	ScreensController mainContainer;
	boolean loggedIn = false;
	boolean started = false;

	public Main() {
		mainContainer = new ScreensController(this);
		mainContainer.loadScreen("GoogleLogin", "GoogleLogin.fxml");
		mainContainer.loadScreen("GumTree", "GumTree.fxml");
		mainContainer.loadScreen("LoadingScreen", "LoadingScreen.fxml");
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
		primaryStage.setOnCloseRequest(e -> {
			// ((LoadingScreenController)
			// mainContainer.getScreenController("LoadingScreen"))
			// .setText("Closing the Program");
			mainContainer.setScreen("LoadingScreen");
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("Driver is ==" + driver);
					System.out.println("Calling exit ");
					if (driver != null) {
						driver.quit();
						Platform.exit();
						System.exit(0);
					}
				}
			}).start();

		});

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void loadChromDriver(String username, String password) {
		System.out.println("LoadingScreen");
		mainContainer.setScreen("LoadingScreen");

		new Thread(new Runnable() {
			@Override
			public void run() {
				String exePath = "dependencies/chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", exePath);
				// driver = new ChromeDriver();
				// driver.get("http://google.com");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("user-data-dir=C:/Users/hamza/AppData/Local/Google/Chrome/User Data");
				driver = new ChromeDriver(options);

				System.out.println("Driver = " + driver);

				loginGumtree();
				// loginGoogle(username, password); // start the google login
			}
		}).start();
		// try {
		//// Thread.sleep(10000);
		// } catch (InterruptedException e) {
		// System.out.println("NOT");
		// e.printStackTrace();
		// }
		mainContainer.setScreen("GumTree");
	}

	public void loginGumtree() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Login Gumtree");
				driver.navigate().to("https://my.gumtree.com/login");
				// Google login
				try {

					driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/button")).click();
					loggedIn = true;
				} catch (Exception e) {
					loggedIn = true;
					System.out.println("Already Logged in");
				}
				// driver.navigate().to("https://google.com");
			}
		}).start();
	}

	public void postADD(String category, String Location, String Title, String youtube, String Description,
			String price, String Phone, ArrayList<String> Images) {
		while (true) {
			try {
				System.out.println("Checking logging in");
				if (loggedIn) {
					System.out.println("It is logged in");
					boolean notFound = true;

					// Post ad
					// /html/body/div[2]/div/header/div[1]/div/nav/ul/li[2]/a/span[2]
					driver.findElement(By.xpath("	/html/body/div[2]/div/header/div[1]/div/nav/ul/li[2]/a/span[2]"))
							.click();
					try {
						Thread.sleep(2000);
						driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/input[2]")).click();
						System.out.println("Draft handled");
					} catch (Exception e) {
						System.out.println("No draft");
					}
					while (notFound) {
						try {
							// category
							driver.findElement(By.xpath("//*[@id=\"post-ad_title-suggestion\"]")).sendKeys(category);
							notFound = false;
							System.out.println("Category is displayed");
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					notFound = true;// will be used later

					// pressing enter
					driver.findElement(By.xpath("//*[@id=\"post-ad_title-suggestion\"]")).sendKeys(Keys.ENTER);
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
					}
					driver.findElement(By.xpath("//*[@id=\"post-ad_title-suggestion\"]")).sendKeys(Keys.ENTER);

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						System.out.println("Thread sleep error");
						e.printStackTrace();
					}

					while (notFound) {
						try {
							// location
							driver.findElement(By.xpath("//*[@id=\"post-ad_postcode\"]")).clear();
							driver.findElement(By.xpath("//*[@id=\"post-ad_postcode\"]")).sendKeys(Location);
							notFound = false;
							System.out.println("Location is displayed");
						} catch (Exception e) {
							System.out.println("Location exception");
							try {
								driver.findElement(By
										.xpath("//*[@id=\"post-ad-container\"]/div[2]/div[1]/div/div[1]/div[1]/button"))
										.click();
							} catch (Exception e1) {
								System.out.println("Location exception 1");

							}
						}
					}
					notFound = true;
					driver.findElement(By.xpath("//*[@id=\"post-ad_postcode\"]")).sendKeys(Keys.ENTER);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						System.out.println("Thread sleep error");
						e.printStackTrace();
					}

					while (notFound) {
						try {
							// title
							driver.findElement(By.xpath("//*[@id=\"post-ad_title\"]")).sendKeys(Title);
							notFound = false;
							System.out.println("Title is displayed");
						} catch (Exception e) {
							System.out.println("Title Exception");
						}
					}

					// youtube link
					driver.findElement(By.xpath("//*[@id=\"post-ad_youtube\"]")).sendKeys(youtube);

					// description
					driver.findElement(By.xpath("//*[@id=\"description\"]")).sendKeys(Description);

					// price
					driver.findElement(By.xpath("//*[@id=\"price\"]")).sendKeys(price);

					// Images
					for (int i = 0; i < 9; i++) {
						driver.findElement(By.className("image-uploadab-add")).click();// .sendKeys(Images.get(i));
						// put path to your image in a clipboard
						StringSelection ss = new StringSelection(Images.get(i));
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

						// imitate mouse events like ENTER, CTRL+C, CTRL+V
						Robot robot = new Robot();
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
					}

					// radio button
					// driver.findElement(By
					// .xpath("//*[@id=\"post-ad-container\"]/div[11]/div/div[2]/div[1]/div/div[1]/label"))
					// .click();
					// // phone number
					// driver.findElement(By.xpath("//*[@id=\"post-ad_contactTelephone\"]"))
					// .sendKeys(Phone);
					// post ad button
					driver.findElement(By.xpath("//*[@id=\"submit-button-2\"]")).click();

					notFound = true;
					while (notFound) {
						try {
							driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[1]/a[1]")).click();
							notFound = false;
							System.out.println("Next Post Ad is displayed");
						} catch (Exception e) {
							System.out.println("Next Post Exception Exception");
						}
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						System.out.println("Thread sleep error");
						e.printStackTrace();
					}
					// conformation
					break;
				} else {
					System.out.println("Injecting started");
				}
			}

			catch (Exception e) {
				System.out.println("Website error");
				// Alert alert = new Alert(AlertType.ERROR);
				// alert.setTitle("Error Dialog");
				// alert.setHeaderText("Gumtree Fields Change");
				// alert.setContentText("The field are changed Contact
				// dev");
				// alert.showAndWait();
			}
		}
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
