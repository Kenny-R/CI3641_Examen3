# Implementa un manejador de clases usando vtables.
# Suponemos que las clases tiene herencia simple.
class manejadorVtables:
    def __init__(self):

        # Almacenamos las clases en un diccionario 
        # donde la key es el nombre de la clase y su valor
        # es una lista con dos valores, el padre de la clase
        # o None si no tiene padre y otro diccionario donde 
        # la key es el nombre de un metodo y su valor es 
        # la clase que implementa este metodo
        self.clases = {}
        
    # Permite añadir una nueva clase al diccionario clases
    # si la clase ya existe, si uno de los metodos esta repetido
    # o si el nombre del padre no existe entonces no se crea la 
    # clase.
    # Entradas:
    #   nombre: Un string con el nombre de la clase
    #   padre: Un string con el nombre de la clase padre 
    #   metodos: Una lista de strings con los nombres de los 
    #            metodos que tendra esta clase
    def crear_clase(self,nombre,padre,metodos):
        if self.existe_clase(nombre): 
            print(f"Ya existe la clase de nombre {nombre}")
            return 
        
        # contamos las ocurrencias de los metodos 
        for i in metodos:
            if metodos.count(i)>=2:
                print(f"El metodo {i} esta repetido")
                return

        table = {}
        
        # si el padre existe copiamos sus metodos.
        if padre != None:
            if not self.existe_clase(padre):
                print(f"No existe la clase {padre}")
                return

            table = self.clases[padre][1].copy()
        
        for i in metodos: 
            table[i] = nombre

        self.clases[nombre] = [padre,table]

    # Verifica si existe una clase en particular en el 
    # diccionario clases.
    # Entradas:
    #   nombre: El nombre de la clase a comprobar.
    def existe_clase(self, nombre):
        return nombre in self.clases.keys()
    
    # Consigue el diccionario de metodos (Vtable) de una clase en particular.
    # Si la clase no existe no devuelve nada.
    # Entrada:
    #   nombre: Un string con el nombre de la clase
    # Salida: 
    #   Un diccionario de strings con los metodos de la clase  
    def obtener_metodos(self,nombre):
        if self.existe_clase(nombre):
            return self.clases[nombre][1]
        else:
            print(f"No existe la clase {nombre}")
            return
