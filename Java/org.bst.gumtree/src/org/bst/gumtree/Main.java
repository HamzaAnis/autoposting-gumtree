package org.bst.gumtree;

public class Main {
    ScreensController mainContainer;
    public Main(){
        mainContainer = new ScreensController(this);
        mainContainer.loadScreen("ChatScreen","ChatScreen.fxml");
        mainContainer.setScreen("StartScreen");
    }
}
