package cl.pipec.applocalizar.clases;

public class GeoDatos {
	int id;
	double latitude;
	double longitude;
	String ip;
	String fecha;
	
	public GeoDatos(double latitude, double longitude, String ip) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.ip = ip;
	}
	
	public GeoDatos() {
		
	}

	/****************/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/********/
	@Override
	public String toString() {
		return "GeoDatos [id=" + id + ", latitude=" + latitude + ", longitude="
				+ longitude + ", ip=" + ip + ", fecha=" + fecha + "]";
	}
}
