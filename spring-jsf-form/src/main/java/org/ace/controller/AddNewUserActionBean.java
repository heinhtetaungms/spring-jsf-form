package org.ace.controller;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.ace.ds.User;
import org.ace.repo.UserRepo;

@ManagedBean
public class AddNewUserActionBean {

	private User user;
	private boolean isNew; // false
	String selectedUserId;
	public static final String USER_ID = "USER_ID";

	@ManagedProperty(value = "#{userRepo}")
	private UserRepo repo;

	@PostConstruct
	public void init() {

		initialize();
		createUser();
	}

	public void initialize() {
		selectedUserId = (String) getParam(USER_ID);
		if (selectedUserId == null) {
			isNew = true;
		} else {
			isNew = false;
		}
	}

	public void createUser() {
		if (isNew) {
			user = new User();
		} else {
			user = repo.findUserById(Integer.parseInt(selectedUserId));
		}
	}

	public String updateUser() {
		repo.update(user);
		return "index.xhtml?faces-redirect=true";
	}

	public String addUser() {
		user.setId(repo.getInputId());
		repo.setInputId(repo.getInputId() + 1);
		repo.addUser(user);
		return "index.xhtml?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserRepo getRepo() {
		return repo;
	}

	public void setRepo(UserRepo repo) {
		this.repo = repo;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
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
