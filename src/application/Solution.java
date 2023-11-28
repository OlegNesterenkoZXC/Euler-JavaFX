package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class Solution {
	private static double discrepancy = 0;
	private static double dy(double x, double y) {
		return (3 * y * y - x * x)/(4 * x * y);
	}
	public static ObservableList<XYChart.Data> getEulerPoints(double y0, double a, double b, int n) {
		ObservableList<XYChart.Data> data = FXCollections.observableArrayList();
		
		n = Math.abs(n) + 1;
		discrepancy = 0;
		
		double xPrev = a;
		double yPrev = y0;
		double yCurrent = xPrev;
		double xCurrent = yPrev;
		data.add(new XYChart.Data(xCurrent, yCurrent));
		
		double h = Math.abs((b - a)) / n;
		
		for(int i = 0; i < n; i++) {
			yCurrent = yPrev + h * dy(xPrev, yPrev);
			xCurrent = xPrev + h;
			data.add(new XYChart.Data(xCurrent, yCurrent));
			
			double temp = Math.abs(yCurrent - AnalyticalSolution.y(xCurrent));
			if(temp > discrepancy) {
				discrepancy = temp;
			}
			
			xPrev = xCurrent;
			yPrev = yCurrent;
		}
		return data;
	}
	public static double getDiscrepancy() {
		return discrepancy;
	}
}
