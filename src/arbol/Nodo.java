package arbol;
// Clase Nodo
class Nodo {
    String nombre;
    Nodo izquierdo, derecho;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.izquierdo = null;
        this.derecho = null;
    }
}

