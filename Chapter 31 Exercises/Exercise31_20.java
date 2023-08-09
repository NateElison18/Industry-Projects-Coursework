import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise31_20 extends Application {   
	TabPane tabPane = new TabPane();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
	// Build tabPane
    Tab tab1 = new Tab("Line");
    StackPane pane1 = new StackPane();
    pane1.getChildren().add(new Line(10, 10, 80, 80));
    tab1.setContent(pane1);
    Tab tab2 = new Tab("Rectangle");
    tab2.setContent(new Rectangle(10, 10, 200, 200));
    Tab tab3 = new Tab("Circle");
    tab3.setContent(new Circle(50, 50, 20));    
    Tab tab4 = new Tab("Ellipse");
    tab4.setContent(new Ellipse(10, 10, 100, 80));
    tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);
    
    // Build radio button pane
    HBox hBox = new HBox();  
    RadioButton topRb = new RadioButton("Top");
    RadioButton leftRb = new RadioButton("Left");
    RadioButton bottomRb = new RadioButton("Bottom");
    RadioButton rightRb = new RadioButton("Right");
    ToggleGroup group = new ToggleGroup();
    topRb.setSelected(true);
    
    topRb.setToggleGroup(group);
    leftRb.setToggleGroup(group);
    rightRb.setToggleGroup(group);
    bottomRb.setToggleGroup(group);
    
    hBox.getChildren().addAll(topRb, leftRb, bottomRb, rightRb);
    hBox.setSpacing(5);
    hBox.setAlignment(Pos.BASELINE_CENTER);
    
    BorderPane mainPane = new BorderPane();
    mainPane.setCenter(tabPane);
    mainPane.setBottom(hBox);
    
    topRb.setOnAction(e -> action("top"));
    bottomRb.setOnAction(e -> action("bottom"));
    leftRb.setOnAction(e -> action("left"));
    rightRb.setOnAction(e -> action("right"));

    Scene scene = new Scene(mainPane, 300, 250);  
    primaryStage.setTitle("DisplayFigure"); // Set the window title
    primaryStage.setScene(scene); // Place the scene in the window
    primaryStage.show(); // Display the window
  }
  
    void action(String side) {
    	switch(side) {
	    	case "top":
	    		tabPane.setSide(Side.TOP);
	    		break;	
	    	case "bottom":
	    		tabPane.setSide(Side.BOTTOM);
	    		break;
	    	case "left":
	    		tabPane.setSide(Side.LEFT);
	    		break;
	    	case "right":
	    		tabPane.setSide(Side.RIGHT);
	    		break;
    	}
    }
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   * line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}