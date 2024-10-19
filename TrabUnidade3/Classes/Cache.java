package Classes;

public class Cache {
    
    private ListaAutoAjustavel cache = new ListaAutoAjustavel(30);

    public void adicionar (Node add) {
        cache.inserir(add.getKey(), add.getOS());
    }
   
}