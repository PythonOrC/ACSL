import java.util.Stack;

public class in2prepost{
    public static void main(String[] args) {
        String in_exp="((4-5)^2)/(6^2)+((7-8)^2)/(9^2)";
        System.out.println("Infix: "+in_exp);
        System.out.println("Prefix: "+zhong2Qian(in_exp));
        System.out.println("Postfix: "+zhong2hou(in_exp));
    }


    private static String zhong2Qian(String expression) {

        // (1) 初始化两个栈：运算符栈S1和储存中间结果的栈S2;
        Stack<Character> ops = new Stack<>();
        Stack<Character> resultValues = new Stack<>();

        // (2) 从右至左扫描中缀表达式;这里是先反转字符串再遍历其字符数组
        expression = reverseString(expression);
        char[] chars = expression.toCharArray();
        for (char theChar : chars) {
            // (3) 遇到操作数时，将其压入S2;
            if (Character.isDigit(theChar))
                resultValues.push(theChar);
            // (4) 遇到运算符时，比较其与S1栈顶运算符的优先级：
            else if (theChar == '+' || theChar == '-' || theChar == '*' || theChar == '/' || theChar == '^') {
                dealTheChar(ops, resultValues, theChar);

            }
            // (5-1) 如果是右括号“)”，则直接压入S1；
            else if (theChar == ')')
                ops.push(theChar);
            // (5-2) 如果是左括号“(”，则依次弹出S1栈顶的运算符，并压入S2，直到遇到右括号为止，此时将这一对括号丢弃;
            else if (theChar == '(') {
                char opsChar = ops.pop();
                while (opsChar != (')')) {
                    resultValues.push(opsChar);
                    opsChar = ops.pop();
                }
            }
        }

        // (7)将S1中剩余的运算符依次弹出并压入S2；
        while (!ops.empty()) {
            resultValues.push(ops.pop());
        }

        // (8) 依次弹出S2中的元素并输出，结果即为中缀表达式对应的前缀表达式。
        // (后缀表达式这里要将字符串反转输出,这里直接输出即可)
        StringBuilder exp = new StringBuilder();
        while (!resultValues.empty())
            exp.append(resultValues.pop());

        return exp.toString();
    }
    
    private static String zhong2hou(String expression) {

        // (1) 初始化两个栈：运算符栈S1和储存中间结果的栈S2；
        Stack<Character> ops = new Stack<>();
        Stack<Character> resultValues = new Stack<>();

        char[] chars = expression.toCharArray();

        // (2) 从左至右扫描中缀表达式；
        for (char theChar : chars) {
            // (3) 遇到操作数时，将其压入S2；
            if (Character.isDigit(theChar))
                resultValues.push(theChar);
            // (4) 遇到运算符时，比较其与S1栈顶运算符的优先级：
            else if (theChar == '+' || theChar == '-' || theChar == '*' || theChar == '/' || theChar == '^')
                dealTheChar(ops, resultValues, theChar);
            // (5) 遇到括号时：
            // (5-1) 如果是左括号“(”，则直接压入S1；
            else if (theChar == '(')
                ops.push(theChar);
            // (5-2) 如果是右括号“)”，则依次弹出S1栈顶的运算符，并压入S2，直到遇到左括号为止，此时将这一对括号丢弃；
            else if (theChar == ')') {
                char opsChar = ops.pop();
                while (opsChar != '(') {
                    resultValues.push(opsChar);
                    opsChar = ops.pop();
                }
            }
        }

        // (7) 将S1中剩余的运算符依次弹出并压入S2；
        while (!ops.isEmpty())
            resultValues.push(ops.pop());

        // (8) 依次弹出S2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式（转换为前缀表达式时不用逆序）。
        StringBuilder exp = new StringBuilder();
        while (!resultValues.empty())
            exp.append(resultValues.pop());

        return reverseString(exp.toString());
    }

    private static String reverseString(String string) {
        StringBuilder sb = new StringBuilder(string);
        return sb.reverse().toString();
    }

    private static void dealTheChar(Stack<Character> ops, Stack<Character> resultValues, char theChar) {
        // (4-1) 如果S1为空，则直接将此运算符入栈;
        if (ops.empty()) {
            ops.push(theChar);
            return;
        }
        char popTheChar = ops.pop().charValue();
        // (4-1) 如果S1为空，或栈顶运算符为右括号“(”，则直接将此运算符入栈;
        // (4-2) 否则，若优先级比栈顶运算符的高，也将运算符压入S1（注意转换为前缀表达式时是优先级较高或相同，而这里则不包括相同的情况）
        if (popTheChar == '(' || getPriorityValue(theChar) > getPriorityValue(popTheChar)) {
            ops.push(popTheChar);
            ops.push(theChar);
        }
        // (4-3) 否则，将S1栈顶的运算符弹出并压入到S2中
        else {
            resultValues.push(popTheChar);
            // ，再次转到(4-1)与S1中新的栈顶运算符相比较;
            dealTheChar(ops, resultValues, theChar);
        }
    }

    private static int getPriorityValue(char theChar) {
        switch (theChar) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }
}