package com.hs.order.mybatisprovider;

import com.hs.common.entity.User;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class UserUpdateProvider extends SQL {
    public String update(User user) {
        return new SQL() {
            {
                UPDATE("user");
                if (StringUtils.hasLength(user.getName())) {
                    SET("name=#{name}");
                }
                if (user.getRole() != null) {
                    SET("role=#{role}");
                }
                if (user.getSex() == 0 || user.getSex() == 1) {
                    SET("sex=#{sex}");
                }
                if (StringUtils.hasLength(user.getImg())) {
                    SET("img=#{img}");
                }
                WHERE("account=#{account,javaType=int}");
            }
        }.toString();
    }
}
