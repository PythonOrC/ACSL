import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class in2post {
    public static void main(String[] args) {

        // 完成中缀转后缀
        String expression = "((3*(4-(6/2)))+(((2-(2*2))+6)^2))";
        List<String> infixExpressionList = toInfixExpression(expression);
        System.out.println(infixExpressionList);
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println(parseSuffixExpressionList);

        // 先定义一个逆波兰表达式
        // 为了方便 数字和符号用空格隔开
        // String suffixExpression = "3 4 + 5 * 6 -";
        // 将表达式放入ArrayList，遍历集合配合栈完成计算

        // List<String> List = getListString(suffixExpression);
        int res = calculate(parseSuffixExpressionList);
        System.out.println("逆波兰表达式结果为：" + res);
    }

    // 将逆波兰表达式，依次将数字和运算法放入ArrayList中
    public static List<String> getListString(String suffixExpression) {
        // 将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    // 方法 将中缀表达式转成对应List
    public static List<String> toInfixExpression(String s) {
        ArrayList<String> ls = new ArrayList<>();
        int i = 0; // 这时是一个指针，用于遍历中缀表达式字符串
        String str; // 多位数的拼接
        char c; // 每次遍历一个字符 就放入c
        do {
            // 如果c为非数字，我们就加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++; // i需要后移
            } else {
                str = ""; // 先将str置成""
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;// 拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    // 将中缀转后缀
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        // 定义两个栈
        Stack<String> s1 = new Stack<>();// s1栈
        // s2整个过程不用pop而且最后还要逆序 我们使用list
        ArrayList<String> s2 = new ArrayList<>();
        // 遍历
        for (String item : ls) {
            // 如果是一个数 就加入到s2
            if (item.matches("\\d+")) { // 正则判断数字
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); // 消除s1的左括号
            } else {
                // 当item优先级小于等于栈顶栈顶
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    // 完成对逆波兰表达式的计算
    public static int calculate(List<String> ls) {
        // 创建一个栈
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            if (item.matches("\\d+")) { // 匹配的是多位数
                // 入栈
                stack.push(item);
            } else {
                // pop出两个数并运算再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else if (item.equals("^")){
                    res = (int) Math.pow(num1, num2);
                } else {
                    throw new RuntimeException("符号有问题");
                }
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

// 编写个返回优先级的类
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;
    private static int POW = 3;

    // 返回对应优先级
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            case "^":
                result = POW;
                break;
            default:
                System.out.println("不存在输入的符号");
                break;
        }
        return result;
    }
}