package org.ace.repo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;

import org.ace.ds.User;
import org.springframework.stereotype.Service;

@ApplicationScoped
@Service(value = "userRepo")
public class UserRepo {
	private int inputId = 1;
	private List<User> list;

	@PostConstruct
	public void init() {
		list = new ArrayList<>();
	}

	public List<User> getUserList() {
		return list;
	}

	public void addUser(User user) {
		list.add(user);
	}

	public void delete(int id) {
		User user = null;
		for (User u : list) {
			if (u.getId() == id) {
				user = u;
			}
		}
		list.remove(user);
	}

	public int getInputId() {
		return inputId;
	}

	public void setInputId(int inputId) {
		this.inputId = inputId;
	}

	public User findUserById(int id) {
		User user = null;
		for (User u : list) {
			if (u.getId() == id) {
				user = u;
			}
		}

		return user;
	}

	public void update(User user) {
		int index = list.indexOf(user);
		list.set(index, user);
	}

}
