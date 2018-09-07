package com.sky.integral.mall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sky.integral.mall.service.IntegralMallService;
import com.sky.integral.mall.vo.GoodTypeReq;
import com.sky.integral.mall.vo.GoodTypeResp;
import com.sky.integral.mall.vo.base.MallResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("mall")
@Api(description="积分商城相关类接口")
public class IntegralMallController {
    private static final Logger logger = LoggerFactory.getLogger("IntegralMallController");
    @Autowired
    private IntegralMallService integralMallService;

    /**
     * 功能描述:获取积分商城,商品类型
     *
     * @Author: Elephone
     * @Date: 2018年08月29日 16:38:48
     * @param
     * @return: java.lang.Object
     **/
    @RequestMapping(value = "/goodsType",method = RequestMethod.POST)
    @ApiOperation(value = "获取积分商城商品类型",notes = "通过平台的代理号查询商品类型")
    public Object selectGoodsType(HttpServletRequest request,@ApiParam(required = true,name = "req",value = "请求报文") @RequestBody GoodTypeReq req){
        // 1,初始化响应报文
        GoodTypeResp resp = new GoodTypeResp();
        resp.setApiPath("/mall/goodsType");
        // 2,请求报文合规校验
        String  res = checkReqParams(request,req);
        if (StringUtils.isNotBlank(res)) {
            resp.setRetCode(MallResp.RETCODE_ERROR);
            resp.setRetMsg("参数错误");
            resp.setData(res);
            logger.error("响应报文：respStr="+JSON.toJSONString(resp));
            return JSON.toJSONString(resp);
        }
        // 3，组装响应报文
        try {
            List<Map<String,Object>> resultList= integralMallService.selectTypeByCagentName(req.getCagentName());
            if(resultList == null || resultList.size() == 0){
                resp.setRetCode(MallResp.RETCODE_ERROR);
                resp.setRetMsg("该代理号没有商品类型");
                return JSON.toJSONString(resp);
            }
            JSONArray jsonArray = integralMallService.getTypeByCagentName(req.getCagentName(),resultList);
            resp.setRetCode(MallResp.RETCODE_SUCCESS);
            resp.setRetMsg("OK");
            resp.setData(jsonArray);
            logger.info("响应报文：respStr="+JSON.toJSONString(resp));
        }catch (Exception e){
            resp.setRetCode(MallResp.RETCODE_ERROR);
            resp.setRetMsg("系统异常");
            logger.error("请求报文处理异常, 原因：" + e.getMessage());
        }
        return JSON.toJSONString(resp);
    }

    /**
     * 功能描述: 请求参数合规校验
     *
     * @Author: Elephone
     * @Date: 2018年08月30日 17:59:57
     * @param request
     * @param req
     * @return: java.lang.String
     **/
    private String checkReqParams(HttpServletRequest request,GoodTypeReq req) {
        logger.info("请求参数：reqStr=" + JSON.toJSONString(req));

        String respStr = null;
        String reqString = req.getCagentName();
        if (StringUtils.isBlank(reqString)) {
            respStr = "cagentName 参数错误";
            return respStr;
        }
        // 查询数据库，看看是不是我们的代理商
        return respStr;
    }

