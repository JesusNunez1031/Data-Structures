import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TarjansSCCDriver {
    /**
     * @param n number of nodes in the graph
     * @return An initialized adjacency list of {@code n} nodes
     */
    public static List<Integer>[] createGraph(int n) {
        ArrayList[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        return graph;
    }

    /**
     * Adds a new edge to {@code graph}
     *
     * @param graph graph where the new node will be added to
     * @param from  source node
     * @param to    destination node to which {@code from} is pointing to
     */
    public static void addEdge(List<Integer>[] graph, int from, int to) {
        graph[from].add(to);
    }

    public static void main(String[] args) {
        int n = 4;
        List<Integer>[] graph = createGraph(n);

        //add edges to the graph
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 0);
        addEdge(graph, 1, 3);
//        addEdge(graph, 6, 0);
//        addEdge(graph, 6, 2);
//        addEdge(graph, 3, 4);
//        addEdge(graph, 6, 4);
//        addEdge(graph, 2, 0);
//        addEdge(graph, 0, 1);
//        addEdge(graph, 4, 5);
//        addEdge(graph, 5, 6);
//        addEdge(graph, 3, 7);
//        addEdge(graph, 7, 5);
//        addEdge(graph, 1, 2);
//        addEdge(graph, 7, 3);
//        addEdge(graph, 5, 0);

        TarjansSCC driver = new TarjansSCC(graph);
        driver.solve();

        int[] sccs = driver.getSccs();

        //organize the nodes into a map so we know which nodes form a SCC
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(sccs[i])) {
                map.put(sccs[i], new ArrayList<>());
            }
            map.get(sccs[i]).add(i);
        }

        System.out.printf("Number of Strongly Connected Components: %d\n", driver.sccCount());
        for (List<Integer> scc : map.values()) {
            System.out.println("Nodes: " + scc + " form a Strongly Connected Component.");
        }
    }
}
