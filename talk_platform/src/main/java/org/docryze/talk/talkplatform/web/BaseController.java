package org.docryze.talk.talkplatform.web;

import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.*;
import org.docryze.talk.talkmodel.exception.CommonException;
import org.docryze.talk.talkmodel.exception.LogoutException;
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

}
