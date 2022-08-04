import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

// Muesta el funcionamiento de la funcion
// dot_product que calcula el producto punto de 
// dos vectores del mismo tamaño, usando concurrencia.
@main def try_dot_product() = {
  var a = Array(1,2,3,4)
  var b = Array(2,3,4,5)
  
  var c = dot_product(a,b)
  println(c)
}

// Calcula el producto punto de dos vectores del mismo tamaño
// Entradas:
//   a: Un arreglo que representa el primer vector.
//   b: Un arreglo que representa el segundo vector.
// Salida:
//   El producto punto de a y b

def dot_product(a: Array[Int], b: Array[Int]): Int = {
  // creamos una lista para almacenar las multiplicaciones
  var futures: List[Future[Int]] = List()
  var result = 0
  for i <- 0 until a.length
  do
    // creamos una nueva entrada en la lista por cada
    // elemento a multiplicar
    futures = futures :+ Future { a(i)*b(i) }
  
  for f <- futures 
  do 
    // vamos sumando esperando a que los valores se vayan calculando
    result += Await.result(f,99.seconds) 

  result
}