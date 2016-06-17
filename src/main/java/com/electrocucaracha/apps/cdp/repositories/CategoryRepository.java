package com.electrocucaracha.apps.cdp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.electrocucaracha.apps.cdp.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
