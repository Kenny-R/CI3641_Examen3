/**
 * Implementa un grafo dirigido usando lista de adyacencia.
 */
class grafo {

    var lista_adyacencias: HashMap<Int, MutableList<Int>> = HashMap<Int, MutableList<Int>> () 

    /**
     * Agrega un vertice al grafo, lo cual es esquivalente a crear una 
     * nueva entrada en la lista de adyacencia. Si el vertice ya existe 
     * no lo vuelve a agregar.
     * Entrada:
     *      elemento: Un entero que representa un vertice del grafo 
     */
    fun agregar_vertice(elemento: Int){
        if (lista_adyacencias.containsKey(elemento) == true) {
            return
        } else {
            lista_adyacencias.put(elemento,mutableListOf<Int>())
        }
    }

    /**
     * Agrega un arco al grafo, lo que es equivalente a añadir  
     * el vertice "fin" a la lista de adyacencia de "inicio". Si
     * alguno de estos vertices no esta en el grafo no se añade 
     * el arco.
     * Entrada:
     *    inicio: Un entero que representa el vertice inicial del arco
     *    fin: Un entero que representa el vertice final del arco.
     */
    fun agregar_arco(inicio:Int, fin:Int) {
        if (lista_adyacencias.containsKey(inicio) == false || lista_adyacencias.containsKey(fin) == false) {
            return
        } else {
            lista_adyacencias[inicio]?.add(fin)
        }
    }
}

/**
 * Clase abstracta que implementa el funcionamiento basico 
 * de algoritmos de busqueda sobre un grafo.
 * Entrada:
 *    g: Un grafo en donde se realizara la busqueda
 */
abstract class Busqueda(var g: grafo) {

    /**
     * Realiza una busqueda sobre el grafo, comenzanod en d
     * con el objetivo de encontrar un camino hasta h. Si
     * no existe un camino hasta h se devuelve "-1"
     * Entradas:
     *    d: Un entero que representa el vertice donde se 
     *       empezara a buscar
     *    h: Un entero que representa el veritce objetivo.
     * Salida:
     *     El numero de vertices explorados hasta encontrar el 
     *     vertice h. Si no se encontro un camino hasta h se 
     *     devuelve "-1"
     */
    fun buscar(d:Int,h:Int): Int {
        var contador = 0
        var secuencia = mutableListOf<Int>()
        var v: Int
        var adyacentes: MutableList<Int>
        
        // Creamos una forma de saber si ya visitamos 
        // el vertice o no 
        var visitados = HashMap<Int,Boolean>()

        for (i in g.lista_adyacencias.keys){
            visitados[i] = false
        }
        
        secuencia.add(d)
        visitados[d] = true

        while (secuencia.size != 0) {
            // como la diferencia mas grande entre una pila
            // y una cola es la manera de eliminar un elemento
            // eso es lo que abstraemos 
            v = sacar_elemento(secuencia) 
            //println(v)
            adyacentes = g.lista_adyacencias[v] ?: mutableListOf<Int>()

            contador += 1
            if (v == h) return contador
            
            for (w in adyacentes) {
                if (visitados[w] == false) {
                    visitados[w] = true
                    secuencia.add(w)
                }
            }
        }
        return -1
    }

    /**
     * Firma de la funcion que implementara la forma de 
     * sacar un elemento de la secuencia de vertices a
     * vistar. Esto repercute en el orden en el que se
     * visitan los vertices.
     * Entrada:
     *    s: La secuencia donde se sacaran los vertices. 
     */
    abstract fun sacar_elemento(s:MutableList<Int>):Int
}

/**
 * Implementa la busqueda en amplitud para grafos.
 * Entrada:
 *    g: El grafo donde se realizara la busqueda
 */
class bfs(g: grafo):  Busqueda(g){ 

    /**
     * Permite sacar los primeros vertices a visitar
     * este es el comportamiento de una cola.
     * Entrada:
     *    s: La secuencia donde se sacaran los vertices. 
     */
    override fun sacar_elemento(s:MutableList<Int>): Int {
        return s.removeFirst()
    }
} 

/**
 * Implementa la busqueda en profundidad para grafos.
 * Entrada:
 *    g: El grafo donde se realizara la busqueda
 */
class dfs(g: grafo):  Busqueda(g){ 

    /**
     * Permite sacar los ultimos vertices a visitar.
     * Este es el comportamiento de una pila.
     * Entrada:
     *    s: La secuencia donde se sacaran los vertices. 
     */
    override fun sacar_elemento(s:MutableList<Int>): Int {
        return s.removeLast()
    }
} 