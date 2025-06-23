import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TVertice vA=new TVertice("A");
        TVertice vB=new TVertice("B");
        TVertice vC=new TVertice("C");
        TVertice vD=new TVertice("D");
        TVertice vE=new TVertice("E");
        Collection<TVertice> vertices= Arrays.asList(vA, vB, vC, vD, vE);
        Collection<TArista> aristas=new ArrayList<>();
        aristas.add(new TArista("A", "B", 2));
        aristas.add(new TArista("A", "C", 3));
        aristas.add(new TArista("B", "C", 1));
        aristas.add(new TArista("B", "D", 4));
        aristas.add(new TArista("C", "D", 5));
        aristas.add(new TArista("C", "E", 6));
        aristas.add(new TArista("D", "E", 7));
        TGrafoNoDirigido grafo=new TGrafoNoDirigido(vertices, aristas);
        Double[][] matrizCostos=UtilGrafos.obtenerMatrizCostos(grafo.getVertices());
        System.out.println("Matriz de Costos (AAM):");
        UtilGrafos.imprimirMatriz(matrizCostos, grafo.getVertices());
        TGrafoNoDirigido arbolExpMin2 = grafo.Kruskal();
        System.out.println("Arbol de Expansión Mínima (Algoritmo KRUSKAL):");
        Collection<TVertice> verticesArbol2=arbolExpMin2.getVertices().values();
        for (TVertice v:verticesArbol2) {
            for (Object o:v.getAdyacentes()) {
                TAdyacencia ady=(TAdyacencia) o;
                System.out.println(v.getEtiqueta() + " - " + ady.getDestino().getEtiqueta() + " : " + ady.getCosto());
            }
        }

        TGrafoNoDirigido arbolExpMin = grafo.Prim();
        System.out.println("Arbol de Expansión Mínima (Algoritmo PRIM):");
        Collection<TVertice> verticesArbol=arbolExpMin.getVertices().values();
        for (TVertice v:verticesArbol) {
            for (Object o:v.getAdyacentes()) {
                TAdyacencia ady=(TAdyacencia) o;
                System.out.println(v.getEtiqueta() + " - " + ady.getDestino().getEtiqueta() + " : " + ady.getCosto());
            }
        }
    }
}