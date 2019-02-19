/**
 * A class representing a simple calculator. The calculator takes in an input string and interprets it as a command.
 * The calculator evaluates the command and returns a result.
 *
 * @author Stephen Thung
 * @version 2019-02-13
 */

public class Calculator
{
    /**
     * Takes an input command of two string Tokens and simulates a calculator command.
     * The Token Length should never be something either than 2. If it is, there is an issue in execute().
     * The input expects the two Tokens formatted in a String array in the form of [command, number].
     * Valid commands are "negate" and "halve". Each command takes an int value as a parameter.
     *  e.g. "negate 5" passed in as an array ["negate", "5"] returns a result of -5
     *       "halve 6" passed in as an array ["halve", "6"] returns a result of 3
     *
     * If the input is invalid, exceptions will be thrown with the following precedence:
     * (0) NumberFormatException
     *      The input number is not an int: look at Integer.parseInt. This exception is thrown automatically if the
     *      string that you try and parse is not an int value). I.e. you do not need to explicitly throw this
     *      exception.
     * (1) CalculatorException("Illegal Command")
     *      The input command is neither negate or halve. You must throw this exception explicitly. The
     *      line will look similar to "throw new CalculatorException(<<message>>);"
     *
     * @param Tokens The input string to the program Tokenized (split up). Should be a String array of two elements:
     *  [command, number].
     * @return The result of the calculator operation ("negate number" or "halve number"). The halved number should
     * be rounded down (i.e. the default when doing int math).
     * @throws NumberFormatException Thrown if the second Token is not convertible from String to int.
     * @throws CalculatorException Thrown if the first Token is not a valid command ("negate" or "halve")
     */
    protected static int calculateTwoTokens(String[] Tokens) throws NumberFormatException, CalculatorException
    {
        int a = Integer.parseInt(Tokens[1]); // Throws NumberFormatException if the second Token is not an int value.
        if(Tokens[0] == "negate") {
        	return a * -1;
        }
        else if(Tokens[0] == "halve") {
        	return a / 2;
        }
        else {
        	throw new CalculatorException("Illegal Command");
        }
    }

    /**
     * Takes an input command of three string Tokens and performs the appropriate calculator command.
     * The Token Length should never be something other than 3. If it is, there is an issue in execute().
     * The input expects the three Tokens formatted in a String array in the form of [num1, command, num2].
     * Valid commands are (numbers must be int values):
     *  "num1 + num2" => return num1 + num2
     *  "num1 - num2" => return num1 - num2
     *  "num1 / num2" => return num1 / num2
     *
     * If the input is invalid, exceptions will be thrown with the following precedence:
     * (0) NumberFormatException
     *      Either input number is not an int: look at Integer.parseInt. This exception is thrown automatically if the
     *      string that you try and parse is not an int value)
     * (1) CalculatorException("Illegal Command")
     *      The input command is neither +, -, or /. You must throw this exception explicitly. The
     *      line will look similar to "throw new CalculatorException(<<message>>);"
     * (2) ArithmeticException
     *      The command is "/" and both numbers are int; the second number is 0. If you attempt to perform
     *      num1 / num2, this exception will automatically be performed. You should not need to explicitly throw
     *      this exception.
     *
     * @param Tokens The input string to the program Tokenized (split up). Should be a String array of three elements:
     *  [num1, command, num2].
     * @return The result of the calculator operation ("num1 + num2", "num1 - num2", or "num1 / num2")
     * @throws ArithmeticException A division by zero has occured.
     * @throws NumberFormatException Thrown if the first or third Token is not convertible from String to int.
     * @throws CalculatorException Thrown if the second Token is not a valid command ("+", "-", or "/")
     */
    protected static int calculateThreeTokens(String[] Tokens)
            throws ArithmeticException, NumberFormatException, CalculatorException
    {
    	int a = Integer.parseInt(Tokens[0]);
    	int b = Integer.parseInt(Tokens[2]);
        if(Tokens[1] == "+") {
        	return a + b;
        }
        else if(Tokens[1] == "-") {
        	return a - b;
        }
        else if(Tokens[1] == "/") {
        	if(b == 0) {
        		throw new ArithmeticException("A division by zero has occurred");
        	}
        	else {
        		return a / b;
        	}
        }
        else {
        	throw new CalculatorException("Illegal Command");
        }
    }

