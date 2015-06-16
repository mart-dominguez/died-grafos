package ar.edu.utn.frsf.isi.died.guias.grafos;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;


public class Grafo<T extends Comparable<T>> {

	private List<Arista<T>> aristas;
	private List<Vertice<T>> vertices;
	
	
	public Grafo(){
		this.aristas = new ArrayList<Arista<T>>();
		this.vertices = new ArrayList<Vertice<T>>();
	}

	public void addNodo(T nodo){
		this.addNodo(new Vertice<T>(nodo));
	}

	private void addNodo(Vertice<T> nodo){
		this.vertices.add(nodo);
	}
	
	public void conectar(T n1,T n2){
		this.conectar(getNodo(n1), getNodo(n2), 0.0);
	}

	public void conectar(T n1,T n2,Number valor){
		this.conectar(getNodo(n1), getNodo(n2), valor);
	}

	private void conectar(Vertice<T> nodo1,Vertice<T> nodo2,Number valor){
		this.aristas.add(new Arista<T>(nodo1,nodo2,valor));
	}
	
	public Vertice<T> getNodo(T valor){
		return this.vertices.get(this.vertices.indexOf(new Vertice<T>(valor)));
	}

	public List<T> getAdyacentes(T valor){ 
		Vertice<T> unNodo = this.getNodo(valor);
		List<T> salida = new ArrayList<T>();
		for(Arista<T> enlace : this.aristas){
			if( enlace.getInicio().equals(unNodo)){
				salida.add(enlace.getFin().getValor());
			}
		}
		return salida;
	}
	

	private List<Vertice<T>> getAdyacentes(Vertice<T> unNodo){ 
		List<Vertice<T>> salida = new ArrayList<Vertice<T>>();
		for(Arista<T> enlace : this.aristas){
			if( enlace.getInicio().equals(unNodo)){
				salida.add(enlace.getFin());
			}
		}
		return salida;
	}
	
	public void imprimirAristas(){
		System.out.println(this.aristas.toString());
	}

	public List<T> caminoMasCorto(T n1,T n2){
		return null;
	}

	public List<T> caminoMenorPeso(T n1,T n2){
		return null;
	}
	
	/**
	 * http://stackoverflow.com/questions/546655/finding-all-cycles-in-graph
	 * 
	 * http://jgrapht.org/
	 * 
	 * https://tekjutsu.wordpress.com/2010/02/03/3/
	 * @return
	 */
	public boolean tieneCiclos(){
		Map<T, Integer> gradoPorNodo = new HashMap<T, Integer>();
		Deque<Vertice<T>> aVisitar= new LinkedList<Vertice<T>>(); 
		
		for(Vertice<T> v : this.vertices){
			int gradoIn = this.gradoEntrada(v.getValor());
			gradoPorNodo.put(v.getValor(), gradoIn);
		}
		System.out.println(gradoPorNodo.toString());
		while(!gradoPorNodo.isEmpty()){
			for(Vertice<T> v : this.vertices){
//				System.out.println(gradoPorNodo+" * "+v+" ** "+v.getValor()+" *** "+aVisitar+" **** "+gradoPorNodo.get(v.getValor()));
				if(gradoPorNodo.containsKey(v.getValor()) && gradoPorNodo.get(v.getValor())==0) aVisitar.add(v);
			}
		
			// 	tiene ciclos todos tienen un camino de llegada
			if(aVisitar.isEmpty()) return true;
		
		
			// 	buscar todos los vertices con grado de entrada 0
			// 	en un grafo donde todos los nodos al menos tienen una entrada entonces hay ciclos
			while(!aVisitar.isEmpty()){
				Vertice<T> v = aVisitar.remove();
				gradoPorNodo.remove(v.getValor());
				for(Vertice<T> ady : this.getAdyacentes(v)){
					int aux = gradoPorNodo.get(ady.getValor());
					gradoPorNodo.replace(ady.getValor(),aux-1);
				}			
			}
		}		
		return false;
	}
	
