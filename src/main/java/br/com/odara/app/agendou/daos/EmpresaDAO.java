package br.com.odara.app.agendou.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.odara.app.agendou.models.Empresa;

@Repository
public class EmpresaDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	public List<Empresa> listAll() {
		return manager.createQuery("select e from Empresa e", Empresa.class).getResultList();
	}

	public void save(Empresa empresa) {
		manager.persist(empresa);
	}

	@Override
	public UserDetails loadUserByUsername(String cnpj) throws UsernameNotFoundException {

		String jpql = "select e from Empresa where u.cnpj = :cnpj";

		List<Empresa> empresas = manager.createQuery(jpql, Empresa.class).setParameter("cnpj", cnpj).getResultList();

		if (empresas.isEmpty()) {
			throw new UsernameNotFoundException("A empresa " + cnpj + " não foi encontrada.");
		}

		return empresas.get(0);
	}

}
