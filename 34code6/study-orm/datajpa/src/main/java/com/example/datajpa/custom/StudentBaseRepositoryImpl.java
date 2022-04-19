package com.example.datajpa.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * 第一个是基于原始的SQL来进行分组查询，第二个是基于Hibernate的HQL进行查询，
 * 最后一个是用Specification中的CriteriaQuery来进行处理，
 * 首先要解决的问题是StudentBaseRepositoryCustom没有实现Repository，该如何来执行SQL语句呢，
 * 我们可以给实现类注入另一个EntityManger，通过EntityManager来执行SQL语句。
 */

/**
 * @NoRepositoryBean，这个表示该接口不会创建这个接口的实例(我们原来定义的StudentPageRepository这些，
 * Spring Data JPA的基础组件都会自动为我们创建一个实例对象，加上这个annotation，spring data jpa的基础组件就不会
 * 再为我们创建它的实例)。
 */
@NoRepositoryBean
public class StudentBaseRepositoryImpl implements StudentBaseRepositoryCustom{
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> groupByStudentAsSql() {
        List<Object[]> list = entityManager.createNativeQuery("select address,count(*) from t_student group by address").getResultList();

        return list;
    }

    @Override
    public List<Object[]> groupByStudentAsHql() {
        List<Object[]> list = entityManager.createQuery("select address,count(*) from Student group by address").getResultList();
        return list;
    }

//    @Override
//    public List<Object[]> groupByStudentAsSpecification() {
//        //根据地址分组查询，并且学生数量大于3的所有地址
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
//        Root<Student> root = query.from(Student.class);
//        query.multiselect(root.get("address"),builder.count(root.get("id")))
//                .groupBy(root.get("address")).having(builder.gt(builder.count(root.get("id")),3));
//
//        return entityManager.createQuery(query).getResultList();
//    }
}
