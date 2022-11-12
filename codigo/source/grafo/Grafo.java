package source.grafo;

import source.ABB;
import source.Lista;

/**
 * Classe básica para um Grafo simples
 */
public abstract class Grafo implements Cloneable {
    // #region Atributos

    public final String nome;
    protected ABB<Vertice> vertices;
    // #endregion

    // #region Construtor

    /**
     * Construtor. Cria um grafo vazio com capacidade para MAX_VERTICES
     */
    public Grafo(String nome) {
        this.nome = nome;
        this.vertices = new ABB<>();
    }
    // #endregion

    // #region Getters

    public int tamanho() {
        return this.ordem() + (this.getAllArestas().length() / 2);
    }

    public int ordem() {
        return this.vertices.size();
    }

    public Vertice getVertice(int id) {
        return vertices.find(id);
    }

    public Vertice[] getAllVertices() {
        Vertice[] allVertices = new Vertice[vertices.size()];
        allVertices = vertices.allElements(allVertices);
        return allVertices;
    }

    public Lista<Aresta> getAllArestas() {
        Lista<Aresta> arestas = new Lista<>();

        for (Vertice vertice : this.getAllVertices()) {
            for (Aresta aresta : vertice.getAllArestas()) {
                arestas.add(aresta);
            }
        }

        return arestas;
    }
    // #endregion

    // #region Métodos booleanos

    /**
     * Método que verifica se o grafo é completo
     * 
     * @return true Caso seja completo
     * @return false Caso seja incompleto
     */
    public boolean completo() {
        Vertice[] vertices = getAllVertices();

        for (Vertice vertice : vertices) {
            if (!vertice.foiVisitado()) {
                for (Vertice destino : vertices) {
                    if ((vertice != destino) && (vertice.existeAresta(destino.getId()) != null)) {
                        return false;
                    }
                }
                vertice.visitar();
            }
            vertice.limparVisita();
        }

        return true;
    }

    /**
     * Confere se um grafo é euleriano
     * 
     * @return true Se o grafo é euleriano
     * @return false Se o grafo não é euleriano
     */
    public boolean euleriano() {
        for (Vertice vertice : this.getAllVertices()) {
            if (vertice.getGrau() % 2 != 0) {
                return false;
            }
        }

        return true;
    }
    // #endregion

    // #region Métodos de Vértices

    /**
     * Adiciona, se possível, um vértice ao grafo. O vértice é auto-nomeado com o
     * próximo id disponível.
     */
    public Vertice addVertice(int id) {
        Vertice novo = new Vertice(id);
        this.vertices.add(id, novo);

        return novo;
    }

    /**
     * Método que retorna a lista de adjacência de um grafo
     */
    public Vertice[] listaAdjacencia(int id) {
        Vertice[] listaAdjacencia = new Vertice[this.ordem()];
        listaAdjacencia = vertices.find(id).getListaAdjacencia().allElements(listaAdjacencia);

        return listaAdjacencia;
    }

    /**
     * Método que recebe um id e verifica se existe um vértice
     * 
     * @return Vertice Se existente
     */
    public Vertice existeVertice(int idVertice) {
        return this.vertices.find(idVertice);
    }
    // #endregion

    // #region Métodos de Arestas

    /**
     * Método que adiciona uma aresta
     * 
     * @param origem  - Id do vértice de origem
     * @param destino - Id do vértice de destino
     * @param peso    - Peso da aresta
     * @return true Se a aresta foi adiciona
     * @return false Se a aresta não foi adicionada
     */
    public boolean addAresta(int origem, int destino, int peso) {
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);

        if (saida != null && chegada != null) {
            saida.addAresta(destino, peso);
            chegada.addAresta(origem, peso);
            return true;
        }

        return false;
    }

    /**
     * Método que verifica a existência de uma aresta entre dois vértices
     * 
     * @param verticeA Id do vértice de origem
     * @param verticeB Id do vértice de destino
     * @return Aresta A aresta encontrada
     */
    public Aresta existeAresta(int verticeA, int verticeB) {
        return vertices.find(verticeA).existeAresta(verticeB);
    }
    // #endregion

    // #region Métodos Principais
    /**
     * Método que encontra um caminho entre dois vértices do grafo
     * 
     * @param verticeInicial Vértice inicial
     * @param verticeDestino Vértice destino
     * @return Uma lista de vértices contendo o caminho percorrido
     */
    public Vertice[] encontrarCaminho(int verticeInicial, int verticeDestino) {
        Lista<Vertice> lista = this.buscaEmProfundidade(verticeInicial);
        Vertice[] visitados = new Vertice[vertices.size()];
        int i = 0;
        for (Vertice vertice : visitados) {
            visitados[i] = lista.pop();
            if (visitados[i].getId() == verticeDestino) {
                break;
            }
            i++;
        }

        return visitados;
    }

    public abstract Grafo subGrafo(Lista<Vertice> vertices) throws Exception;

    /**
     * Método privado utilitário que realiza a busca em profundidade em um grafo
     * 
     * @param id        Id da raiz da busca
     * @param visitados Lista contendo os vértices visitados
     * @return Lista contendo os vértices visitados
     */
    private Lista<Vertice> buscaEmProfundidadeUtil(int id, Lista<Vertice> visitados) {
        Vertice vertice = this.existeVertice(id);
        if (!vertice.foiVisitado()) {
            visitados.add(vertice);
        }
        vertice.visitar();

        Vertice[] listaAdjacencia = this.listaAdjacencia(vertice.getId());

        for (Vertice verticeAdjacente : listaAdjacencia) {
            if (verticeAdjacente != null) {
                if (!verticeAdjacente.foiVisitado()) {
                    verticeAdjacente.visitar();
                    buscaEmProfundidadeUtil(verticeAdjacente.getId(), visitados);
                }
            }
        }

        return visitados;
    }

    /**
     * Método público que chama o método utilitário privado para realizar a busca em
     * profundidade
     * 
     * @param id Id da raiz da busca
     * @return Lista de vértices visitados
     */
    public Lista<Vertice> buscaEmProfundidade(int id) {
        Lista<Vertice> visitados = new Lista<>();
        visitados = buscaEmProfundidadeUtil(id, visitados);

        return visitados;
    }
    // #endregion
}
