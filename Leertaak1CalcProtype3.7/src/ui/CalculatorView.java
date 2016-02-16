package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CalculatorView  extends JPanel implements ActionListener
{
	private JTextArea calculatorDisplay = new JTextArea();
	CalculatorModel calculator;
	
	public CalculatorView() 
	{
		this.setLayout(new FlowLayout());
		this.add(calculatorDisplay);
	}
	
	public Dimension getPreferredSize()
	{
	    return new Dimension(50,50);
	}
	
	public void actionPerformed(ActionEvent e) {
		calculator = (CalculatorModel) e.getSource();
		calculatorDisplay.setText("\n["+calculator.getBase().getName()+","
                            + calculator.getFormat().getName() + " " 
                            + calculator.firstOperand() + " "                   
                            + calculator.secondOperand() + "]");
	} 
}
