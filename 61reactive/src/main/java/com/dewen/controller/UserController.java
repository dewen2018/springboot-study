package com.dewen.controller;

import com.dewen.entity.UserEntity;
import com.dewen.repository.UserDao;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author dewen
 * @date 2022/12/29 14:36
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 返回字符串
     *
     * @return String
     */
    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Hello, WebFlux !");
    }

    @GetMapping("/list")
    public Flux<UserEntity> list() {
        return userDao.findAll();
    }

    @GetMapping("/save")
    public Mono<UserEntity> save() {
        UserEntity user = new UserEntity();
        user.setName("dewen");
        user.setMail("xxx@qq.com");
        user.setPhone("110");
        return userDao.save(user);
    }

    @GetMapping("/{id}")
    public Mono<UserEntity> get(@PathVariable Long id) {
        return userDao.findById(id);
    }

    @GetMapping("/findAllById")
    public Flux<UserEntity> findAllById(Long... ids) {
        return userDao.findAllById(Flux.fromArray(ids));
    }


    @DeleteMapping
    public Mono<Void> del(Long... ids) {
        return userDao.deleteById(Flux.fromArray(ids));
    }


    @Autowired
    ConnectionFactory connectionFactory;

    @GetMapping("/wnmtest")
    public void get() {
        Mono.from(connectionFactory.create())
                .flatMap(connection -> Mono.from(connection.createStatement("INSERT INTO `user`(`name`, `phone`, `mail`) VALUES ( ?name, ?phone, ?mail)")
                                .bind("name", "dewen")
                                .bind("phone", "110")
                                .bind("mail", "xxx@qq.com")
                                .returnGeneratedValues("id")
                                .execute())
                        .map(result -> result.map((row, meta) -> row.get("id")))
                        .flatMap(id -> Mono.from(id))
                        .flatMap(id -> Mono.fromCallable(() -> {
                            //其他业务逻辑
                            System.out.println("写入的ID：" + id);
                            int a = 1 / 0;
                            return id;
                        }))
                        .delayUntil(i -> connection.commitTransaction())
                        .doOnError(e -> {
                            System.out.println("异常回滚事物：" + e.getMessage());
                            connection.rollbackTransaction();
                        })
                ).subscribe(id -> System.out.println("写入：" + id));
    }

    @GetMapping("/wnmtest2")
    public void wnmtest2() {
        Mono.from(connectionFactory.create())
                .flatMap(connection -> Mono.from(
                        connection.createStatement("SELECT id,name,phone,mail FROM user WHERE name = ?name")
                                .bind("name", "dewen")
                                .execute())

                ).flatMapMany(result -> Flux.from(result.map((row, meta) -> {
                    UserEntity entity = new UserEntity();
                    entity.setId(Long.parseLong(String.valueOf(row.get("id"))));
                    entity.setName(String.valueOf(row.get("name")));
                    entity.setPhone(String.valueOf(row.get("phone")));
                    entity.setMail(String.valueOf(row.get("mail")));
                    return entity;
                }))).subscribe(u -> System.out.println(u.toString()));
    }

    // @Autowired
    // DatabaseClient databaseClient;
    //
    // @GetMapping("/wnmtest3")
    // public void wnmtest3() {
    //     UserEntity entity = new UserEntity();
    //     entity.setName("dewen");
    //     entity.setPhone("110");
    //     entity.setMail("xxx@qq.com");
    //
    //     databaseClient.insert()
    //             .into(UserEntity.class)
    //             .using(entity)
    //             .fetch()
    //             .all()
    //             .subscribe(map -> System.out.println("写入ID：" + map.get("LAST_INSERT_ID")));
    //
    //     databaseClient.select().
    //             from(UserEntity.class)
    //             .matching(Criteria.where("name").is("dewen").and(Criteria.where("phone").is("110")))
    //             .orderBy(Sort.Order.desc("id"))
    //             .fetch()
    //             .all()
    //             .subscribe(System.out::println);
    //
    //     databaseClient.update()
    //             .table("user")
    //             .using(Update.update("name", "dewen2").set("phone", "1110"))
    //             .matching(Criteria.where("name").is("dewen").and(Criteria.where("phone").is("110")))
    //             .fetch()
    //             .rowsUpdated()
    //             .subscribe(i -> System.out.println("更新个数：" + i));
    //
    //     databaseClient.delete()
    //             .from("user")
    //             .matching(Criteria.where("name").is("dewen"))
    //             .fetch()
    //             .rowsUpdated()
    //             .subscribe(i -> System.out.println("删除个数：" + i));
    //
    // }


}
