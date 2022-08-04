from manejadorVtables import manejadorVtables as mvt

vity = mvt()

def test_crear_clases(): 
    vity.crear_clase("A",None,["f","g"])
    assert vity.clases["A"] == [None,{"f":"A","g":"A"}]
    vity.crear_clase("B","A",["f","h"])
    assert vity.clases["B"] == ["A",{"f":"B","g":"A","h":"B"}]
    vity.crear_clase("C","B",["z","z"])
    assert ("C" in vity.clases.keys()) == False
    vity.crear_clase("D","hola",["z"])
    assert ("D" in vity.clases.keys()) == False
    vity.crear_clase("A",None,["z"])
    assert vity.clases["A"] == [None,{"f":"A","g":"A"}]

def test_existe_clase():
    assert vity.existe_clase("A") == True
    assert vity.existe_clase("D") == False

def test_obtener_metodos():
    assert vity.obtener_metodos("A") == {"f":"A","g":"A"}
    assert vity.obtener_metodos("B") == {"f":"B","g":"A","h":"B"}
    assert vity.obtener_metodos("C") == None

test_crear_clases()
test_existe_clase()
test_obtener_metodos()