package fun.kwan.iodms.service;

import fun.kwan.iodms.entity.User;
import fun.kwan.iodms.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 简小
 * @create 2020-04-06 14:08
 */
@Service
public class UserService  implements UserMapper{
    @Autowired
    UserMapper userMapper;

    @Override
    public User Sel(int id) {
        return userMapper.Sel(id);
    }

    @Override
    public int Del(int id) {
        return userMapper.Del(id);
    }

    @Override
    public User Upd(User user) {
        return userMapper.Upd(user);
    }
}
