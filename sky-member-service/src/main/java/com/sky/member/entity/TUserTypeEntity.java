package com.sky.member.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;



/**
 * 会员分层
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-04 16:26:37
 */
//@Data
public class TUserTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//代理平台ID
	private Integer cid;
	//层级名
	private String typename;
	//更新时间
	private Date updatetime;
	//备注
	private String rmk;
	//状态 : 0 启用 ,1:停用
	private String status;
	//是否默认: 0 是,1 否
	private String isdefault;
	//cg视讯退水比例
	private Float pCg;
	//ag视讯退水比例
	private Float pAg;
	//申博电子退水比例
	private Float pShenbogame;
	//申博视讯退水比例
	private Float pShenbo;
	//欧博视讯退水比例
	private Float pAb;
	//DS视讯退水比例
	private Float pDs;
	//OG视讯退水比例
	private Float pOg;
	//BBIN视讯退水比例
	private Float pBbin;
	//BBIN电子退水比例
	private Float pBbingame;
	//MG电子退水比例
	private Float pMg;
	//PT电子退水比例
	private Float pPt;
	//HABA电子退水比例
	private Float pHaba;
	//IG视讯退水比例
	private Float pIg;
	//皇冠体育退水比例
	private Float pHg;
	//GG捕鱼退水比例
	private Float pGgby;
	//BG视讯退水比例
	private Float pBgvideo;
	//BG彩票退水比例
	private Float pBglottery;
	//银行卡id,对应t_cagent_bankcard.id
	private Integer bankcardId;
	//支付宝id,t_cagent_qrcodepay.id,type=1
	private String alipayId;
	//微信id,t_cagent_qrcodepay.id,type=3
	private String wechatId;
	//财付通id,t_cagent_qrcodepay.id,type=2
	private String tenpayId;
	//在线支付，t_cagent_ysepay.id
	private String onlinepayId;
	//打码量积分比率
	private Float integralRatio;
	//储值积分比率
	private Float cIntegralRatio;
	//IG香港彩退水比例
	private Float pHkig;
	//
	private Float pVr;
	//
	private Float pJf;
	//JF彩票退水比例
	private Float pJfcp;
	//yorplay电子退水比例
	private Float pYorplay;
	//开云棋牌退水比例
	private Float pKyqp;
	//AG体育退水比例
	private Float pSpta;
	//VG棋牌退水比例
	private Float pVgqp;
	//可支付通道ID,
