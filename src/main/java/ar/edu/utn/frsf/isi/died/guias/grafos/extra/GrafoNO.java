package ar.edu.utn.frsf.isi.died.guias.grafos.extra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import ar.edu.utn.frsf.isi.died.guias.grafos.Arista;
import ar.edu.utn.frsf.isi.died.guias.grafos.Vertice;
public class GrafoNO<T extends Comparable<T>> {

	private List<Arista<T>> aristas;
	private List<Vertice<T>> nodos;
	
	public GrafoNO(){
		this.aristas = new ArrayList<Arista<T>>();
		this.nodos = new ArrayList<Vertice<T>>();
	}

	public void addVertice(T nodo){
		this.addVertice(new Vertice<T>(nodo));
	}

	private void addVertice(Vertice<T> nodo){
		this.nodos.add(nodo);
	}
	
	public void conectar(T n1,T n2){
		this.conectar(getVertice(n1), getVertice(n2), 0.0);
	}

	public void conectar(T n1,T n2,Number valor){
		this.conectar(getVertice(n1), getVertice(n2), valor);
	}

	private void conectar(Vertice<T> nodo1,Vertice<T> nodo2,Number valor){
		this.aristas.add(new Arista<T>(nodo1,nodo2,valor));
	}
	
	public Vertice<T> getVertice(T valor){
		System.out.println(this.nodos.indexOf(new Vertice<T>(valor)));
		return this.nodos.get(this.nodos.indexOf(new Vertice<T>(valor)));
	}

	public List<Vertice<T>> getAdyacentes(T valor){
		return this.getAdyacentes(this.getVertice(valor));
	}

	private List<Vertice<T>> getAdyacentes(Vertice<T> unVertice){ 
		List<Vertice<T>> salida = new ArrayList<Vertice<T>>();
		for(Arista<T> enlace : this.aristas){
			if( enlace.getInicio().equals(unVertice)){
				salida.add(enlace.getFin());
			}
		}
		return salida;
	}
	
	public List<T> caminoMasCorto(T n1,T n2){
		return null;
	}

	public List<T> caminoMenorPeso(T n1,T n2){
		return null;
	}
	
	public List<T> ordenTopologico(){
		/*
		 * Ejemplo: ordenar los cursos de una carrera universitaria en una 
		 * secuencia que no viole los prerrequisitos.
		 * El algoritmo consiste en encontrar primero un vértice v que no
		 * tenga aristas de entrada.
		 * Se imprime este vértice y se borra lógicamente del grafo junto con sus aristas.
		 * Luego, se sigue aplicando la misma estrategia al resto del grafo.
		 * Se utiliza el grado de entrada de cada vértice v ( número de aristas (u,v)) 
		 * el que se decrementa en 1 al borrar lógicamente 
		 */
		return null;
	}

	public List<T> recorridoAnchura(T n1){
		Vertice<T> inicial = this.getVertice(n1);
		
		Map<Vertice<T>, Boolean> visitado = new HashMap<Vertice<T>, Boolean>();
		for(Vertice<T> n : this.nodos){
			visitado.put(n, false);
		}
		
		Queue<Vertice<T>> pendientes = new LinkedList<Vertice<T>>();
		List<Vertice<T>> resultado = new ArrayList<Vertice<T>>();

		visitado.put(inicial, true);
		pendientes.add(inicial);
		
		while(!pendientes.isEmpty()){			
			Vertice<T>  nodoActual= pendientes.poll();
			resultado.add(nodoActual);
			for(Vertice<T> n : this.getAdyacentes(nodoActual)){
				if(!visitado.get(n)){ 
					pendientes.add(n);
					visitado.put(n,true);
				}
				
			}
		}
		/*
		 * procedimiento ra (v)
         Q = cola vacía
         marca[v] =  visitado
         encolar  v en Q
         mientras Q no esté vacía hacer
                u = desencolar (Q)
                visitar u
                para cada nodo w adyacente a u hacer
                         si marca [w] != visitado entonces 
                             marca[w] = visitado
                             encolar w en Q      
		 */
		return null;
	}

	public List<T> recorridoProfundidad(T n1){
		/*
		 * procedimiento rp (v)
         P = pila  vacía
         marca[v] =  visitado
         apilar  v en P
         mientras P no esté vacía hacer
                u = desapilar (P)
                visitar u
                para cada nodo w adyacente a u hacer
                         si marca [w] != visitado entonces 
                             marca[w] = visitado
                             apilar w en Q      
		 */
		
		/*
		 * RECURSIVO !!
		 * procedimiento rp2 (v)
         si v != null
             visitar v
             marca[v] =  visitado
             para cada nodo w adyacente a v hacer
                         si marca [w] != visitado entonces       
                              rp2(w)     

		 */
		return null;
	}
	
	public boolean tieneCiclos(){
		return false;
	}

	public int gradoPositivo(T n1){
		return 0;
	}

	public int gradoNegativo(T n1){
		return 0;
	}

	/**
	 * retorna la distancia más grande 
	 * del menor camino que  hay entre 
	 * n1 y cualquier otro nodo del grafo 
	 * @param n1
	 * @return
	 */
	public int excentricidad(T n1){
		return 0;
	}

	/**
	 * determina la máxima excentricidad 	del grafo
	 * @param unGrafo
	 */
	public int diámetro(){
		return 0;
	}
	
	/**
	 * retorna trae si existe un enlace para cada par de vertices
	 * @return
	 */
	public boolean esCompleto(){
		return false;
	}

	public void imprimir(){
		
	}
	
	
}
