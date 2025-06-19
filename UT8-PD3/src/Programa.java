
import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      // cargar grafo con casas y distancias
        TGrafoRedElectrica laRed =  (TGrafoRedElectrica) UtilGrafos.cargarGrafo(
                "UT8-PD3/barrio.txt",
                "UT8-PD3/distancias.txt",
                false, TGrafoRedElectrica.class);



        System.out.println(new StringBuilder().append("La mejor red electrica es: ").append(laRed.mejorRedElectrica()).toString());
        TAristas conexiones=laRed.mejorRedElectrica();
        double costoTotal=0.0;
        String[] lineas=new String[conexiones.size()+1];
        int i=0;
        for (TArista arista:conexiones) {
            costoTotal+=arista.getCosto();
            lineas[i]=arista.getEtiquetaOrigen().toString()+","+arista.getEtiquetaDestino().toString()+","+arista.getCosto();
            i++;
        }
        ManejadorArchivosGenerico.escribirArchivo("redelectrica.txt", lineas);

           
        /*calcular la mejor red electrica\
        listar en el archivo "redelectrica.txt" el costo total del cableado
        y las conexiones establecidas, una por linea (origen, destino, costo)
        
        */
    }
}
