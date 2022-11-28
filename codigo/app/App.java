package app;

import java.util.List;

import source.Lista;
import source.grafo.*;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("\n====================================================================\n");
        System.out.println("Grafo completo:");

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

        System.out.println("\n");

        System.out.println("\n====================================================================\n");
        System.out.println("Testando achar todas as pontes do grafo com o algoritmo de matriz:\n");

        GrafoNaoPonderado grafoTarjan = new GrafoNaoPonderado("ponteTarjan", 5);
        grafoTarjan.addAresta(1, 0);
        grafoTarjan.addAresta(0, 2);
        grafoTarjan.addAresta(2, 1);
        grafoTarjan.addAresta(0, 3);
        grafoTarjan.addAresta(3, 4);
        grafoTarjan.addAresta(0, 5);

        grafoTarjan.salvar();

        System.out.println("\n");

        boolean ponte = grafoTarjan.ePonte(3, 4);
        System.out.println("O vértice 3-4 é ponte? - " + ponte);

        System.out.println("\n====================================================================\n");
        System.out.println("Testando achar todas as pontes do grafo com o algoritmo de Tarjan:\n");

        GrafoNaoPonderado grafoNaoPonderadoPonte = new GrafoNaoPonderado("GrafoPonte");
        
        grafoNaoPonderadoPonte.addVertice(0);
        grafoNaoPonderadoPonte.addVertice(1);
        grafoNaoPonderadoPonte.addVertice(2);
        grafoNaoPonderadoPonte.addVertice(3);
        grafoNaoPonderadoPonte.addVertice(4);
        grafoNaoPonderadoPonte.addVertice(5);
        grafoNaoPonderadoPonte.addVertice(6);
        grafoNaoPonderadoPonte.addVertice(7);
        grafoNaoPonderadoPonte.addVertice(8);
        grafoNaoPonderadoPonte.addVertice(9);

        grafoNaoPonderadoPonte.addAresta(0, 1);
        grafoNaoPonderadoPonte.addAresta(0, 2);
        grafoNaoPonderadoPonte.addAresta(1, 2);
        grafoNaoPonderadoPonte.addAresta(1, 9);
        grafoNaoPonderadoPonte.addAresta(1, 3);
        grafoNaoPonderadoPonte.addAresta(2, 8);
        grafoNaoPonderadoPonte.addAresta(3, 6);
        grafoNaoPonderadoPonte.addAresta(3, 5);
        grafoNaoPonderadoPonte.addAresta(3, 4);
        grafoNaoPonderadoPonte.addAresta(4, 5);
        grafoNaoPonderadoPonte.addAresta(5, 6);
        grafoNaoPonderadoPonte.addAresta(7, 8);

        Lista<Aresta> arestasTarjan2 = grafoNaoPonderadoPonte.tarjan();

        System.out.println("Pontes identificadas:\n");

        arestasTarjan2.stream().forEach((aresta) -> System.out.println(aresta.getOrigem() + " - " + aresta.getDestino()));

    }

    
}