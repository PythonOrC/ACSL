import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BinarySearchTree {
    TreeNode root;

    public BinarySearchTree(List<Integer> numbers) {
        for (Integer num : numbers) {
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

    public List<List<Integer>> getAdjacencyList() {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        buildAdjacencyList(root, adjacencyList);
        convertToUndirectedAdjacencyList(adjacencyList);
        return adjacencyList;
    }

    private void buildAdjacencyList(TreeNode node, List<List<Integer>> adjacencyList) {
        if (node == null) {
            return;
        }

        List<Integer> neighbors = new ArrayList<>();
        if (node.left != null) {
            neighbors.add(node.left.val);
        }
        if (node.right != null) {
            neighbors.add(node.right.val);
        }

        adjacencyList.add(neighbors);

        buildAdjacencyList(node.left, adjacencyList);
        buildAdjacencyList(node.right, adjacencyList);
    }

    public static List<List<Integer>> convertToUndirectedAdjacencyList(List<List<Integer>> directedAdjacencyList) {
        List<List<Integer>> undirectedAdjacencyList = new ArrayList<>();
        // print adjacency list
        for (int i = 0; i < directedAdjacencyList.size(); i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < directedAdjacencyList.get(i).size(); j++) {
                System.out.print(directedAdjacencyList.get(i).get(j) + " ");
            }
            System.out.println();
        }
        
        for (int i = 0; i < directedAdjacencyList.size(); i++) {
            undirectedAdjacencyList.add(new ArrayList<>(directedAdjacencyList.get(i))); // Copy existing edges

            for (int j = 0; j < directedAdjacencyList.get(i).size(); j++) {
                int neighbor = directedAdjacencyList.get(i).get(j);
                System.out.println("i: " + i + ", neighbor: " + neighbor);
                if (!undirectedAdjacencyList.get(neighbor).contains(i + 1)) {
                    undirectedAdjacencyList.get(neighbor).add(i + 1); // Add reverse edge
                }
            }
        }

        return undirectedAdjacencyList;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class TreePathSum2 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 2, 7, 1, 3, 6, 8);

        BinarySearchTree bst = new BinarySearchTree(numbers);
        List<List<Integer>> directedAdjacencyList = bst.getAdjacencyList();
        List<List<Integer>> undirectedAdjacencyList = new ArrayList<>();
    }
}
