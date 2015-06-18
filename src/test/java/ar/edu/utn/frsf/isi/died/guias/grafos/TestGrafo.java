package ar.edu.utn.frsf.isi.died.guias.grafos;



import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestGrafo{

	static Grafo<String> miGrafo= new Grafo<String>();
	static Grafo<String> miGrafoConCiclos = new Grafo<String>();
	static Grafo<String> miGrafoCompleto = new Grafo<String>();
	static Grafo<String> miGrafoCompleto2 = new Grafo<String>();

	@BeforeClass
	public static void inicializar(){
			miGrafo.addNodo("A");
			miGrafo.addNodo("B");
			miGrafo.addNodo("C");
			miGrafo.addNodo("D");
			miGrafo.addNodo("E");
			miGrafo.addNodo("F");
			miGrafo.conectar("A", "B");
			miGrafo.conectar("A", "C");
			miGrafo.conectar("B", "D");
			miGrafo.conectar("B", "E");
			miGrafo.conectar("C", "E");
			miGrafo.conectar("D", "F");
			miGrafo.conectar("E", "F");
			
			miGrafoConCiclos.addNodo("A");
			miGrafoConCiclos.addNodo("B");
			miGrafoConCiclos.addNodo("C");
			miGrafoConCiclos.addNodo("D");
			miGrafoConCiclos.addNodo("E");
			miGrafoConCiclos.addNodo("F");
			miGrafoConCiclos.addNodo("G");
			miGrafoConCiclos.conectar("A", "B");
			miGrafoConCiclos.conectar("A", "C");
			miGrafoConCiclos.conectar("B", "D");
			miGrafoConCiclos.conectar("B", "E");
			miGrafoConCiclos.conectar("C", "E");
			miGrafoConCiclos.conectar("D", "F");
			miGrafoConCiclos.conectar("E", "F");
			miGrafoConCiclos.conectar("E", "G");
			miGrafoConCiclos.conectar("G", "C");
			miGrafoConCiclos.conectar("G", "A");
			
			miGrafoCompleto.addNodo("A");
			miGrafoCompleto.addNodo("B");
			miGrafoCompleto.addNodo("C");
			miGrafoCompleto.addNodo("D");
			miGrafoCompleto.addNodo("E");
			
			miGrafoCompleto.conectar("A", "B");
			miGrafoCompleto.conectar("A", "C");
			miGrafoCompleto.conectar("A", "D");
			miGrafoCompleto.conectar("A", "E");
			
			miGrafoCompleto.conectar("B", "C");
			miGrafoCompleto.conectar("B", "D");
			miGrafoCompleto.conectar("B", "E");

			miGrafoCompleto.conectar("C", "D");
			miGrafoCompleto.conectar("C", "E");

			miGrafoCompleto.conectar("D", "E");

			miGrafoCompleto2.addNodo("A");
			miGrafoCompleto2.addNodo("B");
			miGrafoCompleto2.addNodo("C");
			miGrafoCompleto2.addNodo("D");
			miGrafoCompleto2.addNodo("E");
			
			miGrafoCompleto2.conectar("A", "B");
			miGrafoCompleto2.conectar("A", "C");
			miGrafoCompleto2.conectar("A", "D");
			miGrafoCompleto2.conectar("A", "E");
			
			miGrafoCompleto2.conectar("B", "C");
			miGrafoCompleto2.conectar("B", "D");
			miGrafoCompleto2.conectar("B", "E");

			miGrafoCompleto2.conectar("C", "D");
			miGrafoCompleto2.conectar("C", "E");

			miGrafoCompleto2.conectar("D", "E");
			miGrafoCompleto2.conectar("E", "D");
	}
	
	@Test 
	public void ordenTopologico(){
		List<String> lista = miGrafo.ordenTopologico();
		System.out.println(lista.toString());
		assertEquals(6, lista.size());
				
	}
	
	@Test
	public void testGradoSalida(){
		assertEquals(2,miGrafo.gradoSalida("A"));
		assertEquals(2,miGrafo.gradoSalida("B"));
		assertEquals(1,miGrafo.gradoSalida("C"));
		assertEquals(1,miGrafo.gradoSalida("D"));
		assertEquals(1,miGrafo.gradoSalida("E"));
		assertEquals(0,miGrafo.gradoSalida("F"));		
	}

	@Test
	public void testGradoEntrada(){
		assertEquals(0,miGrafo.gradoEntrada("A"));
		assertEquals(1,miGrafo.gradoEntrada("B"));
		assertEquals(1,miGrafo.gradoEntrada("C"));
		assertEquals(1,miGrafo.gradoEntrada("D"));
		assertEquals(2,miGrafo.gradoEntrada("E"));
		assertEquals(4,miGrafo.gradoEntrada("F"));		
	}
	
	@Test
	public void tieneCiclo(){
		assertFalse(miGrafo.tieneCiclos());
		assertTrue(miGrafoConCiclos.tieneCiclos());
	}
	
	@Test
	public void testExisteCamino(){
		assertTrue(miGrafo.existeCamino("A", "D"));
		assertFalse(miGrafo.existeCamino("C", "D"));
		assertTrue(miGrafoConCiclos.existeCamino("A", "D"));
		assertTrue(miGrafoConCiclos.existeCamino("A", "C"));
		assertTrue(miGrafoConCiclos.existeCamino("A", "F"));
		assertFalse(miGrafoConCiclos.existeCamino("F", "A"));
		assertTrue(miGrafoConCiclos.existeCamino("C", "A"));
		assertFalse(miGrafoConCiclos.existeCamino("D", "C"));
		assertTrue(miGrafoConCiclos.existeCamino("C", "B")); // ver
	}
	
	@Test
	public void testExisteCaminoIterativo(){
		assertTrue(miGrafo.existeCaminoIterativo("A", "D"));
		assertFalse(miGrafo.existeCaminoIterativo("C", "D"));
		assertTrue(miGrafoConCiclos.existeCaminoIterativo("A", "D"));
		assertTrue(miGrafoConCiclos.existeCaminoIterativo("A", "C"));
		assertTrue(miGrafoConCiclos.existeCaminoIterativo("A", "F"));
		assertFalse(miGrafoConCiclos.existeCaminoIterativo("F", "A"));
		assertTrue(miGrafoConCiclos.existeCaminoIterativo("C", "A"));
		assertFalse(miGrafoConCiclos.existeCaminoIterativo("D", "C"));
		assertTrue(miGrafoConCiclos.existeCaminoIterativo("C", "B")); // ver
	}
	
	@Test
	public void testEsCompleto(){
		assertFalse(miGrafo.esCompleto());
		assertFalse(miGrafo.esCompleto2());
		assertTrue(miGrafoCompleto.esCompleto());
		assertFalse(miGrafoCompleto.esCompleto2());
		assertFalse(miGrafoConCiclos.esCompleto());
		assertFalse(miGrafoConCiclos.esCompleto2());
		assertFalse(miGrafoCompleto2.esCompleto());
		assertFalse(miGrafoCompleto2.esCompleto2());


	}
	
}


