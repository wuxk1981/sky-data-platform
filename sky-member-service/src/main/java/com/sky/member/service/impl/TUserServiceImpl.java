package com.sky.member.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.sky.member.utils.DESEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.sky.member.mapper.TUserMapper;
import com.sky.member.entity.TUserEntity;
import com.sky.member.service.TUserService;


@Service("tUserService")
public class TUserServiceImpl implements TUserService {
    @Autowired
    private TUserMapper tUserMapper;
    private String deskey = "tianxia88";

    @Override
    public TUserEntity queryObject(Integer uid) {
        return tUserMapper.queryObject(uid);
    }

    @Override
    public List<TUserEntity> queryList(Map<String, Object> map) {
        return tUserMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return tUserMapper.queryTotal(map);
    }

    @Override
    public void save(TUserEntity tUser) {
        tUserMapper.save(tUser);
    }

    @Override
    public void update(TUserEntity tUser) {
        tUserMapper.update(tUser);
    }

    @Override
    public void delete(Integer uid) {
        tUserMapper.delete(uid);
    }

    @Override
    public void deleteBatch(Integer[] uids) {
        tUserMapper.deleteBatch(uids);
    }

    @Override
    public List<Map<String, Object>> queryUserByUserName(String userName,String password) {
        return tUserMapper.queryUserByUserName(userName,password);
    }

    @Override
    public List<Map<String, Object>> queryReserveAccount(String userName, String cagent) {
        return tUserMapper.selectReserveAccount(userName, cagent);
    }

    @Override
    public List<Map<String, String>> queryProxyByName(Map<String, Object> map) {
        return tUserMapper.queryProxyByName(map);
    }

    @Override
    public List<Map<String, Object>> getProxyUser(String proxyname, String cagent) {
        return tUserMapper.getProxyUser(proxyname, cagent);
    }

    @Override
    public List<Map<String, Object>> getJuniorProxyUser(String proxyname, String cagent) {
        return tUserMapper.getJuniorProxyUser(proxyname, cagent);
    }

    @Override
    public void insertUser(Map<String, Object> map) {
        DESEncrypt d = new DESEncrypt(deskey);
        try {
            String pwd = map.get("password").toString();
            String agpwd = map.get("ag_password").toString();
            pwd = d.encrypt(pwd);
            agpwd = d.encrypt(agpwd);
            map.put("password", pwd);
            map.put("ag_password", agpwd);

            pwd = map.get("qkpwd").toString();
            if (StringUtils.isEmpty(pwd)) {
                map.put("qkpwd", pwd);
            } else {
                pwd = d.encrypt(pwd);
                map.put("qkpwd", pwd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tUserMapper.insertUser(map);
    }

    @Override
    public Map<String, Object> selectUserById(Map<String, Object> map) {
        return tUserMapper.selectUserById(map);

    }

    @Override
    public List<Map<String, Object>> UserLoginByMobile(String cagent, String mobileNo, String password) {
        return tUserMapper.selectUserByMobileNo(cagent, mobileNo, password);
    }

    @Override
    public List<Map<String, Object>> UserLogin(String userName, String passWord) {
        return tUserMapper.selectUserByUserName(userName, passWord);
    }

    @Override
    public void updateGame(Map<String, Object> map) {
        tUserMapper.updateGame(map);
    }

    @Override
    public List<Map<String, Object>> selectQkpwdCheck(String uid) {
        return tUserMapper.selectQkpwdCheck(uid);
    }

    @Override
    public List<Map<String, String>> checkkpwd(Map<String, Object> map) {
        return tUserMapper.checkkpwd(map);
    }

    @Override
    public Map<String, Object> selectUserInfo(Map<String, Object> map) {
        return tUserMapper.selectUserInfo(map);
    }

//    @Override
//    public List<Map<String, Object>> selectUserByUserName(String cName) {
//        return tUserMapper.selectUserByUserName(cName,null);
//    }
}
