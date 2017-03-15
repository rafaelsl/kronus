package br.com.odara.app.agendou.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(schema = "cadastro", name = "roles")
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="role_seq", sequenceName="role_seq", schema="cadastro", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="role_seq" )
	@Column(name = "seq_role")
	private Long id;

	@Column(name = "nome_role")
	private String nome;

	@Override
	public String getAuthority() {
		return nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
