package sample;

import javafx.application.Platform;
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
    Socket socket;
    @FXML
    private Button ConnectionButton;
    @FXML
    private Button SendButton;

    @FXML
    private TextArea MessageText;
    @FXML
    private TextArea SendMessageText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.ConnectionButton.setOnAction((ev) -> this.ConnectionButtonClicked(ev));
        this.SendButton.setOnAction((ev)->this.SendButtonClicked(ev));
        runLater(() -> {
            try {
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

        Thread thread = new Thread(){
          @Override
            public void run(){
              try {
                  socket = new Socket();
                  socket.connect(new InetSocketAddress("localhost",5003));
                  Platform.runLater(()->{
                      MessageText.setText("서버와의 연결이 되었습니다.\n");
                  });
              } catch (IOException e) {
                  e.printStackTrace();
              }
              ServerReceive();
          }
        };
      thread.start();
    }

    public void SendButtonClicked(ActionEvent Event)
    {

        Thread thread = new Thread(){
            @Override
            public void run(){
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
        };
        thread.start();

    }


    public void ServerReceive(){
        while(true)
        {
            try{
                byte[] Bytes = new byte[100];
                InputStream inputStream = socket.getInputStream();
                int readByteCount = inputStream.read(Bytes);
                if(readByteCount == -1){
                    break;
                }
                String data = new String(Bytes,0,readByteCount,"UTF-8");
                Platform.runLater(()->{
                    MessageText.appendText("서버 메시지 : " + data + "\n");
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
