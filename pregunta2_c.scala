import java.io.File
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

// Muesta el funcionamiento de la funcion files_count que
// cuenta el numero de archivos en el directorio pasado 
// como parametro y todos los subdirectorios dentro de
// este. Para este ejemplo se calculan todos los archivos
// empezando desde el directorio actual.
@main def try_files_count() =
    // contaremos todos los archivos a partir de donde este
    // el ejecutable 
    var place = "."
        
    println(Await.result(files_count(place),Duration.Inf))

// Cuenta el numero de archivos que hay en el directorio
// que se le pase como parametro y en todos los subdirectorios 
// dentro de esto. Usando Concurrencia para calcular los archivo
// en cada directorio por separado.
// Entradas:
//   path: Un string con la direccion del directorio raiz donde
//   se empezara a contar.
// Salida:
//   El posible valor de la cantidad de archivos dentro del directorio.
def files_count(path:String):Future[Int] = Future {
    // creamos una lista con todos los archivos que hay
    // en el path

    val root = new File(path)
    
    var elem: List[File] = List[File]()
    
    if root.exists && root.isDirectory then
        elem = root.listFiles().toList
    // Creamos una lista para los directorios y otra 
    // para los archivos 

    var files: List[File] = List[File]()
    var dirs: List[File] = List[File]()

    for e <- elem
    do
        if e.isDirectory == false then
            files = files :+ e
        else 
            dirs = dirs :+ e
    
    // contamos la cantidad de archivos dentro del directorio
    var count = files.length

    // creamos una lista para ir guardando el numero de archivos en 
    // los subdirectorios

    var futures: List[Future[Int]] = List[Future[Int]]()
    for dir <- dirs
    do
        futures = futures :+ files_count(dir.toPath().toString())

    // esperamos los resultados de todos los subdirectorios
    for fut <- futures
    do 
        count += Await.result(fut, 100.seconds)  

    count
}