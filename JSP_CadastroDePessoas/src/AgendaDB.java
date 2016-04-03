import java.sql.*;

public class AgendaDB {
	private String nome = blank;
	private String endereco = blank;
	private String cidade = blank;
	private String telefone = blank;

	private String result_busca = blank;
	private String result_inserir = blank;

	public static final String BUSCA_INVALIDA = "failure";
	public static final String BUSCA_VALIDA = "success";
	public static final String SUCESSO_INSERCAO = "success";
	public static final String FALHA_INSERCAO = "failure";

	static Connection con = null;
	static Statement stm = null;
	static ResultSet rs;
	static private String blank = "";

	public AgendaDB() {
		if (con == null) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				con = DriverManager
						.getConnection("jdbc:sqlserver://localhost:1433;databaseName=People;integratedSecurity=true");
				System.out.println("Conectado!");
			} catch (SQLException e) {
				System.err.println("Erro: " + e);
				con = null;
			} catch (ClassNotFoundException e) {
				System.out.println("ClassNotFound...");
				e.printStackTrace();
			}
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String inserir() {
		String result_inserir = FALHA_INSERCAO;
		try {
			stm = con.createStatement();
			stm.execute("INSERT INTO pessoa(nome,endereco,cidade,telefone) VALUES ('"
					+ nome
					+ "','"
					+ endereco
					+ "','"
					+ cidade
					+ "','"
					+ telefone + "')");
			stm.close();
			result_inserir = SUCESSO_INSERCAO;
			nome=endereco=cidade=telefone="";
		} catch (SQLException e) {
			System.err.println("Erro: " + e);
			result_inserir = FALHA_INSERCAO;
		}
		return result_inserir;
	}

	public String buscar() throws SQLException {
		String result_busca = BUSCA_INVALIDA;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM pessoa WHERE nome = '" + nome
					+ "'");
			if (rs.next()) {
				nome = rs.getString(1);
				endereco = rs.getString(2);
				cidade = rs.getString(3);
				telefone = rs.getString(4);
				result_busca = BUSCA_VALIDA;
			} else {
				result_busca = BUSCA_INVALIDA;
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			System.err.println("Erro: " + e);
		}
		return result_busca;
	}
	
	public String limpar(){
		nome=endereco=cidade=telefone="";
		return "clean";
	}
}
