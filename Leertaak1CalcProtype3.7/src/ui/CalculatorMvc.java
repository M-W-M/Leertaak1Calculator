package ui;

import javax.swing.*;
import java.awt.*;

public class CalculatorMvc extends JApplet
{
	CalculatorModel calculator;
	CalculatorController controller;
	CalculatorView display;
	CalculationsView calculionsDisplay;
	
	public void init()
	{
		resize(300,300);
		
		calculator = new CalculatorModel();
		
		controller = new CalculatorController(calculator);
		controller.setBackground(Color.DARK_GRAY);
		getContentPane().add(controller,BorderLayout.CENTER);
		
		display = new CalculatorView();
		display.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(display,BorderLayout.NORTH);
		
		calculionsDisplay = new CalculationsView();
		calculionsDisplay.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(calculionsDisplay,BorderLayout.SOUTH);
		
		calculator.addActionListener(display);
		calculator.addActionListener(calculionsDisplay);
	}
}
