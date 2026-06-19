public class BMTCBellmanFord {

    static class Edge {
        String src, dest;
        int weight;

        Edge(String src, String dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {

        String[] vertices = {"MJC", "KEM", "JAY", "KOR", "WHF", "HBR", "MRT"};

        Edge[] edges = {
            new Edge("MJC", "KEM", 4),
            new Edge("MJC", "JAY", 8),
            new Edge("KEM", "KOR", 5),
            new Edge("JAY", "KOR", 2),
            new Edge("KOR", "WHF", 7),
            new Edge("KOR", "HBR", 4),
            new Edge("WHF", "MRT", -3), // Negative edge
            new Edge("HBR", "MRT", 6)
        };

        int V = vertices.length;
        int[] dist = new int[V];

        // Initialize distances
        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        dist[0] = 0; // Source = MJC

        // Bellman-Ford Algorithm
        for (int i = 1; i < V; i++) {
            for (Edge e : edges) {
                int u = getIndex(vertices, e.src);
                int v = getIndex(vertices, e.dest);

                if (dist[u] != Integer.MAX_VALUE && dist[u] + e.weight < dist[v]) {
                    dist[v] = dist[u] + e.weight;
                }
            }
        }

        // Check for negative cycle
        boolean negativeCycle = false;
        for (Edge e : edges) {
            int u = getIndex(vertices, e.src);
            int v = getIndex(vertices, e.dest);

            if (dist[u] != Integer.MAX_VALUE && dist[u] + e.weight < dist[v]) {
                negativeCycle = true;
                break;
            }
        }

        if (negativeCycle) {
            System.out.println("Negative weight cycle detected!");
        } else {
            System.out.println("Shortest Travel Time from MJC (Majestic):");
            for (int i = 0; i < V; i++) {
                System.out.println("MJC -> " + vertices[i] + " = " + dist[i] + " minutes");
            }
        }
    }

    // Function to get index of a vertex
    static int getIndex(String[] vertices, String vertex) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(vertex))
                return i;
        }
        return -1;
    }
}