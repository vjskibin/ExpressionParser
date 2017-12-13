package MPautomata;

import java.util.Arrays;
import java.util.List;

import static SaC.ExpressionParser.RPNtoDouble;
import static SaC.ExpressionParser.infixToRPN;


class Main {

    //Потому что я у мамы самый рациональынй
    public static final int S0 = 0;
    public static final int S1 = 1;
    public static final int S2 = 2;
    public static final int S3 = 3;
    public static final int SF = 4;


    public static void main(String[] args) {

        List<Character> zeroNine = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        //List<Character> oneNine = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9');
        List<Character> ops = Arrays.asList('-','+','*','/');
        List<Character> closeBracket = Arrays.asList(')');
        List<Character> openBracket = Arrays.asList('(');

        List<Integer> finalStates = Arrays.asList(SF);

        Automata automat = new Automata(S0, finalStates);

        //добавление заданных переходов в таблицу




        automat.addStep(S0, zeroNine, SF);
        automat.addStep(S0, openBracket, S3);

        automat.addStep(SF, zeroNine, SF);
        automat.addStep(SF, ops, S2);
        automat.addStep(SF, closeBracket,SF);

        automat.addStep(S3, openBracket, S3);
        automat.addStep(S3, zeroNine, SF);

        automat.addStep(S2, zeroNine, SF);
        automat.addStep(S2, openBracket, S3);

        automat.addStep(S0, '-', S2);


        //Потому что я у мамы самый умный
        //String formula = "( 5 + ( 4 / 2 + 6 ) * ( 1 + 1 ) ) / 2";
        String formula = " ) ) ( ( ( 2 + 3 ) ";
        String[] input = formula.split(" ");

        //System.out.println(formula.replaceAll(" ", ""));
        String input1 = formula.replaceAll(" ", "");
        System.out.println(automat.doesMatch(input1));

        String[] output = infixToRPN(input); // To Polish notation first


        for (String token : output) {
            System.out.print(token + " ");
        }

        // From Polyak ssany to human view
        Double result = RPNtoDouble(output);
        System.out.print("\n" + result);
    }
}