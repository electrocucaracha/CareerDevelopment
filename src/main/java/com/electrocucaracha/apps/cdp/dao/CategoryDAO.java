package com.electrocucaracha.apps.cdp.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.electrocucaracha.apps.cdp.models.Category;

public class CategoryDAO extends BaseDAO<Category> {

	public void update(Category model) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query<?> query = session.createQuery("update Category set category = :category where id = :id");
		query.setParameter("category", model.getCategory());

		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
}
