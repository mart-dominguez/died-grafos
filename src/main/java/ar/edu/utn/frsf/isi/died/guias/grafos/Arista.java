package ar.edu.utn.frsf.isi.died.guias.grafos;

public class Arista<T extends Comparable<T>> {

	private Vertice<T> inicio;
	private Vertice<T> fin;
	private Double valor;
	private Double flujo;


	public Arista(){
		valor=0.0;
	}
	
	public Arista(Vertice<T> ini,Vertice<T> fin){
		this();
		this.inicio = ini;
		this.fin = fin;
	}

	public Arista(Vertice<T> ini,Vertice<T> fin,Double val){
		this(ini,fin);
		this.valor= val;
	}
	
	public Vertice<T> getInicio() {
		return inicio;
	}
	
	public void setInicio(Vertice<T> inicio) {
		this.inicio = inicio;
	}
	
	public Vertice<T> getFin() {
		return fin;
	}
	
	public void setFin(Vertice<T> fin) {
		this.fin = fin;
	}

	public Number getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Double getFlujo() {
		return flujo;
	}

	public void setFlujo(Double flujo) {
		this.flujo = flujo;
	}
	
	@Override
	public String toString() {
		return "( "+this.inicio.getValor()+" --> "+this.fin.getValor()+" )";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Arista<?>) && ((Arista<?>)obj).getValor().equals(this.valor); 
	}
}
