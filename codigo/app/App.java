package app;

import source.Algorithms;
import source.Lista;
import source.grafo.*;

import java.util.*;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Grafo não ponderado:");

        GrafoNaoPonderado grafoNaoPonderado = new GrafoNaoPonderado("grafoNaoPonderado");
        for (int i = 0; i < 10; i++) {
            grafoNaoPonderado.addVertice(i);
        }

        int j = 2;
        for (int i = 0; i < 10; i++) {
            grafoNaoPonderado.addAresta(i, j);
            grafoNaoPonderado.addAresta(j + 2, j);
        }
        grafoNaoPonderado.addAresta(1, 5);
        grafoNaoPonderado.addAresta(4, 7);

        System.out.println("Ordem do grafo não ponderado: " + grafoNaoPonderado.ordem());
        System.out.println("Tamanho do grafo não ponderado: " + grafoNaoPonderado.tamanho());

        grafoNaoPonderado.salvar();

        GrafoNaoPonderado grafoNaoPonderado2 = new GrafoNaoPonderado("grafoNaoPonderado2");
        // grafoNaoPonderado2.carregar("grafoNaoPonderado");

        System.out.println("Ordem do grafo não ponderado 2: " + grafoNaoPonderado2.ordem());
        System.out.println("Tamanho do grafo não ponderado 2: " + grafoNaoPonderado2.tamanho());

        System.out.println("\n====================================================================\n");
        System.out.println("Grafo ponderado:");

        GrafoPonderadoRotulado GrafoPonderadoRotulado = new GrafoPonderadoRotulado("GrafoPonderadoRotulado");
        for (int i = 0; i < 40; i++) {
            GrafoPonderadoRotulado.addVertice(i);
        }

        j = 2;
        for (int i = 0; i < 10; i++) {
            grafoNaoPonderado.addAresta(i, j, i + j);
            grafoNaoPonderado.addAresta(j + 2, j, j - i);
        }
        grafoNaoPonderado.addAresta(1, 5, 3);
        grafoNaoPonderado.addAresta(4, 7, 1);

        System.out.println("Ordem do grafo ponderado: " + GrafoPonderadoRotulado.ordem());
        System.out.println("Tamanho do grafo ponderado: " + GrafoPonderadoRotulado.tamanho());
        GrafoPonderadoRotulado.salvar();

        GrafoPonderadoRotulado grafoPonderado2 = new GrafoPonderadoRotulado("grafoPonderado2");
        // grafoPonderado2.carregar("grafoPonderado");

        System.out.println();
        System.out.println("Ordem do grafo ponderado 2: " + grafoPonderado2.ordem());
        System.out.println("Tamanho do grafo ponderado 2: " + grafoPonderado2.tamanho());

        System.out.println("\n====================================================================\n");
        System.out.println("Teste do gráfico completo:");

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
        GrafoNaoPonderado grafoCaminho = new GrafoNaoPonderado("Grafo para caminho");
        grafoCaminho.addVertice(1);
        grafoCaminho.addVertice(2);
        grafoCaminho.addVertice(3);
        grafoCaminho.addVertice(4);
        grafoCaminho.addVertice(5);
        grafoCaminho.addAresta(1, 3);
        grafoCaminho.addAresta(1, 2);
        grafoCaminho.addAresta(3, 5);
        System.out.println("Encontrar Caminho:");
        Vertice[] listaCaminho = new Vertice[100];
        listaCaminho = grafoCaminho.encontrarCaminho(1, 3);
        for (Vertice vertice : listaCaminho) {
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

        System.out.println("\n");

        System.out.println("\n====================================================================\n");
        System.out.println("Testando achar todas as pontes do grafo com o algoritmo de Tarjan:\n");

        GrafoNaoPonderado grafoTarjan = new GrafoNaoPonderado("ponteTarjan");
        grafoTarjan.addVertice(0);
        grafoTarjan.addVertice(1);
        grafoTarjan.addVertice(2);
        grafoTarjan.addVertice(3);
        grafoTarjan.addVertice(4);
        grafoTarjan.addAresta(1, 0);
        grafoTarjan.addAresta(0, 2);
        grafoTarjan.addAresta(2, 1);
        grafoTarjan.addAresta(0, 3);
        grafoTarjan.addAresta(3, 4);
        // grafoTarjan.printBridges();

        grafoTarjan.salvar();

        System.out.println("\n");

        grafoTarjan.ePonte();

    }
}