package app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import source.Lista;
import source.grafo.*;

public class App {
    public static void main(String[] args) throws Exception {

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
        System.out.println("A matriz está vazia: " + grafoTesteMatriz.matrizVazia(matrizFormatada));
    
        System.out.println("\n====================================================================\n");
        System.out.println("Testando fleury com método de matriz\n");
        System.out.println("Exemplo 1:\n");
        GrafoNaoPonderado grafo7Vertices = new GrafoNaoPonderado("grafoNaoPonderado");
        for (int i = 0; i < 7; i++) {
            grafo7Vertices.addVertice(i);
        }
        grafo7Vertices.addAresta(0, 1);
        grafo7Vertices.addAresta(0, 2);
        
        grafo7Vertices.addAresta(1, 2);
        grafo7Vertices.addAresta(1, 5);
        grafo7Vertices.addAresta(1, 3);
        grafo7Vertices.addAresta(2, 3);
        grafo7Vertices.addAresta(2, 4);
        grafo7Vertices.addAresta(3, 4);
        grafo7Vertices.addAresta(3, 5);
        
        grafo7Vertices.addAresta(4, 5);
        grafo7Vertices.addAresta(4, 6);
        grafo7Vertices.addAresta(5, 6);
        
        long tempoInicial = System.currentTimeMillis();
        for(Vertice selecionado: grafo7Vertices.fleury()){
            System.out.println(selecionado.getId());
        }
        System.out.println("O exemplo 1 com matriz executou em " + (System.currentTimeMillis() - tempoInicial)+" milisegundos\n");

        System.out.println("Exemplo 2:\n");
        GrafoNaoPonderado grafo10Vertices = new GrafoNaoPonderado("grafoNaoPonderado");
        for (int i = 0; i < 10; i++) {
            grafo10Vertices.addVertice(i);
        }
        grafo10Vertices.addAresta(0, 1);
        grafo10Vertices.addAresta(1, 9);
        grafo10Vertices.addAresta(0, 9);
        grafo10Vertices.addAresta(9, 6);
        grafo10Vertices.addAresta(6, 7);
        grafo10Vertices.addAresta(7, 8);
        grafo10Vertices.addAresta(8, 9);
        grafo10Vertices.addAresta(6, 5);
        grafo10Vertices.addAresta(6, 1);
        grafo10Vertices.addAresta(1, 2);
        grafo10Vertices.addAresta(2, 5);
        grafo10Vertices.addAresta(2, 4);
        grafo10Vertices.addAresta(4, 3);
        grafo10Vertices.addAresta(2, 3);

        tempoInicial = System.currentTimeMillis();
        for(Vertice selecionado: grafo10Vertices.fleury()){
            System.out.println(selecionado.getId());
        }
        System.out.println("O exemplo 2 com matriz executou em " + (System.currentTimeMillis() - tempoInicial)+" milisegundos\n");

        System.out.println("\n====================================================================\n");
        System.out.println("Testando fleury com Tarjan\n");
        System.out.println("Exemplo 1:\n");
        
        GrafoNaoPonderado grafo7VerticesTarjan = new GrafoNaoPonderado("grafoNaoPonderado");
        for (int i = 0; i < 7; i++) {
            grafo7VerticesTarjan.addVertice(i);
        }
        grafo7VerticesTarjan.addAresta(0, 1);
        grafo7VerticesTarjan.addAresta(0, 2);
        
        grafo7VerticesTarjan.addAresta(1, 2);
        grafo7VerticesTarjan.addAresta(1, 5);
        grafo7VerticesTarjan.addAresta(1, 3);
        grafo7VerticesTarjan.addAresta(2, 3);
        grafo7VerticesTarjan.addAresta(2, 4);
        grafo7VerticesTarjan.addAresta(3, 4);
        grafo7VerticesTarjan.addAresta(3, 5);
        
        grafo7VerticesTarjan.addAresta(4, 5);
        grafo7VerticesTarjan.addAresta(4, 6);
        grafo7VerticesTarjan.addAresta(5, 6);

        tempoInicial = System.currentTimeMillis();
        for(Vertice selecionado: grafo7VerticesTarjan.fleuryTarjan()){
            System.out.println(selecionado.getId());
        }
        System.out.println("O exemplo 1 com Tarjan executou em " + (System.currentTimeMillis() - tempoInicial)+" milisegundos\n");

        System.out.println("Exemplo 2:\n");

        GrafoNaoPonderado grafo10VerticesTarjan = new GrafoNaoPonderado("grafoNaoPonderado");
        for (int i = 0; i < 10; i++) {
            grafo10VerticesTarjan.addVertice(i);
        }
        grafo10VerticesTarjan.addAresta(0, 1);
        grafo10VerticesTarjan.addAresta(1, 9);
        grafo10VerticesTarjan.addAresta(0, 9);
        grafo10VerticesTarjan.addAresta(9, 6);
        grafo10VerticesTarjan.addAresta(6, 7);
        grafo10VerticesTarjan.addAresta(7, 8);
        grafo10VerticesTarjan.addAresta(8, 9);
        grafo10VerticesTarjan.addAresta(6, 5);
        grafo10VerticesTarjan.addAresta(6, 1);
        grafo10VerticesTarjan.addAresta(1, 2);
        grafo10VerticesTarjan.addAresta(2, 5);
        grafo10VerticesTarjan.addAresta(2, 4);
        grafo10VerticesTarjan.addAresta(4, 3);
        grafo10VerticesTarjan.addAresta(2, 3);

        tempoInicial = System.currentTimeMillis();
        for(Vertice selecionado: grafo10VerticesTarjan.fleuryTarjan()){
            System.out.println(selecionado.getId());
        }
        System.out.println("O exemplo 2 com Tarjan executou em " + (System.currentTimeMillis() - tempoInicial)+" milisegundos");

    }
    
}