package ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import multiformat.*;


public class CalculatorController extends JPanel implements ActionListener
{
	CalculatorModel calculator;
	ArrayList<JButton> calculatedButtons = new ArrayList<JButton>();
	
	private JButton add = new JButton("+");
	private JButton subtract = new JButton("-");
	private JButton multiply = new JButton("x");
	private JButton divide = new JButton("/");
	private JButton result = new JButton("=");
	private JButton negative = new JButton("Negative");
	private JButton delete = new JButton("delete");
	private JButton clear = new JButton("clear");
	private JButton dec = new JButton("Decimal");
	private JButton bin = new JButton("Binary");
	private JButton oct = new JButton("Octal");
	private JButton hex = new JButton("Heximal");
	private JButton fixedMode = new JButton("FixedMode");
	private JButton enter = new JButton("Enter");
	
	public CalculatorController(CalculatorModel model)
	{
		calculator = model;
		
		this.add(add);
		add.addActionListener(this);
		this.add(subtract);
		subtract.addActionListener(this);
		this.add(multiply);
		multiply.addActionListener(this);
		this.add(divide);
		divide.addActionListener(this);
		this.add(result);
		result.addActionListener(this);
		this.add(delete);
		delete.addActionListener(this);
		this.add(clear);
		clear.addActionListener(this);
		this.add(negative);
		negative.addActionListener(this);
		this.add(dec);
		dec.addActionListener(this);
		this.add(bin);
		bin.addActionListener(this);
		this.add(oct);
		oct.addActionListener(this);
		this.add(hex);
		hex.addActionListener(this);
		this.add(fixedMode);
		fixedMode.addActionListener(this);
		this.add(enter);
		enter.addActionListener(this);
		
		CreateButtonBaseNumberLayout();
		AddCalculatedButtons();
		AddActionListeners();
	}
	
	public Dimension getPreferredSize()
	{
	    return new Dimension(50,50);
	}
	
	public void CreateButtonBaseNumberLayout()
	{
		String baseDigits = calculator.getBase().getBaseDigits();		
		char[] buttons = baseDigits.toCharArray();
		for(char button: buttons)
		{
			String buttonNumber = Character.toString(button);
			calculatedButtons.add(new JButton(buttonNumber));			
		}
	}
	
	public void AddCalculatedButtons()
	{
		for( JButton button: calculatedButtons)
		{
			this.add(button);
		}
	}
	
	public void AddActionListeners()
	{
		for (JButton button: calculatedButtons)
		{
			button.addActionListener(this);
		}
	}
	
	public void RemoveButtons()
	{
		for (JButton button: calculatedButtons)
		{
			this.remove(button);		
			this.repaint();
		}
		calculatedButtons.clear();
	}	

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == dec)
		{
			calculator.setBase(new DecimalBase());
			RemoveButtons();
			CreateButtonBaseNumberLayout();
			AddCalculatedButtons();
			AddActionListeners();	
			this.revalidate();
		}
		else if(e.getSource() == bin)
		{
			calculator.setBase(new BinaryBase());
			RemoveButtons();
			CreateButtonBaseNumberLayout();
			AddCalculatedButtons();
			AddActionListeners();	
			this.revalidate();
		}	
		else if(e.getSource() == oct)
		{
			calculator.setBase(new OctalBase());
			RemoveButtons();
			CreateButtonBaseNumberLayout();
			AddCalculatedButtons();
			AddActionListeners();	
			this.revalidate();
		}	
		else if(e.getSource() == hex)
		{
			calculator.setBase(new HexBase());
			RemoveButtons();
			CreateButtonBaseNumberLayout();
			AddCalculatedButtons();
			AddActionListeners();	
			this.revalidate();
		}
		else if(calculatedButtons.contains(e.getSource()))
		{
			System.out.println("ja");
			for(int i = 0; i<calculatedButtons.size();i++)
			{
				if(e.getSource() == calculatedButtons.get(i))
				{
					//String number = calculatedButtons.get(i).getText();
					String number = Integer.toString(i);					
					calculator.addToMemory(number);
					//calculator.cleanMemory();
				}
			}
		}
		else if(e.getSource() == add)
		{
			calculator.add();
		}
		else if(e.getSource() == subtract)
		{
			calculator.subtract();
		}
		else if(e.getSource() == multiply)
		{
			calculator.multiply();
		}
		else if(e.getSource() == divide)
		{
			calculator.divide();
		}
		else if(e.getSource() == negative)
		{
			calculator.negative();
		}
		else if(e.getSource() == delete)
		{
			calculator.delete();
		}
		else if(e.getSource() == clear)
		{
			calculator.clear();
		}
		else if(e.getSource() == fixedMode)
		{
			calculator.setFormat(new FixedPointFormat());
		}
		else if(e.getSource() == enter)
		{
			calculator.memoryToOperand();
		}
	}
}