package lab1;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        System.out.println(line);

        Lexer lexer = new Lexer();
        LinkedList<Token> list = new LinkedList<>(lexer.createTokens(line));

        for (Token t : list) {
            System.out.println(t.type + " " + t.value);
        }
    }
}