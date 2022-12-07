package app;

import java.util.ArrayList;
import java.util.List;

import source.Lista;
import source.grafo.*;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("\n====================================================================\n");
        System.out.println("Teste do grafo completo:");

        GrafoCompleto completo = new GrafoCompleto("completo", 30);
        System.out.println("Tamanho do grafo: " + completo.tamanho());
        System.out.println("O Grafo é completo? " + completo.completo());

        System.out.println("\n====================================================================\n");
        System.out.println("Busca em profundidade: ");

        GrafoNaoPonderado grafo = new GrafoNaoPonderado("GrafoDaBuscaEmProfundidade");
        grafo.addVertice(1);
        grafo.addVertice(2);
        grafo.addVertice(3);
        grafo.addVertice(4);
        grafo.addVertice(5);
        grafo.addAresta(1, 3);
        grafo.addAresta(1, 2);
        grafo.addAresta(3, 5);
        Lista<Vertice> visitados = grafo.buscaEmProfundidade(1);

        Vertice[] visitadosArray = new Vertice[100];
        visitados.allElements(visitadosArray);

        for (Vertice vertice : visitadosArray) {
            if (vertice != null) {
                System.out.println(vertice.getId());
            }
        }

        System.out.println("\n====================================================================\n");
        System.out.println("Subgrafo:");

        GrafoNaoPonderado grafoNaoPonderado1 = new GrafoNaoPonderado("grafoNaoPonderado");
        for (int i = 1; i <= 999; i++) {
            grafoNaoPonderado1.addVertice(i);
        }

        for (int i = 1; i <= 999; i = i + 2) {
            grafoNaoPonderado1.addAresta(i, i + 1);
        }
        System.out.println("Ordem do grafo não ponderado: " + grafoNaoPonderado1.ordem());
        System.out.println("Tamanho do grafo não ponderado: " + grafoNaoPonderado1.tamanho());

        grafoNaoPonderado1.salvar();

        Lista<Vertice> listaDeVertices = new Lista<Vertice>();

        for (int i = 1; i < 499; i++) {
            listaDeVertices.add(new Vertice(i));
        }

        GrafoNaoPonderado subGrafo = grafoNaoPonderado1.subGrafo(listaDeVertices);
        subGrafo.salvar();

        System.out.println("\n====================================================================\n");
        System.out.println("Testando grafo ponderado e rotulado:\n");

        GrafoPonderadoRotulado grafoPR = new GrafoPonderadoRotulado("1");
        grafoPR.addVertice(1, "MG");
        grafoPR.addVertice(2, "SP");
        grafoPR.addVertice(3, "BA");
        grafoPR.addAresta(1, 2, 500, "MG-SP");
        grafoPR.addAresta(1, 3, 850, "MG-BA");
        Vertice[] vertices = grafoPR.getAllVertices();
        Lista<Aresta> listaArestasGrafoPR = grafoPR.getAllArestas();
        for (Vertice vertice : vertices) {
            System.out.println("Vertice " + vertice.getId() + " - Rótulo: " + vertice.getRotulo());
            for (Aresta aresta : vertice.getAllArestas()) {
                System.out.println("Aresta - Peso: " + aresta.getPeso() + " - Rótulo: " + aresta.getRotulo());
            }
        }

        grafoPR.salvar();

        
        /*System.out.println("\n====================================================================\n");
        System.out.println("Testando fleury\n");

        GrafoNaoPonderado grafoNaoPonderado25 = new GrafoNaoPonderado("grafoNaoPonderado");
        for (int i = 0; i < 7; i++) {
            grafoNaoPonderado25.addVertice(i);
        }

        grafoNaoPonderado25.addAresta(0, 1);
        grafoNaoPonderado25.addAresta(0, 2);
        
        grafoNaoPonderado25.addAresta(1, 2);
        grafoNaoPonderado25.addAresta(1, 5);
        grafoNaoPonderado25.addAresta(1, 3);

        grafoNaoPonderado25.addAresta(2, 3);
        grafoNaoPonderado25.addAresta(2, 4);

        grafoNaoPonderado25.addAresta(3, 4);
        grafoNaoPonderado25.addAresta(3, 5);

        
        grafoNaoPonderado25.addAresta(4, 5);
        grafoNaoPonderado25.addAresta(4, 6);

        grafoNaoPonderado25.addAresta(5, 6);

        
        
        for(Vertice selecionado: grafoNaoPonderado25.fleury()){
            System.out.println(selecionado.getId());
        }

        System.out.println("\nSegundo teste fleury: \n");

        GrafoNaoPonderado grafoNaoPonderado23 = new GrafoNaoPonderado("grafoNaoPonderado2");
        for (int i = 0; i < 6; i++) {
            grafoNaoPonderado23.addVertice(i);
        }
        grafoNaoPonderado23.addAresta(0, 1);
        grafoNaoPonderado23.addAresta(0, 3);
        grafoNaoPonderado23.addAresta(0, 5);
        grafoNaoPonderado23.addAresta(0, 4);

        grafoNaoPonderado23.addAresta(1, 2);
        grafoNaoPonderado23.addAresta(1, 5);
        grafoNaoPonderado23.addAresta(1, 3);

        grafoNaoPonderado23.addAresta(3, 2);
        grafoNaoPonderado23.addAresta(3, 4);

    
        for(Vertice selecionado: grafoNaoPonderado23.fleury()){
            System.out.println(selecionado.getId());
        }

        System.out.println("\n====================================================================\n");
        System.out.println("Teste métodos matriz:\n");

        GrafoNaoPonderado grafoTesteMatriz = new GrafoNaoPonderado("a");
        int matriz[][] = grafoTesteMatriz.matrizAdjacencia();
        int matrizFormatada[][] = grafoTesteMatriz.matrizFormatada(matriz);
        
        // Testando se a matriz está vazia
        System.out.println("A matriz está vazia: " + grafoTesteMatriz.matrizVazia(matrizFormatada));

        // Adicionando vértices e arestas a matriz
        grafoTesteMatriz.addVertice(0);
        grafoTesteMatriz.addVertice(1);
        grafoTesteMatriz.addVertice(2);
        grafoTesteMatriz.addAresta(0,1);
        grafoTesteMatriz.addAresta(0,2);
        grafoTesteMatriz.addAresta(1,2);
        matriz = grafoTesteMatriz.matrizAdjacencia();
        matrizFormatada = grafoTesteMatriz.matrizFormatada(matriz);

        // Testando se a matriz foi preenchida
        System.out.println("A matriz está vazia: " + grafoTesteMatriz.matrizVazia(matrizFormatada));

        // Removendo arestas da matriz
        grafoTesteMatriz.removerArestaMatriz(matrizFormatada, 0, 1);
        grafoTesteMatriz.removerArestaMatriz(matrizFormatada, 0, 2);
        grafoTesteMatriz.removerArestaMatriz(matrizFormatada, 1, 2);

        // Testando se a matriz ficou vazia
        System.out.println("A matriz está vazia: " + grafoTesteMatriz.matrizVazia(matrizFormatada));*/
    
        System.out.println("\n====================================================================\n");
        System.out.println("Testando fleury\n");
        GrafoNaoPonderado grafoNaoPonderado25 = new GrafoNaoPonderado("grafoNaoPonderado");
        for (int i = 0; i < 7; i++) {
            grafoNaoPonderado25.addVertice(i);
        }
        grafoNaoPonderado25.addAresta(0, 1);
        grafoNaoPonderado25.addAresta(0, 2);
        
        grafoNaoPonderado25.addAresta(1, 2);
        grafoNaoPonderado25.addAresta(1, 5);
        grafoNaoPonderado25.addAresta(1, 3);
        grafoNaoPonderado25.addAresta(2, 3);
        grafoNaoPonderado25.addAresta(2, 4);
        grafoNaoPonderado25.addAresta(3, 4);
        grafoNaoPonderado25.addAresta(3, 5);
        
        grafoNaoPonderado25.addAresta(4, 5);
        grafoNaoPonderado25.addAresta(4, 6);
        grafoNaoPonderado25.addAresta(5, 6);
        
        
        for(Vertice selecionado: grafoNaoPonderado25.fleuryTarjan()){
            System.out.println(selecionado.getId());
        }

        System.out.println("\n====================================================================\n");
        System.out.println("Testando fleury com Tarjan\n");
        GrafoNaoPonderado grafoNaoPonderado26 = new GrafoNaoPonderado("grafoNaoPonderado");
        grafoNaoPonderado26.carregar("grafoNaoPonderado");
        System.out.println(grafoNaoPonderado26.existeAresta(1, 2).getOrigem() + " - " + grafoNaoPonderado26.existeAresta(1, 2).getDestino());
    }
    
}