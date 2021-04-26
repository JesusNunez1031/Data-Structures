import java.util.*;

public class TarjansSCC {
    /*
    Tarjan's Strongly Connected Components algorithm using an adjacency list
    reference: https://www.youtube.com/watch?v=TyWtx7q2D7Y&ab_channel=WilliamFiset

        In a graph like structure, Strongly Connected Components (SCC's) can be thought of as self-contained cycles within
        a directed graph where every vertex in a given cycle can reach every other vertex in the same cycle.

        Low-Link Value: The low link value of a node is the smallest[lowest] node id (number) reachable from that nodes
        when doing a DFS (including itself)

        Ex:
            given the following directed graph,

            [0] ---> [1] ---> [3]
             ^      / |        |
             |    /   |        |
             |  <     v        v
            [2] ---> [4] ---> [5]
                       ^-------┘
           0's LLV is 0
           1's LLV is 0
           2's LLV is 0

           3's LLV is 3

           4's LLV is 4
           5's LLV is 4

           Note: All nodes with the same LLV belong to the same SCC, hence this graph has 3 SCC's.

           SCC's vary depending on where the DFS search begins leading to wrong identifications of SCC. Tarjan's SCC
           algorithm maintains an invariant that prevents SCC's to interfere with each others' LLV's. To cope with the
           random traversal order of the DFS, Tarjan's algorithm maintains a set (Often as a stack) of valid nodes from
           which to update low-link values from.

           Nodes are added to the stack[set] of valid nodes as they're explored for the first time.

           Nodes are removed from the stack[set] each time a complete SCC is found.

           **Updating a nodes Low-link Value Condition**
           if "u" and "v" are nodes in a graph and we're currently exploring "u" then the new low-link update condition
           is:
                To update node "u" LLV to node "v" LLV there has to be a path of edges from "u" to "v" and node "v"
                must be on the stack, i.e. "v" and "u" are part of the same SCC.

          Time complexity of finding all SCC is O(V + E) where V is the number of vertices and E is the number of edges
          in the graph
     */
    private final int n;
    private final List<Integer>[] graph;

    private boolean solved;
    private int sccCount, time;
    private boolean[] visited;
    /*
        discovery_time[] keeps track of the order in which nodes are visited, can be thought of as the discovery time of
        a node where time is 0 at the root node and time increases as the search through neighbor nodes continues

        low_link[] is the lowest discovery time available to a node, note the time of a root node is 0 since that is the
        start of the time.

        sccs[] is an array holding all the components that belong in the same group, i.e. indexes of equal value belong
        to the same SCC
     */
    private int[] discovery_time, low_link, sccs;
    private Deque<Integer> stack;

    private static final int UNDISCOVERED = -1;


    /**
     * Constructor to initialize {@code graph} with given graph and get the number of {@code n} nodes in graph
     *
     * @param graph non-empty graph passed to be used to find all SCC
     */
    public TarjansSCC(List<Integer>[] graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        }
        n = graph.length;
        this.graph = graph;
    }

    /**
     * @return the number of Strongly Connected Components in {@code graph}
     */
    public int sccCount() {
        if (!solved) {
            solve();
        }
        return sccCount;
    }

    /**
     * @return an array of all of Strongly Connected Components in {@code graph}. Indexes of equal value belong to the
     * same SCC
     */
    public int[] getSccs() {
        if (!solved) {
            solve();
        }
        return sccs;
    }

    /**
     * Begins dfs search through {@code graph} looking for Strongly Connected Components. If the SCC's of {@code graph} have
     * already been found, i.e. {@code solved = true}, {@code solve()} will return.
     */
    public void solve() {
        if (solved) {
            return;
        }
        discovery_time = new int[n];
        low_link = new int[n];
        sccs = new int[n];
        visited = new boolean[n];
        stack = new ArrayDeque<>();

        Arrays.fill(discovery_time, UNDISCOVERED); //mark all nodes as undiscovered

        //dfs search through all the nodes in the graph
        for (int i = 0; i < n; i++) {
            if (discovery_time[i] == UNDISCOVERED) {
                dfs(i);
            }
        }

        //SCC have been found hence graph has been solved
        solved = true;
    }

    //DFS search helper used to search through adjacency lists of ith n node
    private void dfs(int at) {
        //set the discovery time of node to time, and mark it as visited after adding it to the stack
        discovery_time[at] = low_link[at] = time++;
        stack.push(at);
        visited[at] = true;

        //traverse the list of neighbors for the node we are currently at
        for (Integer neighbor : graph[at]) {
            //search through adjacency list of a neighbor if the neighbor of the node we are at is unvisited
            if (discovery_time[neighbor] == UNDISCOVERED) {
                dfs(neighbor);
            }

            /*
                if we've already visited a neighbor of the node we are at, set the LLV of the node we are at to the lowest
                value of the two so we have the smallest value possible since nodes can be linked together by various
                edges.
                Ex: given the edge u, v
                Tree Edge: u -> parent, v -> child
                Back Edge: u -> descendant, v -> ancestor
                Forward Edge: u -> ancestor, v -> descendant
                Cross Edge: No ancestor - descendant relationship
             */
            if (visited[neighbor]) {
                low_link[at] = Math.min(low_link[at], low_link[neighbor]);
            }
        }

        /*
            After visiting all the neighbors of "at", if we are at the start of a SCC empty the stack of processed nodes
            until we are back to the start of the SCC.
         */
        if (discovery_time[at] == low_link[at]) {
            while (!stack.isEmpty()) {
                int node = stack.pop();
                visited[node] = false;
                sccs[node] = sccCount;
                if (node == at) {
                    break;
                }
            }
            //once the stack is clean of all current nodes that make up a SCC, we increase the count of total SCC's
            sccCount++;
        }
    }
}
