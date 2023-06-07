package objetos;

public class Usuario_has_PaginaWeb {

	private int id;
	private int Usuario_id;
	private int PaginaWeb_id;

	public Usuario_has_PaginaWeb(int id, int usuario_id, int paginaWeb_id) {
		super();
		this.id = id;
		Usuario_id = usuario_id;
		PaginaWeb_id = paginaWeb_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUsuario_id() {
		return Usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		Usuario_id = usuario_id;
	}

	public int getPaginaWeb_id() {
		return PaginaWeb_id;
	}

	public void setPaginaWeb_id(int paginaWeb_id) {
		PaginaWeb_id = paginaWeb_id;
	}

	@Override
	public String toString() {
		return "Usuario_has_PaginaWeb [id=" + id + ", Usuario_id=" + Usuario_id + ", PaginaWeb_id=" + PaginaWeb_id
				+ "]";
	}

}
