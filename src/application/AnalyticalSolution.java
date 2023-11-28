package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class AnalyticalSolution {
	private static int N = 10000;
	public static double y(double x) {
		double temp = (double)2/Math.sqrt(x) - 1;
		return x * Math.sqrt(temp);
	}
	public static ObservableList<XYChart.Data> getAnalyticalPoints(double a, double b) {
		ObservableList<XYChart.Data> data = FXCollections.observableArrayList();
		
		double h = Math.abs(b - a) / N;
		double x = a;
		data.add(new XYChart.Data(x, y(x)));
		for(int i = 0; i < N; ++i) {
			x += h;
			data.add(new XYChart.Data(x, y(x)));
		}
		
		return data;
	}
	public static int getN() {
		return N;
	}
	public static void setN(int n) {
		N = n;
	}
}
