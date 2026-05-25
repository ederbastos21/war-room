import java.util.ArrayList;
import java.util.Scanner;

public class Graph {

    ArrayList<String> vertices = new ArrayList<>();
    ArrayList<int[]> edges = new ArrayList<>();

    public void addVertex(String name) {
        vertices.add(name);
    }

    public void addEdge(int a, int b) {
        if (a < 0 || a >= vertices.size() || b < 0 || b >= vertices.size()) {
            throw new IllegalArgumentException("indice vertice invalido:" + a + ", " + b);
        }
        edges.add(new int[]{a, b});
    }

    public static Graph readFromInput(Scanner scanner) {
        Graph graph = new Graph();

        System.out.print("quantos vertices? ");
        int vertexCount = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Digite indices de 0 até " + (vertexCount - 1));
        for (int i = 0; i < vertexCount; i++) {
            System.out.print("  vertice " + i + ": ");
            graph.addVertex(scanner.nextLine().trim());
        }

        System.out.print("quantas arestas? ");
        int edgeCount = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Digite as arestas pelo indice dos vertices:");
        for (int i = 0; i < edgeCount; i++) {
            System.out.print("  aresta " + i + " - indice A: ");
            int a = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("  aresta " + i + " - indice B: ");
            int b = Integer.parseInt(scanner.nextLine().trim());
            graph.addEdge(a, b);
        }

        return graph;
    }
}
