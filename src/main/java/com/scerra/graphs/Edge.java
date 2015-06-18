package com.scerra.graphs;

/**
 * Classe che rappresenta un arco del grafo
 * @author Stefano Scerra
 *
 */
public class Edge
{
	final Node source; // nodo sorgente
	final Node dest; // nodo destinazione
	double cap = 0.0d; // capacità dell'arco
	double flow = 0.0d; // flusso su quest'arco
	
	/**
	 * Inizializza un nuovo arco tra i due nodi indicati e con una data capacità
	 * @param source
	 * @param dest
	 * @param cap
	 */
	public Edge(Node source, Node dest, double cap)
	{
		this.source = source;
		this.dest = dest;
		this.cap = cap;
	}
	
	/**
	 * Inizializza un nuovo arco tra i nodi indicati con una data capacità ed un dato valore di flusso
	 * @param source
	 * @param dest
	 * @param cap
	 * @param flow
	 */
	public Edge(Node source, Node dest, double cap, double flow)
	{
		this.source = source;
		this.dest = dest;
		this.cap = cap;
		this.flow = flow;
	}
	
	/**
	 *  Costruttore per copia
	 * @param e
	 */
	public Edge(Edge e)
	{
		source = e.source;
		dest = e.dest;
		cap = e.cap;
		flow = e.flow;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == null) return false;
		if(!(o instanceof Edge)) return false;
		Edge e = (Edge)o;
		return e.source.equals(source) && e.dest.equals(dest) && e.flow == flow && e.cap == cap;
	}

	@Override
	public String toString()
	{
		return "(" + source.id + ", " + dest.id + ") [" + flow + " / " + cap + "]";
	}	
	
	/**
	 * Restituisce il flusso dell'arco
	 * @return il flusso
	 */
	public double getFlow()
	{
		return flow;
	}

	/**
	 * Imposta il flusso dell'arco
	 * @param flow
	 */
	public void setFlow(double flow)
	{
		if(flow > cap) throw new IllegalArgumentException("Impossibile assegnare un flusso maggiore "
				+ "della capacità dell'arco");
		this.flow = flow;
	}

	/**
	 * Restituisce il nodo da cui l'arco esce
	 * @return il nodo sorgente
	 */
	public Node getSource()
	{
		return source;
	}

	/**
	 * Restituisce il nodo in cui l'arco entra
	 * @return nodo destinazione
	 */
	public Node getDest()
	{
		return dest;
	}

	/**
	 * Restituisce la capacita' dell'arco
	 * @return la capacita'
	 */
	public double getCap()
	{
		return cap;
	}
	
	/**
	 * Imposta una capacita' per l'arco
	 * @param cap
	 */
	public void setCap(double cap)
	{
		this.cap = cap;
	}
	
	/**
	 * Restituisce la capacita' residua dell'arco (capacita' - flusso)
	 * @return la capacita' residua
	 */
	public double getResidualCap()
	{
		return cap - flow;
	}

}