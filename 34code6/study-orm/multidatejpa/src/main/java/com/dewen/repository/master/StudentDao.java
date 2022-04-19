package com.dewen.repository.master;
import com.dewen.entity.master.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student, Integer> {


}
