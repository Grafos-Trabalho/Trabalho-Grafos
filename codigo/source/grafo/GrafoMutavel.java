package source.grafo;

import source.Arquivo;

import java.io.IOException;

public abstract class GrafoMutavel extends Grafo {
    // #region Contrutor

    public GrafoMutavel(String nome) {
        super(nome);
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
     * Método que carrega um grafo mutável de um arquivo txt
     * 
     * @param nomeArquivo Nome do arquivo a ser lido
     * @throws IOException
     */
    public void carregar(String nomeArquivo) throws IOException {
        Arquivo arq = new Arquivo("codigo/app/files/", nomeArquivo, "read");

        while (arq.ready()) {
            String line = arq.readLine();
            line = line.replaceAll("\\n", "");
            String verticesPesos[] = line.split(";");
            int verticeOrigem = Integer.parseInt(verticesPesos[0]);

            for (String verticePeso : verticesPesos) {
                int vertice = Integer.parseInt(verticePeso.split("-")[0]);
                this.addVertice(vertice);

                if (vertice != verticeOrigem) {
                    int peso = Integer.parseInt(verticePeso.split("-")[1]);
                    this.addAresta(verticeOrigem, vertice, peso);
                }
            }
        }

        arq.close();
    }

    /**
     * Método que salva um grafo mutável em um arquivo
     * 
     * @param nomeArquivo Nome do arquivo destino
     * @throws Exception
     */
    public void salvar(String nomeArquivo) throws Exception {
        Arquivo arq = new Arquivo("codigo/app/files/", nomeArquivo, "save");

        for (Vertice vertice : this.getAllVertices()) {
            arq.write(vertice.getId());

            for (Aresta aresta : vertice.getAllArestas()) {
                arq.write(";" + aresta.getDestino() + "-" + aresta.getPeso());
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
