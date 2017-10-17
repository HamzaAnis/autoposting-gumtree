package org.bst.gumtree;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	WebDriver driver;
    ScreensController mainContainer;
    public Main(){
        mainContainer = new ScreensController(this);
        mainContainer.loadScreen("GoogleLogin","GoogleLogin.fxml");
//        mainContainer.loadScreen("check","check.fxml");
        mainContainer.loadScreen("ChatScreen","ChatScreen.fxml");
        mainContainer.setScreen("GoogleLogin");
        
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(mainContainer);
        AnchorPane.setTopAnchor(mainContainer,0.0);
        AnchorPane.setBottomAnchor(mainContainer,0.0);
        AnchorPane.setLeftAnchor(mainContainer,0.0);
        AnchorPane.setRightAnchor(mainContainer,0.0);

        Scene scene = new Scene(root,600,600);
        primaryStage.setTitle("Gumtree Ad post");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void laoadDelete(){
    	System.out.println("Start");
    	 mainContainer.setScreen("ChatScreen");
    }
    public static void main(String[] args) {
        launch(args);
    }
	
    public void loadChromDriver(){
    	
    	 new Thread(new Runnable() {
    	        @Override
    	        public void run() {
    	        	String exePath = "dependencies/chromedriver.exe";
    	    		System.setProperty("webdriver.chrome.driver", exePath);
    	    		driver = new ChromeDriver();
    	    		driver.get("http://google.com");
    	        }
    	    }).start();
    }
}
