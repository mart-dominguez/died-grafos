package com.scerra.graphs;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Semplice implementazione di un grafo orientato e pesato per problemi di flusso con liste di adiacenza e incidenza 
 * @author Stefano Scerra
 *
 */
public class FlowGraph implements Graph
{
	// id nodo -> lista di archi uscenti
	private Map<Integer, LinkedList<Edge>> adjacencies = new HashMap<Integer, LinkedList<Edge>>();
	// id nodo -> lista di archi entranti
	private Map<Integer, LinkedList<Edge>> incidences = new HashMap<Integer, LinkedList<Edge>>();
	// id nodo -> nodo
	private Map<Integer, Node> nodes = new HashMap<Integer, Node>();
	// nodo sorgente
	private Node source;
	// nodo pozzo
	private Node sink;
	
	@Override
	public void addNode(Node n)
	{
		if(containsNode(n)) throw new IllegalArgumentException("Nodo " + n + " già esistente");
		nodes.put(n.id, n);
		adjacencies.put(n.id, new LinkedList<Edge>());
		incidences.put(n.id, new LinkedList<Edge>());
	}
	
	@Override
	public void addEdge(Edge e)
	{
		if(!containsNode(e.source) || !containsNode(e.dest))
			throw new IllegalArgumentException("Impossibile inserire l'arco " + e);
		List<Edge> adjacent = adjacencies.get(e.source.id);
		List<Edge> incident = incidences.get(e.dest.id);
		adjacent.add(e);
		incident.add(e);
	}	
	
	@Override
	public void setSource(Node node)
	{
		source = node;
	}

	@Override
	public Node getSource()
	{
		return source;
	}

	@Override
	public void setSink(Node node)
	{
		sink = node;		
	}

	@Override
	public Node getSink()
	{
		return sink;
	}

	@Override
	public int numNodes()
	{
		return nodes.size();
	}

	@Override
	public int numEdges()
	{
		int numEdges = 0;
		
		for(List<Edge> adjList : adjacencies.values())
		{
			numEdges += adjList.size();
		}
		
		return numEdges;
	}	
	
	/**
	 *  Restituisce gli archi uscenti dal nodo n
	 * @param n
	 */
	@Override
	public List<Edge> adjacent(Node n)
	{
		return adjacencies.get(n.id);
	}
	
	@Override
	public boolean containsNode(Node n)
	{
		return nodes.containsKey(n.id);
	}
	
	@Override
	public boolean containsEdge(Edge e)
	{
		List<Edge> adjList = adjacencies.get(e.source.id);
		return adjList.contains(e);
	}
	
	@Override
	public Node getNode(int id)
	{
		return nodes.get(id);
	}

	@Override
	public Collection<Node> getNodes()
	{
		return nodes.values();
	}
	
	@Override
	public Set<Integer> getNodesIDs()
	{
		return new HashSet<Integer>(nodes.keySet());
	}

	/**
	 * metodo di convenienza per restituire tutti
	 * ma proprio tutti gli archi del grafo
	 * @return lista degli archi del grafo
	 */
	@Override
	public List<Edge> getEdges()
	{
		List<Edge> edges = new LinkedList<Edge>();
		for(List<Edge> adjList: adjacencies.values())
		{
			edges.addAll(adjList);
		}
		
		return edges;
	}
	
	@Override
	public void removeNode(Node n)
	{
		nodes.remove(n.id);
		adjacencies.remove(n.id);
		incidences.remove(n.id);
		
		for(List<Edge> adjList : adjacencies.values())
		{
			Iterator<Edge> it = adjList.iterator();
			while(it.hasNext())
			{
				Edge e = it.next();
				if(e.dest.equals(n))
				{
					it.remove();
					break; // non è mica un multigrafo...
				}
			}
		}
		
		for(List<Edge> incList : incidences.values())
		{
			Iterator<Edge> it = incList.iterator();
			while(it.hasNext())
			{
				Edge e = it.next();
				if(e.source.equals(n))
				{
					it.remove();
					break; // non è mica un multigrafo...
				}
			}
		}
	}
	
