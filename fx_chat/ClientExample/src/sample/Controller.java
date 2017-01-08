package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Platform.runLater;

public class Controller  implements Initializable {
    Socket socket = new Socket();
    @FXML
    private Button ConnectionButton;
    @FXML
    private Button SendButton;
    @FXML
    private Button ReadButton;
    @FXML
    private TextArea MessageText;
    @FXML
    private TextArea SendMessageText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.ConnectionButton.setOnAction((ev) -> this.ConnectionButtonClicked(ev));
        this.SendButton.setOnAction((ev)->this.SendButtonClicked(ev));
        this.ReadButton.setOnAction((ev)->this.ReadButtonClicked(ev));
        runLater(() -> {
            try {
                this.MessageText.setDisable(true);
                this.MessageText.setText("");
            } finally {
            }
        });
        runLater(() -> {
            try {
                this.SendMessageText.setText("");
            } finally {
            }
        });
    }

    public void ConnectionButtonClicked(ActionEvent Event)
    {
        try {
            socket.connect(new InetSocketAddress("localhost",5003));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SendButtonClicked(ActionEvent Event)
    {

        runLater(() -> {
            byte[] byteArr  = new byte[200];
            try {
                InputStream inputStream = socket.getInputStream();

                int readByteCount = inputStream.read(byteArr);
                if(readByteCount == -1){throw new IOException();}
                String data = new String(byteArr,0,readByteCount,"UTF-8");
                this.MessageText.appendText(data);

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    public void ReadButtonClicked(ActionEvent event)
    {
        String Message = SendMessageText.getText().toString();
        runLater(() -> {
            try{
                byte[] byteArr = Message.getBytes("UTF-8");
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(byteArr);
                outputStream.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}
