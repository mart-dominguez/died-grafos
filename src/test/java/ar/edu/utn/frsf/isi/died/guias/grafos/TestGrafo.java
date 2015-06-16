package ar.edu.utn.frsf.isi.died.guias.grafos;



import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestGrafo{

	static Grafo<String> miGrafo= new Grafo<String>();
	static Grafo<String> miGrafoConCiclos = new Grafo<String>();
	
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
			miGrafoConCiclos.conectar("A", "B");
			miGrafoConCiclos.conectar("A", "C");
			miGrafoConCiclos.conectar("B", "D");
			miGrafoConCiclos.conectar("B", "E");
			miGrafoConCiclos.conectar("C", "E");
			miGrafoConCiclos.conectar("D", "F");
			miGrafoConCiclos.conectar("E", "F");
			miGrafoConCiclos.conectar("E", "A");
			
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
		assertEquals(2,miGrafo.gradoEntrada("F"));		
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
//		assertTrue(miGrafo.existeCaminoIterativo("A", "D"));
//		assertFalse(miGrafo.existeCaminoIterativo("C", "D"));
		assertTrue(miGrafoConCiclos.existeCaminoIterativo("A", "D"));
//		assertTrue(miGrafoConCiclos.existeCaminoIterativo("A", "C"));
//		assertTrue(miGrafoConCiclos.existeCaminoIterativo("A", "F"));
//		assertFalse(miGrafoConCiclos.existeCaminoIterativo("F", "A"));
//		assertTrue(miGrafoConCiclos.existeCaminoIterativo("C", "A"));
//		assertFalse(miGrafoConCiclos.existeCaminoIterativo("D", "C"));
//		assertTrue(miGrafoConCiclos.existeCaminoIterativo("C", "B")); // ver
	}
	
}


