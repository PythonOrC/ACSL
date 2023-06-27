
// Java program to find infix for
// a given postfix.
import java.util.*;

class post2prein {
    public static void main(String args[]) {
        String post_exp = "34*82/+75-^";

        // Function call
        System.out.println("Postfix : " + post_exp);
        System.out.println("Prefix : " + postToPre(post_exp));
        System.out.println("Infix : " + postToIn(post_exp));
    }

    static boolean isOperand(char x) {
        return !isOperator(x);
    }

    // Get Infix for a given postfix
    // expression
    static String postToIn(String exp) {
        Stack<String> s = new Stack<String>();

        for (int i = 0; i < exp.length(); i++) {
            // Push operands
            if (isOperand(exp.charAt(i))) {
                s.push(exp.charAt(i) + "");
            }

            // We assume that input is
            // a valid postfix and expect
            // an operator.
            else {
                String op1 = s.peek();
                s.pop();
                String op2 = s.peek();
                s.pop();
                s.push("(" + op2 + exp.charAt(i) +
                        op1 + ")");
            }
        }

        // There must be a single element
        // in stack now which is the required
        // infix.
        return s.peek();
    }

    static boolean isOperator(char x) {

        switch (x) {
            case '+':
            case '-':
            case '/':
            case '*':
            case '^':
            case '%':
                return true;
        }
        return false;
    }

    // Convert postfix to Prefix expression
    static String postToPre(String post_exp) {
        Stack<String> s = new Stack<String>();

        // length of expression
        int length = post_exp.length();

        // reading from right to left
        for (int i = 0; i < length; i++) {

            // check if symbol is operator
            if (isOperator(post_exp.charAt(i))) {

                // pop two operands from stack
                String op1 = s.peek();
                s.pop();
                String op2 = s.peek();
                s.pop();

                // concat the operands and operator
                String temp = post_exp.charAt(i) + op2 + op1;

                // Push String temp back to stack
                s.push(temp);
            }

            // if symbol is an operand
            else {

                // push the operand to the stack
                s.push(post_exp.charAt(i) + "");
            }
        }

        // concatenate all strings in stack and return the
        // answer
        String ans = "";
        for (String i : s)
            ans += i;
        return ans;
    }

    // Driver Code

}

// This code is contributed by Arnab Kundu
