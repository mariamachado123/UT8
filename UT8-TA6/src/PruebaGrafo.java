import java.util.Collection;
import java.util.LinkedList;

public class PruebaGrafo {

    public static void main(String[] args) {
        TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(
                "UT8-TA6/src/vert2.txt",
                "UT8-TA6/src/ari2.txt",
                false, TGrafoNoDirigido.class);

      
    
 
      TGrafoNoDirigido grafoPrim = gnd.Prim();
       System.out.println("cantidad de vertices del grafo por prim:"  + grafoPrim.getVertices().size());
       System.out.println("Costo total: " + grafoPrim.getCostoTotal() + "");
        /*
        mostrar las aristas del AAM por Prim y el costo total
       */
     
        
  
         TGrafoNoDirigido grafoKruskal = gnd.Kruskal();
       System.out.println("cantidad de vertices del grafo por kruskal:"  + grafoKruskal.getVertices().size());
       System.out.println("Costo total: " + grafoKruskal.getCostoTotal() + "");
        /*
        mostrar las aristas del AAM por Kruskal y el costo total
       */
       
       
    }
}
