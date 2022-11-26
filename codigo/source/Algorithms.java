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
            getEulerPath(Grafo, path, path.find(0));
        } else if (oddCount == 2) {
            path.add(oddVertices.get(0));
            getEulerPath(Grafo, path, path.find(0));
        } else {
            throw new IllegalStateException("Euler properties infringed.");
        }

        return path;
    }

    private static void dfsBR(Grafo grafo, Vertice vertice, Lista<Aresta> bridges, Lista<Vertice> ancestrais) {
        Vertice[] adj = new Vertice[vertice.getListaAdjacencia().size()];
        vertice.getListaAdjacencia().allElements(adj);

        for (Vertice verticeAdj : adj) {
            if (!verticeAdj.foiVisitado()) {
                verticeAdj.visitar();
                
                dfsBR(grafo, verticeAdj, bridges, ancestrais);

                if (grafo.ePonte(vertice.getId(), verticeAdj.getId())) {
                    bridges.add(vertice.existeAresta(verticeAdj.getId()));
                }

                vertice.setPai(verticeAdj);
                ancestrais.add(verticeAdj);
            } else
                vertice.setPai(ancestrais.getFirt());
        }
    }

    public static Lista<Aresta> BR(Grafo grafo, Vertice[] allVertices, Lista<Aresta> bridges) {
        Lista<Vertice> ancestrais;

        for (Vertice verticeOrigem : allVertices) {
            verticeOrigem.limparVisita();
        }

        for (Vertice verticeOrigem : allVertices) {
            if (!verticeOrigem.foiVisitado()) {
                verticeOrigem.visitar();
                ancestrais = new Lista<Vertice>();
                ancestrais.add(verticeOrigem);

                dfsBR(grafo, verticeOrigem, bridges, ancestrais);
            }
        }

        return bridges;
    }
}
