import java.util.Collection;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("UT8-TA6/src/aeropuertos_2.txt", "UT8-TA6/src/conexiones_3.txt", false, TGrafoDirigido.class);
        Object[] etiquetasarray = gd.getEtiquetasOrdenado();
        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Conexiones");
        for (int i = 0; i < etiquetasarray.length; i++) {
            for (int j = 0; j < etiquetasarray.length; j++) {
                if (i != j) {
                    String origen = etiquetasarray[i].toString();
                    String destino = etiquetasarray[j].toString();
                    if (gd.existeVertice(origen) && gd.existeVertice(destino)) {
                        Double costo = gd.obtenerCostoMinimo(origen, destino);
                        System.out.println("Costo mínimo de " + origen + " a " + destino + ": " + costo);
                    }
                }
            }
        }
        System.out.println("Dijkstra desde Asuncion");
        Map<Comparable, Double> dijkstra = gd.dijkstra("Asuncion");
        for (Comparable etiqueta : dijkstra.keySet()) {
            System.out.println(etiqueta + " -> " + dijkstra.get(etiqueta));
        }
        System.out.println("Busqueda en amplitud desde vertice");
        Collection<TVertice> bea = gd.bea();
        for (TVertice vertice : bea) {
            System.out.println(vertice.getEtiqueta());
        }
        System.out.println("Hay ciclos?");
        boolean ciclo = gd.tieneCiclo();
        if (ciclo) {
            System.out.println("Tiene ciclo");
        } else {
            System.out.println("No tiene ciclo");
        }
        boolean ciclo2 = gd.tieneCiclo("Asuncion");
        if (ciclo2) {
            System.out.println("Tiene ciclo desde Asuncion");
        } else {
            System.out.println("No tiene ciclo desde Asuncion");
        }
        Comparable excenticidad = gd.obtenerExcentricidad("Asuncion");
        System.out.println("Excentricidad desde Asuncion: " + excenticidad);
        Double[][] floyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(floyd, gd.getVertices(), "Floyd");
        Comparable centro = gd.centroDelGrafo();
        System.out.println("Centro del grafo: " + centro);
        System.out.println("Busqueda en profundidad");
        Collection<TVertice> bpf = gd.bpf();
        for (TVertice vertice : bpf) {
            System.out.println(vertice.getEtiqueta());
        }
        System.out.println("Busqueda en profundidad desde Asuncion");
        Collection<TVertice> bpf2 = gd.bpf("Asuncion");
        for (TVertice vertice : bpf2) {
            System.out.println(vertice.getEtiqueta());
        }
    }
    }
