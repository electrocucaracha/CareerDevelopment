package com.electrocucaracha.apps.cdp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.electrocucaracha.apps.cdp.models.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

}
