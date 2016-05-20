package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
    @FXML TextField heightTxt;
    @FXML TextField widthTxt;

    @FXML protected void build(ActionEvent event) {
        System.out.println("build.. " + heightTxt.getText() + "x" + widthTxt.getText());
    }
}