    /**
     * 功能描述: 获取积分商城商品列表
     *
     * @Author: Elephone
     * @Date: 2018年09月04日 10:09:25
     * @param request
     * @param paramMap
     * @return: java.lang.Object
     **/
    @RequestMapping(value = "/goodsList",method = RequestMethod.POST)
    @ApiOperation(value = "获取积分商城商品列表",notes = "通过平台的代理号以及父子商品类型查询积分商城商品列表")
    @ResponseBody
    public Object selectGoodsList(HttpServletRequest request,@RequestParam Map<String,Object> paramMap){
        if(paramMap.containsKey("pageNo")){
            paramMap.put("from", (Integer.parseInt(paramMap.get("pageNo")+"")-1)*Integer.parseInt(paramMap.get("pageSize")+""));
            paramMap.put("pageSize",Integer.parseInt(paramMap.get("pageSize")+""));
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", integralMallService.countGoodsByCondition(paramMap));
        jsonObject.put("result", integralMallService.selectGoodsByCondition(paramMap));
        return  jsonObject;
    }

    /**
     * 功能描述: 获取子/父商品类型
     *
     * @Author: Elephone
     * @Date: 2018年09月04日 10:14:55
     * @param paramMap
     * @return: java.lang.Object
     **/
    @RequestMapping(value = "/typeSearch",method = RequestMethod.POST)
    @ApiOperation(value = "获取子/父商品类型",notes = "通过平台的代理号以及父子商品类型获取子/父商品类型")
    @ResponseBody
    public Object selectTypeForSearch(@RequestParam Map<String,Object> paramMap){
        return integralMallService.selectTypeByCondition(paramMap);
    }

    /**
     * 功能描述: 获取积分商城商品详情
     *
     * @Author: Elephone
     * @Date: 2018年09月04日 10:21:22
     * @param paramMap
     * @return: java.lang.Object
     **/
    @RequestMapping(value = "/goodsDetails",method = RequestMethod.POST)
    @ApiOperation(value = "获取积分商城商品详情",notes = "通过商品id获取积分商城商品详情")
    @ResponseBody
    public Object goodsDetails(@RequestParam Map<String,Object> paramMap){
        return integralMallService.goodsDetails(paramMap);
    }

    /**
     * 功能描述: 生成商城兑换订单
     *
     * @Author: Elephone
     * @Date: 2018年09月04日 10:27:48
     * @param request
     * @param paramMap
     * @return: java.lang.Object
     **/
    @RequestMapping(value = "/generateOrder",method = RequestMethod.POST)
    @ApiOperation(value = "生成商城兑换订单",notes = "通过商品id收货人姓名等等生成商城兑换订单")
    @ResponseBody
    public Object generateOrder(HttpServletRequest request,@RequestParam Map<String,Object> paramMap){
        HttpSession session = request.getSession();
        Integer uid = Integer.parseInt(session.getAttribute("uid").toString());

        JSONObject jsonObject = integralMallService.generateOrder(uid,paramMap);
        return jsonObject;
    }

    /**
     * 功能描述: 积分兑换记录
     *
     * @Author: Elephone
     * @Date: 2018年09月04日 11:06:45
     * @param request
     * @param paramMap
     * @return: java.lang.Object
     **/
    @RequestMapping(value = "/orderHistory",method = RequestMethod.POST)
    @ApiOperation(value = "积分兑换记录",notes = "通过开始和结束时间以及页码来查询积分兑换记录")
    @ResponseBody
    public Object orderHistory(HttpServletRequest request,@RequestParam Map<String,Object> paramMap){
        HttpSession session = request.getSession();
        Integer uid = Integer.parseInt(session.getAttribute("uid").toString());
        paramMap.put("uid", uid);

        if(paramMap.containsKey("pageNo")){
            paramMap.put("from", (Integer.parseInt(paramMap.get("pageNo")+"")-1)*Integer.parseInt(paramMap.get("pageSize")+""));
            paramMap.put("pageSize",Integer.parseInt(paramMap.get("pageSize")+""));
        }

        net.sf.json.JSONObject jsonObject=new net.sf.json.JSONObject();
        jsonObject.put("result", integralMallService.orderHistory(paramMap));
        jsonObject.put("total", integralMallService.countOrderHistory(paramMap));
        return  jsonObject;
    }
    /**
     * 功能描述: 兑换排行榜
     *
     * @Author: Elephone
     * @Date: 2018年09月04日 11:14:33
     * @param paramMap
     * @return: java.lang.Object
     **/
    @RequestMapping(value = "/rankingList",method = RequestMethod.POST)
    @ApiOperation(value = "兑换排行榜",notes = "直接查询兑换排行榜")
    @ResponseBody
    public Object rankingList(@RequestParam Map<String,Object> paramMap){
        return integralMallService.rankingList(paramMap);
    }
}
