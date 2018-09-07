import base.BaseTest;
import com.xiaoleilu.hutool.json.JSONUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DateTest extends BaseTest {

    @Test
    public void payDemo001(){
        Map<String,String> data = new HashMap<String,String>();
        data.put("amount", "10");
        data.put("callBackUrl", "http://www.1tx888.com/TXY/PlatformPay/bankingNotify.do");
        data.put("callBackViewUrl", "http://localhost/");
        data.put("charset", "UTF-8");
        data.put("goodsName", "充值");
        data.put("merchNo", "MJF201804240266");
        data.put("netwayCode", "WX");
        data.put("orderNum", "MJFbl1201808301601211601214444");
        data.put("randomNum", "cvCo");
        data.put("sign", "3DBE75D344B8FB83C1C5580D923C9CC2");
        data.put("version", "V3.0.0.0");

        Map<String,String> result = new LinkedHashMap<>();
        System.err.println(JSONUtil.toJsonStr(data));
        data.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        System.err.println("=========================start===================");
        System.err.println(JSONUtil.toJsonStr(result));

    }

}
