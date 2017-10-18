package org.bst.gumtree;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
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
			String price, String Phone) {
		if (!started) {
			started = true;
			new Thread(new Runnable() {
				@Override
				public void run() {

					while (true) {
						System.out.println("Checking logging in");
						if (loggedIn) {
							System.out.println("It is logged in");
							new Thread(new Runnable() {
								@Override
								public void run() {
									boolean notFound = true;

									// Post ad
									driver.findElement(By.xpath("/html/body/div[2]/div/main/section/div/div/a"))
											.click();

									try {
										Thread.sleep(2000);
										driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/input[2]"))
												.click();
										System.out.println("Draft handled");
									} catch (Exception e) {
										System.out.println("No draft");
									}
									while (notFound) {
										try {
											// category
											driver.findElement(By.xpath("//*[@id=\"post-ad_title-suggestion\"]"))
													.sendKeys(category);
											notFound = false;
											System.out.println("Category is displayed");
										} catch (Exception e) {
											// TODO: handle exception
										}
									}
									notFound = true;// will be used later

									// pressing enter
									driver.findElement(By.xpath("//*[@id=\"post-ad_title-suggestion\"]"))
											.sendKeys(Keys.ENTER);
									try {
										Thread.sleep(1000);
									} catch (Exception e) {
									}
									driver.findElement(By.xpath("//*[@id=\"post-ad_title-suggestion\"]"))
											.sendKeys(Keys.ENTER);

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
											driver.findElement(By.xpath("//*[@id=\"post-ad_postcode\"]"))
													.sendKeys(Location);
											notFound = false;
											System.out.println("Location is displayed");
										} catch (Exception e) {
											System.out.println("Location exception");
											try {
												driver.findElement(By
														.xpath("//*[@id=\"post-ad-container\"]/div[2]/div[1]/div/div[1]/div[1]/button"))
														.click();
											} catch (Exception e) {
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

									// radio button
									driver.findElement(By
											.xpath("//*[@id=\"post-ad-container\"]/div[11]/div/div[2]/div[1]/div/div[1]/label"))
											.click();
									// phone number
									driver.findElement(By.xpath("//*[@id=\"post-ad_contactTelephone\"]"))
											.sendKeys(Phone);
									// post ad button
									driver.findElement(By.xpath("//*[@id=\"submit-button-2\"]")).click();

									try {
										Thread.sleep(2000);
									} catch (InterruptedException e) {
										System.out.println("Thread sleep error");
										e.printStackTrace();
									}
									driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[1]/a[1]"))
											.click();
									// conformation

								}
							}).start();
							break;
						} else {
							System.out.println("Injecting started");
						}
					}
				}
			}).start();
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
