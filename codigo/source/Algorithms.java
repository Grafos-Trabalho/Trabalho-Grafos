package source;

import java.util.List;
import source.grafo.*;

public final class Algorithms {
    private Algorithms() { }

    /**
     * 
     * @param u
     * @param low
     * @param disc
     * @param stackMember
     * @param st
     */

    // public static void SCCUtil(int u, int low[], int disc[], boolean stackMember[], Stack<Integer> st) {
    //     disc[u] = time;
    //     low[u] = time;
    //     time += 1;
    //     stackMember[u] = true;
    //     st.push(u);

    //     int n;

    //     Iterator<Integer> i = adj[u].iterator();

    //     while (i.hasNext()) {
    //         n = i.next();

    //         if (disc[n] == -1) {
    //             SCCUtil(n, low, disc, stackMember, st);

    //             low[u] = Math.min(low[u], low[n]);
    //         } else if (stackMember[n] == true) {

    //             low[u] = Math.min(low[u], disc[n]);
    //         }
    //     }

    //     int w = -1;
    //     if (low[u] == disc[u]) {
    //         while (w != u) {
    //             w = (int) st.pop();
    //             System.out.print(w + " ");
    //             stackMember[w] = false;
    //         }
    //         System.out.println();
    //     }
    // }

    // void SCC() {
    //     int disc[] = new int[V];
    //     int low[] = new int[V];
    //     for (int i = 0; i < V; i++) {
    //         disc[i] = -1;
    //         low[i] = -1;
    //     }

    //     boolean stackMember[] = new boolean[V];
    //     Stack<Integer> st = new Stack<Integer>();

    //     for (int i = 0; i < V; i++) {
    //         if (disc[i] == -1)
    //             SCCUtil(i, low, disc,
    //                     stackMember, st);
    //     }
    // }

    private static boolean isBridge(Grafo Grafo, Vertice from, Vertice to) {
        if (from.getListaAdjacencia().size() == 1) {
            return false;
        }

        int bridgeCount = Grafo.buscaEmProfundidade(to.getId()).size();
        Grafo.removeAresta(from.getId(), to.getId());

        int nonBridgeCount = Grafo.buscaEmProfundidade(to.getId()).size();
        Grafo.addAresta(from.getId(), to.getId(), 0);
        
        return nonBridgeCount < bridgeCount;
    }

    private static void getEulerPath(Grafo Grafo, Lista<Vertice> path, Vertice from) {
        Vertice[] listaAdjacencia = Grafo.listaAdjacencia(from.getId());
        for (Vertice to : listaAdjacencia) {
            if (!isBridge(Grafo, from, to)) {
                path.add(to);
                Grafo.removeAresta(from.getId(), to.getId());
                getEulerPath(Grafo, path, to);
                break;
            }
        }
    }

    public static Lista<Vertice> getEulerPath(Grafo Grafo) throws IllegalStateException {
        Lista<Vertice> path = new Lista<Vertice>();
        List<Vertice> oddVertices = Grafo.getOddVertices();
        int oddCount = oddVertices.size();

        if (oddCount == 0) {
             path.add(Grafo.getVertice(0));
             getEulerPath(Grafo, path, path.find(0));
        } else if (oddCount == 2) {
             path.add(oddVertices.get(0));
             getEulerPath(Grafo, path, path.find(0));
        } else {
             throw new IllegalStateException("Euler properties infringed.");
        }
        
        return path;
    }
}
