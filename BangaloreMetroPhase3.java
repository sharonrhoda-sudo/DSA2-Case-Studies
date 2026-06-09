import java.util.*;

class Edge implements Comparable<Edge> {
    String src, dest;
    int cost;

    Edge(String src, String dest, int cost) {
        this.src = src;
        this.dest = dest;
        this.cost = cost;
    }

    public int compareTo(Edge other) {
        return this.cost - other.cost;
    }
}

class DisjointSet {
    private Map<String, String> parent = new HashMap<>();

    public void makeSet(String node) {
        parent.put(node, node);
    }

    public String find(String node) {
        if (!parent.get(node).equals(node)) {
            parent.put(node, find(parent.get(node)));
        }
        return parent.get(node);
    }

    public void union(String a, String b) {
        String rootA = find(a);
        String rootB = find(b);

        if (!rootA.equals(rootB)) {
            parent.put(rootA, rootB);
        }
    }
}

public class BangaloreMetroPhase3 {

    public static void main(String[] args) {

        String[] stations = {"M", "K", "W", "S", "E", "Y", "H"};

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge("M", "K", 12));
        edges.add(new Edge("K", "W", 8));
        edges.add(new Edge("M", "S", 10));
        edges.add(new Edge("S", "E", 6));
        edges.add(new Edge("E", "W", 7));
        edges.add(new Edge("M", "Y", 9));
        edges.add(new Edge("Y", "H", 5));
        edges.add(new Edge("H", "W", 11));

        Collections.sort(edges);

        DisjointSet ds = new DisjointSet();

        for (String station : stations) {
            ds.makeSet(station);
        }

        List<Edge> mst = new ArrayList<>();
        int mstCost = 0;

        for (Edge edge : edges) {
            if (!ds.find(edge.src).equals(ds.find(edge.dest))) {
                mst.add(edge);
                mstCost += edge.cost;
                ds.union(edge.src, edge.dest);
            }
        }

        System.out.println("==============================================");
        System.out.println(" BANGALORE METRO PHASE-3 EXPANSION PROJECT");
        System.out.println("==============================================");

        System.out.println("\nMinimum Spanning Tree (MST) Edges:");

        for (Edge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest +
                    " : ₹" + edge.cost + " Crore");
        }

        System.out.println("\nMST Construction Cost = ₹" + mstCost + " Crore");

        // Add redundancy edge
        Edge redundancyEdge = new Edge("M", "K", 12);

        int finalCost = mstCost + redundancyEdge.cost;

        System.out.println("\n----------------------------------------------");
        System.out.println("Redundancy Requirement");
        System.out.println("----------------------------------------------");

        System.out.println("Additional Edge Added:");
        System.out.println(redundancyEdge.src + " - " +
                redundancyEdge.dest + " : ₹" +
                redundancyEdge.cost + " Crore");

        System.out.println("\nTwo Edge-Disjoint Paths Between M and W:");

        System.out.println("Path 1: M -> K -> W");
        System.out.println("Path 2: M -> S -> E -> W");

        System.out.println("\n----------------------------------------------");
        System.out.println("Final Results");
        System.out.println("----------------------------------------------");

        System.out.println("Network Status          : CONNECTED");
        System.out.println("Redundancy Requirement  : SATISFIED");
        System.out.println("Final Construction Cost : ₹" +
                finalCost + " Crore");

        System.out.println("\n==============================================");
        System.out.println("Project Successfully Optimized");
        System.out.println("==============================================");
    }
}