//PC的编码网银:1,扫码支付:2,微信:3,支付宝:4,财付通:5,京东:6,银联扫码:7,银行汇款:8 ,快捷支付:9 -------
//手机端的网银:21,扫码支付:22,微信:23,支付宝:24,财付通:25,京东:26,银联扫码:27,银行汇款:28,手机快捷支付:29

	private String paymentChannel;
	//AG视讯积分比例
	private Float jfAgsx;
	//AG捕鱼积分比例
	private Float jfAgby;
	//BBIN视讯积分比例
	private Float jfBbsx;
	//BBIN电子积分比例
	private Float jfBbdz;
	//CG视讯积分比例
	private Float jfCgsx;
	//DS视讯积分比例
	private Float jfDssx;
	//GG捕鱼积分比例
	private Float jfGgby;
	//HABA电子积分比例
	private Float jfHbdz;
	//皇冠体育积分比例
	private Float jfHgty;
	//IG传统彩积分比例
	private Float jfIgctc;
	//MG电子积分比例
	private Float jfMgdz;
	//欧博视讯积分比例
	private Float jfObsx;
	//OG视讯积分比例
	private Float jfOgsx;
	//PT电子积分比例
	private Float jfPtdz;
	//申博视讯积分比例
	private Float jfSbsx;
	//申博电子积分比例
	private Float jfSbdz;
	//IG香港彩积分比例
	private Float jfIgxgc;
	//IG埔京传统彩积分比例
	private Float jfIgpjctc;
	//IG埔京香港彩积分比例
	private Float jfIgpjxgc;
	//VR彩票积分比例
	private Float jfVrcp;
	//yoplay电子积分比例
	private Float jfYoplaydz;
	//开元棋牌积分比例
	private Float jfKyqp;
	//AG体育积分比例
	private Float jfSpta;
	//VG棋牌积分比例
	private Float jfVgqp;
	//GY彩票退水比例
	private Float pGycp;
	//GY彩票积分比例
	private Float jfGycp;
	//PS彩票退水比例
	private Float pPs;
	//PS彩票积分比例
	private Float jfPs;
	//NB体育退水比例
	private Float pNb;
	//NB体育积分比例
	private Float jfNb;
	//乐游棋牌退水比例
	private Float pLyqp;
	//乐游棋牌积分比例
	private Float jfLyqp;
	//乐游棋牌退水比例
	private Float pJdb;
	//乐游棋牌积分比例
	private Float jfJdb;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}

	public Float getpCg() {
		return pCg;
	}

	public void setpCg(Float pCg) {
		this.pCg = pCg;
	}

	public Float getpAg() {
		return pAg;
	}

	public void setpAg(Float pAg) {
		this.pAg = pAg;
	}

	public Float getpShenbogame() {
		return pShenbogame;
	}

	public void setpShenbogame(Float pShenbogame) {
		this.pShenbogame = pShenbogame;
	}

	public Float getpShenbo() {
		return pShenbo;
	}

	public void setpShenbo(Float pShenbo) {
		this.pShenbo = pShenbo;
	}

	public Float getpAb() {
		return pAb;
	}

	public void setpAb(Float pAb) {
		this.pAb = pAb;
	}

	public Float getpDs() {
		return pDs;
	}

	public void setpDs(Float pDs) {
		this.pDs = pDs;
	}

	public Float getpOg() {
		return pOg;
	}

	public void setpOg(Float pOg) {
		this.pOg = pOg;
	}

	public Float getpBbin() {
		return pBbin;
	}

	public void setpBbin(Float pBbin) {
		this.pBbin = pBbin;
	}

	public Float getpBbingame() {
		return pBbingame;
	}

	public void setpBbingame(Float pBbingame) {
		this.pBbingame = pBbingame;
	}

	public Float getpMg() {
		return pMg;
	}

	public void setpMg(Float pMg) {
		this.pMg = pMg;
	}

	public Float getpPt() {
		return pPt;
	}

	public void setpPt(Float pPt) {
		this.pPt = pPt;
	}

	public Float getpHaba() {
		return pHaba;
	}

	public void setpHaba(Float pHaba) {
		this.pHaba = pHaba;
	}

	public Float getpIg() {
		return pIg;
	}

	public void setpIg(Float pIg) {
		this.pIg = pIg;
	}

	public Float getpHg() {
		return pHg;
	}

	public void setpHg(Float pHg) {
		this.pHg = pHg;
	}

	public Float getpGgby() {
		return pGgby;
	}

	public void setpGgby(Float pGgby) {
		this.pGgby = pGgby;
	}

	public Float getpBgvideo() {
		return pBgvideo;
	}

	public void setpBgvideo(Float pBgvideo) {
		this.pBgvideo = pBgvideo;
	}

	public Float getpBglottery() {
		return pBglottery;
	}

	public void setpBglottery(Float pBglottery) {
		this.pBglottery = pBglottery;
	}

	public Integer getBankcardId() {
		return bankcardId;
	}

	public void setBankcardId(Integer bankcardId) {
		this.bankcardId = bankcardId;
	}

	public String getAlipayId() {
		return alipayId;
	}

	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public String getTenpayId() {
		return tenpayId;
	}

	public void setTenpayId(String tenpayId) {
		this.tenpayId = tenpayId;
	}

	public String getOnlinepayId() {
		return onlinepayId;
	}

	public void setOnlinepayId(String onlinepayId) {
		this.onlinepayId = onlinepayId;
	}

	public Float getIntegralRatio() {
		return integralRatio;
	}

	public void setIntegralRatio(Float integralRatio) {
		this.integralRatio = integralRatio;
	}

	public Float getcIntegralRatio() {
		return cIntegralRatio;
	}

	public void setcIntegralRatio(Float cIntegralRatio) {
		this.cIntegralRatio = cIntegralRatio;
	}

	public Float getpHkig() {
		return pHkig;
	}

	public void setpHkig(Float pHkig) {
		this.pHkig = pHkig;
	}

	public Float getpVr() {
		return pVr;
	}

	public void setpVr(Float pVr) {
		this.pVr = pVr;
	}

	public Float getpJf() {
		return pJf;
	}

	public void setpJf(Float pJf) {
		this.pJf = pJf;
	}

	public Float getpJfcp() {
		return pJfcp;
	}

	public void setpJfcp(Float pJfcp) {
		this.pJfcp = pJfcp;
	}

	public Float getpYorplay() {
		return pYorplay;
	}

	public void setpYorplay(Float pYorplay) {
		this.pYorplay = pYorplay;
	}

	public Float getpKyqp() {
		return pKyqp;
	}

	public void setpKyqp(Float pKyqp) {
		this.pKyqp = pKyqp;
	}

	public Float getpSpta() {
		return pSpta;
	}

	public void setpSpta(Float pSpta) {
		this.pSpta = pSpta;
	}

	public Float getpVgqp() {
		return pVgqp;
	}

	public void setpVgqp(Float pVgqp) {
		this.pVgqp = pVgqp;
	}

	public String getPaymentChannel() {
		return paymentChannel;
	}

	public void setPaymentChannel(String paymentChannel) {
		this.paymentChannel = paymentChannel;
	}

	public Float getJfAgsx() {
		return jfAgsx;
	}

	public void setJfAgsx(Float jfAgsx) {
		this.jfAgsx = jfAgsx;
	}

	public Float getJfAgby() {
		return jfAgby;
	}

	public void setJfAgby(Float jfAgby) {
		this.jfAgby = jfAgby;
	}

	public Float getJfBbsx() {
		return jfBbsx;
	}

	public void setJfBbsx(Float jfBbsx) {
		this.jfBbsx = jfBbsx;
	}

	public Float getJfBbdz() {
		return jfBbdz;
	}

	public void setJfBbdz(Float jfBbdz) {
		this.jfBbdz = jfBbdz;
	}

	public Float getJfCgsx() {
		return jfCgsx;
	}

	public void setJfCgsx(Float jfCgsx) {
		this.jfCgsx = jfCgsx;
	}

	public Float getJfDssx() {
		return jfDssx;
	}

	public void setJfDssx(Float jfDssx) {
		this.jfDssx = jfDssx;
	}

	public Float getJfGgby() {
		return jfGgby;
	}

	public void setJfGgby(Float jfGgby) {
		this.jfGgby = jfGgby;
	}

	public Float getJfHbdz() {
		return jfHbdz;
	}

	public void setJfHbdz(Float jfHbdz) {
		this.jfHbdz = jfHbdz;
	}

	public Float getJfHgty() {
		return jfHgty;
	}

	public void setJfHgty(Float jfHgty) {
		this.jfHgty = jfHgty;
	}

	public Float getJfIgctc() {
		return jfIgctc;
	}

	public void setJfIgctc(Float jfIgctc) {
		this.jfIgctc = jfIgctc;
	}

	public Float getJfMgdz() {
		return jfMgdz;
	}

	public void setJfMgdz(Float jfMgdz) {
		this.jfMgdz = jfMgdz;
	}

	public Float getJfObsx() {
		return jfObsx;
	}

	public void setJfObsx(Float jfObsx) {
		this.jfObsx = jfObsx;
	}

	public Float getJfOgsx() {
		return jfOgsx;
	}

	public void setJfOgsx(Float jfOgsx) {
		this.jfOgsx = jfOgsx;
	}

	public Float getJfPtdz() {
		return jfPtdz;
	}

	public void setJfPtdz(Float jfPtdz) {
		this.jfPtdz = jfPtdz;
	}

	public Float getJfSbsx() {
		return jfSbsx;
	}

	public void setJfSbsx(Float jfSbsx) {
		this.jfSbsx = jfSbsx;
	}

	public Float getJfSbdz() {
		return jfSbdz;
	}

	public void setJfSbdz(Float jfSbdz) {
		this.jfSbdz = jfSbdz;
	}

	public Float getJfIgxgc() {
		return jfIgxgc;
	}

	public void setJfIgxgc(Float jfIgxgc) {
		this.jfIgxgc = jfIgxgc;
	}

	public Float getJfIgpjctc() {
		return jfIgpjctc;
	}

	public void setJfIgpjctc(Float jfIgpjctc) {
		this.jfIgpjctc = jfIgpjctc;
	}

	public Float getJfIgpjxgc() {
		return jfIgpjxgc;
	}

	public void setJfIgpjxgc(Float jfIgpjxgc) {
		this.jfIgpjxgc = jfIgpjxgc;
	}

	public Float getJfVrcp() {
		return jfVrcp;
	}

	public void setJfVrcp(Float jfVrcp) {
		this.jfVrcp = jfVrcp;
	}

	public Float getJfYoplaydz() {
		return jfYoplaydz;
	}

	public void setJfYoplaydz(Float jfYoplaydz) {
		this.jfYoplaydz = jfYoplaydz;
	}

	public Float getJfKyqp() {
		return jfKyqp;
	}

	public void setJfKyqp(Float jfKyqp) {
		this.jfKyqp = jfKyqp;
	}

	public Float getJfSpta() {
		return jfSpta;
	}

	public void setJfSpta(Float jfSpta) {
		this.jfSpta = jfSpta;
	}

	public Float getJfVgqp() {
		return jfVgqp;
	}

	public void setJfVgqp(Float jfVgqp) {
		this.jfVgqp = jfVgqp;
	}

	public Float getpGycp() {
		return pGycp;
	}

	public void setpGycp(Float pGycp) {
		this.pGycp = pGycp;
	}

	public Float getJfGycp() {
		return jfGycp;
	}

	public void setJfGycp(Float jfGycp) {
		this.jfGycp = jfGycp;
	}

	public Float getpPs() {
		return pPs;
	}

	public void setpPs(Float pPs) {
		this.pPs = pPs;
	}

	public Float getJfPs() {
		return jfPs;
	}

	public void setJfPs(Float jfPs) {
		this.jfPs = jfPs;
	}

	public Float getpNb() {
		return pNb;
	}

	public void setpNb(Float pNb) {
		this.pNb = pNb;
	}

	public Float getJfNb() {
		return jfNb;
	}

	public void setJfNb(Float jfNb) {
		this.jfNb = jfNb;
	}

	public Float getpLyqp() {
		return pLyqp;
	}

	public void setpLyqp(Float pLyqp) {
		this.pLyqp = pLyqp;
	}

	public Float getJfLyqp() {
		return jfLyqp;
	}

	public void setJfLyqp(Float jfLyqp) {
		this.jfLyqp = jfLyqp;
	}

	public Float getpJdb() {
		return pJdb;
	}

	public void setpJdb(Float pJdb) {
		this.pJdb = pJdb;
	}

	public Float getJfJdb() {
		return jfJdb;
	}

	public void setJfJdb(Float jfJdb) {
		this.jfJdb = jfJdb;
	}
}
