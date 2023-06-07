package objetos;

public class Usuario {

	private int idUsuario;
	private String nick;
	private String email;
	private String paswd;

	public Usuario(String nick, String email, String paswd) {
		super();
		this.nick = nick;
		this.email = email;
		this.paswd = paswd;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPaswd() {
		return paswd;
	}

	public void setPaswd(String paswd) {
		this.paswd = paswd;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nick=" + nick + ", email=" + email + ", paswd=" + paswd + "]";
	}

}
