package ru.nsu.fit.g15205.shishlyannikov;

import ru.nsu.fit.g15205.shishlyannikov.calculator.Lexer;
import ru.nsu.fit.g15205.shishlyannikov.calculator.Parser;

import java.io.InputStreamReader;
import java.io.Reader;

public class App {
    public static void main( String[] args ) {
        Reader reader = new InputStreamReader(System.in);

        try {
            Lexer lexer = new Lexer(reader);
            Parser parser = new Parser(lexer);
            System.out.println(parser.calculate());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(ex.getLocalizedMessage());;
        }
    }
}
