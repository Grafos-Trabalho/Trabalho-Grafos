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
        Vertice verticeOrigem = this.existeVertice(origem);
        Vertice verticeDestino = this.existeVertice(destino);

        if (verticeOrigem != null && verticeDestino != null) {
            verticeOrigem.removeAresta(destino);
            verticeDestino.removeAresta(origem);

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

    
    public List<Vertice> fleury(){
        int controle = 0;
     List<Vertice> caminho = new ArrayList<Vertice>();
     int[][] matriz = new int[vertices.size() + 1][vertices.size() + 1];
     Vertice selecionado = this.existeVertice(0);
     Vertice primeiro = this.existeVertice(0);
     matriz = matrizFormatada(this.listaAdjacencia());
     Grafo teste = new Grafo("nada") {

        @Override
        public Grafo subGrafo(Lista<Vertice> vertices) throws Exception {
            // TODO Auto-generated method stub
            return null;
        }
        
    };
    
        try {
            teste = (Grafo) this.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        while(!this.matrizVazia(matriz)){
            if(existeAresta(matriz, selecionado.getId(),controle) != null){
                Aresta atual = existeAresta(matriz, selecionado.getId(), controle);
                if(teste.ePonte(atual.getOrigem(), atual.getDestino()) && quantidadeAresta(matriz, atual.getOrigem() ) ==controle+1){
                    caminho.add(selecionado);
                    Vertice aux = this.existeVertice(atual.getDestino());
                    matriz[atual.getOrigem()][atual.getDestino()]=0;
                    matriz[atual.getDestino()][atual.getOrigem()]=0;
                    teste.removeAresta(atual.getOrigem(), atual.getOrigem());
                    teste.removeAresta(atual.getDestino(), atual.getOrigem());
                    selecionado=aux;
                    controle=0;
                    if(this.matrizVazia(matriz) && selecionado==primeiro){
                        caminho.add(primeiro);
                    }
                }else{
                    if(!teste.ePonte(atual.getOrigem(), atual.getDestino()) && teste.existeAresta(atual.getOrigem(),atual.getDestino()) != null){
                        caminho.add(selecionado);
                        Vertice aux = this.existeVertice(atual.getDestino());
                        matriz[atual.getOrigem()][atual.getDestino()]=0;
                        matriz[atual.getDestino()][atual.getOrigem()]=0;
                        teste.removeAresta(atual.getOrigem(), atual.getOrigem());
                        teste.removeAresta(atual.getDestino(), atual.getOrigem());
                        selecionado=aux;
                        controle=0;
                    }else{
                        controle++;
                    }
                }
            }
        }

     return caminho;
    }
    
    
    private int[][] matrizFormatada(int matriz [][]){
        int formatada [][] = new int[matriz.length-1][matriz.length-1];

        for(int i=1; i<matriz.length;i++){
            for(int j=1; j<matriz.length; j++){
                formatada[i-1][j-1] = matriz[i][j];
            }
        }

        return formatada;
    }

    public boolean matrizVazia(int [][] matriz){
        int qtd=0;
        for(int i=0; i<matriz.length;i++){
            for(int j=0; j<matriz.length;j++){
               if(matriz[i][j] !=0){
                qtd++;
               }
            }
        }
        return qtd==0;
    }

    public Aresta existeAresta(int matriz[][],int id, int controle){
        int teste=0;
        for(int i=0; i<matriz[id].length;i++){
               if(matriz[id][i] !=0){
                if(teste==controle){
                    return existeAresta(id, i);
                }
                teste++;
               }
        }
        return null;
    }
    public int quantidadeAresta(int matriz[][],int id){
        int qtd = 0;
        for(int i=0; i<matriz[id].length;i++){
               if(matriz[id][i] !=0){
                qtd++;
               }
        }
        return qtd;
    }
    public Lista<Aresta> tarjan() {
        this.bridges.removeAll();
        return Algorithms.BR(this, this.getAllVertices(), this.bridges);
    }




    
}