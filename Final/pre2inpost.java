import java.util.Stack;

class pre2inpost {
    public static void main(String[] args) {
        String exp = "^+*34/82-75";
        System.out.println("Prefix : " + exp);
        System.out.println("Infix : " + preToIn(exp));
        System.out.println("Postfix : " + preToPost(exp));
    }

    // Function to check if character
    // is operator or not
    static boolean isOperator(char x) {
        switch (x) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '^':
            case '%':
                return true;
        }
        return false;
    }

    // Convert prefix to Infix expression
    public static String preToIn(String str) {
        Stack<String> stack = new Stack<>();

        // Length of expression
        int l = str.length();

        // Reading from right to left
        for (int i = l - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (isOperator(c)) {
                String op1 = stack.pop();
                String op2 = stack.pop();

                // Concat the operands and operator
                String temp = "(" + op1 + c + op2 + ")";
                stack.push(temp);
            } else {

                // To make character to string
                stack.push(c + "");
            }
        }
        return stack.pop();
    }

    // Convert prefix to Postfix expression
    static String preToPost(String pre_exp) {

        Stack<String> s = new Stack<String>();

        // length of expression
        int length = pre_exp.length();

        // reading from right to left
        for (int i = length - 1; i >= 0; i--) {
            // check if symbol is operator
            if (isOperator(pre_exp.charAt(i))) {
                // pop two operands from stack
                String op1 = s.peek();
                s.pop();
                String op2 = s.peek();
                s.pop();

                // concat the operands and operator
                String temp = op1 + op2 + pre_exp.charAt(i);

                // Push String temp back to stack
                s.push(temp);
            }

            // if symbol is an operand
            else {
                // push the operand to the stack
                s.push(pre_exp.charAt(i) + "");
            }
        }

        // stack contains only the Postfix expression
        return s.peek();
    }

    // Driver Code

    // Driver code

}