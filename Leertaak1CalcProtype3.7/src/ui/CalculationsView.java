package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CalculationsView extends JPanel implements ActionListener
{
	private JTextArea sumTotal = new JTextArea();
	CalculatorModel calculator;
	
	public CalculationsView()
	{
		this.setLayout(new FlowLayout());
		this.add(sumTotal);
	}
	
	public Dimension getPreferredSize()
	{
	    return new Dimension(50,50);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		calculator = (CalculatorModel) e.getSource();		
		sumTotal.setText(calculator.getSum());		
	}
}
