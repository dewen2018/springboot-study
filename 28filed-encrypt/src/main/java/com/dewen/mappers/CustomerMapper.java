package com.dewen.mappers;

import com.dewen.entity.Customer;
import com.dewen.typehandler.Encrypt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {

    int addCustomer(@Param("phone") Encrypt phone, @Param("address") String address, @Param("state") int state);

    List<Customer> findCustomer(@Param("phone") Encrypt phone);
}
