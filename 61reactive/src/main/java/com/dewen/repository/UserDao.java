package com.dewen.repository;

import com.dewen.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface UserDao extends ReactiveCrudRepository<UserEntity, Long> {

    @Query("SELECT * FROM user WHERE id = :id")
    Flux<UserEntity> findDateById(Long id);

    @Query("UPDATE user SET name = :name WHERE id = :id")
    Mono<Integer> updateNameById(String name, Long id);

    // 按名称查找
    Flux<UserEntity> findByName(String name);

    // 查找给定范围内的
    // findBy<fieldName>GreaterThan
    Flux<UserEntity> findByIdGreaterThan(Long startId);

    // 查询名称以给定字符串开头的数据
    Flux<UserEntity> findByNameStartingWith(String start);

    // 分页
    Flux<UserEntity> findByIdGreaterThanEqual(Long startId, Pageable pageable);

    Mono<Integer> deleteByName(String name);

    @Query("select  id,name from delivery_company where id in  (:ids)")
    Flux<UserEntity> findByIds2(List<Long> ids);

    @Query("select  id,name from delivery_company where name = :name")
    Flux<UserEntity> findByName2(String name);

    @Modifying
    @Query("update delivery_company set name = :name where id = :id")
    Mono<UserEntity> update2(@Param("id") long id, @Param("name") String name);
}
