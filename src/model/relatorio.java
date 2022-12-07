package model;
import java.io.Serializable;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;

public class relatorio implements Serializable {
	private static final long serialVersionUID = 1L;
	private String qCriou;
	private String nome;
	private String descricao;
	private String data;
	private String nomeEmpresa;
	private String cnpj;
	private String informacoes;

	public relatorio() {
	}
	public relatorio(String nomeA, String descricaoA, String date){
		this.nome = nomeA;
		this.descricao = descricaoA;
		this.data = date;
	}
	public relatorio(String nomeA, String descricaoA, String dataA, String nomeEmpresaA, String cnpjA, String informacoesA, String qCriou){
		this.nome = nomeA;
		this.descricao = descricaoA;
		this.data = dataA;
		this.nomeEmpresa = nomeEmpresaA;
		this.cnpj = cnpjA;
		this.informacoes = informacoesA;
		this.qCriou = qCriou;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
	public String getDate() {
		return data;
	}
	public void setDate(String date) {
		this.data = date;
	}
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void String(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInformacoes() {
		return informacoes;
	}
	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}
	public String getqCriou() {
		return qCriou;
	}
	public void setqCriou(String qCriou) {
		this.qCriou = qCriou;
	}	
	public String getCnpjComP() {
		String aux = null;
		try {
			MaskFormatter mask = new MaskFormatter("###.###.###/####-##");
			mask.setValueContainsLiteralCharacters(false);
			aux = (mask.valueToString(cnpj));
		} catch (ParseException ex) {}
		return aux;
	}

	public String getAll() {
		String aux = "Nome do relatorio: " + nome +
				"\nCriado por:" + qCriou +
				"\nNome Empresa: " + nomeEmpresa +
				"\nCNPJ: " + getCnpjComP() +
				"\nData: " + data +
				"\nDescrição: " + descricao +
				"\nInformações: " + informacoes;
		return aux;
	}

}
