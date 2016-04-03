package datatable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import javax.faces.model.DataModelListener;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import model.Role;

@Named
@ViewScoped
public class DataTableMB implements Serializable {

	private static final long serialVersionUID = 2155446126762406682L;

	private DataModel<Role> roles;
	private DataModelListener dataModelListener;

	@PostConstruct
	void init() {
		List<Role> rolesList = new ArrayList<>();
		Role role = null;
		for (int i = 0; i < 10; i++) {
			role = new Role();
			role.setId(i + 1);
			role.setNome("Role " + i);
			rolesList.add(role);
		}

		this.roles = new ListDataModel<>(rolesList);
		this.dataModelListener = new CustomDataModelListener();
	}

	public void addDataModelListener() {
		this.roles.addDataModelListener(this.dataModelListener);
	}

	public void removeDataModelListener() {
		this.roles.removeDataModelListener(this.dataModelListener);
	}

	/**
	 * @return the roles
	 */
	public DataModel<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(DataModel<Role> roles) {
		this.roles = roles;
	}

}
