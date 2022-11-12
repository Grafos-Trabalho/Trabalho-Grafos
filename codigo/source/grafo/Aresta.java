package source.grafo;

public class Aresta {
    private int peso;
    private int destino;
    private boolean visitada;

    /**
     * Construtor para arestas com ou sem peso
     * @param peso Peso da aresta
     * @param destino Vértice de destino
     */
    public Aresta(int peso, int destino){
        this.peso = peso;
        this.destino = destino;
        this.visitada = false;
    }

    /**
     * Método de acesso para o peso da aresta
     * @return the peso
     */
    public int getPeso() {
        return this.peso;
    }
   
    /**
     * Método de acesso para o destino da aresta
     * @return the destino
     */
    public int getDestino() {
        return this.destino;
    }

    public void visitar(){
        this.visitada = true;
    }

    public void limparVisita(){
        this.visitada = false;
    }

    public boolean foiVisitada(){
        return this.visitada;
    }
}
