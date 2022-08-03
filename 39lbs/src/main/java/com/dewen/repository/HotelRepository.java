package com.dewen.repository;

import com.dewen.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * 这里的用法其实和SpringDataJPA相似, 可根据需要来自定义方法
 * Hotel, String 行对应的对象类型, 主键列类型
 */
public interface HotelRepository extends MongoRepository<Hotel, String> {
    // 可根据需求自己定义方法, 无需对方法进行实现
    List<Hotel> getAllByName(String name);

    // @Query(value = "{'name':?0}", fields = "{'name':1}")
    // @Query(value="{'_id':{'$ne':null}}",fields="{'name':1}")
    Page<Hotel> findByNameLike(String name, Pageable pageable);

    // @Query(value = "aggregate({ " +
    //         "    $geoNear:{ " +
    //         "        near: [115.999567,28.681813], " +
    //         "        spherical: true, // 计算球面距离 " +
    //         "        distanceMultiplier: 6378137, " +
    //         "        maxDistance: 2000/6378137, " +
    //         "        distanceField: \"distance\" " +
    //         "    } " +
    //         "})")
    // List<Hotel> adada();
}