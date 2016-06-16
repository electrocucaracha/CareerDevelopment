package com.electrocucaracha.apps.cdp.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.electrocucaracha.apps.cdp.models.HasId;

public abstract class BaseDAO<T extends HasId> {

	protected SessionFactory sessionFactory = null;
	private Class<T> typeOfT;

	@SuppressWarnings("unchecked")
	public BaseDAO() {
		typeOfT = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	public void create(T model) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<T> retrieve() {
		List<T> result = sessionFactory.openSession().createQuery("from " + this.typeOfT.getSimpleName()).list();

		return result;
	}

	@SuppressWarnings("unchecked")
	public T get(int id) {
		T result = (T) sessionFactory.openSession()
				.createQuery("from " + this.typeOfT.getSimpleName() + " where id = :id").setInteger("id", id)
				.uniqueResult();

		return result;
	}

	private void delete(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.createQuery("delete " + this.typeOfT.getSimpleName() + " where id = :id").setParameter("id", id)
				.executeUpdate();
		session.getTransaction().commit();
	}

	public void delete(T model) {
		this.delete(model.getId());
	}

	public void update(T model) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(model);
		session.getTransaction().commit();
		session.close();
	}

}
