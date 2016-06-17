package com.electrocucaracha.apps.cdp.dao;

import static com.electrocucaracha.apps.cdp.dao.HibernateUtil.getSessionFactory;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;

import com.electrocucaracha.apps.cdp.models.BaseDbObject;

public abstract class BaseDAO<T extends BaseDbObject> {

	private Class<T> typeOfT;

	@SuppressWarnings("unchecked")
	public BaseDAO() {
		typeOfT = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void create(T model) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<T> retrieve() {
		List<T> result = getSessionFactory().openSession().createQuery("from " + this.typeOfT.getSimpleName()).list();

		return result;
	}

	@SuppressWarnings("unchecked")
	public T get(long id) {
		T result = (T) getSessionFactory().openSession()
				.createQuery("from " + this.typeOfT.getSimpleName() + " where id = :id").setLong("id", id)
				.uniqueResult();

		return result;
	}

	private void delete(long id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.createQuery("delete " + this.typeOfT.getSimpleName() + " where id = :id").setParameter("id", id)
				.executeUpdate();
		session.getTransaction().commit();
	}

	public void delete(T model) {
		this.delete(model.getId());
	}

	public void update(T model) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.update(model);
		session.getTransaction().commit();
		session.close();
	}

}
