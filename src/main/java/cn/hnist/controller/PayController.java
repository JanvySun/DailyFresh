package cn.hnist.controller;

import cn.hnist.pojo.OrderInfo;
import cn.hnist.pojo.ResultInfo;
import cn.hnist.service.OrderService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 支付控制器
 */
@Controller
@RequestMapping("/user")
public class PayController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/alipay/{ID}")
    public void aliPay(@PathVariable("ID") String order_id, HttpServletResponse response)
            throws IOException {

        AlipayClient alipayClient = new
                DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                "2016092900626932",
                "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCqfE6Pj0dEZa+HICntfWLigeNsurd3LDJF9HsVEttA40lmQC42PNIsBIoeMYDSy85n8Gekv/1Ks0Dc0Rauw4L3jFsigk3PXck3X0OnI5z+lbkYkGdHRP2VCdbZpe1P9lZH0KifD49DVhvhAYgE2RofYHQ7oPrVolI5gQNFmj6pHVqwDElmIsojiXIcvyQl58MMm+G/AmxW2yWpbT1v053IeCKALkMc5ZwUrFLPYx69sISx5447Ex98BsqJFINaRGysWf2ekBjshk1Ur+eIFldJMzY5z6zTEp+HchRd4Vzaa/Khop2WGRJ55QsPPSrsPpbuNh143FFLiAEWTbXP28Z7AgMBAAECggEAPPMwCvcIXZWVCVQgaT14is4xBoBCcyKj3oqnLkONi0c2yI2Nd+qKoR4GWwELs1EVu+cgxq+02G35dHh0CYrwWZweQNohA0/eTiOKxkhfKk1Q3U1S/xHVLuFTpIBDtWC2O/qHLkaby1EPqoOno0QSqMENDPxicNoVV2WeS9X3LPIxqxuY4t77LjltLA50JYVxKwercl4yk8x+8hMX7lsEJGZy0L3rMDVx3xEd2QLsQ9Io0VG/ACTQDqfzWa92Oowp5RNIlMM/1rjGlPdHziovfbAkx0jKaeNK5V5MWqRAHxFDfX2dMtsY7xDQgzGpgDxz+5D6A0txnz+C/agiKm4pcQKBgQDkp8em+XbNkPTdSlBvJYVOY2NzUy+4c+vT4NYhArIbQykf1Gk64OyOEoSSolXLbt57NgC2LGVqsAh47G1UN+As7Qb5OwEMx8a6D0Yw/i3iMBEQ2kEujE/OkoUVdSnrvY4epIAhmb1kks4fXB6+RHXhXjvWs+g/XL+LqsGUVRLTdwKBgQC+36ysZ+jSqyLCWJaFDsouYPetfJDZ7nB1ZYiDfOXekbFEXi+Vuggyj9AoFRfuhE9wyR8nZ4KiE0D34+eVd/udgI0xGSdJOJr68P+4iIgMeHqkj1/MU1RM6vKzxT5fIsBjDDj8FDe2cfa8oTKyIAQZ+UBtUFr2b1mInZOxkVU+HQKBgENbkZsEnhTMgPW3cJ5tZSnEaYWNmd8yduRBtZKf4qvZSwIPZrhrjgQEPH2dyLxW+r89F1STG4UkOjq1GzfWBfn2ETnWQebr6wTgfwLrvcSF+GEIim8V8mSeEQpTK3hArN8mAoDjc0FvZb0jPAM806UCR4pmESKpRdv0/3Wc4iDdAoGAFh9+uAzjI2fKg5QY/hsUvANpMaZ6c4f7aN/3zqWvTvi7/xybj1McmB+rFLo9J9wWCRCGl96beYWZxiGYOe7KLxszQjmUIBx7ehJs0Uqh2jAspt4nUFtoLfIdZ1C5RfWVLuW/xRfizTawTnSwl4y2bPxCx2jE4kNNsVPyRmZ95tECgYBQMzB0401HIWNsR6j8ej9M2x1+/f2Pj0dE+yQpW71H9RAVGqQjjZgfy5wlUYPejSRVTEunzprEWos2b2pWJVm/dKt2F3oIFb5Qs4Yuzj/vSWHw+41RjhniMMwIImfEfVHuiIEXKCh5G9wLFXROSd5ULXxriycF3/rE4BsK3Hg3HA==",
                "json",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoGaKMmnTIzQCdIthV2PTKguhR35kaMYIi+bK/8ocW1BWH9EzgvwQmTUl5C4fzPYNOgewVVjhzqOAgJzUIk+jZMPCIyL2swSpFtIDizQTQltVT7jG7P0ykgspPxPCRbQWjhsJcgwAfhAVbwPL447yb9aqDwkiXhieABq1zc+Isp7SmEJSi01Dq/yo9QGfERsKX1toyYvqnFZMOHJ+uMenVQ9qOFK05J+nQ3s+/DHzZY4fDpPi7dJtTH24l0hG3cLgBuJE8HT8dNiaE+hPSvd9i7QfK2dJiOzDoBEHm6ry9yv0i73tLng9F2/t6e8iJpBG1zltwb7MT8Sx+J/Ceqr31QIDAQAB",
                "RSA2");

        OrderInfo orderInfo = orderService.findOrderInfoById(order_id);

        String out_trade_no = order_id;
        out_trade_no = URLDecoder.decode(out_trade_no, "UTF-8");
        String total_amount = String.valueOf(orderInfo.getTotal_price() + orderInfo.getTransit_price());
        total_amount = URLDecoder.decode(total_amount, "UTF-8");
        String subject = String.format("天天生鲜%s", order_id);
        subject = URLDecoder.decode(subject, "UTF-8");
        String body = "";
        body = URLDecoder.decode(body, "UTF-8");

        //创建API对应的request
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl("http://localhost/user/order");
        alipayRequest.setNotifyUrl("http://localhost/payOk");
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"" + out_trade_no + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":" + total_amount + "," +
                "    \"subject\":\"" + subject + "\"," +
                "    \"body\":\"" + body + "\"" +
                "    }" +
                "  }");
        //填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * ajax响应订单支付状态查询
     */
    @RequestMapping(value = "/order/check", method = RequestMethod.POST)
    public ResultInfo check(String order_id, HttpSession session) throws AlipayApiException, UnsupportedEncodingException, InterruptedException {
        ResultInfo info = new ResultInfo();

        AlipayClient alipayClient = new
                DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                "2016092900626932",
                "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCqfE6Pj0dEZa+HICntfWLigeNsurd3LDJF9HsVEttA40lmQC42PNIsBIoeMYDSy85n8Gekv/1Ks0Dc0Rauw4L3jFsigk3PXck3X0OnI5z+lbkYkGdHRP2VCdbZpe1P9lZH0KifD49DVhvhAYgE2RofYHQ7oPrVolI5gQNFmj6pHVqwDElmIsojiXIcvyQl58MMm+G/AmxW2yWpbT1v053IeCKALkMc5ZwUrFLPYx69sISx5447Ex98BsqJFINaRGysWf2ekBjshk1Ur+eIFldJMzY5z6zTEp+HchRd4Vzaa/Khop2WGRJ55QsPPSrsPpbuNh143FFLiAEWTbXP28Z7AgMBAAECggEAPPMwCvcIXZWVCVQgaT14is4xBoBCcyKj3oqnLkONi0c2yI2Nd+qKoR4GWwELs1EVu+cgxq+02G35dHh0CYrwWZweQNohA0/eTiOKxkhfKk1Q3U1S/xHVLuFTpIBDtWC2O/qHLkaby1EPqoOno0QSqMENDPxicNoVV2WeS9X3LPIxqxuY4t77LjltLA50JYVxKwercl4yk8x+8hMX7lsEJGZy0L3rMDVx3xEd2QLsQ9Io0VG/ACTQDqfzWa92Oowp5RNIlMM/1rjGlPdHziovfbAkx0jKaeNK5V5MWqRAHxFDfX2dMtsY7xDQgzGpgDxz+5D6A0txnz+C/agiKm4pcQKBgQDkp8em+XbNkPTdSlBvJYVOY2NzUy+4c+vT4NYhArIbQykf1Gk64OyOEoSSolXLbt57NgC2LGVqsAh47G1UN+As7Qb5OwEMx8a6D0Yw/i3iMBEQ2kEujE/OkoUVdSnrvY4epIAhmb1kks4fXB6+RHXhXjvWs+g/XL+LqsGUVRLTdwKBgQC+36ysZ+jSqyLCWJaFDsouYPetfJDZ7nB1ZYiDfOXekbFEXi+Vuggyj9AoFRfuhE9wyR8nZ4KiE0D34+eVd/udgI0xGSdJOJr68P+4iIgMeHqkj1/MU1RM6vKzxT5fIsBjDDj8FDe2cfa8oTKyIAQZ+UBtUFr2b1mInZOxkVU+HQKBgENbkZsEnhTMgPW3cJ5tZSnEaYWNmd8yduRBtZKf4qvZSwIPZrhrjgQEPH2dyLxW+r89F1STG4UkOjq1GzfWBfn2ETnWQebr6wTgfwLrvcSF+GEIim8V8mSeEQpTK3hArN8mAoDjc0FvZb0jPAM806UCR4pmESKpRdv0/3Wc4iDdAoGAFh9+uAzjI2fKg5QY/hsUvANpMaZ6c4f7aN/3zqWvTvi7/xybj1McmB+rFLo9J9wWCRCGl96beYWZxiGYOe7KLxszQjmUIBx7ehJs0Uqh2jAspt4nUFtoLfIdZ1C5RfWVLuW/xRfizTawTnSwl4y2bPxCx2jE4kNNsVPyRmZ95tECgYBQMzB0401HIWNsR6j8ej9M2x1+/f2Pj0dE+yQpW71H9RAVGqQjjZgfy5wlUYPejSRVTEunzprEWos2b2pWJVm/dKt2F3oIFb5Qs4Yuzj/vSWHw+41RjhniMMwIImfEfVHuiIEXKCh5G9wLFXROSd5ULXxriycF3/rE4BsK3Hg3HA==",
                "json",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoGaKMmnTIzQCdIthV2PTKguhR35kaMYIi+bK/8ocW1BWH9EzgvwQmTUl5C4fzPYNOgewVVjhzqOAgJzUIk+jZMPCIyL2swSpFtIDizQTQltVT7jG7P0ykgspPxPCRbQWjhsJcgwAfhAVbwPL447yb9aqDwkiXhieABq1zc+Isp7SmEJSi01Dq/yo9QGfERsKX1toyYvqnFZMOHJ+uMenVQ9qOFK05J+nQ3s+/DHzZY4fDpPi7dJtTH24l0hG3cLgBuJE8HT8dNiaE+hPSvd9i7QfK2dJiOzDoBEHm6ry9yv0i73tLng9F2/t6e8iJpBG1zltwb7MT8Sx+J/Ceqr31QIDAQAB",
                "RSA2");

        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();

        String out_trade_no = order_id;
        out_trade_no = URLDecoder.decode(out_trade_no, "UTF-8");
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + out_trade_no + "\"," +
                "  }");
        while (true) {
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            String code = response.getCode();
            String tradeStatus = response.getTradeStatus();
            if ("40004".equals(code)) {
                Thread.sleep(3000);
            }
            if ("10000".equals(code)) {
                break;
            }
        }

        info.setFlag(true);
        return info;
    }

}
