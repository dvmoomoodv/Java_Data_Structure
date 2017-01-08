package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 제목 : OKController
 * 내용 : 해당되는 IP를 승인하는 Controller Class
 *
 */

public class OKController implements Initializable {
    @FXML
    private Button OKButton;
    @FXML
    private Button NOButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.OKButton.setOnAction((ev) -> this.OKButtonClicked(ev));
        // Client 연결 해제 할 경우
        this.NOButton.setOnAction((ev) -> {
            this.NOButtonClicked(ev);
        });
    }
    public void OKButtonClicked(ActionEvent event)
    {

    }
    public void NOButtonClicked(ActionEvent event)
    {

    }
}
