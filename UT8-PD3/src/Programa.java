import java.util.*;
//NOSE PORQUE NO ANDA EL PROGRAMA
/*public class Programa {

    public static void main(String[] args) {

        TGrafoRedElectrica laRed = (TGrafoRedElectrica) UtilGrafos.cargarGrafo("UT8-PD3/barrio.txt", "UT8-PD3/distancias.txt", false, TGrafoRedElectrica.class);
        System.out.println("Vértices cargados:");
        for (TVertice v:laRed.getVertices().values()) {
            System.out.println("[" + v.getEtiqueta() + "] (long: " + v.getEtiqueta().toString().length() + ")");
        }
        // Limpiar etiquetas con BOM (\uFEFF)
        Map<Comparable, TVertice> nuevosVertices=new HashMap<>();
        for (TVertice vertice : laRed.getVertices().values()) {
            String etiquetaLimpia=vertice.getEtiqueta().toString().replace("\uFEFF", "").trim();
            vertice.setEtiqueta(etiquetaLimpia);
            nuevosVertices.put(etiquetaLimpia, vertice);
        }
        laRed.reemplazarMapaVertices(nuevosVertices);
        TAristas conexiones=laRed.mejorRedElectrica();
        System.out.println("Cantidad de aristas en mejorRedElectrica: " + conexiones.size());
        double costoTotal=0.0;
        String[] lineas=new String[conexiones.size() + 1];
        int i=0;
        for (TArista arista : conexiones) {
            costoTotal+=arista.getCosto();
            lineas[i++]=arista.getEtiquetaOrigen() + "," + arista.getEtiquetaDestino() + "," + arista.getCosto();
        }
        lineas[i]="Costo Total: " + costoTotal;
        ManejadorArchivosGenerico.escribirArchivo("redelectrica.txt", lineas);
        System.out.println("Red eléctrica generada correctamente.");
    }
}*/
