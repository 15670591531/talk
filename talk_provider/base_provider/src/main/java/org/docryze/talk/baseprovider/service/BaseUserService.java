package org.docryze.talk.baseprovider.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.docryze.talk.baseapi.IBaseUserService;
import org.docryze.talk.baseprovider.mapper.BaseUserMapper;
import org.docryze.talk.talkmodel.table.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通过注解来注册服务
 */
@Service(interfaceClass=IBaseUserService.class,interfaceName = "baseUserService", version="1.0.0",timeout=2000,retries=2,loadbalance="random",actives=5)
public class BaseUserService implements IBaseUserService {
    @Autowired
    private BaseUserMapper baseUserMapper;

    @Override
    public User selectUserById(String id) {

        User user = new User();
        user.setId(6);
        user.setName("dubbo");

        return user;

//        return baseUserMapper.selectUserById(id);
    }
}
