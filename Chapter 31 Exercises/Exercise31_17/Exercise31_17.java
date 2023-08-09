import javafx.application.Application;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Exercise31_17 extends Application {
	
	TextField investmentAmountTf = new TextField();
	TextField numOfYearsTf = new TextField();
	TextField annualInterestRateTf = new TextField();
	TextField futureValueTf = new TextField();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane mainPane = new BorderPane();
		mainPane.setPadding(new Insets(5));
		mainPane.getStyleClass().add("region");
		Insets insets = new Insets(5, 0, 0, 0);		
		
		// Create Menu
		MenuBar menuBar = new MenuBar();
		Menu operation = new Menu("Operation");
		MenuItem calculateMi = new MenuItem("Calculate");
		MenuItem closeMi = new MenuItem("Close");
		operation.getItems().addAll(calculateMi, closeMi);
		menuBar.getMenus().add(operation);
		
		// Create GridPane
		GridPane gridPane = new GridPane();
		Label investmentAmountLb = new Label("Investment Amount:");
		Label numOfYearsLb = new Label("Number of Years:");
		Label annualInterestRateLb = new Label("Annual Interest Rate:");
		Label futureValueLb = new Label("Future value:");		
		
//		investmentAmountTf.getStyleClass().add("text-field");
		futureValueTf.setEditable(false);
		
		gridPane.addColumn(0, investmentAmountLb, numOfYearsLb, annualInterestRateLb, futureValueLb);
		gridPane.addColumn(1, investmentAmountTf, numOfYearsTf, annualInterestRateTf, futureValueTf);
		
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.setPadding(insets);

		// Create button
		StackPane buttonPane = new StackPane();
		Button calculate = new Button("Calculate");
		buttonPane.setPadding(insets);
		buttonPane.getChildren().add(calculate);
		buttonPane.setPrefWidth(mainPane.getWidth());
		buttonPane.setAlignment(Pos.TOP_RIGHT);
		
		// Combine panes
		mainPane.setTop(menuBar);
		mainPane.setCenter(gridPane);
		mainPane.setBottom(buttonPane);
		
		//Add event listeners
		calculateMi.setOnAction(e -> action());
		calculate.setOnAction(e -> action());
		closeMi.setOnAction(e -> System.exit(0));
		
	    // Create a scene and place it in the stage
	    Scene scene = new Scene(mainPane);
	    scene.getStylesheets().add("styles.css");
	    primaryStage.setTitle("Exercise31_17"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	}
	
    void action() {
    	double investmentAmount = Double.parseDouble(investmentAmountTf.getText());
    	int years = Integer.parseInt(numOfYearsTf.getText());
    	double annualInterestRate = Double.parseDouble(annualInterestRateTf.getText());
    	double monthlyInterestRate = annualInterestRate / 12 / 100;
    	double futureValue = (int) (investmentAmount * (Math.pow((1 + monthlyInterestRate), (years * 12))) * 100);
    	
    	futureValue /= 100;
    	
    	futureValueTf.setText("$" + futureValue);
    }
}
