package org.docryze.talk.baseprovider.mapper;

import org.docryze.talk.baseprovider.Application;
import org.docryze.talk.baseprovider.mapper.BaseUserMapper;
import org.docryze.talk.talkmodel.table.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BaseUserMapperTest {
    @Autowired
    private BaseUserMapper baseUserMapper;

    @Test
    public void test() {
        User user = baseUserMapper.selectUserById("1");
        System.out.println(user.getId() + "---" + user.getName());
    }
}
