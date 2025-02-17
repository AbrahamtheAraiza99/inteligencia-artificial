package arbol;
// Clase Principal para probar el árbol
public class main {
    public static void main(String[] args) {
        // Crear una instancia del árbol
        Arbol arbol = new Arbol();

        // Insertar nodos en el árbol
        arbol.insertar("Carlos");
        arbol.insertar("Ana");
        arbol.insertar("Pedro");
        arbol.insertar("Luis");

        // Verificar si el árbol está vacío
        System.out.println("¿El árbol está vacío? " + (arbol.vacio() ? "Sí" : "No"));

        // Buscar un nodo en el árbol
        String nombreBuscado = "Pedro";
        Nodo buscado = arbol.buscarNodo(nombreBuscado);
        if (buscado != null) {
            System.out.println("Nodo encontrado: " + buscado.nombre);
        } else {
            System.out.println("Nodo '" + nombreBuscado + "' no encontrado.");
        }

        // Mostrar el recorrido en orden
        System.out.print("Recorrido en orden: ");
        arbol.inOrden();
        System.out.println(); // Salto de línea final para mejor visualización
    }
}
