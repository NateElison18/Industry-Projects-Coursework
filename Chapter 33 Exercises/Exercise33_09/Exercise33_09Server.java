import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;

public class Exercise33_09Server extends Application {
    private TextArea history = new TextArea();
    private TextArea taServer = new TextArea();
    DataOutputStream outputToClient = null;
    DataInputStream inputFromClient = null;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        taServer.setWrapText(true);
        history.setWrapText(true);
        history.setDisable(true);


        BorderPane pane1 = new BorderPane();
        pane1.setTop(new Label("History"));
        pane1.setCenter(new ScrollPane(history));
        BorderPane pane2 = new BorderPane();
        pane2.setTop(new Label("New Message"));
        pane2.setCenter(new ScrollPane(taServer));

        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(pane1, pane2);

        taServer.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String newMessage = taServer.getText().trim();
                taServer.clear();
                history.appendText("S: " + newMessage + "\n");
                try {
                    outputToClient.writeUTF(newMessage);
                    outputToClient.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(vBox, 200, 200);
        primaryStage.setTitle("Exercise31_09Server"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        // TODO
        new Thread( () -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8000);
                Socket socket = serverSocket.accept();
                System.out.println(socket.toString());
                inputFromClient = new DataInputStream(socket.getInputStream());
                outputToClient = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    String newMessage = inputFromClient.readUTF();
                    Platform.runLater( () -> {
                        history.appendText(newMessage + "\n");
                    });
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

    }


    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

