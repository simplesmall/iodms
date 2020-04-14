package fun.kwan.iodms.mapper;

import fun.kwan.iodms.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 简小
 * @create 2020-04-06 14:10
 */
@Repository
public interface UserMapper {
    User Sel(int id);
    int Del(int id);
    User Upd(User user);

}
