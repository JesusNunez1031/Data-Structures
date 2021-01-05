import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class topologicalOrder {
    /*
        a topological sort or topological ordering of a directed graph is a linear ordering of its vertices such that
        for every directed edge uv from vertex u to vertex v, u comes before v in the ordering. For instance, the vertices
        of the graph may represent tasks to be performed, and the edges may represent constraints that one task must be
        performed before another; in this application, a topological ordering is just a valid sequence for the tasks. A
        topological ordering is possible if and only if the graph has no directed cycles, that is, if it is a directed
        acyclic graph (DAG).

        The topological order of a DAG is not unique, there can be many variations on the order, however, any DAG has at
        least one topological ordering.

        Time complexity: O(V + E) where v is the number of vertices and E is the number of edges on a graph
     */
    //Directed a-cyclical graph of nodes, for every node, node[0] = value | node[1] = the value the node points to
    public static int[][] DAG = {{0, 1}, {0, 2}, {0, 3}, {2, 3}, {1, 4}, {1, 3}, {3, 5}, {4, 6}, {4, 7}, {5, 7}, {5, 8}, {6, 7}, {7, 9}, {8, 9}};
    //public static int[][] DAG = {{1,0},{2,0},{3,1},{3,2}};
    public static int n = 10; //values of nodes from 0 to n

    private static int[] topologicalOrder() {
        //array to hold the topological order of the given DAG
        int[] order = new int[n];
        int j = 0;  //index used to store the values into order array
        int nodes_processed = 0;    //counter to keep track of how many nodes have been processed

        //array of lists to hold nodes and all the other nodes they are directed to
        ArrayList[] graph = new ArrayList[n];

        //array to hold the degree of each node value
        int[] degree = new int[n];

        //make a list for each node value, the lists will hold all the nodes ith node points to
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        //increase the degree of each node
        for (int[] node : DAG) {
            graph[node[0]].add(node[1]);    //add the value the node points to the the value list
            degree[node[1]]++;
        }

        //Queue used to to a DFS on the DAG
        Queue<Integer> queue = new LinkedList<>();

        //add all the nodes with degree of 0 to the queue
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                queue.add(i);
                nodes_processed++;
            }
        }

        while (queue.size() != 0) {
            //remove the node to process from the queue
            int value = queue.poll();
            order[j++] = value; //add the value processed to the topological order array

            //visit all the nodes the current value points to and mark them as visited by decreasing their degree
            for (int i = 0; i < graph[value].size(); i++) {
                int pointer = (int) graph[value].get(i);
                degree[pointer]--;
                //if a neighbor node has a degree of 0, add it to the queue for processing
                if (degree[pointer] == 0) {
                    queue.add(pointer);
                    nodes_processed++;
                }
            }
        }
        /*
            if all n nodes were processed, there were no cycles in the graph so we have a valid topological order, otherwise
            return an empty array
         */
        if (nodes_processed == n) {
            return order;
        } else {
            return new int[0];
        }
    }

    public static void main(String[] args) {
        System.out.println("Topological Order: " + Arrays.toString(topologicalOrder()));
    }
}
