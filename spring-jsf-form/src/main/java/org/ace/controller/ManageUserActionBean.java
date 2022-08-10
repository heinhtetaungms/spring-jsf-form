package org.ace.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.ace.ds.User;
import org.ace.repo.UserRepo;

@ManagedBean
public class ManageUserActionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;
	private List<User> users;
	String selectedUserId;

	public static final String USER_ID = "USER_ID";

	@ManagedProperty(value = "#{userRepo}")
	private UserRepo repo;

	@PostConstruct
	public void init() {
		initialize();
		users = repo.getUserList();

	}

	public void initialize() {
		selectedUserId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedUserId");
	}

	public String editUser() {
		putParam(USER_ID, selectedUserId);
		return "index.xhtml?faces-redirect=true";
	}

	public String deleteUser(User user) {
		repo.delete(user.getId());
		return "index.xhtml?faces-redirect=true";
	}

	// unused
	public boolean globalFilterFunction(Object value, Object filter) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}
		int filterInt = Integer.valueOf(filterText);

		User u = (User) value;
		return u.getId() == filterInt || u.getName().toLowerCase().contains(filterText) || u.getPhone().toLowerCase().contains(filterText)
				|| u.getEmail().toLowerCase().contains(filterText) || u.getAddress().toLowerCase().contains(filterText);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public UserRepo getRepo() {
		return repo;
	}

	public void setRepo(UserRepo repo) {
		this.repo = repo;
	}

	protected static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	protected Map<String, Object> getSessionMap() {
		return getFacesContext().getExternalContext().getSessionMap();
	}

	protected void putParam(String key, Object obj) {
		getSessionMap().put(key, obj);
	}

	protected Object getParam(String key) {
		return getSessionMap().get(key);
	}

	protected boolean isExistParam(String key) {
		return getSessionMap().containsKey(key);
	}

	protected void removeParam(String key) {
		if (isExistParam(key)) {
			getSessionMap().remove(key);
		}
	}
}
