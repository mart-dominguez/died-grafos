package ar.edu.utn.frsf.isi.died.guias.grafos;

public class Vertice<T extends Comparable<T>> implements Comparable<T>{

	private T valor;
	
	public Vertice(){	}
	
	public Vertice(T v){
		this.valor = v;
	}
	
	public void setValor(T v){
		this.valor = v;
	}
	
	public T getValor(){
		return this.valor;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Vertice) return this.valor.equals( ((Vertice<?>) obj).valor );
		return false;
	}
	
	@Override
	public String toString() {
		return valor.toString();
	}
	
	@SuppressWarnings("unchecked")
	public int compareTo(T obj) {
		if(obj instanceof Vertice) return this.valor.compareTo( (T) ((Vertice<?>) obj).valor);
		return 0;
	};
}