	@Override
	public void removeEdge(Edge e)
	{
		List<Edge> adjList = adjacencies.get(e.source.id);
		List<Edge> incList = incidences.get(e.dest.id);
		adjList.remove(e);
		incList.remove(e);
	}
	
	@Override
	public void clear()
	{
		nodes.clear();
		adjacencies.clear();
		incidences.clear();
	}
	
	/**
	 *  Restituisce gli archi entranti nel nodo n
	 * @param n
	 */
	@Override
	public List<Edge> incident(Node n)
	{
		return incidences.get(n.id);
	}
	
	@Override
	public Object clone()
	{
		FlowGraph graph = new FlowGraph();
		for(Node n : getNodes())
		{ // inizializza le strutture dati relative ai nodi nel nuovo grafo
			Node clonedNode = new Node(n);
			graph.adjacencies.put(n.id, new LinkedList<Edge>());
			graph.incidences.put(n.id, new LinkedList<Edge>());
			graph.nodes.put(n.id, clonedNode);
			
			if(n.equals(source))
			{
				graph.setSource(clonedNode);
			}
			else if(n.equals(sink))
			{
				graph.setSink(clonedNode);
			}
		}
		
		
		for(Node n : getNodes())
		{
			LinkedList<Edge> clonedAdjList = graph.adjacencies.get(n.id);
			
			// riempi le liste di adiacenza e di incidenza
			for(Edge e : adjacent(n))
			{
				Node clonedSrc = graph.nodes.get(e.source.id);
				Node clonedDest = graph.nodes.get(e.dest.id);
				Edge clonedEdge = new Edge(clonedSrc, clonedDest, e.cap, e.flow);
				clonedAdjList.add(clonedEdge);
				LinkedList<Edge> clonedIncList = graph.incidences.get(e.dest.id);
				clonedIncList.add(clonedEdge);
			}
		}
		
		
		return graph;
	}
	
	/**
	 * Restituisce il sottografo indotto dall'insieme di nodi s
	 * @param s insieme di id di nodi
	 * @return un nuovo sottografo
	 */
	@Override
	public Graph getSubGraph(Set<Integer> s)
	{
		FlowGraph subGraph = new FlowGraph();
		
		for(int n : s)
		{
			Node node = nodes.get(n);
			Node clonedNode = new Node(node);
			subGraph.addNode(clonedNode);
		}
		
		if(source != null)
		{
			Node clonedSource = new Node(source);
			subGraph.addNode(clonedSource);
			subGraph.setSource(clonedSource);
		}
		if(sink != null)
		{
			Node clonedSink = new Node(sink);
			subGraph.addNode(clonedSink);
			subGraph.setSink(clonedSink);
		}
		
		for(int n : subGraph.getNodesIDs())
		{
			LinkedList<Edge> clonedAdjList = subGraph.adjacencies.get(n);
			Node node = nodes.get(n);
			// riempi le liste di adiacenza e di incidenza
			for(Edge e : adjacent(node))
			{
				if(subGraph.containsNode(e.dest))
				{
					Node clonedSrc = subGraph.nodes.get(e.source.id);
					Node clonedDest = subGraph.nodes.get(e.dest.id);
					Edge clonedEdge = new Edge(clonedSrc, clonedDest, e.cap, e.flow);
					clonedAdjList.add(clonedEdge);
					LinkedList<Edge> clonedIncList = subGraph.incidences.get(e.dest.id);
					clonedIncList.add(clonedEdge);
				}
			}
		}

		return subGraph;
	}

	@Override
	public String toString()
	{
		return "Adiacenze: " + adjacencies.toString() + "\nIncidenze: " + incidences.toString();
	}
	
}
