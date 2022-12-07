package source.grafo;

import source.Arquivo;

import java.io.IOException;

public abstract class GrafoMutavel extends Grafo {
    // #region Contrutor

    public GrafoMutavel(String nome) {
        super(nome);
    }

    public GrafoMutavel(String nome, int tam) {
        super(nome, tam);
    }
    // #endregion

    // #region boolean Vertice

    /**
     * Método que deleta um vértice do grafo mutável
     * 
     * @param idVertice Id do vértice a ser excluído
     * @return true Se o vértice foi deletado
     * @return false Se o vértice não foi deletado
     */
    public boolean delVertice(int idVertice) {
        return delVertice(idVertice);
    }
    // #endregion

    // #region boolean Aresta

    public abstract boolean addAresta(int origem, int destino);

    /**
     * Método que deleta uma aresta do grafo mutável
     * 
     * @param Id do vértice de origem da aresta
     * @param Id do vértice de destino da aresta
     * @return true Se a aresta foi deletada
     * @return false Se a aresta não foi deletada
     */
    public boolean delAresta(int origem, int destino) {
        return delAresta(origem, destino);
    }
    // #endregion

    // #region Manipular Arquivo

    /**
     * Método que salva um grafo mutável em um arquivo
     * 
     * @param nomeArquivo Nome do arquivo destino
     * @throws Exception
     */
    public void salvar(String nomeArquivo) throws Exception {
        Arquivo arq = new Arquivo("codigo/app/files/", nomeArquivo, "net");
        arq.write("*Vertices " + this.getAllVertices().length + "\n");
        arq.write("*arcs\n");

        for (Vertice vertice : this.getAllVertices()) {

            for (Aresta aresta : vertice.getAllArestas()) {
                arq.write(vertice.getId() + " " + aresta.getDestino());
            }

            arq.write("\n");
        }

        arq.close();
    }

    public void salvar() throws Exception {
        salvar(this.nome);
    }


    // #endregion
}
