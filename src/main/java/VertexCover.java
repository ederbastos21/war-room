import java.util.ArrayList;

public class VertexCover {

    public static ArrayList<String> solve(Graph graph) {
        if (graph.vertices.size() <= 20) {
            System.out.println("grafo pequeno, usando forca bruta (soluçao exata)");
            return bruteForce(graph);
        } else {
            System.out.println("Grafo grande, usando 2-aproximacao.");
            return approximation(graph);
        }
    }

    private static ArrayList<String> bruteForce(Graph graph) {
        int n = graph.vertices.size();
        ArrayList<String> best = null;

        long totalCombinations = (long) Math.pow(2, n);

        for (long mask = 0; mask < totalCombinations; mask++) {
            ArrayList<Integer> candidate = new ArrayList<>();

            for (int bit = 0; bit < n; bit++) {
                if ((mask & (1L << bit)) != 0) {
                    candidate.add(bit);
                }
            }

            if (coversAllEdges(candidate, graph)) {
                if (best == null || candidate.size() < best.size()) {
                    best = new ArrayList<>();
                    for (int idx : candidate) {
                        best.add(graph.vertices.get(idx));
                    }
                }
            }
        }

        if (best == null) {
            best = new ArrayList<>();
        }

        return best;
    }

    private static boolean coversAllEdges(ArrayList<Integer> candidate, Graph graph) {
        for (int[] edge : graph.edges) {
            boolean aIsCovered = candidate.contains(edge[0]);
            boolean bIsCovered = candidate.contains(edge[1]);

            if (!aIsCovered && !bIsCovered) {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<String> approximation(Graph graph) {
        ArrayList<String> cover = new ArrayList<>();
        boolean[] used = new boolean[graph.vertices.size()];

        ArrayList<int[]> remainingEdges = new ArrayList<>();
        for (int[] edge : graph.edges) {
            remainingEdges.add(new int[]{edge[0], edge[1]});
        }

        while (!remainingEdges.isEmpty()) {
            int[] picked = remainingEdges.get(0);
            int u = picked[0];
            int v = picked[1];

            if (!used[u]) {
                used[u] = true;
                cover.add(graph.vertices.get(u));
            }

            if (!used[v]) {
                used[v] = true;
                cover.add(graph.vertices.get(v));
            }

            ArrayList<int[]> toRemove = new ArrayList<>();
            for (int[] edge : remainingEdges) {
                if (edge[0] == u || edge[1] == u || edge[0] == v || edge[1] == v) {
                    toRemove.add(edge);
                }
            }

            remainingEdges.removeAll(toRemove);
        }

        return cover;
    }
}