	/*
	 * Ejemplo: ordenar los cursos de una carrera universitaria en una 
	 * secuencia que no viole los prerrequisitos.
	 * El algoritmo consiste en encontrar primero un vértice v que no
	 * tenga aristas de entrada.
	 * Se imprime este vértice y se borra lógicamente del grafo junto con sus aristas.
	 * Luego, se sigue aplicando la misma estrategia al resto del grafo.
	 * Se utiliza el grado de entrada de cada vértice v ( número de aristas (u,v)) 
	 * el que se decrementa en 1 al borrar lógicamente 
	 * 
	 */
	public List<T> ordenTopologico(){
		List<T> resultado = new ArrayList<T>();
		Map<T, Integer> gradoPorNodo = new HashMap<T, Integer>();
		for(Vertice<T> v : this.vertices){
			gradoPorNodo.put(v.getValor(), this.gradoEntrada(v.getValor()));
		}
		for(Vertice<T> vert : this.vertices){
			if(gradoPorNodo.get(vert.getValor())==0) {
				resultado.add(vert.getValor());
				for(T v: this.getAdyacentes(vert.getValor())){
					int aux = gradoPorNodo.get(v);
					gradoPorNodo.put(v, aux-1);
				}
				gradoPorNodo.remove(vert.getValor());
			}						
		}		
		return resultado;
	}

	public List<T> recorridoAnchura(T n1){
		//obtengo el nodo desde donde comienza el recorrido
		Vertice<T> inicial = this.getNodo(n1);
		
		// armo un map donde marco cada nodo como no visitado
		Map<Vertice<T>, Boolean> visitado = new HashMap<Vertice<T>, Boolean>();
		for(Vertice<T> n : this.vertices){
			visitado.put(n, false);
		}
		
		//creo la cola para almacenar los nodos pendientes de visitar.
		Queue<Vertice<T>> pendientes = new LinkedList<Vertice<T>>();
		//creo la lista de resultados.
		List<T> resultado = new ArrayList<T>();

		// tomo el primer nodo y lo marco como visitado
		visitado.put(inicial, true);
		// y lo agrego a los pendientes a visitar
		pendientes.add(inicial);
		
		while(!pendientes.isEmpty()){			
			//quito el nodo pendiente
			Vertice<T>  nodoActual= pendientes.remove();
			// lo agrego a los resultados
			resultado.add(nodoActual.getValor());
			for(Vertice<T> n : this.getAdyacentes(nodoActual)){
				/* 
				 * a cada adyacente del nodo visitado lo agrego 
				 * a la cola de los pendientes 
				 * si no fue visitado y lo marco como visitado
				 */
				if(!visitado.get(n)){ 
					pendientes.add(n);
					visitado.put(n,true);
				}
				
			}
		}
		return resultado;
	}

	public List<T> recorridoProfundidad(T n1){
		//obtengo el nodo desde donde comienza el recorrido
		Vertice<T> inicial = this.getNodo(n1);
		
		// armo un map donde marco cada nodo como no visitado
		Map<Vertice<T>, Boolean> visitado = new HashMap<Vertice<T>, Boolean>();
		for(Vertice<T> n : this.vertices){
			visitado.put(n, false);
		}
		
		//creo la cola para almacenar los nodos pendientes de visitar.
		Stack<Vertice<T>> pendientes = new Stack<Vertice<T>>();
		//creo la lista de resultados.
		List<T> resultado = new ArrayList<T>();

		// tomo el primer nodo y lo marco como visitado
		visitado.put(inicial, true);
		// y lo agrego a los pendientes a visitar
		pendientes.add(inicial);
		
		while(!pendientes.isEmpty()){			
			//quito el nodo pendiente
			Vertice<T>  nodoActual= pendientes.pop();
			// lo agrego a los resultados
			resultado.add(nodoActual.getValor());
			for(Vertice<T> n : this.getAdyacentes(nodoActual)){
				/* 
				 * a cada adyacente del nodo visitado lo agrego 
				 * a la pila de los pendientes 
				 * si no fue visitado y lo marco como visitado
				 */
				if(!visitado.get(n)){ 
					pendientes.push(n);
					visitado.put(n,true);
				}
				
			}
		}
		return resultado;
	}
	
	
	public List<T> recorridoProfundidadAll(T n1,T n2){
		//obtengo el nodo desde donde comienza el recorrido
		Vertice<T> inicial = this.getNodo(n1);
		
		// armo un map donde marco cada nodo como no visitado
		Map<Vertice<T>, Boolean> visitado = new HashMap<Vertice<T>, Boolean>();
		for(Vertice<T> n : this.vertices){
			visitado.put(n, false);
		}
		
		//creo la cola para almacenar los nodos pendientes de visitar.
		Stack<Vertice<T>> pendientes = new Stack<Vertice<T>>();
		//creo la lista de resultados.
		List<T> resultado = new ArrayList<T>();

		// tomo el primer nodo y lo marco como visitado
		visitado.put(inicial, true);
		// y lo agrego a los pendientes a visitar
		pendientes.add(inicial);
		
		while(!pendientes.isEmpty()){			
			//quito el nodo pendiente
			Vertice<T>  nodoActual= pendientes.pop();
			// lo agrego a los resultados
			resultado.add(nodoActual.getValor());
			for(Vertice<T> n : this.getAdyacentes(nodoActual)){
				/* 
				 * a cada adyacente del nodo visitado lo agrego 
				 * a la pila de los pendientes 
				 * si no fue visitado y lo marco como visitado
				 */
				if(!visitado.get(n)){ 
					pendientes.push(n);
					visitado.put(n,true);
				}
				
			}
		}
		return resultado;
	}
	
