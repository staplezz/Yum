package modelo;

import java.util.ArrayList;
import java.util.List;
import modelo.Alimento; 

public class Categoria {
	private int id; 
	private String nombre; 
	private List<Alimento> listaAlimentos = new ArrayList<Alimento>(); 
	
	
	public Categoria(String nombre){ 
		this.nombre = nombre; 
	}

	//getters and setters 

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getNombre(){
		return nombre;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public List<Alimento> getListaAlimentos(){
		return listaAlimentos;
	}
	
	public void setListaAlimentos(List<Alimento> listaAlimentos) {
		this.listaAlimentos = listaAlimentos;
	}

}
