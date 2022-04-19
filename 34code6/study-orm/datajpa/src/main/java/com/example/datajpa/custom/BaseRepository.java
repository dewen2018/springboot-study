package com.example.datajpa.custom;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface BaseRepository<T,ID extends Serializable> extends JpaRepository<T,ID> {
    List<Object[]> listBySQL(String sql);
}
