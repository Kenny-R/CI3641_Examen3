from manejadorVtables import manejadorVtables as mvt
msg1 = "Bienvenido a al simulador de Vtable Vity "

print(msg1)

vity = mvt()

while True:
    opcion = input(">>")
    opcion = opcion.split(" ")
    
    if opcion[0] == "CLASS":
        if opcion[2] == ":":
            vity.crear_clase(opcion[1],opcion[3],opcion[4:])
        else:
            vity.crear_clase(opcion[1],None,opcion[2:])
    elif opcion[0] == "DESCRIBIR":
        metodos = vity.obtener_metodos(opcion[1])
        if metodos == None: continue
        for  m in metodos.keys():
            print(f"{m} -> {metodos[m]} :: {m}")  
    elif opcion[0] == "SALIR":
        print("Hasta luego.")
        break
