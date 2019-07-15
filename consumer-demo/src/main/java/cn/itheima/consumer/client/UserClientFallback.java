package cn.itheima.consumer.client;

import cn.itheima.consumer.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public User queryById(Long id) {
        User user=new User();
        user.setName("未知用户!");
        return user;
    }
}
