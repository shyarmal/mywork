package airwell.rpn.calculator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class CalculatorTest {
	
	@Test
	public void shouldReturnStackIfNoOperationsDone() {
		Calculator calculator = new Calculator();
		calculator.processInput("5 2");
		assertEquals("5 2", calculator.resultToString());
	}
	
	@Test
	public void shouldCalculateSquareRoots() {
		Calculator calculator = new Calculator();
		calculator.processInput("2 sqrt");
		assertEquals("1.4142135623", calculator.resultToString().substring(0, 12));
		calculator.processInput("clear 9 sqrt");
		assertEquals("3", calculator.resultToString());
	}

	@Test
	public void shouldDoSubstractions() {
		Calculator calculator = new Calculator();
		calculator.processInput("5 2 -");
		assertEquals("3", calculator.resultToString());
		calculator.processInput("3 -");
		assertEquals("0", calculator.resultToString());
		calculator.processInput("clear");
		assertEquals("", calculator.resultToString());
	}
	
	@Test
	public void shouldDivideAndMultiply() {
		Calculator calculator = new Calculator();
		calculator.processInput("7 12 2 /");
		assertEquals("7 6", calculator.resultToString());
		calculator.processInput("*");
		assertEquals("42", calculator.resultToString());
		calculator.processInput("4 /");
		assertEquals("10.5", calculator.resultToString());
	}
	
	@Test
	public void shouldWorkWithMultipleParameters() {
		Calculator calculator = new Calculator();
		calculator.processInput("1 2 3 4 5");
		assertEquals("1 2 3 4 5", calculator.resultToString());
		calculator.processInput("* * * *");
		assertEquals("120", calculator.resultToString());
	}
	
	@Test
	public void shouldMultiplyClearAndSubstractSuccessfully() {
		Calculator calculator = new Calculator();
		calculator.processInput("1 2 3 4 5");
		assertEquals("1 2 3 4 5", calculator.resultToString());
		calculator.processInput("*");
		assertEquals("1 2 3 20", calculator.resultToString());
		calculator.processInput("clear 3 4 -");
		assertEquals("-1", calculator.resultToString());
	}
	
	@Test
	public void shouldUndoSuccessfully() {
		Calculator calculator = new Calculator();
		calculator.processInput("5 4 3 2");
		assertEquals("5 4 3 2", calculator.resultToString());
		calculator.processInput("undo undo *");
		assertEquals("20", calculator.resultToString());
		calculator.processInput("5 *");
		assertEquals("100", calculator.resultToString());
		calculator.processInput("undo");
		assertEquals("20 5", calculator.resultToString());
	}
	
	@Test
	public void shouldThrowExceptionIfShortOfParameters() {
		Calculator calculator = new Calculator();
		try {
			calculator.processInput("1 2 3 * 5 + * * 6 5");
		} catch (Exception e) {
			assertEquals("11", calculator.resultToString());
		}		
	}
	
	@After
	public void tearDown() {
	}
}
