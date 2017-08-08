package org.docryze.talk.talkplatform.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.*;
import org.docryze.talk.baseapi.IBaseUserService;
import org.docryze.talk.talkmodel.exception.CommonException;
import org.docryze.talk.talkmodel.exception.LogoutException;
import org.docryze.talk.talkmodel.table.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * BaseController
 */
@Api(tags="BaseController")
@RestController
@RequestMapping(value = "/login",method = RequestMethod.POST)
public class BaseController{
    /**
     * 通过注解引用服务
     */
    @Reference(interfaceClass = IBaseUserService.class, interfaceName="baseUserService",version="1.0.0",timeout=3000)
    private IBaseUserService baseUserService;

    @ApiOperation(value="base", notes="测试访问")
    @RequestMapping(value = "user",method = RequestMethod.POST)
    public String base(@ApiParam(required=true, name="name", value="姓名") @RequestParam("name") String name) throws CommonException{
        /*
        异常捕获测试
        if(true){
            throw new LogoutException("this is common exception");
        }*/
        return name;
    }

    @ApiOperation(value="base", notes="测试dubbo调用")
    @RequestMapping(value = "query",method = RequestMethod.POST)
    public User queryUserInfo(@ApiParam(required = true, name="id", value="用户id" ) @RequestParam("id") String id){
        return baseUserService.selectUserById(id);
    }

}
