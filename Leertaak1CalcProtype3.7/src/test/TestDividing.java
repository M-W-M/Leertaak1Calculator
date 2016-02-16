package test;

import static org.junit.Assert.*;

import org.junit.Test;

import multiformat.*; //import alles uit deze package

public class TestDividing
{

	@Test
	public void test() 
	{
		Calculator calc = new Calculator();
		try
		{
			/*
			 * Onderstaande code moet foutmelding geven.
			 */
			calc.addOperand("4");
			calc.addOperand("0");
			calc.divide();
			
			/*
			 * Onderstaande code moet 2 als resultaat geven.
			 */
			calc.addOperand("4");
			calc.addOperand("2");
			calc.divide();
			
			
			System.out.print("\n["+calc.getBase().getName()+","
                    + calc.getFormat().getName()+","
                    + calc.firstOperand() + ", "
                    + calc.secondOperand() + "] >");
			
		} catch (FormatException DivideErrorMessage)
		{
			System.out.println(DivideErrorMessage);
		}
	}

}
