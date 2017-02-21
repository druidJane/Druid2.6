package com.druid.dao;

import com.druid.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by druid on 20/02/17.
 */
@RepositoryRestResource(path="product")
public interface ProductRepostitory extends JpaRepository<Product,Long>{

}
