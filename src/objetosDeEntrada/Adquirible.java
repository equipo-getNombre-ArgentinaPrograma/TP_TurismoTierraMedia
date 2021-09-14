package objetosDeEntrada;

public interface Adquirible {

	public double getPrecio();

	public double getTiempoDeRealizacion();

	public String getTipoDeAtraccion();

	public boolean usarCupo();

	public boolean hayCupos();

	public boolean esPromocion();

	public void imprimirEnPantalla();
}
