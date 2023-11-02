package com.example.tech.interview.dao.repo;

import com.example.tech.interview.dao.entity.AccountEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

    @Query(nativeQuery = true, value = "select * from accounts where id = ?1")
    AccountEntity findByAccountId(Long accountId);



}
