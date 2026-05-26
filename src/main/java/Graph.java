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

        System.out.println("Digite um nome para cada vertice: ");
        for (int i = 0; i < vertexCount; i++) {
            System.out.print("  vertice " + i + ": ");
            graph.addVertex(scanner.nextLine().trim());
        }

        System.out.println("\nVertices cadastrados:");
        for (int i = 0; i < graph.vertices.size(); i++) {
            System.out.println("  [" + i + "] " + graph.vertices.get(i));
        }

        System.out.print("quantas arestas? ");
        int edgeCount = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("\nInforme as arestas usando os indices acima.");
        System.out.println("Exemplo: 0 1 para ligar o vertice 0 com o 1");
        for (int i = 0; i < edgeCount; i++) {
            System.out.println("\nAresta " + (i + 1) + " de " + edgeCount + ":");
            System.out.print("  de (indice): ");
            int a = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("  para (indice): ");
            int b = Integer.parseInt(scanner.nextLine().trim());
            graph.addEdge(a, b);
        }

        return graph;
    }
}
