package sample;


import com.sun.security.ntlm.Client;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.application.Platform.runLater;

/**
 * 제목 : MainController
 * 내용 : Server Main UI Controller Class
 */

public class MainController implements Initializable {
    @FXML
    private Button InButton;
    @FXML
    private Button OutButton;
    @FXML
    private TextArea ContentArea;
    String UIString;
    ExecutorService executorService; // 스레드 풀
    ServerSocket serverSocket; // 적용시킬 소켓
    List<Client> connection = new Vector<Client>(); // 동적인 길이 저장
    InetSocketAddress isa;

    // 문자 및 버튼 세팅
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 스레드 풀 작동
        executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        // 서버 생성
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 5003));
        } catch (Exception e) {
            if (!serverSocket.isClosed()) {
                e.printStackTrace();
            }
        }
        // Client 연결 됬을 경우
        this.InButton.setOnAction((ev) -> this.InButtonClicked(ev));
        // Client 연결 해제 할 경우
        this.OutButton.setOnAction((ev) -> {
            this.OutButtonClicked(ev);
        });
        runLater(() -> {
            try {
                this.ContentArea.setDisable(true);
                this.ContentArea.setText("");
            } finally {
            }
        });
    }

    // Client 승인 버튼
    public void InButtonClicked(ActionEvent event) {
        Platform.runLater(() -> {
            if (serverSocket.isClosed()) {
                try {
                    serverSocket = new ServerSocket();
                    serverSocket.bind(new InetSocketAddress("localhost", 5003));
                    Platform.runLater(() -> {
                        ContentArea.appendText("서버를 재연결하였습니다.\n");
                    });
                } catch (IOException e) {

                }
            }

            try {
                // 대화상자를 이용하여 메시지를 띄움
                Stage Primary = (Stage) InButton.getScene().getWindow();
                Stage dialog = new Stage(StageStyle.DECORATED);
                dialog.initModality(Modality.WINDOW_MODAL);
                dialog.initOwner(Primary);
                dialog.setTitle("IP 확인 후 승인");
                Parent parent = FXMLLoader.load(getClass().getResource("../scene/clientstyle.fxml"));
                TextArea Area = (TextArea) parent.lookup("#TextID");
                Button OKButton = (Button) parent.lookup("#OKButton");
                Button NOButton = (Button) parent.lookup("#NOButton");

                Socket socket = serverSocket.accept();
                Platform.runLater(() -> {
                    try {
                        isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                        ContentArea.appendText("IP :" + isa.getHostName() + "가 연결을 시도 하였습니다.\n");
                        Area.setText("IP :" + isa.getHostName() + "가 연결을 시도 하였습니다. 승낙 하시겠습니까?");
                    } catch (Exception e) {

                    }
                    finally {
                        UIString = isa.toString();
                    }
                });

                // 작동 시작

                Platform.runLater(() -> {
                    OKButton.setOnAction(event1 -> {
                        ContentArea.appendText("IP :" + isa.getHostName() + "의 연결이 승낙되었습니다.\n");
                        dialog.close();
                    });

                    NOButton.setOnAction(event1 -> {
                        if (!serverSocket.isClosed()) {
                            try {
                                serverSocket.close();
                            } catch (IOException e) {

                            }
                        }
                        ContentArea.appendText("IP :" + isa.getHostName() + "의 연결을 취소하고 서버를 껐습니다.\n");
                        dialog.close();
                    });
                });

                Scene scene = new Scene(parent);
                dialog.setScene(scene);
                dialog.setResizable(false);
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

    //Client 취소 버튼
    public void OutButtonClicked(ActionEvent event) {

        Platform.runLater(() -> {
            if (!serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException e) {

                }
            }
            ContentArea.appendText("Client의 연결을 종료합니다.\n");

        });
    }


}
