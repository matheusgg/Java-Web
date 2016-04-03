package deltaspike.data;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.User;

import org.omnifaces.util.Messages;

/**
 * http://deltaspike.apache.org/documentation/data.html
 * 
 * @author mggoes
 *
 */
@Named
@ViewScoped
public class DeltaSpikeMB implements Serializable {

	private static final long serialVersionUID = 4685684679510608861L;

	@Inject
	private UserRepository userRepository;

	public void testRepository() {
		try {
			// User user = this.userRepository.findById(1);
			// Messages.addInfo(null, "Usuário: {0} - {1}", user.getId(),
			// user.getNome());

			// User user = this.userRepository.findByNomeLike("%Gom%");
			// Messages.addInfo(null, "Usuário: {0} - {1}", user.getId(),
			// user.getNome());

			// Long count = this.userRepository.countByNomeLike("Matheus%");
			// Messages.addInfo(null, "Count: {0}", count);

			// List<User> users = this.userRepository.findAllNamedQuery();
			// Messages.addInfo(null, users.toString());

			// User user = this.userRepository.findByIdNamedQuery(1);
			// Messages.addInfo(null, user.getNome());

			// Integer modifications =
			// this.userRepository.updateLoginNamedQuery("matheus", 1);
			// Messages.addInfo(null, modifications.toString());

			// User user = this.userRepository.findOptionalByNomeLike("Teste");
			// Messages.addInfo(null, String.valueOf(user == null));

			// User user =
			// this.userRepository.findOptionalByNomeLikeJPQL("Teste");
			// Messages.addInfo(null, String.valueOf(user == null));

			// User user = this.userRepository.findAnyByNomeLike("Matheus%");
			// Messages.addInfo(null, String.valueOf(user == null));

			User user = this.userRepository.findByIdCriteria(1);
			Messages.addInfo(null, user.getNome());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
