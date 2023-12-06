package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.model.User;

@Repository
public class UserDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Transactional
	public void insertUser(User user) {
		try {
			this.hibernateTemplate.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public List<User> userLogin(User user) {
		List<User> user1 = null;
		try {
			List<?> resultList = hibernateTemplate.findByNamedParam(
					"FROM User WHERE email = :email AND password = :password", new String[] { "email", "password" },
					new Object[] { user.getEmail(), user.getPassword() });

			// The raw list needs to be cast to List<User>
			user1 = (List<User>) resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user1;
	}

	@Transactional
	public void updateProfile(User user) {
		try {
			hibernateTemplate.update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public boolean checkOldPassword(String op, int id) {
		boolean flag = false;
		try {
			List<?> resultList = hibernateTemplate.findByNamedParam("FROM User WHERE password = :password AND id = :id",
					new String[] { "password", "id" }, new Object[] { op, id });

			flag = !resultList.isEmpty();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Transactional
	public void updatePassword(String np, int id) {
		try {
			User user = hibernateTemplate.get(User.class, id);
			user.setPassword(np);
			hibernateTemplate.update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public List<User> getAllUser() {
		List<User> list = null;
		try {
			list = hibernateTemplate.loadAll(User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Transactional
	public User getUserById(int id) {
		User user = null;
		try {
			user = hibernateTemplate.get(User.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Transactional
	public void deleteUser(int id) {
		try {
			User user = hibernateTemplate.get(User.class, id);
			if (user != null) {
				hibernateTemplate.delete(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void updatePassword1(String email, String newPassword) {
		try {
			User user = hibernateTemplate.get(User.class, newPassword);
			user.setPassword(newPassword);
			hibernateTemplate.update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
