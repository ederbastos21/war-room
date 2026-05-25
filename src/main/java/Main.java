import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Graph graph = Graph.readFromInput(scanner);

        System.out.println();
        System.out.println("grafo carregado");
        System.out.println("vertices: " +graph.vertices.size());
        System.out.println("Arestas: " +graph.edges.size());
        System.out.println();

        System.out.println("Vertices:");
        for (int i = 0; i < graph.vertices.size(); i++) {
            System.out.println("  [" + i + "] " + graph.vertices.get(i));
        }

        System.out.println("arestas:");
        for (int[] edge : graph.edges) {
            String nameA = graph.vertices.get(edge[0]);
            String nameB = graph.vertices.get(edge[1]);
            System.out.println("  " + nameA + " <-> " + nameB);
        }

        System.out.println();
        System.out.println("calculando vertex cover...");
        System.out.println();

        long startTime = System.currentTimeMillis();
        ArrayList<String> cover = VertexCover.solve(graph);
        long endTime = System.currentTimeMillis();
        long timeMs = endTime - startTime;

        System.out.println("RESULTADO\n");
        System.out.println("vertices escolhidos (vertex cover):");
        for (String v : cover) {
            System.out.println("  - " +v);
        }

        System.out.println();
        System.out.println("tamanho da cobertura: " +cover.size());
        System.out.println("Total de vertices: " +graph.vertices.size());
        System.out.println("total de arestas: " +graph.edges.size());
        System.out.println("Tempo: " +timeMs+ "ms");

        System.out.println();
        System.out.println("ANALISE DE COMPLEXIDADE\n");

        if (graph.vertices.size() <= 20) {
            System.out.println("metodo: forca bruta");
            System.out.println("Complexidade de tempo: O(2^n * m)");
            System.out.println("  n = " +graph.vertices.size()+ " (vertices)");
            System.out.println("  m = " +graph.edges.size()+ " (arestas)");
            System.out.println("  combinacoes verificadas: " +(long) Math.pow(2, graph.vertices.size()));
            System.out.println("complexidade de espaco: O(n)");
            System.out.println("Resultado: OTIMO (vertex cover minimo exato)");
        } else {
            System.out.println("Metodo: 2-aproximacao (emparelhamento guloso)");
            System.out.println("complexidade de tempo: O(n + m)");
            System.out.println("  n = " +graph.vertices.size()+ " (vertices)");
            System.out.println("  m = " +graph.edges.size()+ " (arestas)");
            System.out.println("Complexidade de espaco: O(n + m)");
            System.out.println("resultado: APROXIMADO (no maximo 2x o tamanho otimo)");
        }

        System.out.println();
        System.out.println("Vertex Cover e um problema NP-Completo.");
        System.out.println("nao existe algoritmo exato polinomial conhecido para entradas grandes.");

        scanner.close();
    }
}
