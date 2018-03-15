package ru.nsu.fit.g15205.shishlyannikov.calculator;

public class Lexeme {
    LexemeType type;
    String text;

    Lexeme(LexemeType t, String text) {
        type = t;
        this.text = text;
    }
}
