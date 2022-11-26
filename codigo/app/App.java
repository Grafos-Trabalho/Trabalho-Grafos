package app;

import source.Lista;
import source.grafo.*;

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

        GrafoNaoPonderado grafoTarjan = new GrafoNaoPonderado("ponteTarjan", 5);
        grafoTarjan.addAresta(1, 0);
        grafoTarjan.addAresta(0, 2);
        grafoTarjan.addAresta(2, 1);
        grafoTarjan.addAresta(0, 3);
        grafoTarjan.addAresta(3, 4);
        grafoTarjan.addAresta(0, 5);
        Lista<Aresta> arestas = grafoTarjan.tarjan();

        arestas.stream().forEach((aresta) -> System.out.println(aresta.getOrigem() + " -> " + aresta.getDestino()));

        grafoTarjan.salvar();

        System.out.println("\n");

        boolean ponte = grafoTarjan.ePonte(3, 4);
        System.out.println("O vértice 3-4 é ponte? - " + ponte);

        System.out.println("\n====================================================================\n");
        System.out.println("Testando achar todas as pontes do grafo com o algoritmo de Tarjan 2:\n");

        GrafoNaoPonderado grafoTarjan2 = new GrafoNaoPonderado("ponteTarjan2", 15);
        
        grafoTarjan2.addAresta(0, 1);

        grafoTarjan2.addAresta(2, 3);
        grafoTarjan2.addAresta(2, 6);

        grafoTarjan2.addAresta(4, 8);
        grafoTarjan2.addAresta(4, 9);

        grafoTarjan2.addAresta(6, 7);

        grafoTarjan2.addAresta(8, 9);
        grafoTarjan2.addAresta(8, 13);
        
        grafoTarjan2.addAresta(9, 13);
        grafoTarjan2.addAresta(9, 10);

        grafoTarjan2.addAresta(10, 11);
        grafoTarjan2.addAresta(10, 14);

        grafoTarjan2.addAresta(11, 15);

        grafoTarjan2.addAresta(12, 13);

        grafoTarjan2.addAresta(14, 15);

        Lista<Aresta> arestasTarjan2 = grafoTarjan2.tarjan();

        arestasTarjan2.stream().forEach((aresta) -> System.out.println(aresta.getOrigem() + " -> " + aresta.getDestino()));

        System.out.println("\n");

        boolean ponte2 = grafoTarjan2.ePonte(0, 1);
        System.out.println("O vértice 0-1 é ponte? - " + ponte2);
        ponte2 = grafoTarjan2.ePonte(12, 13);
        System.out.println("O vértice 12-13 é ponte? - " + ponte2);
        ponte2 = grafoTarjan2.ePonte(9, 10);
        System.out.println("O vértice 9-10 é ponte? - " + ponte2);
        ponte2 = grafoTarjan2.ePonte(10, 11);
        System.out.println("O vértice 10-11 é ponte? - " + ponte2);
    }
}