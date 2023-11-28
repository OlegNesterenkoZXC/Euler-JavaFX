package application;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
    public void start(Stage stage) {
        
		NumberAxis xAxis = new NumberAxis(1, 2, 0.05);
		xAxis.setLabel("x");
		
		NumberAxis yAxis = new NumberAxis(1, 1.5, 0.05);
		yAxis.setLabel("y(x)");
		
		LineChart<Number, Number> linechart = new LineChart<Number, Number>(xAxis, yAxis);
		linechart.setCreateSymbols(false);
		
		XYChart.Series seriesA = new XYChart.Series();
		seriesA.setName("Аналитическое решение");
		
		XYChart.Series seriesE = new XYChart.Series();
		seriesE.setName("Решение методом ломанных Эйлера");
		
		seriesA.setData(AnalyticalSolution.getAnalyticalPoints(1, 2));
				
		linechart.getData().addAll(seriesA, seriesE);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(linechart);
		
		Label inputLabel = new Label("Введите N");
		TextField inputText = new TextField();
		Button inputButton = new Button("Старт");
		
		FlowPane input = new FlowPane(25, 0, inputLabel, inputText, inputButton);
		input.setAlignment(Pos.CENTER);
		
		Label outputLabel = new Label("Максимальная невязка");
		TextField outputText = new TextField();
		outputText.setEditable(false);
		FlowPane output = new FlowPane(25, 0, outputLabel, outputText);
		output.setAlignment(Pos.CENTER);
		
		VBox vbox = new VBox(input, output);
		vbox.setMargin(input, new Insets(10, 0, 0 , 0));
		vbox.setMargin(output, new Insets(10, 0, 10, 0));
		
		borderPane.setBottom(vbox);
		
		Scene scene = new Scene(borderPane, 1280, 720);
		
		stage.setTitle("App");
		stage.setScene(scene);
		stage.show();
			
		inputButton.setOnAction(e -> {
			try
		    {
				int n = Integer.parseInt(inputText.getText().trim());
				seriesE.getData().clear();
				seriesE.setData(Solution.getEulerPoints(1, 1, 2, n));
				outputText.setText(Double.toString(Solution.getDiscrepancy()));
		      
		    }
		    catch (NumberFormatException nfe)
		    {
		    	outputText.setText("Неправильные данные");
		    }
		});
	}
    public static void main(String[] args) {
        launch(args);
    }
}
