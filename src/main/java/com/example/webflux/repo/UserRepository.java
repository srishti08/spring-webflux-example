package com.example.webflux.repo;

import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;

import com.example.webflux.entity.User;

@ViewIndexed(designDoc = "test", viewName="testView")
public interface UserRepository extends ReactiveCouchbaseRepository<User, String>{

}
