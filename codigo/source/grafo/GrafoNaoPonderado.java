package source.grafo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import source.Arquivo;
import source.Lista;

public class GrafoNaoPonderado extends GrafoMutavel {
    // #region Contrutor

    public GrafoNaoPonderado(String nome, int tam) {
        super(nome, tam);
    }

    public GrafoNaoPonderado(String nome) {
        super(nome);
    }
    // #endregion

    // #region boolean addAresta

    /**
     * Adiciona uma aresta entre dois vértices do grafo.
     * Não verifica se os vértices pertencem ao grafo.
     * 
     * @param origem  Vértice de origem
     * @param destino Vértice de destino
     */
    @Override
    public boolean addAresta(int origem, int destino) {
        return addAresta(origem, destino, 0);
    }
    // #endregion

    // #region Subgrafo

    /**
     * Método que gera um subgrafo de um grafo não ponderado
     * 
     * @param Lista de vértices que vão gerar o subgrafo
     * @return Subgrafo não ponderado gerado
     */
    public GrafoNaoPonderado subGrafo(Lista<Vertice> listaVertice) {
        GrafoNaoPonderado novoSubGrafo = new GrafoNaoPonderado("subGrafo");
        Vertice[] listaVertices = new Vertice[this.ordem()];
        listaVertices = listaVertice.allElements(listaVertices);

        for (int i = 0; i < listaVertices.length && listaVertices[i] != null; i++) {
            novoSubGrafo.addVertice(listaVertices[i].getId());

            for (int j = 0; j < novoSubGrafo.ordem() - 1; j++) {
                if (this.existeAresta(listaVertices[i].getId(), listaVertices[j].getId()) != null) {
                    novoSubGrafo.addAresta(listaVertices[i].getId(), listaVertices[j].getId());
                }
            }
        }
        return novoSubGrafo;
    }

    @Override
    public void carregar(String arquivo) throws IOException {
        Arquivo arq = new Arquivo("codigo/app/files/", arquivo, "read");
        String linha = arq.readLine();
        String[] split = linha.split(" ");
        Integer conteudo;
        conteudo = Integer.parseUnsignedInt(split[1].trim());
        arq.readLine();
        List<Integer> verticeControle = new ArrayList<Integer>();
        for (int i = 0; i < conteudo - 1; i++) {
            linha = arq.readLine();
            if (!verticeControle.contains(Integer.parseInt(linha.split(" ")[0]))) {
                this.addVertice(Integer.parseInt(linha.split(" ")[0]));
                verticeControle.add(Integer.parseInt(linha.split(" ")[0]));
            }
        }

        Arquivo arq1 = new Arquivo("codigo/app/files/", arquivo, "read");
        linha = arq1.readLine();
        linha = arq1.readLine();
        linha = arq1.readLine();

        while(linha.split(" ")[0].trim() != "" ) {
            split = linha.split(" ");
            this.addAresta(Integer.parseUnsignedInt(split[0].trim()), Integer.parseUnsignedInt(split[1].trim()));
            linha = arq1.readLine();
        }
        arq.close();

    }
    // #endregion

}
