import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeCreationGPT {
    private static List<Character> letters;
    private static List<Integer> values;
    private static Deque<Integer> visitOrder;

    public static void main(String[] args) {
        String input = "PYTHONN";
        System.out.println(getTraversals(input));
    }

    public static String getTraversals(String input) {
        letters = new ArrayList<>();
        values = new ArrayList<>();
        visitOrder = new LinkedList<Integer>();

        for (char letter : input.toCharArray()) {
            insertLetter(letter);
        }
        System.out.println(sweepArray());
        return "A";
    }

    private static void insertLetter(char letter) {
        int idx = 0;
        while (idx < letters.size() && letters.get(idx) < letter) {
            idx++;
        }
        letters.add(idx, letter);
        int value;
        if (letters.size() == 1) {
            value = 0;
        } else if (idx == letters.size() - 1) {
            value = values.get(idx - 1) + 1;
        } else if (idx == 0) {
            value = values.get(idx) + 1;
        } else {
            value = Math.max(values.get(idx - 1), values.get(idx)) + 1;
        }
        values.add(idx, value);
    }


    private static String[] sweepArray() {
        String firstString = "";
        String secondString = "";
        int zeroIndex = values.indexOf(0);
        StringBuilder firstConstruct = new StringBuilder();
        StringBuilder secondConstruct = new StringBuilder();
        sweep(zeroIndex, firstConstruct, 0);


        return new String[] { firstString, secondString };
    }

    private static void sweep(int startIndex, StringBuilder construct, int prev) {
        if (Collections.max(values) == prev) {
            return;
        }

        visitOrder.add(null);
    }



    
    public class BinarySearchTree {
        // Represent the node of binary tree
        public static class Node {
            int data;
            Node left;
            Node right;

            public Node(int data) {
                // Assign data to the new node, set left and right children to null
                this.data = data;
                this.left = null;
                this.right = null;
            }
        }

        // Represent the root of binary tree
        public Node root;

        public BinarySearchTree() {
            root = null;
        }

        // factorial() will calculate the factorial of given number
        public int factorial(int num) {
            int fact = 1;
            if (num == 0)
                return 1;
            else {
                while (num > 1) {
                    fact = fact * num;
                    num--;
                }
                return fact;
            }
        }

        // numOfBST() will calculate the total number of possible BST by calculating
        // Catalan Number for given key
        public int numOfBST(int key) {
            int catalanNumber = factorial(2 * key) / (factorial(key + 1) * factorial(key));
            return catalanNumber;
        }
    }
}


