package lab1;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    static Map<String, Pattern> lexemes = new HashMap<>();
    public Lexer() {
        lexemes.put("VAR", Pattern.compile("[a-z][a-z0-9]*"));

        lexemes.put("ASSIGN_OP", Pattern.compile("="));
        lexemes.put("SUBTRACTION_OP", Pattern.compile("-"));
        lexemes.put("ADDICTION_OP", Pattern.compile("\\+"));

        lexemes.put("STRING", Pattern.compile("\"[0-9a-zA-Z*\\/&s ]*\"|str(\"[0-9a-zA-Z*\\/&s ]*\")"));
        lexemes.put("INT", Pattern.compile("int([1-9][0-9]*)|[1-9][0-9]*|0"));

        lexemes.put("IF_KW", Pattern.compile("IF"));
        lexemes.put("ELSE_KW", Pattern.compile("ELSE"));
        lexemes.put("FOR_KW", Pattern.compile("FOR"));
        lexemes.put("WHILE_KW", Pattern.compile("WHILE"));

        lexemes.put("L_BRACKET", Pattern.compile("\\("));
        lexemes.put("R_BRACKET", Pattern.compile("\\)"));
        lexemes.put("L_BRACE", Pattern.compile("\\{"));
        lexemes.put("R_BRACE", Pattern.compile("}"));
        lexemes.put("END_LINE", Pattern.compile(";"));
        lexemes.put("SPACE", Pattern.compile(" "));
    }

    public static LinkedList<Token> createTokens(String line){

        LinkedList<Token> list = new LinkedList<>();

        char[] chars = line.toCharArray();

        String previousLine = "";
        for (int i = 0; i < line.length(); i++){
            String nextLine = " ";
            previousLine += chars[i];
            if(i < line.length()-1){
                nextLine = previousLine + chars[i+1];
            }

            for(String key: Lexer.lexemes.keySet()){
                Pattern pattern = Lexer.lexemes.get(key);

                Matcher matcherForPreviousLine = pattern.matcher(previousLine);
                Matcher matcherForNextLine = pattern.matcher(nextLine);

                if (matcherForPreviousLine.matches() && !matcherForNextLine.matches()){
                    list.add(new Token(key, previousLine));
                    previousLine = "";
                }

            }
        }
        return list;
    }
}