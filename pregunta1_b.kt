/**
 * Interfaz que determina el funcionamiento basico de 
 * los tipos de datos Secuencia de tipo T 
 * (como la pila o la cola ) 
 */
interface Secuencia<T> {
    
    fun agregar(elemento:T)
    
    fun remover()

    fun vacio(): Boolean
}

/**
 * Implementa el funcionamiento de una pila heradando 
 * el funcionamiento de la interfaz Secuencia.
 */
class pila<T>: Secuencia<T> {
    var contenido: MutableList<T> = mutableListOf<T>()  

    /**
     * Agrega un elemento al final de la pila.
     * Entradas:
     *      elemento: El elemento de tipo T que se agregara
     *                al final de la pila. 
     */
    override fun agregar(elemento:T) {
        contenido.add(elemento)
    }

    /**
     * Elimina el ultimo elemento agregado a la pila.
     */
    override fun remover() {
        contenido.removeLast()
    }

    /**
     * Verifica si la pila esta vacia
     * Salida:
     *    Un valor booleano que representa si 
     *    la pila esta vacia o no.
     */
    override fun vacio(): Boolean {
        return contenido.ifEmpty{ null } == null
    }
}

/**
 * Implementa el funcionamiento de una cola heradando 
 * el funcionamiento de la interfaz Secuencia.
 */
class cola<T>: Secuencia<T> {
    var contenido: MutableList<T> = mutableListOf<T>()  

    /**
     * Agrega un elemento al final de la cola.
     * Entradas:
     *      elemento: El elemento de tipo T que se agregara
     *                al final de la cola. 
     */
    override fun agregar(elemento:T) {
        contenido.add(elemento)
    }

    /**
     * Elimina el primer elemento de la cola.
     */
    override fun remover() {
        contenido.removeFirst()
    }

    /**
     * Verifica si la cola esta vacia
     * Salida:
     *    Un valor booleano que representa si 
     *    la cola esta vacia o no.
     */
    override fun vacio(): Boolean {
        return contenido.ifEmpty{ null } == null
    }
}