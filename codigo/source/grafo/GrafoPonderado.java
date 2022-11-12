package source.grafo;

import source.Lista;
import source.Arquivo;

public class GrafoPonderado extends GrafoMutavel {
    // #region Contrutor

    public GrafoPonderado(String nome) {
        super(nome);
    }
    // #endregion

    // #region boolean Aresta

    /**
     * Método que adiciona uma aresta em um grafo ponderado
     * 
     * @param origem  Id do vértice de origem
     * @param destino Id do vértice de destino
     * @return true Caso a aresta foi adicionada
     * @return false Caso a aresta não foi adicionada
     */
    @Override
    public boolean addAresta(int origem, int destino) {
        return addAresta(origem, destino, 0);
    }
    // #endregion

    // #region Subgrafo

    /**
     * Método que gera um subgrafo de um grafo ponderado
     * 
     * @param lista Lista de vértices para gerar o subgrafo
     * @return Grafo ponderado gerado
     */
    @Override
    public GrafoPonderado subGrafo(Lista<Vertice> listaVertice) {
        Vertice[] listaVertices = new Vertice[this.ordem()];
        listaVertices = listaVertice.allElements(listaVertices);
        GrafoPonderado novoSubGrafo = new GrafoPonderado("subGrafo");

        for (int i = 0; i < listaVertices.length && listaVertices[i] != null; i++) {
            novoSubGrafo.addVertice(listaVertices[i].getId());

            for (int j = 0; j < novoSubGrafo.ordem() - 1; j++) {
                if (this.existeAresta(listaVertices[i].getId(), listaVertices[j].getId()) != null) {
                    novoSubGrafo.addAresta(listaVertices[i].getId(), listaVertices[j].getId(),
                            this.existeAresta(listaVertices[i].getId(), listaVertices[j].getId()).getPeso());
                }
            }
        }

        return novoSubGrafo;
    }
    // #endregion
}
