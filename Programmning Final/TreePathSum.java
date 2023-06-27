import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {
    static int sz = 10000;
    static int n;
    static ArrayList[] bst = new ArrayList[sz];
    static int[] subtree_size = new int[sz];
    static int[] vis = new int[sz];
    static void AddEdge(int a, int b) {
        bst[a].add(b);
        bst[b].add(a);
    }
    static int dfs(int node) {
        vis[node] = 1;
        subtree_size[node] = 1;
        for (int child : (ArrayList<Integer>) bst[node]) {
            if (vis[child] == 0) {
                subtree_size[node] += dfs(child);
            }
        }
        return subtree_size[node];
    }

    static int contribution(int node, int ans) {
        vis[node] = 1;
        for (int child : (ArrayList<Integer>) bst[node]) {
            if (vis[child] == 0) {
                ans += (subtree_size[child] *
                        (n - subtree_size[child]));
                ans = contribution(child, ans);
            }
        }
        return ans;
    }

    // Function to return the required sum
    static int getSum() {

        // First pass of the dfs
        Arrays.fill(vis, 0);
        dfs(0);

        // Second pass
        int ans = 0;
        Arrays.fill(vis, 0);
        ans = contribution(0, ans);

        return ans;
    }

    public static int countUniqueSums(String inputString) {
        // convert string to int array
        String[] str = inputString.split(" ");
        int[] arr = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }




    }

}

public class TreePathSum {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String inputString = bufferedReader.readLine();

        int result = Result.countUniqueSums(inputString);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
