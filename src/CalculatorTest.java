/**
 * Test class for calculator class
 *
 * @author Stephen Thung
 * @version 2019-02-13
 */
public class CalculatorTest {

    /**
     * Test correct calculation of two valid Tokens (negate):
     */
    public void calculateTwoTokensTestValidNegate() throws AssertException
    {
        try
        {
            int result = Calculator.calculateTwoTokens(new String[] {"negate", "5"});
            Assert.assertEquals(-5, result);
        }
        catch (Exception e)
        {
            Assert.fail("Legal expression threw an Exception: " + e.getMessage());
        }
    }

    /**
     * Test correct calculation of two valid Tokens (halve):
     */
    public void calculateTwoTokensTestValidHalve() throws AssertException
    {
        try {
        	int result = Calculator.calculateTwoTokens(new String[] {"halve", "6"});
        	Assert.assertEquals(3, result);
        }
        catch (Exception e) {
        	Assert.fail("Legal expression threw an Exception " + e.getMessage());
        }
    }
    
    /**
     * Test invalid two-Token input (number is not an int):
     */
    public void calculateTwoTokensTestInvalidNumber() throws AssertException
    {
        try
        {
            Calculator.calculateTwoTokens(new String[] {"halve", "foo"});
            Assert.fail("Illegal expression did not throw an Exception");
        }
        catch (NumberFormatException e)
        {
            // We expect the function to throw a NumberFormatException (from failure of Integer.parseInt)
            // Success; Assert.fail will not be thrown and the code will complete the test, thus succeeding.
        }
        catch (Exception e)
        {
            Assert.fail("Unexpected Exception (not NumberFormatException) caught");
        }
    }

    /**
     * Test invalid two-Token input (command is not negate/halve):
     */
    public void calculateTwoTokensTestInvalidCommand() throws AssertException
    {
        try {
        	Calculator.calculateTwoTokens(new String[] {"Fail", "10"});
        	Assert.fail("Illegal command");
        }
        catch (CalculatorException e) {
        }
    }

    /**
     * Test correct calculation of three valid Tokens (+):
     */
    public void calculateThreeTokensTestValidAdd() throws AssertException
    {
        try {
        	int result = Calculator.calculateThreeTokens(new String[] {"2", "+", "3"});
        	Assert.assertEquals(5, result);
        }
        catch(Exception e) {
        	Assert.fail("Legal expression threw an exception " + e.getMessage());
        }
    }

    /**
     * Test correct calculation of three valid Tokens (-):
     */
    public void calculateThreeTokensTestValidSubtract() throws AssertException
    {
    	try {
        	int result = Calculator.calculateThreeTokens(new String[] {"3", "-", "2"});
        	Assert.assertEquals(1, result);
        }
        catch(Exception e) {
        	Assert.fail("Legal expression threw an exception " + e.getMessage());
        }
    }

    /**
     * Test correct calculation of three valid Tokens (/):
     */
    public void calculateThreeTokensTestValidDivide() throws AssertException
    {
    	try {
        	int result = Calculator.calculateThreeTokens(new String[] {"9", "/", "3"});
        	Assert.assertEquals(3, result);
        }
        catch(Exception e) {
        	Assert.fail("Legal expression threw an exception " + e.getMessage());
        }
    }

    /**
     * Test invalid three-Token input (either number is not an int):
     */
    public void calculateThreeTokensTestInvalidNumber() throws AssertException
    {
        // Try for first number:
        try
        {
            Calculator.calculateThreeTokens(new String[] {"foo", "+", "5"});
            Assert.fail("Illegal expression did not throw an Exception");
        }
        catch (NumberFormatException e)
        {
            // We expect the function to throw a NumberFormatException (from failure of Integer.parseInt)
            // Success; Assert.fail will not be thrown and the code will complete the test, thus succeeding.
        }
        catch (Exception e)
        {
            Assert.fail("Unexpected Exception (not NumberFormatException) caught");
        }

        // Try for second number:
        try
        {
            Calculator.calculateThreeTokens(new String[] {"5", "+", "foo"});
            Assert.fail("Illegal expression did not throw an Exception");
        }
        catch (NumberFormatException e)
        {
            // We expect the function to throw a NumberFormatException (from failure of Integer.parseInt)
            // Success; Assert.fail will not be thrown and the code will complete the test, thus succeeding.
        }
        catch (Exception e)
        {
            Assert.fail("Unexpected Exception (not NumberFormatException) caught");
        }
    }

