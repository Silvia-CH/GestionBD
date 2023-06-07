package objetos;

public class PaginaWeb {

	private int idPaginaWeb;
	private String dominio;
	private String url;

	public PaginaWeb(String dominio, String url) {
		super();
		this.dominio = dominio;
		this.url = url;
	}

	public int getidPaginaWeb() {
		return idPaginaWeb;
	}

	public void setidPaginaWeb(int idPaginaWeb) {
		this.idPaginaWeb = idPaginaWeb;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	@Override
	public String toString() {
		return "PaginaWeb [idPaginaWeb=" + idPaginaWeb + ", dominio=" + dominio + ", url=" + url + "]";
	}

}