package MPautomata;

import static SaC.ExpressionParser.RPNtoDouble;
import static SaC.ExpressionParser.infixToRPN;


class Main {

    public static void main(String[] args) {
        String[] input = "( 5 + 4 / 2 + 6 ) * 2".split(" ");
        String[] output = infixToRPN(input);

        // Build output RPN string minus the commas
        for (String token : output) {
            System.out.print(token + " ");
        }

        // Feed the RPN string to RPNtoDouble to give result
        Double result = RPNtoDouble(output);
        System.out.print("\n" + result);
    }
}