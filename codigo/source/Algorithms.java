package source;

import java.util.List;
import source.grafo.*;

public final class Algorithms {
    private Algorithms() {
    }


    private static void getEulerPath(Grafo grafo, Lista<Vertice> path, Vertice from) {
        Vertice[] listaAdjacencia = grafo.listaAdjacencia(from.getId());
        for (Vertice to : listaAdjacencia) {
            if (!grafo.ePonte(from.getId(), to.getId())) {
                path.add(to);
                grafo.removeAresta(from.getId(), to.getId());
                getEulerPath(grafo, path, to);
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
            getEulerPath(Grafo, path, path.getFirt());
        } else if (oddCount == 2) {
            path.add(oddVertices.get(0));
            getEulerPath(Grafo, path, path.getFirt());
        } else {
            throw new IllegalStateException("Euler properties infringed.");
        }

        return path;
    }

    private static int Time;
    
    private static void dfsBR(Vertice vertice, Lista<Aresta> bridges, int[] disc, int[] low, int p) {
        int u = vertice.getId();
        low[u] = disc[u] = ++Time;

        Vertice[] adj = new Vertice[vertice.getListaAdjacencia().size()];
        vertice.getListaAdjacencia().allElements(adj);

        int v;

        for(Vertice verticeAdj : adj) {
            v = verticeAdj.getId();

            if(v != p) {
                if (disc[v] == 0) {
                    dfsBR(verticeAdj, bridges, disc, low, u);

                    if (disc[u] < low[v]) {
                        bridges.add(vertice.existeAresta(v));
                    }

                    low[u] = Math.min(low[u], low[v]);
                } else
                    low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public static Lista<Aresta> BR(Vertice[] allVertices) {
        int[] low, disc;
        Lista<Aresta> bridges = new Lista<Aresta>();
        low = disc = new int[allVertices.length];
        Time = 0;
        int u;

        for (Vertice verticeOrigem : allVertices) {
            u = verticeOrigem.getId();

            if (disc[u] == 0) {
                dfsBR(verticeOrigem, bridges, disc, low, u);
            }
        }

        return bridges;
    }
}
