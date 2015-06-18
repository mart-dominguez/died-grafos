package com.scerra.graphs;

/**
 * Classe che rappresenta un nodo del grafo
 * @author Stefano Scerra
 *
 */
public class Node
{
	final int id;
	String label = "";

	/**
	 * Inizializza un oggetto nodo con un dato ID
	 * @param id
	 */
	public Node(int id)
	{
		this.id = id;
	}
	
	/**
	 * Inizializza un oggetto nodo con un dato ID ed una data etichetta
	 * @param id
	 * @param label
	 */
	public Node(int id, String label)
	{
		this.id = id;
		this.label = label;
	}
	
	/**
	 * Costruttore per copia
	 * @param n Nodo
	 */
	public Node(Node n)
	{
		id = n.getId();
		if(n.label != null) label = new String(n.label);
	}
	
	/**
	 * Restituisce l'ID del nodo
	 * @return l'ID del nodo
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Restituisce l'etichetta del nodo
	 * @return l'etichetta del nodo
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * Imposta una data etichetta per il nodo
	 * @param label
	 */
	public void setLabel(String label)
	{
		this.label = label;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof Node)) return false;
		Node n = (Node)obj;
		return n.id == id;
	}

	@Override
	public int hashCode()
	{
		return id;
	}
	
	@Override
	public String toString()
	{
		return "Node [id = " + id + ", label = " + label + "]";
	}	
	
	
}
