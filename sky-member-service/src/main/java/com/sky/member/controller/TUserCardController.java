package com.sky.member.controller;

import java.io.IOException;
import java.util.*;

import com.sky.member.config.GlobalExceptionHandler;
import com.sky.member.service.TUserService;
import com.sky.member.utils.DESEncrypt;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.sky.member.entity.TUserCardEntity;
import com.sky.member.service.TUserCardService;
import com.sky.member.utils.PageUtils;
import com.sky.member.utils.Query;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.sky.member.controller.BaseController.addUserCard;


/**
 * 会员银行卡信息
 *
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 21:34:09
 */
@RestController
@RefreshScope
@Api(description = "会员银行卡信息")
@RequestMapping("/card")
public class TUserCardController {
    private final String deskey = "tianxia88";
    @Autowired
    private GlobalExceptionHandler exceptionHandle;
    @Autowired
    private TUserCardService tUserCardService;
    @Autowired
    private TUserService tUserService;

    @ApiOperation(value = "获取用户银行卡", notes = "获取用户银行卡接口")
    @RequestMapping(value = "/getUserCard", method = RequestMethod.GET)
    public JSONArray getUserCard(HttpServletRequest request) throws IOException {

        HttpSession session = request.getSession();
        String uid = session.getAttribute("uid").toString();
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("Ttime", System.currentTimeMillis());
        List<Map<String, String>> lm = tUserCardService.selectUserCard(map);
        JSONArray ja = new JSONArray(lm);
        return ja;
    }


