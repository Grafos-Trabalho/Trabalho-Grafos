package source;

import java.util.Iterator;
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
