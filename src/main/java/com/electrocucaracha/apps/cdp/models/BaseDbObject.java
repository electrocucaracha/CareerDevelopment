package com.electrocucaracha.apps.cdp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public abstract class BaseDbObject implements HasId {

	protected long id;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
