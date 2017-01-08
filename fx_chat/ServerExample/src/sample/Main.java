package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{

        // 스타일 적용 후 Main 창 출력
        Parent root = FXMLLoader.load(getClass().getResource("../scene/mainstyle.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style/chates.css").toString());
        primaryStage.setTitle("채팅 관리 - ( 서버 )"); // 제목
        primaryStage.setScene(scene); // Scene 관리
        primaryStage.setResizable(false); // 확대 금지
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
