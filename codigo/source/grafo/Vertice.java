package source.grafo;

import source.ABB;
import source.Lista;

public class Vertice {
    private ABB<Aresta> arestas;    
    private final int id;
    private boolean visitado;
    private int tempoDescoberta, tempoTermino;
    private Vertice pai;
    private Lista<Vertice> adj;

    public int getTempoDescoberta() {
        return this.tempoDescoberta;
    }

    public void setTempoDescoberta(int tempoDescoberta) {
        this.tempoDescoberta = tempoDescoberta;
    }

    public int getTempoTermino() {
        return this.tempoTermino;
    }

    public void setTempoTermino(int tempoTermino) {
        this.tempoTermino = tempoTermino;
    }

    public Vertice getPai() { return this.pai; }

    public void setPai(Vertice pai) {
        this.pai = pai;
    }

    public Lista<Vertice> getListaAdjacencia() {
        return this.adj;
    }


    /**
     * Construtor para criação de vértice identificado
     * @param id Número/id do vértice a ser criado (atributo final).
    */
    public Vertice(int id){
        this.id = id;
        this.arestas = new ABB<Aresta>();
        this.adj = new Lista<Vertice>();
        this.visitado = false;
    }


    /**
     * Adiciona uma aresta neste vértice para um destino
     * @param destino Vértice de destino
    */
    public boolean addAresta(int destino){
        adj.add(new Vertice(destino));
        return this.arestas.add(destino, new Aresta(0, destino));
    }
    
    /**
     * Adiciona uma aresta neste vértice para um destino
     * @param peso Peso da aresta (1 para grafos não ponderados)
     * @param destino Vértice de destino
    */
    public boolean addAresta(int destino, int peso){
        this.adj.add(new Vertice(destino));
        return this.arestas.add(destino, new Aresta(peso, destino));
    }

    /**
     * Verifica se já existe aresta entre este vértice e um destino. Método privado
     * @param destino Vértice de destino
     * @return TRUE se existe aresta, FALSE se não
    */
    public Aresta existeAresta(int destino){
        return arestas.find(destino);
    }

    public Aresta[] getAllArestas() {
        Aresta[] allArestas = new Aresta[arestas.size()];
        allArestas = arestas.allElements(allArestas);

        return allArestas;
    }

    
    /**
     * Retorna o grau do vértice
     * @return Grau (inteiro não negativo)
    */
    public int getGrau(){
        return this.arestas.size();
    }

    public void visitar(){
        this.visitado = true;
    }
    
    public void limparVisita(){
        this.visitado = false;
    }
    
    public boolean foiVisitado(){
        return this.visitado;
    }

    public int getId() {
        return this.id;
    }
}
