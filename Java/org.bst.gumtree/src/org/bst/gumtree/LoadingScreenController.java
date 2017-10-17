package org.bst.gumtree;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoadingScreenController implements ControlledScreen {

    ScreensController myScreenController;
    Main myLogicalParent;

    @Override
    public void setParents(ScreensController screenPage,Main logical) {
        myLogicalParent = logical;
        myScreenController = screenPage;
    }

    @FXML
    private Label loadLbl;


    public void setText(String str){
        loadLbl.setText(str);
    }

}