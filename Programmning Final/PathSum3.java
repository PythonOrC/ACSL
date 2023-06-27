
import java.util.HashSet;
import java.util.Set;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

class BinarySearchTree {
    TreeNode root;

    public BinarySearchTree(String digits) {
        for (char c : digits.toCharArray()) {
            int num = Character.getNumericValue(c);
            root = insert(root, num);
        }
    }

    private TreeNode insert(TreeNode root, int num) {
        if (root == null) {
            return new TreeNode(num);
        }

        if (num < root.val) {
            root.left = insert(root.left, num);
        } else if (num > root.val) {
            root.right = insert(root.right, num);
        }

        return root;
    }

    public int countDistinctPathLengths() {
        Set<Integer> pathLengths = new HashSet<>();
        TreeNode node1 = root;
        TreeNode node2 = root;
        traverse(node1, node2);
        return pathLengths.size();
    }

    private void traverse(TreeNode node1, TreeNode node2) {
        int dist = findDistance(node1, node2);
    }
    


public class PathSum3 {
    public static void main(String[] args) {
        String digits = "1241";
        BinarySearchTree bst = new BinarySearchTree(digits);
        int distinctSums = bst.countDistinctPathLengths();

        System.out.println("Number of different sums: " + distinctSums);
    }
}