	public List<List<T>> todosLosCaminosViejo(T inicio,T fin){
		List<List<T>> salida = new ArrayList<List<T>>();
		Vertice<T> nodoInicial = this.getNodo(inicio);
		Vertice<T> nodoFinal = this.getNodo(fin);
		
		//chequeo si existe camino directo entre origen y final.
		// chequeo si existe un camino desde algún adyacente.
		for(Vertice<T> n1 : this.getAdyacentes(nodoInicial)){
				List<T> camino = new ArrayList<T>();
				camino.add(nodoInicial.getValor());
				//si un adyacente ya es destino lo agrego
				if(n1.equals(nodoFinal)){
					camino.add(nodoFinal.getValor());
					salida.add(camino);
				}
				//sigo buscando camino por el adyacente
				else{
					List<List<T>> aux = camino2(n1,nodoFinal);
					if(aux!=null){
//						camino.addAll(aux);
						salida.addAll(aux);
					}
				}
		}
		return salida;
	}
	
	public List<List<T>> todosLosCaminosNuevo(T inicio,T fin){
		List<List<T>> salida = new ArrayList<List<T>>();
		List<T> caminoActual = new ArrayList<T>();
		Vertice<T> nodoInicial = this.getNodo(inicio);
		Vertice<T> nodoFinal = this.getNodo(fin);
		caminoActual.add(nodoInicial.getValor());
		caminoAux(nodoInicial,nodoFinal,caminoActual,salida);
		return salida;
	}
	
	private void caminoAux(Vertice<T> origen,Vertice<T> destino, List<T> marcados,List<List<T>> camino){
		
		for(Vertice<T> n1 : this.getAdyacentes(origen)){
			if(n1.equals(destino)){
				marcados.add(n1.getValor());				
			}else{				
				if(!marcados.contains(n1.getValor())){
					marcados.add(n1.getValor());
					caminoAux(origen, destino, marcados, camino);
				}
//				marcados.remove(n1.getValor());
			}
		}
		
	}	
	
	private List<T> camino3(Vertice<T> origen,Vertice<T> destino){
		List<T> resultado = new ArrayList<T>();
		for(Vertice<T> n1 : this.getAdyacentes(origen)){
			resultado.add(origen.getValor());
			if(n1.equals(destino)){
				resultado.add(destino.getValor());
				return resultado;
			}else{				
				List<T> aux = camino3(n1,destino);
				if(aux!=null) {
					resultado.addAll(aux);
					return resultado;
				}else resultado.clear();
			}
		}
		resultado.clear();
		resultado = null;
		return null;
	}
	
	private List<List<T>> camino2(Vertice<T> origen,Vertice<T> destino){
		List<List<T>> resultado = new ArrayList<List<T>>();
		List<T> caminoEncontrado = new ArrayList<T>();
		for(Vertice<T> n1 : this.getAdyacentes(origen)){
			caminoEncontrado.add(origen.getValor());
			if(n1.equals(destino)){
				caminoEncontrado.add(destino.getValor());
				resultado.add(caminoEncontrado);
			}else{				
				List<T> aux = camino3(n1,destino);
				if(aux!=null) {
					caminoEncontrado.addAll(aux);
					resultado.add(caminoEncontrado);
				}else caminoEncontrado.clear();
			}
		}
		resultado.clear();
		resultado = null;
		return null;
	}
	
	
	/**
	 * Chequea si tiene ciclos. 
	 * Para esto toma cada nodo y realiza un recorrido en profundidad, pero en cada paso chequea si el nodo al cual le solicitará
	 * los adyacentes no está en la lista de adyacentes. Si lo está hay ciclos.
	 * @return
	 */
	public boolean tiene2Caminos(){
		for(Vertice<T> vertice : this.vertices){		
			Stack<Vertice<T>> aVisitar= new Stack<Vertice<T>>(); 
			//para cada vertice busco el recorrido en profundidad			
			Set<Vertice<T>> visitados = new TreeSet<Vertice<T>>();
			aVisitar.push(vertice);			
			while(!aVisitar.isEmpty()){				
				Vertice<T> aux = aVisitar.pop();				
				for(Vertice<T> ady :this.getAdyacentes(aux)){
					if(visitados.contains(ady)) {
						System.out.println("ciclo entre "+aux.getValor()+" : "+ady.getValor());
						return true;
					}else{
						aVisitar.add(ady);
					}
				}
				visitados.add(aux);
			}						
		}		
		return false;
	}