    /**
     * Method to execute the expression encoded in a sequence of Tokens. The user input is Tokenized in parseAndExecute
     * and the Tokens are passed as an array to this method.  For each number of Tokens that the program receives, this
     * method will behave in different ways. The methods calculateTwoTokens and calculateThreeTokens will be used as
     * helper methods. They handle the cases where the number of Tokens = 2 and = 3.
     *
     * That is, for each number of Tokens, the program expects a certain format of input. When
     * the input does not match its expectations, the program should throw an exception. Follow
     * the below cases to determine when you should throw an exception. The cases outline all
     * possibilities for inputs - all the ways an input can be formatted both correctly and
     * incorrectly, and how to choose what to do as a result (operate normally or throw and
     * exception).
     *
     * 0 Tokens: throw a CalculatorException(message="Illegal Token Length", type=0)
     * 1 Token:
     *      Token[0] = "quit": return Integer.MIN_VALUE (i.e. the program should quit)
     *      Token[0] = anything else: throw a CalculatorException(message="Illegal Command", type=1)
     * 2 Tokens: return the result of calculateTwoTokens(Tokens)
     * 3 Tokens: return the result of calculateThreeTokens(Tokens)
     * 4+ Tokens: throw a CalculatorException(message="Illegal Token Length", type=0)
     *
     * @param Tokens The input string to the calculator split into Tokens and passed as a String array.
     * @throws ArithmeticException A division by zero has occurred.
     * @throws NumberFormatException Thrown if a numeric Token is not convertible from String to int.
     * @throws CalculatorException Thrown if the command Token is not a valid command ("quit", "negate", "halve"
     * "+", "-", "/"). For the 3 Tokens case, the command Token is the second Token (e.g. "1 + 3", + is the command).
     * For the 2 Tokens case, the command Token is the first Token (e.g. "halve 2", halve is the command.
     */
    protected static int execute(String[] Tokens) throws NumberFormatException, CalculatorException
    {
        // Condition on the number of Tokens (number of strings in user input separated by spaces)
        switch(Tokens.length)
        {
        case 0:
        	throw new CalculatorException("Illegal Token Length");
        case 1:
        	if(Tokens[0] == "quit") {
        		return Integer.MIN_VALUE;
        	}
        	else {
        		throw new CalculatorException("Illegal Command");
        	}
        case 2:
        	return calculateTwoTokens(Tokens);
        case 3:
        	return calculateThreeTokens(Tokens);
        default:
        	throw new CalculatorException("Illegal Token Length");
        }

    }

    /**
     * Method to split up the user input. "Tokenizes" (converts a large string into string chunks) by splitting the
     * input string on spaces. The Tokenized input is passed to execute to be processed. If the input is bad, execute
     * will throw an exception. If the input is valid, an int value will be returned. An int value of Integer.MIN_VALUE
     * is returned by execute if the program should quit.
     *
     * This method handles the exceptions thrown by execute. The string returned by this method is the final result
     * of processing a user input, whether it be a valid or invalid command.
     *
     * Valid commands are:
     * "quit" - the program should quit
     * "increment" - an int should be incremented by 1
     * "decrement" - an int should be decremented by 1
     * "+" - two numbers should be added
     * "-" - a number should be subtracted from another
     * "/" - a number should be divided by another
     *
     * @param input A String possibly containing a calculator command. Tokens of the command are separated by space.
     * e.g. A valid command would be "50 + 20". This will be split up (Tokenized) as an array of three Strings:
     * ["50", "+", "20"].
     * @return The following values are returned under the given conditions:
     * (1) "quit" - if the program should end
     * (2) "The result is: %d", where %d is replaced with the returned value of execute(Tokens) - the command is
     * executed correctly and is not "quit".
     * (3) "Attempted to divide by 0. Please try again." - an ArithmeticException has been caught.
     * (4) "Input number cannot be parsed to an int. Please try again." - a NumberFormat has been caught.
     * (5) "Calculator Exception, message is: %s", where %s is the message of a
     * CalculatorException - a CalculatorException has been caught.
     */
    public static String parseAndExecute(String input)
    {
        String[] Tokens = input.split(" ");
        String result = null;
        try {
        	if(execute(Tokens) == Integer.MIN_VALUE) {
        		result = "quit";
        	}
        	else {
        		result = String.format("The result is: %d", execute(Tokens));
        	}
        }
        catch(ArithmeticException a) {
        	result = "Attempted to divide by 0. Please try again.";
        }
        catch(NumberFormatException n) {
        	result = "Input number cannot be parsed to an int. Please try again.";
        }
        catch(CalculatorException c) {
        	result = String.format("Calculator Exception, message is: %s", c.getMessage());
        }
        return result;
    }
}
