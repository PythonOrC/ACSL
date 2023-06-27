public class TreeCreation {
    

    public static void main(String[] args) {
        String input = "PYTHONN";
        System.out.println(getTraversals(input));
    }

    public static String getTraversals(String input) {
        BinaryTree tree = new BinaryTree();
        for(char c : input.toCharArray()){
            tree.add(c);
        }

        return tree.traversePreOrder(tree.root) + " " + tree.traversePostOrder(tree.root);
    }

}

class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
        right = null;
        left = null;
    }
}

class BinaryTree {

    Node root;

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value <= current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }

        return current;
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    public String traversePreOrder(Node node) {
        if (node == null)
            return "";
        return (char) node.value + traversePreOrder(node.left) + traversePreOrder(node.right);
    }

    public String traversePostOrder(Node node) {
        if (node == null)
            return "";
        return traversePostOrder(node.left) + traversePostOrder(node.right) + (char) node.value;
    }
}