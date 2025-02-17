
package arbol;
// Clase Arbol
class Arbol {
    private Nodo raiz;

    public Arbol() {
        this.raiz = null;
    }

    // Método para verificar si el árbol está vacío
    public boolean vacio() {
        return raiz == null;
    }

    // Método para insertar un nodo en el árbol
    public void insertar(String nombre) {
        raiz = insertarRec(raiz, nombre);
    }

    private Nodo insertarRec(Nodo nodo, String nombre) {
        if (nodo == null) {
            return new Nodo(nombre);
        }
        if (nombre.compareTo(nodo.nombre) < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, nombre);
        } else if (nombre.compareTo(nodo.nombre) > 0) {
            nodo.derecho = insertarRec(nodo.derecho, nombre);
        }
        return nodo;
    }

    // Método para buscar un nodo en el árbol
    public Nodo buscarNodo(String nombre) {
        return buscarRec(raiz, nombre);
    }

    private Nodo buscarRec(Nodo nodo, String nombre) {
        if (nodo == null || nodo.nombre.equals(nombre)) {
            return nodo;
        }
        if (nombre.compareTo(nodo.nombre) < 0) {
            return buscarRec(nodo.izquierdo, nombre);
        }
        return buscarRec(nodo.derecho, nombre);
    }

    // Método para recorrer el árbol en orden
    public void inOrden() {
        inOrdenRec(raiz);
    }

    private void inOrdenRec(Nodo nodo) {
        if (nodo != null) {
            inOrdenRec(nodo.izquierdo);
            System.out.print(nodo.nombre + " ");
            inOrdenRec(nodo.derecho);
        }
    }
}