
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ocamp
 */
public class TGrafoRedElectrica extends TGrafoNoDirigido implements IGrafoRedElectrica {

    public TGrafoRedElectrica(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @Override
    public TAristas mejorRedElectrica() {
        TGrafoNoDirigido mst = new TGrafoNoDirigido(this.getVertices().values(), new LinkedList<TArista>());
        LinkedList<TArista> aristasOrdenadas = new LinkedList<>(lasAristas);
        Collections.sort(aristasOrdenadas, new Comparator<TArista>() {
            @Override
            public int compare(TArista a1, TArista a2) {
                return Double.compare(a1.getCosto(), a2.getCosto());
            }
        });

        DisjointSet ds = new DisjointSet(this.getVertices().size());
        Map<Comparable, Integer> mapaIndices = new HashMap<>();
        int i = 0;
        for (TVertice v : this.getVertices().values()) {
            mapaIndices.put(v.getEtiqueta(), i++);
        }
        Set<String> aristasAgregadas = new HashSet<>();


        for (TArista arista : aristasOrdenadas) {
            String clave1 = arista.getEtiquetaOrigen() + "-" + arista.getEtiquetaDestino();
            String clave2 = arista.getEtiquetaDestino() + "-" + arista.getEtiquetaOrigen();
            if (aristasAgregadas.contains(clave1) || aristasAgregadas.contains(clave2)) continue;

            if (!mapaIndices.containsKey(arista.getEtiquetaOrigen()) || !mapaIndices.containsKey(arista.getEtiquetaDestino())) {
                System.out.println("Error: No se encontró alguna casa: " + arista.getEtiquetaOrigen() + " -> " + arista.getEtiquetaDestino());
                continue;
            }
            int origen = mapaIndices.get(arista.getEtiquetaOrigen());
            int destino = mapaIndices.get(arista.getEtiquetaDestino());

            if (ds.find(origen) != ds.find(destino)) {
                mst.insertarArista(arista);
                ds.union(origen, destino);
                aristasAgregadas.add(clave1);
                if (mst.getLasAristas().size() == this.getVertices().size() - 1)
                    break;
            }
        }
        return mst.getLasAristas();
    }

    private class DisjointSet {
        private int[] padre, rango;

        public DisjointSet(int n) {
            padre = new int[n];
            rango = new int[n];
            for (int i = 0; i < n; i++) padre[i] = i;
        }

        public int find(int x) {
            if (padre[x] != x) padre[x] = find(padre[x]);
            return padre[x];
        }

        public void union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx == ry) return;
            if (rango[rx] < rango[ry]) padre[rx] = ry;
            else if (rango[ry] < rango[rx]) padre[ry] = rx;
            else {
                padre[ry] = rx;
                rango[rx]++;
            }
        }
    }
}