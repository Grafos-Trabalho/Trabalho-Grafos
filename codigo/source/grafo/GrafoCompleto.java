package source.grafo;

import source.ABB;
import source.Lista;

public class GrafoCompleto extends Grafo {
    // #region Atributo

    private int ordem;
    // #endregion

    // #region Construtor

    /**
     * Construtor do grafo completo
     * 
     * @param nome  Nome do grafo
     * @param ordem Ordem do grafo a ser gerado
     * @return Grafo completo gerado
     */
    public GrafoCompleto(String nome, int ordem) {
        super(nome);
        this.setOrdem(ordem);

        this.vertices = new ABB<>();
        Vertice[] arrayVertices = new Vertice[ordem];

        int i = 0;
        for (Vertice vertice : arrayVertices) {
            vertice = this.addVertice(i++);

            for (int j = 0; j < (i - 1); j++) {
                this.addAresta(j, vertice.getId(), 0);
            }
        }
    }
    // #endregion

    // #region Setters

    public void setOrdem(int ordem) {
        if (ordem > 0)
            this.ordem = ordem;
    }
    // #endregion

    // #region Booleanos

    /**
     * Verifica se este é um grafo completo.
     * 
     * @return TRUE para grafo completo, FALSE caso contrário
     */
    @Override
    public boolean completo() {
        return true;
    }

    /**
     * Verifica se o grafo completo é euleriano
     * 
     * @return true Caso seja euleriano
     * @return false Caso não seja euleriano
     */
    @Override
    public boolean euleriano() {
        Vertice vertice = getVertice(this.ordem());

        if (vertice.getGrau() % 2 != 0) {
            return false;
        } else {
            return true;
        }
    }
    // #endregion

    // #region Métodos Principais

    /**
     * Método que cria um subgrafo completo a partir de uma lista de vertices
     * parametrizada
     * 
     * @param listaVertice Lista de vétices do subgrafo
     * @throws Exception
     */
    @Override
    public GrafoCompleto subGrafo(Lista<Vertice> listaVertice) throws Exception {
        Vertice[] listaVertices = new Vertice[this.ordem()];
        listaVertices = listaVertice.allElements(listaVertices);
        GrafoCompleto novoSubGrafo = new GrafoCompleto("subGrafo", 0);

        for (int i = 0; (i < listaVertices.length) && (listaVertices[i] != null); i++) {
            novoSubGrafo.addVertice(listaVertices[i].getId());

            for (int j = 0; j < (novoSubGrafo.ordem() - 1); j++) {
                if (this.existeAresta(listaVertices[i].getId(), listaVertices[j].getId()) != null) {
                    novoSubGrafo.addAresta(listaVertices[i].getId(), listaVertices[j].getId(), 0);
                }
            }
        }

        if (novoSubGrafo.completo()) {
            return novoSubGrafo;
        } else {
            throw new Exception("Esses vertices não formam um grafo completo");
        }
    }
    // #endregion
}
