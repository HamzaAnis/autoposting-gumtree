package org.bst.gumtree;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;

public class ChatScreenController implements ControlledScreen {

    ScreensController myScreenController;
    Main myLogicalParent;

    @Override
    public void setParents(ScreensController screenPage,Main logical) {
        myLogicalParent = logical;
        myScreenController = screenPage;
    }

    @FXML
    private TextFlow chatLog;

    @FXML
    private TextArea chatText;

    @FXML
    protected void sendMassage() {
      System.out.println("Send");
    }

    public void recivedMassage(String msg){
     System.out.println("Recceive");
    }

}


