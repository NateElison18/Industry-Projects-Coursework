import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.*;

public class Exercise33_09Server extends Application {
  private TextArea history = new TextArea();
  private TextArea taServer = new TextArea();
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    taServer.setWrapText(true);
    history.setWrapText(true);
    history.setDisable(true);

    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(taServer));
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(history));
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 200, 200);
    primaryStage.setTitle("Exercise31_09Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    // TODO
    new Thread( () -> {
      try {
        ServerSocket serverSocket = new ServerSocket(8000);

        while (true) {
          Socket socket = serverSocket.accept();
          new Thread(new HandleAClient(socket)).start();
        }

      } catch (IOException e) {
        e.printStackTrace();
      }

    }).start();
  }

  class HandleAClient implements Runnable{
    private Socket socket;

    public HandleAClient(Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run() {
      try {
        ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream outputToClient = new ObjectOutputStream(socket.getOutputStream());

        while (true) {
          System.out.println("Hey");
          String newMessage = (String) inputFromClient.readObject();
          System.out.println("Received message " + newMessage);
          Platform.runLater( () -> {
            history.appendText("C: " + newMessage);
          });
        }
      }
      catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }

    }
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
