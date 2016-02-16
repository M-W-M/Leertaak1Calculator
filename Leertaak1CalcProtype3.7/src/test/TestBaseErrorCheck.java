package test;

import static org.junit.Assert.*;

import org.junit.Test;

import multiformat.*;

public class TestBaseErrorCheck
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
			calc.addOperand("A");	
		
			
			System.out.print("\n["+calc.getBase().getName()+","
                    + calc.getFormat().getName()+","
                    + calc.firstOperand() + ", "
                    + calc.secondOperand() + "] >");
			
		} catch (FormatException e)
		{
			System.out.println(e);
		} 
	}
}
