package modelo;

import java.util.List;

public class Carrito {

	int idCarrito;	
	String cliente;
	List<AlimentoCarrito> alimentos;	
	int total;
	
	public Carrito(int idCarrito, String cliente, List<AlimentoCarrito> alimentos, int total) {
		this.idCarrito = idCarrito;
		this.cliente = cliente;
		this.alimentos = alimentos;
		this.total = total;
	}
	
	public int getId() {
		return idCarrito;
	}

	
	public String getCliente() {
		return cliente;
	}
	
	public List<AlimentoCarrito>  getAlimentos() {
		return alimentos;
	}

	public int getTotal() {
        return total;
    }
}
