package objetosDeEntrada;

public interface Adquirible{

	public double getPrecio();

	public double getTiempoDeRealizacion();

	public String getTipoDeAtraccion();

	public void usarCupos();

	public boolean hayCupos();

	public boolean esPromocion();
	
	public void imprimirEnPantalla();
}
