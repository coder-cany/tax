package com.hs.order.dao;

import com.hs.common.entity.Business;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
@Mapper
public interface BusinessDao {

    @Select("select * from task limit 100")
    List<Business> getBusiness();

    @Insert("insert into task (order_id,service,pre_person,next_person,creat_time)" +
            " values (#{orderId},#{service},#{account},#{nextPerson},#{now})")
    void createBusiness(String orderId, String service, int account, String nextPerson, LocalDateTime now);
}
