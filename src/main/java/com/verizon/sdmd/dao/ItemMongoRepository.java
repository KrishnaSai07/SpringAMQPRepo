package com.verizon.sdmd.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.verizon.sdmd.model.Item;

@Repository
public interface ItemMongoRepository extends MongoRepository<Item, Integer>  {

}
