package bigWork;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TurtleGraphics extends Application{
	
	TurtlePane turtle = new TurtlePane();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane pane = new BorderPane();
		VBox vBox = new VBox();
		vBox.setSpacing(5);
		
		Text text = new Text("Please input 'dropPen', 'liftPen' , 'move to x,y'"
				+ " or 'fivePointedStar':");
		TextField tfCommand = new TextField();
		tfCommand.setPrefColumnCount(1);
		Button btOK = new Button("OK");
		vBox.getChildren().addAll(text, tfCommand, btOK);
		
		pane.setCenter(turtle);
		pane.setBottom(vBox);
		
		btOK.setOnMouseClicked(e->{	
			String s = tfCommand.getText();
			draw(s);
			
			tfCommand.clear();
		});
		
		Scene scene = new Scene(pane, 400, 400);
		primaryStage.setTitle("TurtleGraphics");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void draw(String s) {
		if(s.equalsIgnoreCase("dropPen"))
			turtle.dropPen();
		else if(s.equalsIgnoreCase("liftPen"))
			turtle.liftPen();
		else if(s.indexOf("move to") != -1) {
			int pos = s.indexOf("to ") +3;
			double x = Double.parseDouble(s.substring(pos, s.indexOf(',')));
			double y = Double.parseDouble(s.substring(s.indexOf(',') +1));			
			turtle.move(x, y);
		}
	    else if(s.equalsIgnoreCase("fivePointedStar")) {
	    	fivePointedStar();
		}
	}
	
	class TurtlePane extends Pane {
		private double startX, startY, endX, endY;
		private ImageView turtle;
		private boolean penLocation;
		
		public TurtlePane() {
			penLocation = true;
			
			javafx.scene.image.Image image = new javafx.scene.image.Image("wugui.png");
			turtle = new ImageView(image);
			turtle.setFitHeight(50);
			turtle.setFitWidth(50);
			turtle.setX(70-25);
			turtle.setY(100-25);
			
			startX = turtle.getX() +25;
			startY = turtle.getY() +25;
			
			getChildren().add(turtle);
		}

		public void dropPen() {
			penLocation = false;
		}
		
		public void move(double x, double y) {
			
			if(penLocation == false) {
				endX = x;
			endY = y;
			Line line = new Line(startX, startY, endX, endY);
			line.setStroke(Color.BLACK);
			getChildren().add(line);
			
			turtle.setX(endX-25);
			turtle.setY(endY-25);
			
			startX = endX;
			startY = endY;
			}
		}
		
		public void liftPen() {
			penLocation=true;
		}
		
		
	}
	
	public void fivePointedStar() {		
		turtle.liftPen();
		turtle.move(20,150);
		turtle.dropPen();
		turtle.move(180,150);
		turtle.move(80,50);
		turtle.move(100,150);
		turtle.move(140,50);
		turtle.move(20,150);
		turtle.liftPen();
	
}

	
	public static void main(String[] args) {
		Application.launch(args);
	}
}


