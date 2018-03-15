package ru.nsu.fit.g15205.shishlyannikov.calculator;

import ru.nsu.fit.g15205.shishlyannikov.exceptions.LexerException;
import ru.nsu.fit.g15205.shishlyannikov.exceptions.ParserException;

public class Parser {
    private Lexer lexer;
    private Lexeme current;

    public Parser(Lexer l) throws LexerException {
        lexer = l;
        current = lexer.getLexeme();

    }

    private int parseExpression() throws ParserException, LexerException {
        int temp = parseTerm();

        while (current.type == LexemeType.PLUS || current.type == LexemeType.MINUS) {
            if (current.type == LexemeType.PLUS) {
                current = lexer.getLexeme();
                temp += parseTerm();
            }

            if (current.type == LexemeType.MINUS) {
                current = lexer.getLexeme();
                temp -= parseTerm();
            }
        }

        return temp;
    }

    private int parseTerm() throws ParserException, LexerException {
        int temp = parseFactor();

        while (current.type == LexemeType.MUL || current.type == LexemeType.DIV) {
            if (current.type == LexemeType.MUL) {
                current = lexer.getLexeme();
                temp *= parseFactor();
            }

            if (current.type == LexemeType.DIV) {
                current = lexer.getLexeme();
                temp /= parseFactor();
            }
        }

        return temp;
    }

    private int parseFactor() throws ParserException, LexerException {
        if (current.type == LexemeType.MINUS) {
            current = lexer.getLexeme();
            return -parsePower();
        } else {
            return parsePower();
        }
    }

    private int parsePower() throws ParserException, LexerException {
        int temp = parseAtom();

        if (current.type == LexemeType.POWER) {
            current = lexer.getLexeme();
            temp = (int) Math.pow(temp, parseFactor());
        }

        return temp;
    }

    private int parseAtom() throws ParserException, LexerException {
        int temp;
        if (current.type == LexemeType.NUMBER) {
            temp = Integer.parseInt(current.text);
            current = lexer.getLexeme();
            return temp;
        }

        if (current.type == LexemeType.OPEN) {
            current = lexer.getLexeme();
            temp = parseExpression();
            if (current.type != LexemeType.CLOSE) {
                throw new ParserException("invalid expression");
            }

            current = lexer.getLexeme();
            return temp;
        }

        throw new ParserException("unknow error");
    }

    public int calculate() throws ParserException, LexerException {
        int temp = parseExpression();

        if (current.type != LexemeType.EOF) {
            throw new ParserException("invalid expression: end != EOF");
        }

        return temp;
    }

}
