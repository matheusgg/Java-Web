package deltaspike.data;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@SessionScoped
public class EntityManagerProducer implements Serializable {

	private static final long serialVersionUID = -3086950208549324669L;

	@PersistenceUnit
	private EntityManagerFactory emf;

	@Produces
	@RequestScoped
	public EntityManager create() {
		return this.emf.createEntityManager();
	}

	public void destroy(@Disposes EntityManager em) {
		if (em.isOpen()) {
			em.close();
		}
	}

}
