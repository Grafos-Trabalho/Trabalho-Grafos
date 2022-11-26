package source.grafo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import source.Algorithms;
import source.ABB;
import source.Lista;

/**
 * Classe básica para um Grafo simples
 */
public abstract class Grafo implements Cloneable {
    // #region Atributos

    public final String nome;
    protected ABB<Vertice> vertices;
    Lista<Aresta> bridges;
    // #endregion

    // #region Construtor

    /**
     * Construtor. Cria um grafo vazio com capacidade para MAX_VERTICES
     */
    private void init() {
        this.vertices = new ABB<Vertice>();
        this.bridges = new Lista<Aresta>();
    }

    public Grafo(String nome, int tam) {
        this.nome = nome;
        init();

        for(int i = 0; i < tam; i++) {
            this.addVertice(i);
        }
    }

    public Grafo(String nome) {
        this.nome = nome;
        init();
    }
    // #endregion

    // #region Getters

    public int tamanho() {
        return this.ordem() + (this.getAllArestas().size() / 2);
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

    public Vertice addVertice(int id, String rotulo) {
        Vertice novo = new Vertice(id, rotulo);
        this.vertices.add(id, novo);

        return novo;
    }

    /**
     * Adiciona, se possível, um vértice ao grafo. O vértice é auto-nomeado com o
     * próximo id disponível.
     */
    public boolean removeVertice(int vertice) {
        return this.vertices.remove(vertice);
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

    public boolean removeAresta(int origem, int destino) {
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);

        if (saida != null && chegada != null) {
            saida.removeAresta(destino);
            chegada.removeAresta(origem);
            return true;
        }

        return false;
    }

    /**
     * Método que adiciona uma aresta rotulada
     * 
     * @param origem  - Id do vértice de origem
     * @param destino - Id do vértice de destino
     * @param peso    - Peso da aresta
     * @param rotulo  - Rotulo da aresta
     * @return true Se a aresta foi adiciona
     * @return false Se a aresta não foi adicionada
     */
    public boolean addAresta(int origem, int destino, int peso, String rotulo) {
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);

        if (saida != null && chegada != null) {
            saida.addAresta(destino, peso, rotulo);
            chegada.addAresta(origem, peso, rotulo);
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

        for (Vertice vertice : visitados) {
            vertice = lista.pop();
            if (vertice.getId() == verticeDestino) {
                break;
            }
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
        Lista<Vertice> visitados = new Lista<Vertice>();
        visitados = buscaEmProfundidadeUtil(id, visitados);

        return visitados;
    }

    public Lista<Vertice> caminhoEuleriano() {
        return Algorithms.getEulerPath(this);
    }
    // #endregion

    public List<Vertice> getOddVertices() {
        return vertices.stream().filter((vertice) -> vertice.getId() % 2 == 1).collect(Collectors.toList());
    }

    // public void printBridges() {

    // }

    public int[][] listaAdjacencia() {
        Vertice[] array = new Vertice[vertices.size()];
        vertices.allElements(array);
        int[][] matriz = new int[vertices.size() + 1][vertices.size() + 1];
        matriz[0][0] = 2;

        // Padrão da primeira linha
        for (int i = 1; i < array.length + 1; i++) {
            matriz[0][i] = array[i - 1].getId();
        }

        // Padrão da primeira coluna
        for (int i = 1; i < array.length + 1; i++) {
            matriz[i][0] = array[i - 1].getId();
        }

        // Preenchendo a matriz
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i].existeAresta(array[j].getId()) != null) {
                    matriz[i + 1][j + 1] = 1;
                } else {
                    matriz[i + 1][j + 1] = 0;
                }
            }
        }

        return matriz;
    }

    public boolean ePonte(int origem, int destino) {
        Vertice[] listaVertices = this.getAllVertices();
        int[][] matriz = this.listaAdjacencia();
        for (int i = 0; i < listaVertices.length; i++) {
            for (int j = 0; j < listaVertices.length; j++) {
                if (i + 1 == origem + 1 && j + 1 == destino + 1) {
                    if (matriz[origem + 1][destino + 1] == 1) {
                        matriz[origem + 1][destino + 1] = 0;
                        matriz[destino + 1][origem + 1] = 0;
                        int comp = getComponent(matriz);
                        if (comp > 1)
                            return true;
                        matriz[origem + 1][destino + 1] = 1;
                        matriz[destino + 1][origem + 1] = 1;
                    }
                }
            }
        }
        return false;
    }

    public static int getComponent(int[][] grafo) {
        int n = grafo.length;
        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i] == false) {
                count++;
                dfs(grafo, i, visited);
            }
        }
        return count;
    }

    public static void dfs(int[][] grafo, int node, boolean[] visited) {
        visited[node] = true;
        int n = grafo.length;
        for (int i = 0; i < n; i++) {
            if (grafo[node][i] == 1 && visited[i] == false) {
                dfs(grafo, i, visited);
            }
        }
    }

    Vertice select;
    Vertice ultimo;

    public List<Vertice> fleury() {
        Boolean euleriano = true;
        ABB<Vertice> grafoaux = vertices;
        List<Vertice> caminho = new ArrayList<Vertice>();
        int contador = 0;
        for (Vertice selecionado : grafoaux.allElements(getAllVertices())) {
            if ((selecionado.getGrau() % 2) != 0) {
                contador++;
            }
        }
        if (contador >= 3) {
            euleriano = false;
        }

        Vertice[] array = grafoaux.allElements(getAllVertices());

        for (Vertice selecionado1 : array) {
            {
                if (selecionado1.getGrau() % 2 != 0) {
                    select = selecionado1;
                }
            }
        }
        if (select == null) {
            select = this.getVertice(1);
        }
        int controlador = 0;
        if (euleriano) {
            Vertice selecionado = select;
            caminho.add(selecionado);
            while (this.tamanho() - this.vertices.size() >= controlador) {
                for (Aresta arestaSelecionada : selecionado.getAllArestas()) {
                    if (ePonte(selecionado.getId(), arestaSelecionada.getDestino())) {
                        selecionado = grafoaux.find(arestaSelecionada.getDestino());
                        caminho.add(selecionado);
                        arestaSelecionada.foiVisitada();
                        euleriano = false;
                        // lançar excessão
                    } else {
                        selecionado = grafoaux.find(arestaSelecionada.getDestino());
                        selecionado.getAllArestas()[0].foiVisitada();
                        caminho.add(selecionado);
                    }
                    controlador++;
                }

                ultimo = selecionado;
            }
        } else {

            // lançar excessão
        }

        if (ultimo.getId() == select.getId()) {
            euleriano = true;
            // lançar excessão
        }

        if (euleriano) {
            return caminho;
        }

        return caminho;
    }

    public Lista<Aresta> tarjan() {
        this.bridges.removeAll();
        return Algorithms.BR(this, this.getAllVertices(), this.bridges);
    }
}