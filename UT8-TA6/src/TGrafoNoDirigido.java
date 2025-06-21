
import java.util.*;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {

    protected TAristas lasAristas = new TAristas();

    /**
     *
     * @param vertices
     * @param aristas
     */
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        lasAristas.insertarAmbosSentidos(aristas);

    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }

    public TAristas getLasAristas() {
        return lasAristas;
    }

    @Override
    public TGrafoNoDirigido Prim() {
        if(this.getVertices().isEmpty()) return null;
        TGrafoNoDirigido mst=new TGrafoNoDirigido(this.getVertices().values(),new LinkedList<TArista>());
        Set<Comparable> visitados=new HashSet<>();
        PriorityQueue<TArista> pq=new PriorityQueue<>(Comparator.comparingDouble(TArista::getCosto));

        TVertice inicio=this.getVertices().values().iterator().next();
        visitados.add(inicio.getEtiqueta());
        for (TArista arista:lasAristas) {
            if (arista.getEtiquetaOrigen().equals(inicio.getEtiqueta()) && !visitados.contains(arista.getEtiquetaDestino())) {
                pq.add(arista);
            }
            if (arista.getEtiquetaDestino().equals(inicio.getEtiqueta()) && !visitados.contains(arista.getEtiquetaOrigen())) {
                pq.add(new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto()));
            }
        }

        while(!pq.isEmpty()&&visitados.size()<this.getVertices().size()) {
            TArista aristaMin=pq.poll();

            if(visitados.contains(aristaMin.getEtiquetaDestino())) continue;
            mst.getLasAristas().add(aristaMin);
            Comparable nuevoVertice=aristaMin.getEtiquetaDestino();
            visitados.add(nuevoVertice);
            for(TArista arista:lasAristas) {
                if(arista.getEtiquetaOrigen().equals(nuevoVertice)&&!visitados.contains(arista.getEtiquetaDestino())) {
                    pq.add(arista);
                }
            }
        }
        return mst;
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        TGrafoNoDirigido mst=new TGrafoNoDirigido(this.getVertices().values(), new LinkedList<TArista>());
        LinkedList<TArista> aristasOrdenadas=new LinkedList<>(lasAristas);
        Collections.sort(aristasOrdenadas, new Comparator<TArista>() {
            @Override
            public int compare(TArista a1, TArista a2) {
                return Double.compare(a1.getCosto(), a2.getCosto());
            }
        });

        DisjointSet ds=new DisjointSet(this.getVertices().size());
        Map<Comparable, Integer> mapaIndices=new HashMap<>();
        int i = 0;
        for (TVertice v:this.getVertices().values()) {
            mapaIndices.put(v.getEtiqueta(), i++);
        }
        Set<String> aristasAgregadas=new HashSet<>();


        for (TArista arista:aristasOrdenadas) {
            String clave1=arista.getEtiquetaOrigen() + "-" + arista.getEtiquetaDestino();
            String clave2=arista.getEtiquetaDestino() + "-" + arista.getEtiquetaOrigen();
            if (aristasAgregadas.contains(clave1)||aristasAgregadas.contains(clave2)) continue;

            int origen=mapaIndices.get(arista.getEtiquetaOrigen());
            int destino=mapaIndices.get(arista.getEtiquetaDestino());

            if (ds.find(origen)!=ds.find(destino)) {
                mst.getLasAristas().add(arista);
                ds.union(origen,destino);
                aristasAgregadas.add(clave1);
                if (mst.getLasAristas().size()==this.getVertices().size() - 1)
                    break;
            }
        }
        return mst;
    }

    public String getCostoTotal() {
        double costoTotal=0;
        for(TArista arista:lasAristas) {
            costoTotal+=arista.getCosto();
        }
        return String.format("%.2f", costoTotal);
    }

    private class DisjointSet {
        private int[] padre,rango;
        public DisjointSet(int n) {
            padre=new int[n];
            rango=new int[n];
            for(int i=0;i<n;i++) padre[i]=i;
        }
        public int find(int x) {
            if(padre[x]!=x) padre[x]=find(padre[x]);
            return padre[x];
        }
        public void union(int x,int y) {
            int rx=find(x);
            int ry=find(y);
            if(rx==ry) return;
            if(rango[rx]<rango[ry]) padre[rx]=ry;
            else if(rango[ry]<rango[rx]) padre[ry]=rx;
            else { padre[ry]=rx; rango[rx]++; }
        }
    }


    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen){
        LinkedList<TVertice> visitados=new LinkedList<>();
        TVertice origen=getVertices().get(etiquetaOrigen);
        if(origen==null){
            return visitados; // vacía si no existe el vértice
        }
        Queue<TVertice> cola=new LinkedList<>();
        Set<Comparable> vistos=new HashSet<>();
        cola.add(origen);
        vistos.add(origen.getEtiqueta());

        while(!cola.isEmpty()){
            TVertice actual=cola.poll();
            visitados.add(actual);

            for(Object o:actual.getAdyacentes()){
                TAdyacencia ady=(TAdyacencia) o;
                TVertice verticeAdyacente=ady.getDestino();
                if(!vistos.contains(ady.getEtiqueta())){
                    cola.add(verticeAdyacente);
                    vistos.add(ady.getEtiqueta());
                }
            }
        }

        return visitados;
    }

}
