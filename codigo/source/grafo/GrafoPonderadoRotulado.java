package source.grafo;

import source.Lista;
import source.Arquivo;

public class GrafoPonderadoRotulado extends GrafoMutavel {
    // #region Contrutor

    public GrafoPonderadoRotulado(String nome) {
        super(nome);
    }

    public GrafoPonderadoRotulado(String nome, int tam) {
        super(nome, tam);
    }
    // #endregion

    // #region boolean Aresta

    /**
     * Método que adiciona uma aresta com peso 0 em um grafo ponderado
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

    /**
     * Método que adiciona uma aresta com peso em um grafo ponderado
     * 
     * @param origem  Id do vértice de origem
     * @param destino Id do vértice de destino
     * @param peso    Peso da aresta
     * @return true Caso a aresta foi adicionada
     * @return false Caso a aresta não foi adicionada
     */
    @Override
    public boolean addAresta(int origem, int destino, int peso, String rotulo) {
        return super.addAresta(origem, destino, peso, rotulo);
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
    public GrafoPonderadoRotulado subGrafo(Lista<Vertice> listaVertice) {
        Vertice[] listaVertices = new Vertice[this.ordem()];
        listaVertices = listaVertice.allElements(listaVertices);
        GrafoPonderadoRotulado novoSubGrafo = new GrafoPonderadoRotulado("subGrafo");

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

    @Override
    public void salvar(String nomeArquivo) throws Exception {
        Arquivo arq = new Arquivo("codigo/app/files/", nomeArquivo, "net");
        arq.write("*Vertices " + this.getAllVertices().length + "\n");
        for (Vertice vertice : this.getAllVertices()) {
            arq.write(vertice.getId() + " \"" + vertice.getRotulo() + "\"" + "\n");
        }
        arq.write("*arcs\n");

        for (Vertice vertice : this.getAllVertices()) {

            for (Aresta aresta : vertice.getAllArestas()) {
                arq.write(vertice.getId() + " " + aresta.getDestino() + " " + aresta.getPeso() + "\n");
            }

            arq.write("\n");
        }

        arq.close();
    }

    @Override
    public void salvar() throws Exception {
        salvar(this.nome);
    }
    // #endregion
}
