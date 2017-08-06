package org.docryze.talk.talkplatform.exception;


import org.docryze.talk.talkmodel.dto.ErrorInfo;
import org.docryze.talk.talkmodel.exception.CommonException;
import org.docryze.talk.talkmodel.exception.LoginException;
import org.docryze.talk.talkmodel.exception.LogoutException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 基于视图的异常处理
     * @param req   请求
     * @param e     异常
     * @return      视图模板
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    /**
     * 基于ajax请求的异常处理
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setUrl(req.getRequestURL().toString());
        if(e instanceof LogoutException){
            r.setData("this is logoutException");
        }
        if(e instanceof LoginException){
            r.setData("this is loginException");
        }
        return r;
    }


}
