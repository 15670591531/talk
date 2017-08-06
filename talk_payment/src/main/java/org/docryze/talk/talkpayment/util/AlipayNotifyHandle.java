package org.docryze.talk.talkpayment.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import org.docryze.talk.talkpayment.talkpay.ITalkPayNotify;

import java.util.Map;

/**
 * 异步通知的处理
 */
public class AlipayNotifyHandle {
    public String notifyHandle(ITalkPayNotify talkPayNotify){
        Map<String, String> paramsMap = null; //将异步通知中收到的所有参数都存放到map中
        boolean signVerified = false; //调用SDK验证签名
        String log = "";
        try {
            signVerified = AlipaySignature.rsaCheckV1(paramsMap, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);

            if(signVerified){
                // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
                talkPayNotify.notifyHandle();
                log = "";
            }else{
                // TODO 验签失败则记录异常日志，并在response中返回failure.
                log = "";
                return "failure";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }finally {
            AlipayConfig.logResult(log);
        }
        return "success";
    }
}
