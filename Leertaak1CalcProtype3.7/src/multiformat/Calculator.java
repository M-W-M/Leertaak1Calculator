/*
 * (C) Copyright 2005 Davide Brugali, Marco Torchiano
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307  USA
 */
package multiformat;

/**
 * The multiformat calculator
 */
public class Calculator {
  private Rational operand_0 = new Rational();
  private Rational operand_1 = new Rational();
  
  // The current format of the calculator
  private Format format = new FixedPointFormat();
  // The current numberbase of the calculator
  private Base base = new DecimalBase();

  public void addOperand(String newOperand) throws FormatException {
	  try 
	  {
		  numberBaseCheck(newOperand);  
	  }
	  catch(NumberBaseException e)
	  {
		  System.out.println(e);
	  }
	  operand_1 = operand_0;
      operand_0 = format.parse(newOperand, base);
  }

  public void add(){
    operand_0 = operand_1.plus(operand_0);
    operand_1 = new Rational();
  }
  public void subtract() {
    operand_0 = operand_1.minus(operand_0);
    operand_1 = new Rational();
  }
  public void multiply() {
    operand_0 = operand_1.mul(operand_0);
    operand_1 = new Rational();
  }
  public void divide() {
    try
	{
		operand_0 = operand_1.div(operand_0);
		operand_1 = new Rational();
	} catch (FormatException DivideErrorMessage)
	{
		// TODO Auto-generated catch block
		System.out.println(DivideErrorMessage);
	}
  }
  public void delete() {
    operand_0 = operand_1;
    operand_1 = new Rational();
  }

  public String firstOperand(){
    return format.toString(operand_1,base);
  }
  public String secondOperand(){
    return format.toString(operand_0,base);
  }

  public void setBase(Base newBase){
    base = newBase;
  }
  public Base getBase(){
    return base;
  }
  public void setFormat(Format newFormat){
    format = newFormat;
  }
  public Format getFormat(){
    return format;
  }
  
  public void numberBaseCheck(String command) throws NumberBaseException
  {
	  //System.out.println(command);
	  command = command.replace(".", "");
	  command = command.replace("/", "");
	 // System.out.println("command:" + command);
	  
	  char[] numberInDigits = command.toCharArray(); 
	  for(char digit:numberInDigits)
	  {
		  int digitExists = this.getBase().getBaseDigits().indexOf(digit);
		  if(digitExists < 0)
		  {
			  throw new NumberBaseException(digit + " is een getal dat niet bestaat in het "
			  		    + this.getBase().getName() + " talstelsel!");
		  }
	  }
  }
}