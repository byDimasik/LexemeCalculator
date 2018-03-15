package ru.nsu.fit.g15205.shishlyannikov;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ru.nsu.fit.g15205.shishlyannikov.calculator.Lexer;
import ru.nsu.fit.g15205.shishlyannikov.calculator.Parser;
import ru.nsu.fit.g15205.shishlyannikov.exceptions.LexerException;
import ru.nsu.fit.g15205.shishlyannikov.exceptions.ParserException;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    private int getResult(String expression) throws Exception {
        Reader reader = new StringReader(expression);

        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);

        return parser.calculate();
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        try {
            assertEquals(2, getResult("1+1"));
            assertEquals(6, getResult("2+2*2"));
            assertEquals(8, getResult("(2+2)*2"));

            try {
                getResult("1/0");
                assertTrue(false);
            } catch (ArithmeticException ex) {
                assertTrue(true);
            }

            assertEquals(-1276, getResult("2^2-4*5*((-2)^6)"));
            assertEquals(1284, getResult("2^2-4*5*(-2^6)"));

            try {
                getResult("1+1(");
                assertTrue(false);
            } catch (ParserException ex) {
                assertTrue(true);
            }

            try {
                getResult("abc");
                assertTrue(false);
            } catch (LexerException ex) {
                assertTrue(true);
            }
        } catch (Exception ex) {
            ex.getLocalizedMessage();
        }
    }
}
