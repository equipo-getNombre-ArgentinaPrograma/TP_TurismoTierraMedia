package objetosDeEntrada;

public interface PuedeSerComprada extends Comparable<PuedeSerComprada>{

	public double getPrecio();

	public double getTiempoDeRealizacion();

	public String getTipoDeAtraccion();

	public void usarCupos();

	public boolean hayCupos();

	public int compareTo(PuedeSerComprada otro);
	
	
}
