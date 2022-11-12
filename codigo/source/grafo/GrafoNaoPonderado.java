package source.grafo;

import source.Lista;

public class GrafoNaoPonderado extends GrafoMutavel {
    // #region Contrutor

    public GrafoNaoPonderado(String nome) {
        super(nome);
    }
    // #endregion

    // #region boolean addAresta

    /**
     * Adiciona uma aresta entre dois vértices do grafo.
     * Não verifica se os vértices pertencem ao grafo.
     * 
     * @param origem  Vértice de origem
     * @param destino Vértice de destino
     */
    @Override
    public boolean addAresta(int origem, int destino) {
        return addAresta(origem, destino, 0);
    }
    // #endregion

    // #region Subgrafo

    /**
     * Método que gera um subgrafo de um grafo não ponderado
     * 
     * @param Lista de vértices que vão gerar o subgrafo
     * @return Subgrafo não ponderado gerado
     */
    public GrafoNaoPonderado subGrafo(Lista<Vertice> listaVertice) {
        GrafoNaoPonderado novoSubGrafo = new GrafoNaoPonderado("subGrafo");
        Vertice[] listaVertices = new Vertice[this.ordem()];
        listaVertices = listaVertice.allElements(listaVertices);

        for (int i = 0; i < listaVertices.length && listaVertices[i] != null; i++) {
            novoSubGrafo.addVertice(listaVertices[i].getId());

            for (int j = 0; j < novoSubGrafo.ordem() - 1; j++) {
                if (this.existeAresta(listaVertices[i].getId(), listaVertices[j].getId()) != null) {
                    novoSubGrafo.addAresta(listaVertices[i].getId(), listaVertices[j].getId());
                }
            }
        }
        return novoSubGrafo;
    }
    // #endregion
}
