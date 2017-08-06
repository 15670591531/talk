package org.docryze.talk.talkpayment.talkpay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.docryze.talk.talkpayment.util.AlipayConfig;

import static com.alipay.api.AlipayConstants.*;
/**
 * 电脑网站支付
 */
public class WindowWebPay {
    /**
     *  生成支付表单
     *          直接将完整的表单html输出到页面
     *          httpResponse.setContentType("text/html;charset=" + CHARSET);
     *          httpResponse.getWriter().write(form);
     *          httpResponse.getWriter().flush();
     *          httpResponse.getWriter().close();
     * @param returnUrl             页面跳转同步通知页面路径
     * @param notifyUrl             服务器异步通知页面路径
     * @param bizContentJson        业务参数
     * @return                       支付表单form
     */
    public String generatePayForm(String returnUrl,String notifyUrl,String bizContentJson){
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, AlipayConfig.format, AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl(returnUrl);//"http://domain.com/CallBack/return_url.jsp"
        alipayRequest.setNotifyUrl(notifyUrl);//在公共参数中设置回跳和通知地址"http://domain.com/CallBack/notify_url.jsp"
//        alipayRequest.setBizContent("{" +
//                "    \"out_trade_no\":\"20150320010101001\"," +
//                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
//                "    \"total_amount\":88.88," +
//                "    \"subject\":\"Iphone6 16G\"," +
//                "    \"body\":\"Iphone6 16G\"," +
//                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
//                "    \"extend_params\":{" +
//                "    \"sys_service_provider_id\":\"2088511833207846\"" +
//                "    }"+
//                "  }");
        alipayRequest.setBizContent(bizContentJson);//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;
//        httpResponse.setContentType("text/html;charset=" + CHARSET);
//        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
//        httpResponse.getWriter().flush();
//        httpResponse.getWriter().close();
    }
}
