// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads

import java.io.*;
import java.net.*;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise33_01Server extends Application {
	
  private ObjectOutputStream outputToFile;
  private ObjectInputStream inputFromClient;
  // Text area for displaying contents
  private TextArea ta = new TextArea();
  String taString = "";

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
	
    ta.setWrapText(true);
    ta.setEditable(false);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 400, 200);
    primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    new Exercise33_01Server();
  }
  
  @SuppressWarnings("deprecation")
  public Exercise33_01Server() {
	  try {
		  ServerSocket serverSocket = new ServerSocket(8500);
		  taString = "Exercise33_01Server started at " + new Date().toLocaleString();
		  ta.setText(taString);
		  
		  outputToFile = new ObjectOutputStream(new FileOutputStream("loan.dat", true));
		  
		  while(true) {
			  Socket socket = serverSocket.accept();
			  inputFromClient = new ObjectInputStream(socket.getInputStream());
			  Loan loan = (Loan) inputFromClient.readObject();
			  
			  outputToFile.writeObject(loan);
			  taString = taString + "Connected to a client at " + new Date().toLocaleString() + "\n"
						+ "Annual Interest Rate: " + loan.getAnnualInterestRate() + "\n"
						+ "Number of Years: " + loan.getNumberOfYears() + "\n"
						+ "Loan Amount: " + loan.getLoanAmount() + "\n"
						+ "Monthly Payment: " + loan.getMonthlyPayment() + "\n"
						+ "Total Payment: " + loan.getTotalPayment();
		  ta.setText(taString);
		  }
	  }
	  
	  catch(ClassNotFoundException e) {
		  e.printStackTrace();
	  }
	  
	  catch(IOException e) {
		  e.printStackTrace();
	  }
	  finally {
		  try {
			  inputFromClient.close();
			  outputToFile.close();
		  }
		  catch (Exception e) {
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
