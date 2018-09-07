package com.sky.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.druid.support.json.JSONUtils;
import com.sky.member.config.GlobalExceptionHandler;
import com.xiaoleilu.hutool.json.JSONArray;
import com.xiaoleilu.hutool.json.JSONObject;
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

import com.sky.member.entity.*;
import com.sky.member.service.*;
import com.sky.member.utils.PageUtils;
import com.sky.member.utils.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 会员信息
 *
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-04 16:26:37
 */
@RestController
@RefreshScope
@Api(description = "会员信息")
@RequestMapping("/tuser")
public class TUserController extends BaseController {
    private final String deskey = "tianxia88";
    @Autowired
    private GlobalExceptionHandler exceptionHandle;
    @Autowired
    private TUserService tUserService;
    @Autowired
    private TRefererUrlService tRefererUrlService;
    @Autowired
    private TUserLoginService tUserLoginService;
    @Autowired
    private TPlatformConfigService tPlatformConfigService;
    @Autowired
    private TCagentYsepayService tCagentYsepayService;
    @Autowired
    private TLoginerrormapService tLoginerrormapService;

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息接口")
    @RequestMapping(value = "/changeQkpwd", method = RequestMethod.POST)
    public JSONObject changeQkpwd(@RequestBody Map<String, Object> params,HttpServletRequest request) throws IOException {
        JSONObject jo = new JSONObject();
        try {
            HttpSession session = request.getSession();
            String password=params.get("password").toString();
            String npassword=params.get("npassword").toString();
            String renpassword=params.get("renpassword").toString();
            String username = (String) request.getSession().getAttribute("userName");
            String uid = request.getSession().getAttribute("uid").toString();
            DESEncrypt d = new DESEncrypt(deskey);
            String regex = "[a-zA-Z0-9]{4,20}";

            // 取款密码是否为空
            List<Map<String, Object>> list = tUserService.selectQkpwdCheck(uid);
            if (list.size() > 0) {
                if (StringUtils.isEmpty(renpassword)) {
                    return jo.put("msg", "密码不能为空");
                }
                if (!npassword.equals(renpassword)) {
                   return jo.put("msg", "两次密码不一致");
                }
                if (npassword.length() < 4 || npassword.length() > 6) {
                   return  jo.put("msg", "取款密码4-6位");
                }
                Map<String, Object> map = new HashMap<>();

                try {
                    npassword = d.encrypt(npassword);
                } catch (Exception e) {
                   return  jo.put("msg", "系统错误");
                }
                map.put("userName", username);
                map.put("qkpwd", npassword);
                tUserService.updateGame(map);
                jo.put("msg", "success");
                return jo;
            } else {
                if (StringUtils.isEmpty(password)) {
                    return jo.put("msg", "密码不能为空");
                }

                if (StringUtils.isEmpty(npassword)) {
                    return jo.put("msg", "密码不能为空");
                }

                if (StringUtils.isEmpty(renpassword)) {
                    return jo.put("msg", "密码不能为空");
                }

                if (!npassword.equals(renpassword)) {
                 return    jo.put("msg", "两次密码不一致");
                }
                if (!npassword.matches(regex)) {
                    return  jo.put("msg", "008");
                }
                try {
                    password = d.encrypt(password);
                    npassword = d.encrypt(npassword);
                } catch (Exception e) {
                    return jo.put("msg", "系统错误");
                }

                Map<String, Object> map = new HashMap<>();
                map.put("uid", uid);
                map.put("pwd", password);

                List<Map<String, String>> lm = tUserService.checkkpwd(map);
                if (lm.size() < 1) {
                    return jo.put("msg", "密码错误");
                }
                map.clear();
                map.put("userName", username);
                map.put("qkpwd", npassword);

                tUserService.updateGame(map);
                return jo.put("msg", "success");
            }
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }



    @ApiOperation(value = "获取用户信息", notes = "获取用户信息接口")
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public JSONObject getUserInfo(HttpServletRequest request) throws IOException {
        JSONObject jo = new JSONObject();
        try {
            HttpSession session = request.getSession();
            String uid = request.getSession().getAttribute("uid").toString();
            Map<String, Object> param = new HashMap<>();
            param.put("uid", uid);
            Map<String, Object> lm = tUserService.selectUserInfo(param);
            return jo.put("userinfo", lm);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    @ApiOperation(value = "会员信息注册列表", notes = "会员注册信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "查询参数", required = false, dataType = "Map", paramType = "body")})
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JSONObject register(@RequestBody Map<String, Object> params, HttpSession session, HttpServletRequest request) {
        //查询列表数据
        JSONObject jo = new JSONObject();
        Properties pro = new Properties();
        InputStream in;
        try {
            String cagent = params.get("cagent").toString();

            if (StringUtils.isEmpty(cagent)) {
                return jo.put("msg", "error");
            }
            // 验证来源域名是否属于该代理平台
            String refurl = request.getHeader("referer");
            if (StringUtils.isEmpty(refurl)) {
                return jo.put("msg", "error");
            } else {

                List<Map<String, String>> list = tRefererUrlService.selectRefererUrl(null, params.get("cagent").toString());
                String refurls = list.toString();
                String[] urls = refurl.split("/");
                if (refurls.indexOf(urls[2]) < 0) {
                    return jo.put("msg", "域名不匹配");
                }
            }
            try {
                in = this.getClass().getResourceAsStream("/file.properties");
                pro.load(in);
                String temp = pro.getProperty("cagent");
                if (!"".equals(temp)) {
                    cagent = temp;
                }
            } catch (Exception e) {

            }
            String caregex = "[a-zA-Z][a-zA-Z0-9]{2,2}";
            if (cagent == null || "".equals(cagent) || !cagent.matches(caregex)) {
                return jo.put("msg", "error");

            }

            String regex = "[a-zA-Z0-9]{5,15}";
            if (StringUtils.isEmpty(params.get("userName").toString())) {
                return jo.put("msg", "001");
            }
            if (StringUtils.isEmpty(params.get("realName").toString())) {
                params.put("realName", "会员");
            }
            if (!params.get("userName").toString().matches(regex)) {
                return jo.put("msg", "002");

            }
            if (StringUtils.isEmpty(params.get("mobileNo").toString())) {
                return jo.put("msg", "003");
            }
            if (params.get("mobileNo").toString().length() != 11) {
                return jo.put("msg", "004");
            }
            if (StringUtils.isEmpty(params.get("passWord").toString())) {
                return jo.put("msg", "005");
            }

            if (StringUtils.isEmpty(params.get("repassWord").toString())) {
                return jo.put("msg", "006");
            }


            if (!params.get("passWord").toString().equals(params.get("repassWord").toString())) {
                return jo.put("msg", "007");
            }
            if (params.get("passWord").toString().length() < 5 || params.get("passWord").toString().length() > 20) {
                return jo.put("msg", "008");
            }

            if (!StringUtils.isEmpty(params.get("qkpwd").toString())) {
                if (StringUtils.isEmpty(params.get("reqkpwd").toString())) {
                    return jo.put("msg", "014");
                }
                if (!params.get("qkpwd").toString().equals(params.get("reqkpwd").toString())) {
                    return jo.put("msg", "015");
                }
                if (params.get("qkpwd").toString().length() < 4) {
                    return jo.put("msg", "016");
                }
                if (params.get("qkpwd").toString().equals(params.get("passWord").toString())) {
                    return jo.put("msg", "017");
                }
            } else {
                params.put("qkpwd", "");
            }
            if (StringUtils.isEmpty(params.get("reguuid").toString())) {
                return jo.put("msg", "010");
            }
            if (!"0".equals(params.get("isImgCode").toString())) {
                // 图形验证码为空
                if (StringUtils.isEmpty(params.get("imgcode").toString())) {
                    return jo.put("msg", "011");
                }
                String simgcode = (String) request.getSession().getAttribute("imgcode");
                if (StringUtils.isEmpty(simgcode)) {
                    return jo.put("msg", "011");
                }
                simgcode = simgcode.toLowerCase();
                if (!simgcode.equals(params.get("imgcode").toString())) { // 忽略验证码大小写
                    return jo.put("msg", "012");
                }
            }
            request.getSession().setAttribute("imgcode", "");
            String isMobile = params.get("isMobile").toString();

            if (isMobile == null || (!"2".equals(isMobile) && !"1".equals(isMobile) && !"0".equals(isMobile))) {
                isMobile = "0";
            }

            StringBuilder str = new StringBuilder();
            str.append(cagent);
            Random randoms = new Random();
            String[] urls = refurl.split("/");
            // 随机生成数字，并添加到字符串
            int max = 999999;
            int min = 100000;
            int ss = randoms.nextInt(max) % (max - min + 1) + min;
            str.append(ss);

            String agpwd = str.toString();
            if (agpwd.length() > 9) {
                agpwd = agpwd.substring(0, 9);
            }
            // 参数传入到Map中保存到数据库
            String ip = IPTools.getIp(request);
            Map<String, Object> map = new HashMap<>();

            String mobilereg = "[1][0-9]{10}";
            if (params.get("userName").toString().matches(mobilereg)) {
                map.put("loginmobile", params.get("userName").toString());
            }

            // 用户名统一转小写
            map.put("username", (cagent + params.get("userName").toString()).toLowerCase());
            map.put("password", params.get("passWord").toString());
            map.put("reg_ip", ip);
            // MG游戏帐号超长截取
            String ag_username = (cagent + params.get("userName").toString()).toLowerCase();
            // if(ag_username.length()>13){
            // ag_username=ag_username.substring(0, 13);
            // }
            map.put("hg_username", (cagent + params.get("userName").toString()).toLowerCase());
            map.put("ag_username", ag_username);
            map.put("email", "");
            map.put("vip_level", "1");
            map.put("mobile", params.get("mobileNo").toString());
            map.put("is_daili", "0");
            map.put("top_uid", "0");
            map.put("is_mobile", isMobile);
            map.put("cagent", cagent.toLowerCase());

            map.put("qkpwd", params.get("qkpwd").toString());
            map.put("realName", params.get("realName").toString());
            map.put("ag_password", agpwd);
            map.put("regurl", urls[2]);
            String remark = params.get("remark").toString();
            if (StringUtils.isEmpty(remark)) {
                remark = "";
            }
            map.put("remark", remark);
            // 检测用户是否存在
            List<Map<String, Object>> list = tUserService.queryUserByUserName(cagent + params.get("userName").toString(),null);
            if (list.size() > 0) {
                return jo.put("msg", "009");
            }

            // 检测用户是否为系统保留账户
            list = tUserService.queryReserveAccount(cagent + params.get("userName").toString(), cagent);
            if (list.size() > 0) {
                return jo.put("msg", "009");
            }

            String ruuid = request.getSession().getAttribute("reguuid").toString();
            if (StringUtils.isEmpty(params.get("ruuid").toString())) {
                return jo.put("msg", "010");
            } else if (!ruuid.equals(params.get("reguuid").toString())) {
                return jo.put("msg", "010");
            }
            request.getSession().setAttribute("reguuid", "");
            map.put("top_uid", "0");
            map.put("junior_uid", "0");
            String proxyname = params.get("proxyname").toString();
            // 根据来源域名更新代理商
            if (proxyname == null || "".equals(proxyname)) {
                proxyname = "";
                Map<String, Object> ProxyMap = new HashMap<String, Object>();
                ProxyMap.put("cagent", cagent);
                String domain = urls[2];// cdsr.com
                int num = domain.indexOf(":");
                if (num > 0) {
                    domain = domain.substring(0, num);
                }
                String regip = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                        + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                        + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
                String regex1 = "[\\w-]+\\.(com.cn|net.cn|gov.cn|org\\.nz|org.cn|com|net|org|gov|cc|biz|info|cn|co|vip|top|name|wang|shop|xin|tv|site|info|hk|ltd|xyz|red|club|"
                        + "wang|ink|pro|edu|group|link|mobi|ren|kim|idv|asia|ceo)\\b()*";
                if (!domain.matches(regip) && !"localhost".equals(domain)) {
                    Pattern p = Pattern.compile(regex1);
                    Matcher m = p.matcher(domain);
                    List<String> strList = new ArrayList<String>();
                    while (m.find()) {
                        strList.add(m.group());
                    }
                    domain = strList.toString();
                    domain = domain.substring(1, domain.length() - 1);
                }

                List<Map<String, String>> ProxyList = tUserService.queryProxyByName(ProxyMap);
                if (ProxyList.size() > 0) {
                    for (int i = 0; i < ProxyList.size() && "".equals(proxyname); i++) {
                        if (ProxyList.get(i).containsKey("domain") && !"".equals(ProxyList.get(i).get("domain"))) {
                            String[] domains = ProxyList.get(i).get("domain").split(";");
                            for (int j = 0; j < domains.length && "".equals(proxyname); j++) {
                                // if (domains[j].equals(domain)) {
                                if (domain.equals(domains[j].trim())) {
                                    proxyname = ProxyList.get(i).get("user_name");
                                }
                            }
                        }
                    }
                }
            }
            // 根据推荐账号,更新代理商
            if (proxyname != null && !"".equals(proxyname)) {
                list = tUserService.getProxyUser(proxyname, cagent);
                if (list.size() > 0) {
                    map.put("top_uid", list.get(0).get("pid"));
                    if (!"0".equals(list.get(0).get("d_user_type"))) {
                        map.put("usertype", list.get(0).get("d_user_type"));
                    }
                    map.put("junior_uid", "0");
                } else {
                    list = tUserService.getJuniorProxyUser(proxyname, cagent);
                    if (list.size() > 0) {
                        map.put("top_uid", list.get(0).get("up_id"));
                        if (!"0".equals(list.get(0).get("d_user_type"))) {
                            map.put("usertype", list.get(0).get("d_user_type"));
                        }
                        map.put("junior_uid", list.get(0).get("pid"));
                    }
                }
            }

            tUserService.insertUser(map);
            List<Map<String, Object>> users = tUserService.queryUserByUserName(cagent + params.get("userName").toString(),null);
            Map<String, Object> user = null;
            if (users.size() > 0) {
                user = users.get(0);
                String hg_username = user.get("hg_username").toString();

                UUID uuid = UUID.randomUUID();
                // TODO 待优化
                String address = getIpAddress(request);

                // 登录日志
                Map<String, Object> map1 = new HashMap<>();
                map1.put("uid", user.get("uid"));
                map1.put("ip", IPTools.getIp(request));
                map1.put("is_mobile", isMobile);
                String[] urls1 = refurl.split("/");
                map1.put("refurl", urls1[2]);
                map1.put("address", address);
                tUserLoginService.insertLogin(map1);

                List<Map<String, String>> list1 = tPlatformConfigService.selectPlatFromInfo("");
                Map<String, Object> param = new HashMap<>();
                param.put("uid", user.get("uid").toString());
                String balance = tUserService.selectUserById(param).get("wallet").toString();

                jo.put("userKey", uuid.toString());
                jo.put("userName", cagent + params.get("userName").toString());
                jo.put("balance", balance);

                request.getSession().setAttribute("uid", user.get("uid"));
                request.getSession().setAttribute("userName", cagent + params.get("userName").toString());
                request.getSession().setAttribute("ag_username", user.get("ag_username"));
                request.getSession().setAttribute("hg_username", hg_username);
                request.getSession().setAttribute("ag_password", user.get("ag_password"));
                request.getSession().setAttribute("userkey", uuid.toString());
                request.getSession().setAttribute("WithDraw", "0");
                request.getSession().setAttribute("gameStatus", list1);
                request.getSession().setAttribute("loginmobile", null);

                // 登录信息放入登录表,防止重复登录
                Map<String, String> loginmap = new HashMap<>();
                loginmap.put("uid", user.get("uid") + "");
                loginmap.put("sessionid", session.getId());
                loginmap.put("ip", IPTools.getIp(request));
                loginmap.put("refurl", urls[2]);
                loginmap.put("address", address);
                loginmap.put("cagent", cagent);
                loginmap.put("login_time", System.currentTimeMillis() + "");
                loginmap.put("isMobile", isMobile);
                // userService.insertLoginMap(loginmap);

                // 登陆用户session
                loginmaps.put(user.get("uid") + "", loginmap);

                // 把游戏配置存入session
                List<Map<String, String>> plist = tPlatformConfigService.selectPlatFromInfo(null);
                Map<String, String> pmap = new HashMap<>();
                for (int i = 0; i < plist.size(); i++) {
                    if ("1".equals(plist.get(i).get("platform_status").toString())) {
                        JSONObject platform_configJson = new JSONObject(plist.get(i).get("platform_config").toString());
                        String platform_config = "";
                        if (platform_configJson.containsKey(cagent.toUpperCase())) {
                            logger.info("代理号:" + cagent.toUpperCase());
                            platform_config = platform_configJson.get(cagent.toUpperCase()).toString();
                            logger.info("新增线路配置信息:" + platform_config);
                        } else if (platform_configJson.containsKey("ALL")) {
                            platform_config = platform_configJson.get("ALL").toString();
                            logger.info("新线路默认配置信息:" + platform_config);
                        } else {
                            platform_config = plist.get(i).get("platform_config").toString();
                            // logger.info("默认配置信息:" + platform_config);
                        }
                        pmap.put(plist.get(i).get("platform_key").toString(), platform_config);
                    }
                }
                request.getSession().setAttribute("platformMap", pmap);

                plist = tCagentYsepayService.getYsepayConfig(cagent + params.get("userName").toString());
                if (plist.size() > 0) {
                    request.getSession().setAttribute("ysPay", plist.get(0));
                } else {
                    request.getSession().setAttribute("ysPay", null);
                }
            }
            jo.put("msg", "success");
            request.getSession().setAttribute("isreg", "true");


        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    private String getIpAddress(HttpServletRequest request) {
        String address = "未知";
        IP.enableFileWatch = false; // 默认值为：false，如果为true将会检查ip库文件的变化自动reload数据
        try {
            IP.load("/IPAddress.dat");
            String ipads[] = IP.find(IPTools.getIp(request));
            StringBuffer s = new StringBuffer();
            for (int i = 1; i < ipads.length; i++) {
                if (ipads[i] != null && !"".equals(ipads[i])) {
                    if (i > 1) {
                        s.append(",");
                    }
                    s.append(ipads[i]);
                }
            }
            address = s.toString();
        } catch (Exception e) {
        }
        return address;
    }

    /**
     * 登陆
     *
     * @param params,老接口中的所有参数 放入params中
     * @return 用户信息集合
     * @throws IOException
     */
    @ApiOperation(value = "会员登录列表", notes = "会员登录信息接口")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(@RequestBody Map<String, Object> params, HttpServletRequest request) throws IOException {
        //String tname,String tpwd,String savelogin,String imgcode,String isMobile,String isImgCode
        JSONObject jo = new JSONObject();
        try {
            UUID uuid = UUID.randomUUID();
            HttpSession session = request.getSession();
            String simgcode = (String) request.getSession().getAttribute("imgcode");
            String imgcode = params.get("imgcode").toString();
            String isreg = (String) request.getSession().getAttribute("isreg");
            boolean is_control = true;
            Properties pro = new Properties();
            InputStream in;

            in = this.getClass().getResourceAsStream("/file.properties");
            pro.load(in);
            String controlUser = pro.getProperty("controlUser");
            if (controlUser.equals(params.get("tname"))) {
                is_control = false;
            }
            if (is_control) {
                if (!"0".equals(params.get("isImgCode"))) {
                    if (simgcode == null) {
                        return jo.put("status", "faild").put("errmsg", "请刷新图片验证码");
                    }
                    //图形验证码
                    if (StringUtils.isEmpty(imgcode)) {
                        return jo.put("status", "faild").put("errmsg", "请输入图形验证码");
                    }
                    simgcode = simgcode.toLowerCase();
                    imgcode = imgcode.toLowerCase();
                    if (!simgcode.equals(imgcode)) {  //忽略验证码大小写
                        return jo.put("status", "faild").put("errmsg", "图形验证码不正确");
                    }
                }
            }

            request.getSession().setAttribute("isreg", "");
            request.getSession().setAttribute("imgcode", "");
            String username = (String) request.getSession().getAttribute("userName");
            String tname = params.get("tname").toString();
            String tpwd = params.get("tpwd").toString();
            if (tname == null || "".endsWith(tname)) {
                return jo.put("status", "faild").put("errmsg", "用户名不能为空");
            }
            if (tpwd == null || "".endsWith(tpwd)) {
                return jo.put("status", "faild").put("errmsg", "密码不能为空");
            }

            //验证来源域名是否属于该代理平台
            String refurl = request.getHeader("referer");
            if (refurl == null || "".endsWith(refurl)) {
                jo.put("msg", "error");
                return jo;
            } else {
                String refurls = "";
                List<Map<String, String>> list = tRefererUrlService.selectRefererUrl(null, tname.substring(0, 3));
                refurls = list.toString();

                String[] urls = refurl.split("/");
                if (refurls.indexOf(urls[2]) < 0) {
                    jo.put("msg", "域名不匹配");
                    return jo;
                }
            }

            try {
                List<Map<String, Object>> elist = tLoginerrormapService.selectLoginErrorMap(tname);
                if (elist.size() > 0) {
                    Map<String, Object> m = elist.get(0);
                    int ltimes = Integer.parseInt(m.get("times") + "");
                    Date logintime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(m.get("logintime").toString().substring(0, 19));
                    Date now = new Date();
                    //System.out.println((now.getTime()-logintime.getTime())/1000);
                    if ((now.getTime() - logintime.getTime()) / 1000 > 60 * 60 * 24) {
                        tLoginerrormapService.deleteLoginErrorMap(tname);
                    } else if ((now.getTime() - logintime.getTime()) / 1000 < 300) {
                        if (ltimes >= 5) {
                            return jo.put("status", "faild").put("errmsg", "密码错误次数过多,已锁定5分钟");
                        }
                    } else if ((now.getTime() - logintime.getTime()) / 1000 < 60 * 60 * 24) {
                        if (ltimes >= 10) {
                            return jo.put("status", "faild").put("errmsg", "密码错误次数过多,已锁定一天");
                        }
                    }
                }

                List<Map<String, Object>> users = null;
                String mobilereg = "[1][0-9]{10}";
                String userName = tname.substring(3, tname.length());
                if (userName.matches(mobilereg)) {
                    users = tUserService.UserLoginByMobile(tname.substring(0, 3), userName, tpwd);
                } else {
                    users = tUserService.UserLogin(tname, tpwd);
                }
                //判断用户名及密码是否正确，正确则登录，错误则提示重新输入
                String isMobile = params.get("isMobile").toString();
                if (users.size() > 0) {
                    tLoginerrormapService.deleteLoginErrorMap(tname);
                    // isMobile => 0/null : pc端 ,1: 手机H5 , 2:App
                    if (isMobile == null || (!"2".equals(isMobile) && !"1".equals(isMobile) && !"0".equals(isMobile))) {
                        isMobile = "0";
                    }

                    Map<String, Object> user = users.get(0);
                    if ("1".equals(user.get("is_stop").toString())) {
                        return jo.put("status", "faild").put("errmsg", "账户已被锁定,请联系客服");
                    }
                    // TODO 待优化
                    String address = getIpAddress(request);

                    // 登录日志
                    Map<String, Object> map = new HashMap<>();
                    map.put("uid", user.get("uid"));
                    map.put("ip", IPTools.getIp(request));
                    map.put("is_mobile", isMobile);
                    String[] urls = refurl.split("/");
                    map.put("refurl", urls[2]);
                    map.put("address", address);
                    tUserLoginService.insertLogin(map);

                    map.clear();
                    map.put("userName", tname.toLowerCase());
                    map.put("login", "1");
                    map.put("ip", IPTools.getIp(request));
                    tUserService.updateGame(map);

                    List<Map<String, String>> list = tPlatformConfigService.selectPlatFromInfo("");
                    Map<String, Object> param = new HashMap<>();
                    param.put("uid", user.get("uid").toString());
                    Map<String, Object> dataMap = tUserService.selectUserById(param);
                    String balance = dataMap.get("wallet").toString();
                    String integral = dataMap.get("integral").toString();
                    jo.put("status", "ok");
                    jo.put("userKey", uuid.toString());
                    jo.put("userName", tname);
                    jo.put("balance", balance);
                    jo.put("integral", integral);

                    request.getSession().setAttribute("uid", user.get("uid"));
                    request.getSession().setAttribute("userName", tname);
                    request.getSession().setAttribute("ag_username", user.get("ag_username"));
                    request.getSession().setAttribute("hg_username", user.get("hg_username"));
                    request.getSession().setAttribute("ag_password", user.get("ag_password"));
                    request.getSession().setAttribute("userkey", uuid.toString());
                    request.getSession().setAttribute("Transfer", "0");
                    request.getSession().setAttribute("WithDraw", "0");
                    request.getSession().setAttribute("gameStatus", list);
                    request.getSession().setAttribute("loginmobile", user.get("loginmobile"));

                    //异步通知其它服务器,更新登录信息
                    final String cagent = tname.substring(0, 3);
                    final String uid = user.get("uid") + "";


                    //登录信息放入登录表,防止重复登录
                    Map<String, String> loginmap = new HashMap<>();
                    loginmap.put("uid", user.get("uid") + "");
                    loginmap.put("sessionid", session.getId());
                    loginmap.put("ip", IPTools.getIp(request));
                    loginmap.put("refurl", urls[2]);
                    loginmap.put("address", address);
                    loginmap.put("cagent", tname.substring(0, 3));
                    loginmap.put("login_time", System.currentTimeMillis() + "");
                    loginmap.put("isMobile", isMobile);

                    loginmaps.put(user.get("uid") + "", loginmap);

                    //把游戏配置存入session
                    List<Map<String, String>> plist = tPlatformConfigService.selectPlatFromInfo(null);
                    Map<String, String> pmap = new HashMap<>();
                    for (int i = 0; i < plist.size(); i++) {
                        if ("1".equals(plist.get(i).get("platform_status").toString())) {
                            JSONObject platform_configJson = new JSONObject(plist.get(i).get("platform_config").toString());
                            String platform_config = "";
                            if (platform_configJson.containsKey(cagent.toUpperCase())) {
                                platform_config = platform_configJson.get(cagent.toUpperCase()).toString();
                            } else if (platform_configJson.containsKey("ALL")) {
                                platform_config = platform_configJson.get("ALL").toString();
                            } else {
                                platform_config = plist.get(i).get("platform_config").toString();
                            }
                            pmap.put(plist.get(i).get("platform_key").toString(), platform_config);
                        }
                    }
                    request.getSession().setAttribute("platformMap", pmap);

                    plist = tCagentYsepayService.getYsepayConfig(tname);
                    if (plist.size() > 0) {
                        request.getSession().setAttribute("ysPay", plist.get(0));
                    } else {
                        request.getSession().setAttribute("ysPay", null);
                    }


                } else {
                    tLoginerrormapService.insertLoginErrorMap(tname);

                    jo.put("status", "faild").put("errmsg", "用户名或密码错误!");
                }

            } catch (Exception e) {
                e.printStackTrace();
                jo.put("status", "faild").put("errmsg", "用户名或密码错误!");
            }
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    @ApiOperation(value = "具体会员登录状态列表", notes = "具体会员登录状态接口")
    @RequestMapping(value = "/checklogin/{cName}", method = RequestMethod.GET)
    public JSONObject checklogin(@PathVariable("cName") String cName, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        try {
            HttpSession session = request.getSession();
            String username = (String) request.getSession().getAttribute("userName");
            String loginmobile = (String) request.getSession().getAttribute("loginmobile");
            String uid;

            if (StringUtils.isNotBlank(cName)) {
                List<Map<String, Object>> users = tUserService.UserLogin(cName, null);
                if (users.size() > 0) {
                    Map<String, Object> user = users.get(0);
                    String uidNew = String.valueOf(user.get("uid"));
                    if (loginmaps.containsKey(uidNew)) {
                        return jo.put("userName", cName).put("msg", "success");
                    }
                }
            }
            // ***解决导演提出的bug问题*******
            if (StringUtils.isEmpty(username)) {
                return jo.put("msg", "faild");
            } else {
                uid = request.getSession().getAttribute("uid").toString();
                Map<String, Object> param = new HashMap<>();
                param.put("uid", uid);
                String sissionid = session.getId();
                String ip = IPTools.getIp(request);

                //登录信息放入登录表,防止重复登录

                if (loginmaps.containsKey(uid)) {
                    Map<String, String> loginmap = loginmaps.get(uid);
                    if (loginmap.containsKey("sessionid")) {
                        if (!sissionid.equals(loginmap.get("sessionid").toString())) {
                            session.invalidate();
                            return jo.put("msg", "outlogin");

                        }
                    }
                } else {
                    try {
                        session.invalidate();
                        return jo.put("msg", "faild");

                    } catch (Exception e) {

                    }
                }
                String userkey = (String) request.getSession().getAttribute("userkey");
                return jo.put("userkey", userkey).put("userName", username).put("loginmobile", loginmobile).put("msg", "success");

            }


        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    @ApiOperation(value = "检查cookie并自动登录列表", notes = "检查cookie并自动登录接口")
    @RequestMapping(value = "/checkCookie", method = RequestMethod.GET)
    public JSONObject checkCookie(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        try {
            Cookie[] cookies = request.getCookies();
            HttpSession session = request.getSession();
            String username = (String) request.getSession().getAttribute("userName");
            if (cookies == null) {
                return jo.put("msg", "faild");
            }
            if (username == null || "".equals(username)) {
                if (null != cookies) {
                    return jo.put("msg", "faild");
                } else {
                    Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
                    for (Cookie cookie : cookies) {
                        cookieMap.put(cookie.getName(), cookie);
                    }
                    String tname = cookieMap.get("Temp_T1").getValue();
                    String tpwd = cookieMap.get("Temp_T2").getValue();

                    DESEncrypt d = new DESEncrypt(deskey);
                    try {
                        tname = d.decrypt(tname);
                        tpwd = d.decrypt(tpwd);
                    } catch (Exception e) {

                    }

                    List<Map<String, Object>> users = tUserService.UserLogin(tname, tpwd);

                    if (users.size() > 0) {
                        UUID uuid = UUID.randomUUID();
                        Map<String, Object> user = users.get(0);
                        session = request.getSession();
                        request.getSession().setAttribute("uid", user.get("uid"));
                        request.getSession().setAttribute("userName", tname);
                        request.getSession().setAttribute("ag_password", user.get("ag_password"));
                        //判断是否已在别处登录
                        Map<String, String> loginmap = loginmaps.get(user.get("uid") + "");

                        //判断是否已在别处登录
                        if (loginmap.containsKey("sessionid")) {
                            //根据以拄的session 得到session,将它不可用。
                            HttpSession sess = session.getSessionContext().getSession(loginmap.get("sessionid"));
                            sess.invalidate();
                            //将现在的SeesionId进行存储起来
                            loginmap.put("sessionid", session.getId());

                        }
                        loginmap.put("sessionid", session.getId());
                        loginmaps.put(user.get("uid") + "", loginmap);
                        return jo.put("userKey", uuid).put("userName", tname).put("balance", user.get("wallet")).put("msg", "success");

                    } else {
                        //清除cookie
                        cookies = request.getCookies();
                        if (null == cookies) {
                        } else {
                            for (Cookie cookie : cookies) {
                                cookie.setValue(null);
                                cookie.setMaxAge(0);// 立即销毁cookie
                                cookie.setPath("/");
                                cookie.setHttpOnly(true);
                                response.addCookie(cookie);
                            }
                        }
                        return jo.put("msg", "faild");
                    }
                }
            } else {
                return jo.put("msg", "islogin");
            }
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    @ApiOperation(value = "登出列表", notes = "登出接口")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public JSONObject logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        try {
            String uid = request.getSession().getAttribute("uid").toString();
            loginmaps.remove(uid);
            Cookie[] cookies = request.getCookies();
            if (null == cookies) {
            } else {
                for (Cookie cookie : cookies) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);// 立即销毁cookie
                    cookie.setPath("/");
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                }
            }
            session.invalidate();
            jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    @ApiOperation(value = "请求图片验证码", notes = "请求图片验证码接口")
    @RequestMapping(value = "/validateCode", method = RequestMethod.GET)
    public void validateCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");
            //禁止图像缓存。
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            HttpSession session = request.getSession();
            ValidateCode vCode = new ValidateCode(100, 30, 4, 30);
            String code = vCode.getCode();
            request.getSession().setAttribute("imgcode", code);
            vCode.write(response.getOutputStream());

        } catch (Exception e) {

        }
    }

    @ApiOperation(value = "修改登录密码", notes = "修改登录密码接口")
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public JSONObject changePassword(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        try {
            HttpSession session = request.getSession();

            String username = (String) request.getSession().getAttribute("userName");
            String password = params.get("password").toString();
            String npassword = params.get("npassword").toString();
            String renpassword = params.get("renpassword").toString();
            String regex = "[a-zA-Z0-9]{5,20}";
            if (StringUtils.isEmpty(password)) {
                jo.put("msg", "密码不能为空");
                return jo;
            }

            if (StringUtils.isEmpty(npassword)) {
                jo.put("msg", "密码不能为空");
                return jo;
            }

            if (StringUtils.isEmpty(renpassword)) {
                jo.put("msg", "密码不能为空");
                return jo;
            }

            if (!npassword.equals(renpassword)) {
                jo.put("msg", "两次密码不一致");
                return jo;
            }
            if (!npassword.matches(regex)) {
                jo.put("msg", "008");
                return jo;
            }
            List<Map<String, Object>> lm = tUserService.UserLogin(username, password);

            if (lm.size() < 1) {
                jo.put("msg", "密码错误");
                return jo;
            }
            DESEncrypt d = new DESEncrypt(deskey);
            try {
                npassword = d.encrypt(npassword);
            } catch (Exception e) {
                jo.put("msg", "系统错误");
                return jo;
            }

            Map<String, Object> map = new HashMap<>();
            map.put("userName", username);
            map.put("password", npassword);

            tUserService.updateGame(map);
            jo.put("msg", "success");

        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    /**
     * 列表
     */
    @ApiOperation(value = "会员信息列表", notes = "查询会员信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "查询参数", required = false, dataType = "Map", paramType = "body")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JSONObject list(@RequestBody Map<String, Object> params) {
        //查询列表数据
        JSONObject jo = new JSONObject();
        try {
            Query query = new Query(params);

            List<TUserEntity> tUserList = tUserService.queryList(query);
            int total = tUserService.queryTotal(query);

            PageUtils pageUtil = new PageUtils(tUserList, total, query.getLimit(), query.getPage());

            return jo.put("code", 0).put("page", pageUtil);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    /**
     * 信息
     */

    @ApiOperation(value = "具体会员信息列表", notes = "查询具体会员信息接口")
    @RequestMapping(value = "/info/{uid}", method = RequestMethod.GET)
    public JSONObject info(@PathVariable("uid") Integer uid) {
        JSONObject jo = new JSONObject();
        try {
            TUserEntity tUser = tUserService.queryObject(uid);
            return jo.put("code", 0).put("tUser", tUser);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存会员信息列表", notes = "保存具体会员信息接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject save(@RequestBody TUserEntity tUser) {
        JSONObject jo = new JSONObject();
        try {
            tUserService.save(tUser);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改会员信息列表", notes = "修改会员信息接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject update(@RequestBody TUserEntity tUser) {
        JSONObject jo = new JSONObject();
        try {
            tUserService.update(tUser);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 删除
     */
    @ApiOperation(value = "批量删除会员信息", notes = "批量删除会员信息接口")
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject delete(@PathVariable Integer[] ids) {
        JSONObject jo = new JSONObject();
        try {
            tUserService.deleteBatch(ids);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

}