	/**
	 * Cantidad de aristas que llegan al grafo (cantidad de veces que es destino )
	 * @param n1
	 * @return
	 */
	public int gradoEntrada(T n1){
		int grado = 0;
		for(Arista<T> arista : this.aristas){
			if(arista.getFin().getValor().equals(n1)) grado++;
		}
		return grado;
	}

	/**
	 * Cantidad de aristas que salen del grafo (cantidad de veces que es origen ) 
	 * @param n1
	 * @return
	 */
	public int gradoSalida(T n1){
		int grado = 0;
		for(Arista<T> arista : this.aristas){
			if(arista.getInicio().getValor().equals(n1)) grado++;
		}
		return grado;
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


	/**
	 * Determina si existe un camino entre dos vértices.
	 * 
	 * @param vertice1
	 *            El nombre del vértice inicial del camino.
	 * @param vertice2
	 *            El nombre del vértice final del camino.
	 * @return <code>true</code> si existe un camino entre los vértices,
	 *         <code>false</code> en caso contrario.
	 * @throws VerticeInexistenteException
	 *             Cuando no existe un vértice con alguno de los nombres dados.
	 */
	public boolean existeCamino(T vertice1, T vertice2) {
		List<T> adyacentes = this.getAdyacentes(vertice1);
		Set<T> buscados = new HashSet<T>();
		for(T unAdyacente : adyacentes){
			if(unAdyacente.equals(vertice2)) return true;
			else{
				boolean buscar = false;
				if(!buscados.contains(vertice2)){
					buscados.add(vertice2);
					buscar=true;
				}
				if ( buscar && existeCamino(unAdyacente, vertice2)) return true;
			}
		}
		return false;
	}


	/**
	 * Determina si existe un camino entre dos vértices.
	 * 
	 * @param vertice1
	 *            El nombre del vértice inicial del camino.
	 * @param vertice2
	 *            El nombre del vértice final del camino.
	 * @return <code>true</code> si existe un camino entre los vértices,
	 *         <code>false</code> en caso contrario.
	 * @throws VerticeInexistenteException
	 *             Cuando no existe un vértice con alguno de los nombres dados.
	 */
	public boolean existeCaminoIterativo(T vertice1, T vertice2) {
		List<T> adyacentes = this.getAdyacentes(vertice1);
		Set<T> buscados = new HashSet<T>();
		for(T unAdyacente : adyacentes){
			if(unAdyacente.equals(vertice2)) return true;
			else{
				if ( existeCamino(unAdyacente, vertice2,buscados)) return true;
			}
		}
		return false;
	}
	
	private boolean existeCamino(T vertice1, T vertice2,Set<T> buscados) {
		boolean buscar = false;
		if(!buscados.contains(vertice2)){
			buscados.add(vertice2);
			buscar = true;
		}
		
		List<T> adyacentes = this.getAdyacentes(vertice1);
		for(T unAdyacente : adyacentes){
			if(unAdyacente.equals(vertice2)) return true;
			else{
				if ( buscar && existeCamino(unAdyacente, vertice2,buscados)) return true;
			}
		}
		return false;
	}
	
	/**
	 * Retorna una lista de todos los caminos de como máximo "n" <code>saltos<code>
	 * que permiten ir desde el vertice1 a vertices cuyo nombre contiene el caracter "patronNombre".
	 * 
	 * @param vertice1
	 *            El nombre del vértice inicial del camino.
	 * @param patronNombre
	 *            Permite establecer un patrón de busqueda 
	 * @param saltos
	 *            Cantidad de saltos minimos.
	 * @return una lista donde cada entrada es una lista que contiene los nombres de los vertices que conforman un camino encontrado. 
	 *
	 * @throws VerticeInexistenteException
	 *             Cuando no existe un vértice con alguno de los nombres dados.
	 */
	public List<List<String>> caminoMenorIgualNSaltos(String vertice1, char patronNombre,int saltos)
			throws VerticeInexistenteException {
		// TODO TAREA EXTRA NO OBLIGATORIA - Implementar este método.
		// TIP: para ver si un caracter está presente en un string puede usar el método de string
		// indexOf(char), que retorna -1 si no está presente, de lo contrario retorna la posición
		// donde dicho caracter está presente comenzando con 0
		return null;
	}
}

