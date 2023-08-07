// Exercise31_01Client.java: The client sends the input to the server and receives
// result back from the server
import java.io.*;
import java.net.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise33_01Client extends Application {
  // Text field for receiving radius
  private TextField tfAnnualInterestRate = new TextField();
  private TextField tfNumOfYears = new TextField();
  private TextField tfLoanAmount = new TextField();
  private Button btSubmit= new Button("Submit");
  private String host = "localhost";

  // Text area to display contents
  private TextArea ta = new TextArea();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
    ta.setEditable(false);
   
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Annual Interest Rate"), 0, 0);
    gridPane.add(new Label("Number Of Years"), 0, 1);
    gridPane.add(new Label("Loan Amount"), 0, 2);
    gridPane.add(tfAnnualInterestRate, 1, 0);
    gridPane.add(tfNumOfYears, 1, 1);
    gridPane.add(tfLoanAmount, 1, 2);
    gridPane.add(btSubmit, 2, 1);
    
    btSubmit.setOnAction(new ButtonListener());
    
    tfAnnualInterestRate.setAlignment(Pos.BASELINE_RIGHT);
    tfNumOfYears.setAlignment(Pos.BASELINE_RIGHT);
    tfLoanAmount.setAlignment(Pos.BASELINE_RIGHT);
    
    tfLoanAmount.setPrefColumnCount(5);
    tfNumOfYears.setPrefColumnCount(5);
    tfLoanAmount.setPrefColumnCount(5);
            
    BorderPane pane = new BorderPane();
    pane.setCenter(new ScrollPane(ta));
    pane.setTop(gridPane);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise31_01Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

  }
  

class ButtonListener implements EventHandler<ActionEvent> {
	

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Button pressed");
			Socket socket = new Socket(host, 8500);
			System.out.print("Socket created");

			ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
			
			String annualInterestRateString = tfAnnualInterestRate.getText().trim();
			String numOfYearsString = tfNumOfYears.getText().trim();
			String loanAmountString = tfLoanAmount.getText().trim();
			
			double annualInterestRate = Double.parseDouble(annualInterestRateString);
			int numOfYears = Integer.parseInt(numOfYearsString);
			double loanAmount = Double.parseDouble(loanAmountString);
			
			Loan loan = new Loan(annualInterestRate, numOfYears, loanAmount);
			
			toServer.writeObject(loan);
			
			ta.setText(
					"Annual Interest Rate: " + loan.getAnnualInterestRate() + "\n"
					+ "Number of Years: " + loan.getNumberOfYears() + "\n"
					+ "Loan Amount: " + loan.getLoanAmount() + "\n"
					+ "Monthly Payment: " + loan.getMonthlyPayment() + "\n"
					+ "Total Payment: " + loan.getTotalPayment()
					);
		}
		catch (IOException e ) {
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