    @ApiOperation(value = "删除用户银行卡", notes = "删除用户银行卡接口")
    @RequestMapping(value = "/delUserCard", method = RequestMethod.POST)
    public JSONObject delUserCard(@RequestBody Map<String, Object> params, HttpServletRequest request) throws IOException {
        // String cardId, String password
        JSONObject jo = new JSONObject();
        try {
            HttpSession session = request.getSession();
            String uid = session.getAttribute("uid").toString();
            String cardId = params.get("cardId").toString();
            String password = params.get("password").toString();
            List<Map<String, Object>> list = tUserService.selectQkpwdCheck(uid);
            if (list.size() > 0) {
                return jo.put("msg", "需要设置取款密码");
            }
            if (StringUtils.isEmpty(cardId)) {
                return jo.put("msg", "卡号不能为空");
            }
            if (StringUtils.isEmpty(password)) {
                return jo.put("msg", "密码不能为空");
            }

            DESEncrypt d = new DESEncrypt(deskey);
            try {
                password = d.encrypt(password);
            } catch (Exception e) {
                jo.put("msg", "系统错误");
                return jo;
            }
            try {
                // 验证二级密码
                Map<String, Object> map = new HashMap<>();
                map.put("uid", uid);
                map.put("pwd", password);
                List<Map<String, String>> lm = tUserService.checkkpwd(map);
                if (lm.size() < 1) {
                    jo.put("msg", "密码错误");
                    return jo;
                }
                map.clear();
                map.put("uid", uid);
                map.put("id", Integer.parseInt(cardId));
                List<Map<String, String>> lm1 = tUserCardService.selectUserCard(map);
                if (lm1.size() > 0) {
                    tUserCardService.deleteUserCard(map);
                }
                jo.put("msg", "success");
                // connection.commit();
            } catch (Exception e) {
                jo = exceptionHandle.defaultErrorHandler(e);
            }
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    @ApiOperation(value = "添加用户银行卡", notes = "添加用户银行卡接口")
    @RequestMapping(value = "/addUserCard", method = RequestMethod.POST)
    public JSONObject addUserCard(@RequestBody Map<String, Object> params, HttpServletRequest request) throws IOException {
        //String cardUserName, String bankCode, String cardNum,
        //            String cardAddress, String password
        JSONObject jo = new JSONObject();
        try {
            HttpSession session = request.getSession();

            String uid = session.getAttribute("uid").toString();
            String cardUserName = params.get("cardUserName").toString();
            String bankCode = params.get("bankCode").toString();
            String cardNum = params.get("cardNum").toString();
            String cardAddress = params.get("cardAddress").toString();
            String password = params.get("password").toString();

            List<Map<String, Object>> list = tUserService.selectQkpwdCheck(uid);
            if (list.size() > 0) {
                return jo.put("msg", "需要设置取款密码");
            }

            if (StringUtils.isEmpty(bankCode)) {
                return jo.put("msg", "银行代号为空");
            }
            if (StringUtils.isEmpty(cardUserName)) {
                return jo.put("msg", "开卡人不能为空");
            }
            if (StringUtils.isEmpty(cardNum)) {
                return jo.put("msg", "卡号不能为空");
            }
            if (StringUtils.isEmpty(cardAddress)) {
                return jo.put("msg", "开户地址不能为空");
            }
            if (StringUtils.isEmpty(password)) {
                return jo.put("msg", "密码不能为空");
            }

            try {
                // 验证银行卡数量
                synchronized (addUserCard) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("uid", uid);
                    List<Map<String, String>> lm = tUserCardService.selectUserCard(map);
                    if (lm.size() > 0) {
                        return jo.put("msg", "银行卡数量超过最大数");
                    }

                    DESEncrypt d = new DESEncrypt(deskey);
                    try {
                        password = d.encrypt(password);
                    } catch (Exception e) {
                        return jo.put("msg", "系统错误");
                    }
                    try {
                        Integer.parseInt(bankCode);
                    } catch (Exception e) {
                        return jo.put("msg", "银行信息错误");
                    }
                    // 验证二级密码
                    map.put("pwd", password);
                    lm = tUserService.checkkpwd(map);
                    if (lm.size() < 1) {
                        return jo.put("msg", "密码错误");
                    }

                    map.put("cardusername", cardUserName);
                    map.put("bankid", bankCode);
                    map.put("cardnum", cardNum);
                    map.put("cardaddress", cardAddress);

                    tUserCardService.insertUserCard(map);
                    jo.put("msg", "success");
                }

            } catch (Exception e) {
                jo = exceptionHandle.defaultErrorHandler(e);
            }

        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    /**
     * 列表
     */
    @ApiOperation(value = "会员银行卡列表", notes = "查询会员银行卡列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "查询参数", required = false, dataType = "Map", paramType = "body")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JSONObject list(@RequestParam Map<String, Object> params) {
        JSONObject jo = new JSONObject();
        try {
            //查询列表数据
            Query query = new Query(params);
            List<TUserCardEntity> tUserCardList = tUserCardService.queryList(query);
            int total = tUserCardService.queryTotal(query);
            PageUtils pageUtil = new PageUtils(tUserCardList, total, query.getLimit(), query.getPage());
            return jo.put("code", "0").put("page", pageUtil);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    /**
     * 信息
     */
    @ApiOperation(value = "具体会员银行卡查询", notes = "依据会员银行卡的ID进行查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "查询参数", required = true, dataType = "Long", paramType = "path")})
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public JSONObject info(@PathVariable("id") Long id) {
        JSONObject jo = new JSONObject();
        try {
            TUserCardEntity tUserCard = tUserCardService.queryObject(id);
            return jo.put("code", "0").put("tUserCard", tUserCard);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    @ApiOperation(value = "保存会员银行卡", notes = "保存会员银行卡信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject save(@RequestBody TUserCardEntity tUserCard) {
        JSONObject jo = new JSONObject();
        try {
            tUserCardService.save(tUserCard);
            return jo.put("code", "0");
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    @ApiOperation(value = "修改会员银行卡", notes = "修改具体会员银行卡信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject update(@RequestBody TUserCardEntity tUserCard) {
        JSONObject jo = new JSONObject();
        try {
            tUserCardService.update(tUserCard);
            return jo.put("code", "0");
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    @ApiOperation(value = "删除会员银行卡", notes = "删除指定的会员银行卡ID数组")
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject delete(@PathVariable Long[] ids) {
        JSONObject jo = new JSONObject();
        try {
            tUserCardService.deleteBatch(ids);
            return jo.put("code", "0");
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

}
