/****************************************************************** 
 *
 * Powered By tianxia-online. 
 *
 * Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 * http://www.d-telemedia.com/ 
 *
 * Package: com.sky.integral.mall.service.impl 
 *
 * Filename: IntegralMallServiceImpl.java 
 *
 * Description: 积分商城服务实现类
 *
 * Copyright: Copyright (c) 2018-2020 
 *
 * Company: 天下网络科技 
 *
 * @author: Elephone
 *
 * @version: 1.0.0
 *
 * Create at: 2018年08月29日 16:18 
 *
 * Revision: 
 *
 * 2018/8/29 16:18 
 * - first revision 
 *
 *****************************************************************/
package com.sky.integral.mall.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sky.integral.mall.mapper.IntegralMallMapper;
import com.sky.integral.mall.service.IntegralMallService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName IntegralMallServiceImpl
 * @Description 积分商城服务实现类
 * @Author Elephone
 * @Date 2018年08月29日 16:18
 * @Version 1.0.0
 **/
@Service
public class IntegralMallServiceImpl implements IntegralMallService {

    @Autowired
    private IntegralMallMapper integralMallMapper;
    @Override
    public JSONArray getTypeByCagentName(String cagentName,List<Map<String,Object>> resultList) {

        Map<Integer,List<Map<String,Object>>> showDataMap=new HashMap<Integer,List<Map<String,Object>>>();
        Map<Integer,String> goodsName=new HashMap<Integer,String>();
        for(Map<String,Object> dataMap:resultList){
            if("0".equals(dataMap.get("pid")+"")){
                // 父节点，直接添加
                List<Map<String,Object>> dataList=new ArrayList<Map<String,Object>>();
                showDataMap.put(Integer.parseInt(dataMap.get("id")+""), dataList);
            }else{
                // 子节点，添加到相对应父节点下子节点的集合
                showDataMap.get(Integer.parseInt(dataMap.get("pid")+"")).add(dataMap);
            }
            goodsName.put(Integer.parseInt(dataMap.get("id")+""), dataMap.get("catename")+"");
        }
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=null;
        for(Map.Entry<Integer,List<Map<String,Object>>> entry:showDataMap.entrySet()){
            jsonObject=new JSONObject();
            jsonObject.put("typeName",goodsName.get(entry.getKey()));
            jsonObject.put("data",entry.getValue());
            jsonArray.add(jsonObject);
        }
       return jsonArray;
    }

    @Override
    public List<Map<String, Object>> orderHistory(Map<String, Object> paramMap) {
        return integralMallMapper.orderHistory(paramMap);
    }

    @Override
    public int countOrderHistory(Map<String, Object> paramMap) {
        return integralMallMapper.countOrderHistory(paramMap);
    }

    @Override
    public List<Map<String, Object>> rankingList(Map<String, Object> paramMap) {
        return integralMallMapper.rankingList(paramMap);
    }

    @Override
    public List<Map<String, Object>> selectTypeByCagentName(String cagentName) {
        return integralMallMapper.selectTypeByCagentName(cagentName);
    }
    @Override
    public int countGoodsByCondition(Map<String, Object> paramMap) {
        return integralMallMapper.countGoodsByCondition(paramMap);
    }

    @Override
    public List<Map<String, Object>> selectGoodsByCondition(Map<String, Object> paramMap) {
        return integralMallMapper.selectGoodsByCondition(paramMap);
    }

    @Override
    public List<Map<String, Object>> selectTypeByCondition(Map<String, Object> paramMap) {
        return integralMallMapper.selectTypeByCondition(paramMap);
    }

    @Override
    public Map<String, Object> goodsDetails(Map<String, Object> paramMap) {
        return integralMallMapper.goodsDetails(paramMap);
    }

    @Override
    public JSONObject generateOrder(Integer uid,Map<String,Object> paramMap) {
        Map<String,Object> queryMap=new HashMap<String,Object>();
        queryMap.put("uid", uid);
        Map<String,Object> resultMap= integralMallMapper.selectUserById(queryMap);
        String cagentName=resultMap.get("cagent").toString();
        paramMap.put("cagentName", cagentName);

        JSONObject jsonObject=new JSONObject();
        // 输入数量不能为负数
        String paraNum = paramMap.get("num").toString();
        if (StringUtils.isBlank(paraNum)||Integer.parseInt(paraNum)<1) {
            jsonObject.put("status", "error");
            jsonObject.put("msg", "兑换数量不能为空和负数！！！");
            return jsonObject;
        }

        //根据uid查询用户剩余积分
        queryMap.clear();
        queryMap.put("uid", uid);
        queryMap.put("type", "1");

        Map<String,Object> userWallet= integralMallMapper.selectUserWallet(queryMap);
        if(null==userWallet){
            jsonObject.put("status", "error");
            jsonObject.put("msg", "用户积分数据不存在！！！");
            return jsonObject;
        }

        //根据商品id查询商品兑换需要的积分
        queryMap.clear();
        queryMap.put("id",paramMap.get("id"));
        Map<String,Object> goodsMsg= integralMallMapper.goodsDetails(paramMap);
        if(null==goodsMsg){
            jsonObject.put("status", "error");
            jsonObject.put("msg", "不存在该商品！！！");
            return jsonObject;
        }
        //判断库存
        if(Integer.parseInt(paraNum)>Integer.parseInt(goodsMsg.get("num")+"")){
            jsonObject.put("status", "error");
            jsonObject.put("msg", "商品库存不足！！！");
            return jsonObject;
        }
        //计算兑换积分*兑换数量是否大于用户剩余积分
        double price=Double.parseDouble(goodsMsg.get("price")+"");//兑换积分
        // 兑换积分不能为负数
        if (price<1) {
            jsonObject.put("status", "error");
            jsonObject.put("msg", "兑换积分不能为空和负数！！！");
            return jsonObject;
        }

        int num=Integer.parseInt(paraNum);//兑换数量
        double balance=Double.parseDouble(userWallet.get("balance")+"");//剩余积分

        if(price*num>balance){
            jsonObject.put("status", "error");
            jsonObject.put("msg", "积分不足！！！");
            return jsonObject;
        }

        paramMap.put("balance", userWallet.get("balance"));
        paramMap.put("walletId", userWallet.get("id"));
        paramMap.put("price", goodsMsg.get("price"));
        paramMap.put("uid", uid);
        paramMap.put("pluId", paramMap.get("id"));
        paramMap.put("type", "OUT");
        paramMap.put("wtype", "1");
        paramMap.put("_err", "0");

        if("1".equals(integralMallMapper.generatorOrder(paramMap))){
            jsonObject.put("status", "error");
            jsonObject.put("msg", "系统异常！！！");
            return jsonObject;
        }
		/*

		//将积分扣除，存入冻结积分
		userWallet.put("balance", -num*price);
		userWallet.put("frozen_balance", num*price);
		integralShopService.updateWallet(userWallet);

		//生成订单
		paramMap.put("uid", uid);
		integralShopService.insertOrder(paramMap);

		//记录积分流水
		queryMap.clear();
		queryMap.put("uid", uid);
		queryMap.put("type", "OUT");
		queryMap.put("wtype", "1");
		queryMap.put("amount", num*price);
		queryMap.put("old_money", balance);
		queryMap.put("new_money", balance-(num*price));
		queryMap.put("upuid", uid);
		queryMap.put("rmk", "兑换商品");
		integralShopService.insertWalletLog(queryMap);
		*/

        jsonObject.put("status", "success");
        jsonObject.put("msg", "兑换成功！！！");
        return jsonObject;
    }
}