    /**
     * Test invalid three-Token input (command is not +/-//):
     */
    public void calculateThreeTokensTestInvalidCommand() throws AssertException
    {
        try {
        	Calculator.calculateThreeTokens(new String[] {"5", "heh", "3"});
        	Assert.fail("Illegal expression did not throw an Exception");
        }
        catch (CalculatorException e) {
        }
        catch (Exception e) {
        	Assert.fail("Unexpected Exception (not CalculatorException) caught");
        }
    }

    /**
     * Test correct execution of command (one Token - only can be "quit"):
     */
    public void executeTestValidQuit() throws AssertException
    {
        try {
        	int result = Calculator.execute(new String[] {"quit"});
        	Assert.assertEquals(Integer.MIN_VALUE, result);
        }
        catch(Exception e) {
        	Assert.fail("Legal expression threw an exception" + e.getMessage());
        }
    }

    /**
     * Test correct execution of command (any valid two-Token command):
     */
    public void executeTestValidTwoTokens() throws AssertException
    {
        try
        {
            int result = Calculator.calculateTwoTokens(new String[] {"negate", "5"});
            Assert.assertEquals(-5, result);
        }
        catch (Exception e)
        {
            Assert.fail("Legal expression threw an Exception: " + e.getMessage());
        }
    }

    /**
     * Test correct execution of command (any valid three-Token command):
     */
    public void executeTestValidThreeTokens() throws AssertException
    {
    	try
        {
            int result = Calculator.calculateThreeTokens(new String[] {"2", "+", "3"});
            Assert.assertEquals(5, result);
        }
        catch (Exception e)
        {
            Assert.fail("Legal expression threw an Exception: " + e.getMessage());
        }
    }

    /**
     * Test invalid execute input (single Token; not "quit"):
     */
    public void executeTestInvalidCommand() throws AssertException
    {
        try
        {
            Calculator.execute(new String[] {"foo"});
            Assert.fail("Illegal expression did not throw an Exception");
        }
        catch (CalculatorException e)
        {
            // We expect the function to throw a CalculatorException.
            // Check to make sure the CalculatorException has the correct message and type:
            Assert.assertEquals("Illegal Command", e.getMessage());
        }
        catch (Exception e)
        {
            Assert.fail("Unexpected Exception (not CalculatorException) caught");
        }
    }

    /**
     * Test invalid execute input (invalid Token Lengths):
     */
    public void executeTestInvalidTokenLength() throws AssertException
    {
        try {
        	Calculator.execute(new String[] {});
        	Assert.fail("Illegal expression did not throw an Exception");
        }
        catch (CalculatorException e) {
        }
        try {
        	Calculator.execute(new String[] {"5", "-", "2", "4"});
        	Assert.fail("Illegal expression did not throw an Exception");
        	}
        catch (CalculatorException e) {
        }
    }

    /**
     * Test correct parseAndExecution of command (input of "quit"):
     */
    public void parseAndExecuteTestValidQuit() throws AssertException
    {
        String result = Calculator.parseAndExecute("quit");
        Assert.assertEquals("quit", result);
    }

    /**
     * Test correct parseAndExecution of command (any valid 2 or 3 Token command):
     */
    public void parseAndExecuteTestValidCommand() throws AssertException
    {
        String result = Calculator.parseAndExecute("5 + 4");
        Assert.assertEquals("The result is: 9", result);
    }

    /**
     * Test incorrect parseAndExecution of command (divide by zero):
     */
    public void parseAndExecuteTestDivideByZero() throws AssertException
    {
        String result = Calculator.parseAndExecute("5 / 0");
        Assert.assertEquals("Attempted to divide by 0, please try again", result);
    }

    /**
     * Test incorrect parseAndExecution of command (2 or 3 Token command with invalid number):
     */
    public void parseAndExecuteTestInvalidNumber() throws AssertException
    {
        String result = Calculator.parseAndExecute("6 hehe 9");
        Assert.assertEquals("Calculator Exception, message is: Illegal Command", result);
    }

    /**
     * Test incorrect parseAndExecution of command (invalid command):
     */
    public void parseAndExecuteTestInvalidCommand() throws AssertException
    {
        String result = Calculator.parseAndExecute("foo 6");
        Assert.assertEquals("Calculator Exception, message is: Illegal Command", result);
    }

    /**
     * Test incorrect parseAndExecution of command (invalid Token Length):
     */
    public void parseAndExecuteTestInvalidTokenLength() throws AssertException
    {
        String result = Calculator.parseAndExecute("yes yes yes yes");
        Assert.assertEquals("Calculator Exception, message is: Illegal Token Length", result);
    }
}
