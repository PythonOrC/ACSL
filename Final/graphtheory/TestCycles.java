
import java.util.List;

/**
 * Testfile for elementary cycle search.
 *
 * @author Frank Meyer
 *
 */
public class TestCycles {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int SIZE = 5;
		String nodes[] = new String[SIZE];
		boolean adjMatrix[][] = new boolean[SIZE][SIZE];

		for (int i = 0; i < SIZE; i++) {
			nodes[i] = "Node " + i;
		}

		adjMatrix[0][1] = true; adjMatrix[0][2] = true; adjMatrix[0][3] = true; adjMatrix[0][4] = true;
		adjMatrix[1][0] = true; adjMatrix[1][2] = true; adjMatrix[1][4] = true;
		adjMatrix[2][1] = true;
		adjMatrix[3][0] = true; adjMatrix[3][4] = true;
		adjMatrix[4][0] = true; adjMatrix[3][3] = true;

		ElementaryCyclesSearch ecs = new ElementaryCyclesSearch(adjMatrix, nodes);
		List cycles = ecs.getElementaryCycles();
		for (int i = 0; i < cycles.size(); i++) {
			List cycle = (List) cycles.get(i);
			for (int j = 0; j < cycle.size(); j++) {
				String node = (String) cycle.get(j);
				if (j < cycle.size() - 1) {
					System.out.print(node + " -> ");
				} else {
					System.out.print(node);
				}
			}
			System.out.print("\n");
		}
	}

}
