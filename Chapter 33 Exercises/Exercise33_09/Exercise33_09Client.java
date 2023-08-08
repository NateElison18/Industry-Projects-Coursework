import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.SocketHandler;
import java.net.*;

public class Exercise33_09Client extends Application {
  private TextArea history = new TextArea();
  private TextArea taClient = new TextArea();
  private String host = "localhost";
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    history.setWrapText(true);
    taClient.setWrapText(true);
    history.setDisable(true);

    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(history));
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(taClient));
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);

    taClient.setOnKeyPressed(new EnterListener());

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 200, 200);
    primaryStage.setTitle("Exercise31_09Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    // To complete later


  }

  class EnterListener implements EventHandler<KeyEvent> {

    @Override
    public void handle(KeyEvent event) {
      if (event.getCode() == KeyCode.ENTER) {
        String newMessage = taClient.getText().trim();
        taClient.clear();
        try {
          Socket socket = new Socket(host, 8000);
          ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());

          toServer.writeObject("C: " + newMessage);
          history.appendText("\nC: " + newMessage);

        } catch (IOException e) {
          e.printStackTrace();
        }


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
