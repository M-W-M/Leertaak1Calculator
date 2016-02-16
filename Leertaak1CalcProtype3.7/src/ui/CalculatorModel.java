package ui;
import multiformat.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalculatorModel
{
	/**
	 * The multiformat calculator
	 */
	  private ArrayList<ActionListener> actionListenerList = new ArrayList<ActionListener>();
	  private Rational operand_0 = new Rational();
	  private Rational operand_1 = new Rational();
	  private String memoryCell = "";
	  private int sumTotal = 0;
	  
	  // The current format of the calculator
	  private Format format = new FixedPointFormat();
	  // The current numberbase of the calculator
	  private Base base = new DecimalBase();
	  
	  public void addToMemory(String value)
	  {
		  memoryCell += value;
		  try
		{
			addOperandZero(memoryCell);
		} catch (FormatException e)
		{
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		  processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
	  }
	  
	  public void cleanMemory()
	  {
		  memoryCell = "";
		  processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
	  }
	  
	  public void memoryToOperand()
	  {
		  shiftOperand();
		  processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
	  }
	  
	  public void addOperandOne(String newOperand) throws FormatException {
	      operand_1 = format.parse(newOperand, base);
	      processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
	  }
	  
	  public void addOperandZero(String newOperand) throws FormatException {
	      operand_0 = format.parse(newOperand, base);
	      processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
	  }
	  
	  public void shiftOperand() 
	  {
	      operand_1 = operand_0;
	      cleanMemory();
	      try
		{
			addOperandZero("0");
		} catch (FormatException e)
		{
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	      processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
	  }
	  
	  public void addToSum()
	  {
		  sumTotal ++;
		  processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
	  }
	  
	  
	  public void resetSum()
	  {
		  sumTotal = 0; 
		  processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
	  }
	  
	  
	  public String getSum()
	  {
		  return "Aantal berekeningen: "+sumTotal;		  
	  }
	
	  public void addOperand(String newOperand) throws FormatException 
	  {
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
	      processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
	  }
	
	  public void add()
	  {
		addToSum();
		cleanMemory();
	    operand_0 = operand_1.plus(operand_0);
	    operand_1 = new Rational();
	    processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
	  }
	  
	  public void subtract() 
	  {
		addToSum();
		cleanMemory();
	    operand_0 = operand_1.minus(operand_0);
	    operand_1 = new Rational();
	    processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
	  }
	  
	  public void multiply() 
	  {
		addToSum();
		cleanMemory();
	    operand_0 = operand_1.mul(operand_0);
	    operand_1 = new Rational();
	    processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
	  }
	  
	  public void divide() 
	  {
		addToSum();
		cleanMemory();
	    try
		{
			operand_0 = operand_1.div(operand_0);
			operand_1 = new Rational();
			processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
		} catch (FormatException DivideErrorMessage)
		{
			// TODO Auto-generated catch block
			System.out.println(DivideErrorMessage);
		}
	  }
	  
	  public void negative() 
	  {
		  Rational negatief = new Rational(-1);
		  operand_0 = operand_0.mul(negatief);
		  processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
	  }
	  
	  public void delete() 
	  {
		cleanMemory();
	    operand_0 = operand_1;
	    operand_1 = new Rational();
	    processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
	  }
	  
	  public void clear() 
	  {
		resetSum();
		cleanMemory();
	    operand_0 = new Rational();
	    operand_1 = new Rational();
	    processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
	  }
	
	  public String firstOperand()
	  {
	    return format.toString(operand_1,base);
	  }
	  public String secondOperand()
	  {
	    return format.toString(operand_0,base);
	  }
	
	  public void setBase(Base newBase)
	  {
	    base = newBase;
	  }
	  
	  public Base getBase()
	  {
	    return base;
	  }
	  
	  public void setFormat(Format newFormat)
	  {
	    format = newFormat;
	    processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
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
	  
	  public void addActionListener( ActionListener listener) {
			actionListenerList.add( listener );
	  }
	  
	  private void processEvent(ActionEvent e) 
	  {
			// Hieronder gebruiken we het nieuwe Java "foreach" statement. 
			// Lees het als: "for each ActionListener in actionListenerList do ..."
			// Je kunt ook een for-lus of een iterator gebruiken, maar foreach is het elegantste.
			for( ActionListener l : actionListenerList)
			{
				l.actionPerformed( e );
			}
	  }
}