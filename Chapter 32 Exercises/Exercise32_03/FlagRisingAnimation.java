/**
 * Author: Nate Elison
 * Date: 8/1/23
 *
 * This program uses JavaFX and a thread to animate a flag being raised.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FlagRisingAnimation extends Application {
	int y = 180;
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Create a pane
		Pane pane = new Pane();
	
		// Add an image view and add it to pane
		ImageView imageView = new ImageView("image/us.gif");
		pane.getChildren().add(imageView);


		new Thread(() -> {
			try {
				while(true) {
					if (y <= 10)
						y = 180;
					else
						y-= 2;
					Platform.runLater(() -> imageView.setY(y));
					Thread.sleep(100);
				}
			}
			catch (InterruptedException ex) {

			}
		}).start();
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, 250, 200); 
		primaryStage.setTitle("FlagRisingAnimation"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}