package ru.nsu.fit.g15205.shishlyannikov.calculator;

import ru.nsu.fit.g15205.shishlyannikov.exceptions.LexerException;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private Reader reader;
    private int current;

    public Lexer(Reader r) {
        reader = r;
        try {
            current = reader.read();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    Lexeme getLexeme() throws LexerException {
        Lexeme lexeme;

        if ((char) current == '+') {
            lexeme = new Lexeme(LexemeType.PLUS, "+");
        } else if ((char) current == '-') {
            lexeme = new Lexeme(LexemeType.MINUS, "-");
        } else if ((char) current == '*') {
            lexeme = new Lexeme(LexemeType.MUL, "*");
        } else if ((char) current == '/') {
            lexeme = new Lexeme(LexemeType.DIV, "/");
        } else if ((char) current == '(') {
            lexeme = new Lexeme(LexemeType.OPEN, "(");
        } else if ((char) current == ')') {
            lexeme = new Lexeme(LexemeType.CLOSE, ")");
        } else if ((char) current == '^') {
            lexeme = new Lexeme(LexemeType.POWER, "^");
        } else if (Character.isDigit((char) current)) {
            StringBuilder stringBuilder = new StringBuilder();

            do {
                stringBuilder.append((char) current);
                try {
                    current = reader.read();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    throw new LexerException("read error");
                }
            } while (Character.isDigit((char) current) && current != -1);

            return new Lexeme(LexemeType.NUMBER, stringBuilder.toString());
        } else if (current == -1 || (char) current == '\n') {
            lexeme = new Lexeme(LexemeType.EOF, "-1");
        } else {
            throw new LexerException("unknown lexeme: " + (char) current);
        }

        try {
            if (reader.ready()) {
                current = reader.read();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new LexerException("read error");
        }
        return lexeme;
    }
}
