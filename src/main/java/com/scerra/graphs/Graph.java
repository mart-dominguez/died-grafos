package com.scerra.graphs;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Interfaccia del grafo
 * @author Stefano Scerra
 *
 */
public interface Graph extends Cloneable
{
	/**
	 * Aggiunge un nodo al grafo
	 * @param n il nodo da aggiungere
	 */
	void addNode(Node n);

	/**
	 * Aggiunge un arco al grafo
	 * @param e l'arco da aggiungere
	 */
	void addEdge(Edge e);

	/**
	 * Restituisce il numero di nodi contenuti nel grafo
	 * @return numero di nodi nel grafo
	 */
	int numNodes();
	
	/**
	 * Restituisce il numero di archi contenuti nel grafo
	 * @return il numero di archi nel grafo
	 */
	int numEdges();
	
	/**
	 *  Restituisce gli archi uscenti dal nodo n
	 * @param n
	 * @return la lista degli archi uscenti da n
	 */
	List<Edge> adjacent(Node n);

	/**
	 *  Restituisce gli archi entranti nel nodo n
	 * @param n
	 * @return la lista degli archi entranti in n
	 */
	List<Edge> incident(Node n);
	
	/**
	 * Verifica se il grafo contiene il nodo indicato
	 * @param n 
	 * @return true se il grafo contiene il nodo, false altrimenti
	 */
	boolean containsNode(Node n);

	/**
	 * Verifica se il grafo contiene l'arco indicato
	 * @param e
	 * @return true se il grafo contiene l'arco, false altrimenti
	 */
	boolean containsEdge(Edge e);

	/**
	 * Restituisce il nodo con l'id specificato
	 * @param id identificativo del nodo cercato
	 * @return il nodo corrispondente all'id specificato
	 */
	Node getNode(int id);
	
	/**
	 * Restituisce una collezione contenente i nodi del grafo
	 * @return collezione di nodi del grafo
	 */
	Collection<Node> getNodes();
	
	/**
	 * Restituisce l'insieme degli id di tutti i nodi del grafo
	 * @return l'insieme di ID dei nodi del grafo
	 */
	Set<Integer> getNodesIDs();
	
	/**
	 * Imposta un nodo come nodo sorgente
	 * @param node
	 */
	void setSource(Node node);
	
	/**
	 * Restituisce il nodo sorgente
	 * @return il nodo sorgente
	 */
	Node getSource();
	
	/**
	 * Imposta un nodo come nodo pozzo
	 * @param node
	 */
	void setSink(Node node);
	
	/**
	 * Restituisce il nodo pozzo
	 * @return il nodo pozzo
	 */
	Node getSink();

	/**
	 * Metodo di convenienza per restituire tutti gli archi del grafo
	 * @return tutti gli archi del grafo
	 */
	List<Edge> getEdges();

	/**
	 * Rimuove un nodo dal grafo
	 * @param n
	 */
	void removeNode(Node n);

	/**
	 * Rimuove un arco dal grafo
	 * @param e
	 */
	void removeEdge(Edge e);

	/**
	 * Azzera  il grafo eliminando tutti i nodi e tutti gli archi
	 */
	void clear();

	/**
	 * Clona il grafo
	 * @return il clone del grafo
	 */
	Object clone();

	/**
	 * Restituisce il sottografo di G indotto da S e dai nodi source e sink
	 * @param s insieme di id di nodi
	 * @return un sottografo (nuova istanza)
	 */
	Graph getSubGraph(Set<Integer> s);
	
	

}