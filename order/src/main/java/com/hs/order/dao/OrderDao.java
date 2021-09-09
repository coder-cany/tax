package com.hs.order.dao;
import com.hs.common.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
@Mapper
public interface OrderDao {
    @Select("select * from order limit 100")
    List<Order> getOrders();
}
