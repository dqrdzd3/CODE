package com.hw.hwsafe.knowledge.pojo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

/**
 * 
 * 
 * 项目名称：framework
 * 类名称：Tmsds
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-5-29 上午10:38:37
 * 修改人：李玉梅
 * 修改时间：2012-5-29 上午10:38:37
 * 修改备注：
 * @version 
 *
 */
public class TmsdsPO {
	// Fields
	/**
	 * 主键
	 */
	private String objid;
	/**
	 * 化学品中文名称
	 */
	private String chnname;
	/**
	 * 技术说明书编码
	 */
	private String suming;
	/**
	 * 化学品英文名称
	 */
	private String engname;
	/**
	 * 化学品俗名或商品名
	 */
	private String tinfo;
	/**
	 * 国家应急电话
	 */
	private String countrytel;
	/**
	 * 纯品/混合物
	 */
	private String type;
	/**
	 * 化学品名称
	 */
	private String hxpname;
	/**
	 * 有害物成分
	 */
	private String ingredient;
	/**
	 * 含量
	 */
	private String content;
	/**
	 * CAS No.
	 */
	private String casnum;
	/**
	 * 危险性类别
	 */
	private String dangersort;
	/**
	 * 侵入途径
	 */
	private String invade;
	/**
	 * 健康危害
	 */
	private String healthharm;
	/**
	 * 环境危害
	 */
	private String environment;
	/**
	 * 燃爆危险
	 */
	private String blast;
	/**
	 * 皮肤接触
	 */
	private String skincontact;
	/**
	 * 眼睛接触
	 */
	private String eyecontact;
	/**
	 * 吸 入
	 */
	private String breathe;
	/**
	 * 食 入
	 */
	private String eat;
	/**
	 * 危险特性
	 */
	private String dangercha;
	/**
	 * 有害燃烧产物
	 */
	private String decompose;
	/**
	 * 灭火方法及灭火剂
	 */
	private String fireoutmethod;
	/**
	 * 灭火注意事项
	 */
	private String fireoutnotice;
	/**
	 * 应急处理
	 */
	private String contingencymethod;
	/**
	 * 消除方法
	 */
	private String eliminatemethod;
	/**
	 * 操作注意事项
	 */
	private String operatenotice;
	/**
	 * 储存注意事项
	 */
	private String savenotice;
	/**
	 * 最高容许浓度
	 */
	private String maxallowableconcentration;
	/**
	 * 监测方法
	 */
	private String monitoringmethod;
	/**
	 * 工程控制
	 */
	private String procontrol;
	/**
	 * 呼吸系统防护
	 */
	private String breathedefend;
	/**
	 * 眼睛防护
	 */
	private String eyedefend;
	/**
	 * 身体防护
	 */
	private String suit;
	/**
	 * 手防护
	 */
	private String handdefend;
	/**
	 * 其他防护
	 */
	private String otherdefend;
	/**
	 * 外观与性状
	 */
	private String aspect;
	/**
	 * PH值
	 */
	private String ph;
	/**
	 * 熔点（℃）
	 */
	private String melpoint;
	/**
	 * 相对密度（水=1）
	 */
	private String densityw;
	/**
	 * 沸点（℃）
	 */
	private String boipoint;
	/**
	 * 相对蒸气密度（空气=1）
	 */
	private String densitya;
	/**
	 * 饱和蒸气压（kPa）
	 */
	private String streampress;
	/**
	 * 燃烧热（kJ/mol）
	 */
	private String burningheat;
	/**
	 * 临界温度（℃）
	 */
	private String criticialtem;
	/**
	 * 临界压力（MPa）
	 */
	private String criticialpre;
	/**
	 * 辛醇/水分配系数的对数值
	 */
	private String wdatavalues;
	/**
	 * 闪点（℃）
	 */
	private String flapoint;
	/**
	 * 爆炸上限%（V/V）
	 */
	private String highexplode;
	/**
	 * 引燃温度（℃）
	 */
	private String selfignitetem;
	/**
	 * 爆炸下限%（V/V）
	 */
	private String lowexplode;
	/**
	 * 溶解性
	 */
	private String resolvable;
	/**
	 * 主要用途
	 */
	private String purpose;
	/**
	 * 其他理化性质
	 */
	private String physicochemicalproperty;
	/**
	 * 稳定性
	 */
	private String stability;
	/**
	 * 禁配物
	 */
	private String taboo;
	/**
	 * 避免接触的条件
	 */
	private String avertcontact;
	/**
	 * 聚合危害
	 */
	private String polymerize;
	/**
	 * 分解产物
	 */
	private String cleavageproduct;
	/**
	 * 急性毒性
	 */
	private String acutetoxicity;
	/**
	 * 亚急性和慢性毒性
	 */
	private String subac;
	/**
	 * 刺激性
	 */
	private String thrill;
	/**
	 * 致敏性
	 */
	private String sensitization;
	/**
	 * 致突变性
	 */
	private String mutagenicity;
	/**
	 * 致畸性
	 */
	private String teratogenicity;
	/**
	 * 致癌性
	 */
	private String carcinogenicity;
	/**
	 * 其 他
	 */
	private String otherpathopoiesia;
	/**
	 * 生态毒性
	 */
	private String ecotoxicity;
	/**
	 * 生物降解性
	 */
	private String biodegradability;
	/**
	 * 非生物降解性
	 */
	private String naturalabioticdegradation;
	/**
	 * 生物富集或生物积累性
	 */
	private String bioconcentration;
	/**
	 * 其他有害作用
	 */
	private String otherharmful;
	/**
	 * 是否废物危害
	 */
	private String scrapcharacter;
	/**
	 * 废弃处置方法
	 */
	private String scrapmethod;
	/**
	 * 废弃注意事项
	 */
	private String scrapnotice;
	/**
	 * 危险货物编号
	 */
	private String wxhwnum;
	/**
	 * UN编号
	 */
	private String unnum;
	/**
	 * 包装标志
	 */
	private String packtitle;
	/**
	 * 包装类别
	 */
	private String packsort;
	/**
	 * 包装方法
	 */
	private String packmethod;
	/**
	 * 运输注意事项
	 */
	private String trafficnotice;
	/**
	 * 法规信息
	 */
	private String law;
	/**
	 * 参考文献
	 */
	private String referencedocumentation;
	/**
	 * 填表日期
	 */
	private Date inputdate;
	/**
	 * 填表部门
	 */
	private String inputdept;
	/**
	 * 数据审核单位
	 */
	private String applydept;
	/**
	 * 修改说明
	 */
	private String modifyremark;
	/**
	 * 其他信息
	 */
	private String other;
	/**
	 * 企业名称
	 */
	private String corpname;
	/**
	 * 企业地址
	 */
	private String address;
	/**
	 * 邮编
	 */
	private String postcode;
	/**
	 * 邮件地址
	 */
	private String email;
	/**
	 * 传真
	 */
	private String fax;
	/**
	 * 企业电话
	 */
	private String corptel;
	/**
	 * 生效日期
	 */
	private Date availabilitydate;
	/**
	 * 填表日期止
	 */
	private Date inputdateEnd;
	/**
	 * 生效日期止
	 */
	private Date availabilitydateEnd;
	/**
	 * 填表人
	 */
	private String creator;
	
	
	public String getObjid() {
		return objid;
	}
	public void setObjid(String objid) {
		this.objid = objid;
	}
	public String getChnname() {
		return chnname;
	}
	public void setChnname(String chnname) {
		this.chnname = chnname;
	}
	public String getSuming() {
		return suming;
	}
	public void setSuming(String suming) {
		this.suming = suming;
	}
	public String getEngname() {
		return engname;
	}
	public void setEngname(String engname) {
		this.engname = engname;
	}
	public String getTinfo() {
		return tinfo;
	}
	public void setTinfo(String tinfo) {
		this.tinfo = tinfo;
	}
	public String getCountrytel() {
		return countrytel;
	}
	public void setCountrytel(String countrytel) {
		this.countrytel = countrytel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHxpname() {
		return hxpname;
	}
	public void setHxpname(String hxpname) {
		this.hxpname = hxpname;
	}
	public String getIngredient() {
		return ingredient;
	}
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCasnum() {
		return casnum;
	}
	public void setCasnum(String casnum) {
		this.casnum = casnum;
	}
	public String getDangersort() {
		return dangersort;
	}
	public void setDangersort(String dangersort) {
		this.dangersort = dangersort;
	}
	public String getInvade() {
		return invade;
	}
	public void setInvade(String invade) {
		this.invade = invade;
	}
	public String getHealthharm() {
		return healthharm;
	}
	public void setHealthharm(String healthharm) {
		this.healthharm = healthharm;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getBlast() {
		return blast;
	}
	public void setBlast(String blast) {
		this.blast = blast;
	}
	public String getSkincontact() {
		return skincontact;
	}
	public void setSkincontact(String skincontact) {
		this.skincontact = skincontact;
	}
	public String getEyecontact() {
		return eyecontact;
	}
	public void setEyecontact(String eyecontact) {
		this.eyecontact = eyecontact;
	}
	public String getBreathe() {
		return breathe;
	}
	public void setBreathe(String breathe) {
		this.breathe = breathe;
	}
	public String getEat() {
		return eat;
	}
	public void setEat(String eat) {
		this.eat = eat;
	}
	public String getDangercha() {
		return dangercha;
	}
	public void setDangercha(String dangercha) {
		this.dangercha = dangercha;
	}
	public String getDecompose() {
		return decompose;
	}
	public void setDecompose(String decompose) {
		this.decompose = decompose;
	}
	public String getFireoutmethod() {
		return fireoutmethod;
	}
	public void setFireoutmethod(String fireoutmethod) {
		this.fireoutmethod = fireoutmethod;
	}
	public String getFireoutnotice() {
		return fireoutnotice;
	}
	public void setFireoutnotice(String fireoutnotice) {
		this.fireoutnotice = fireoutnotice;
	}
	public String getContingencymethod() {
		return contingencymethod;
	}
	public void setContingencymethod(String contingencymethod) {
		this.contingencymethod = contingencymethod;
	}
	public String getEliminatemethod() {
		return eliminatemethod;
	}
	public void setEliminatemethod(String eliminatemethod) {
		this.eliminatemethod = eliminatemethod;
	}
	public String getOperatenotice() {
		return operatenotice;
	}
	public void setOperatenotice(String operatenotice) {
		this.operatenotice = operatenotice;
	}
	public String getSavenotice() {
		return savenotice;
	}
	public void setSavenotice(String savenotice) {
		this.savenotice = savenotice;
	}
	public String getMaxallowableconcentration() {
		return maxallowableconcentration;
	}
	public void setMaxallowableconcentration(String maxallowableconcentration) {
		this.maxallowableconcentration = maxallowableconcentration;
	}
	public String getMonitoringmethod() {
		return monitoringmethod;
	}
	public void setMonitoringmethod(String monitoringmethod) {
		this.monitoringmethod = monitoringmethod;
	}
	public String getProcontrol() {
		return procontrol;
	}
	public void setProcontrol(String procontrol) {
		this.procontrol = procontrol;
	}
	public String getBreathedefend() {
		return breathedefend;
	}
	public void setBreathedefend(String breathedefend) {
		this.breathedefend = breathedefend;
	}
	public String getEyedefend() {
		return eyedefend;
	}
	public void setEyedefend(String eyedefend) {
		this.eyedefend = eyedefend;
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	public String getHanddefend() {
		return handdefend;
	}
	public void setHanddefend(String handdefend) {
		this.handdefend = handdefend;
	}
	public String getOtherdefend() {
		return otherdefend;
	}
	public void setOtherdefend(String otherdefend) {
		this.otherdefend = otherdefend;
	}
	public String getAspect() {
		return aspect;
	}
	public void setAspect(String aspect) {
		this.aspect = aspect;
	}
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	public String getMelpoint() {
		return melpoint;
	}
	public void setMelpoint(String melpoint) {
		this.melpoint = melpoint;
	}
	public String getDensityw() {
		return densityw;
	}
	public void setDensityw(String densityw) {
		this.densityw = densityw;
	}
	public String getBoipoint() {
		return boipoint;
	}
	public void setBoipoint(String boipoint) {
		this.boipoint = boipoint;
	}
	public String getDensitya() {
		return densitya;
	}
	public void setDensitya(String densitya) {
		this.densitya = densitya;
	}
	public String getStreampress() {
		return streampress;
	}
	public void setStreampress(String streampress) {
		this.streampress = streampress;
	}
	public String getBurningheat() {
		return burningheat;
	}
	public void setBurningheat(String burningheat) {
		this.burningheat = burningheat;
	}
	public String getCriticialtem() {
		return criticialtem;
	}
	public void setCriticialtem(String criticialtem) {
		this.criticialtem = criticialtem;
	}
	public String getCriticialpre() {
		return criticialpre;
	}
	public void setCriticialpre(String criticialpre) {
		this.criticialpre = criticialpre;
	}
	public String getWdatavalues() {
		return wdatavalues;
	}
	public void setWdatavalues(String wdatavalues) {
		this.wdatavalues = wdatavalues;
	}
	public String getFlapoint() {
		return flapoint;
	}
	public void setFlapoint(String flapoint) {
		this.flapoint = flapoint;
	}
	public String getHighexplode() {
		return highexplode;
	}
	public void setHighexplode(String highexplode) {
		this.highexplode = highexplode;
	}
	public String getSelfignitetem() {
		return selfignitetem;
	}
	public void setSelfignitetem(String selfignitetem) {
		this.selfignitetem = selfignitetem;
	}
	public String getLowexplode() {
		return lowexplode;
	}
	public void setLowexplode(String lowexplode) {
		this.lowexplode = lowexplode;
	}
	public String getResolvable() {
		return resolvable;
	}
	public void setResolvable(String resolvable) {
		this.resolvable = resolvable;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getPhysicochemicalproperty() {
		return physicochemicalproperty;
	}
	public void setPhysicochemicalproperty(String physicochemicalproperty) {
		this.physicochemicalproperty = physicochemicalproperty;
	}
	public String getStability() {
		return stability;
	}
	public void setStability(String stability) {
		this.stability = stability;
	}
	public String getTaboo() {
		return taboo;
	}
	public void setTaboo(String taboo) {
		this.taboo = taboo;
	}
	public String getAvertcontact() {
		return avertcontact;
	}
	public void setAvertcontact(String avertcontact) {
		this.avertcontact = avertcontact;
	}
	public String getPolymerize() {
		return polymerize;
	}
	public void setPolymerize(String polymerize) {
		this.polymerize = polymerize;
	}
	public String getCleavageproduct() {
		return cleavageproduct;
	}
	public void setCleavageproduct(String cleavageproduct) {
		this.cleavageproduct = cleavageproduct;
	}
	public String getAcutetoxicity() {
		return acutetoxicity;
	}
	public void setAcutetoxicity(String acutetoxicity) {
		this.acutetoxicity = acutetoxicity;
	}
	public String getSubac() {
		return subac;
	}
	public void setSubac(String subac) {
		this.subac = subac;
	}
	public String getThrill() {
		return thrill;
	}
	public void setThrill(String thrill) {
		this.thrill = thrill;
	}
	public String getSensitization() {
		return sensitization;
	}
	public void setSensitization(String sensitization) {
		this.sensitization = sensitization;
	}
	public String getMutagenicity() {
		return mutagenicity;
	}
	public void setMutagenicity(String mutagenicity) {
		this.mutagenicity = mutagenicity;
	}
	public String getTeratogenicity() {
		return teratogenicity;
	}
	public void setTeratogenicity(String teratogenicity) {
		this.teratogenicity = teratogenicity;
	}
	public String getCarcinogenicity() {
		return carcinogenicity;
	}
	public void setCarcinogenicity(String carcinogenicity) {
		this.carcinogenicity = carcinogenicity;
	}
	public String getOtherpathopoiesia() {
		return otherpathopoiesia;
	}
	public void setOtherpathopoiesia(String otherpathopoiesia) {
		this.otherpathopoiesia = otherpathopoiesia;
	}
	public String getEcotoxicity() {
		return ecotoxicity;
	}
	public void setEcotoxicity(String ecotoxicity) {
		this.ecotoxicity = ecotoxicity;
	}
	public String getBiodegradability() {
		return biodegradability;
	}
	public void setBiodegradability(String biodegradability) {
		this.biodegradability = biodegradability;
	}
	public String getNaturalabioticdegradation() {
		return naturalabioticdegradation;
	}
	public void setNaturalabioticdegradation(String naturalabioticdegradation) {
		this.naturalabioticdegradation = naturalabioticdegradation;
	}
	public String getBioconcentration() {
		return bioconcentration;
	}
	public void setBioconcentration(String bioconcentration) {
		this.bioconcentration = bioconcentration;
	}
	public String getOtherharmful() {
		return otherharmful;
	}
	public void setOtherharmful(String otherharmful) {
		this.otherharmful = otherharmful;
	}
	public String getScrapcharacter() {
		return scrapcharacter;
	}
	public void setScrapcharacter(String scrapcharacter) {
		this.scrapcharacter = scrapcharacter;
	}
	public String getScrapmethod() {
		return scrapmethod;
	}
	public void setScrapmethod(String scrapmethod) {
		this.scrapmethod = scrapmethod;
	}
	public String getScrapnotice() {
		return scrapnotice;
	}
	public void setScrapnotice(String scrapnotice) {
		this.scrapnotice = scrapnotice;
	}
	public String getWxhwnum() {
		return wxhwnum;
	}
	public void setWxhwnum(String wxhwnum) {
		this.wxhwnum = wxhwnum;
	}
	public String getUnnum() {
		return unnum;
	}
	public void setUnnum(String unnum) {
		this.unnum = unnum;
	}
	public String getPacktitle() {
		return packtitle;
	}
	public void setPacktitle(String packtitle) {
		this.packtitle = packtitle;
	}
	public String getPacksort() {
		return packsort;
	}
	public void setPacksort(String packsort) {
		this.packsort = packsort;
	}
	public String getPackmethod() {
		return packmethod;
	}
	public void setPackmethod(String packmethod) {
		this.packmethod = packmethod;
	}
	public String getTrafficnotice() {
		return trafficnotice;
	}
	public void setTrafficnotice(String trafficnotice) {
		this.trafficnotice = trafficnotice;
	}
	public String getLaw() {
		return law;
	}
	public void setLaw(String law) {
		this.law = law;
	}
	public String getReferencedocumentation() {
		return referencedocumentation;
	}
	public void setReferencedocumentation(String referencedocumentation) {
		this.referencedocumentation = referencedocumentation;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getInputdate() {
		return inputdate;
	}
	public void setInputdate(Date inputdate) {
		this.inputdate = inputdate;
	}
	public String getInputdept() {
		return inputdept;
	}
	public void setInputdept(String inputdept) {
		this.inputdept = inputdept;
	}
	public String getApplydept() {
		return applydept;
	}
	public void setApplydept(String applydept) {
		this.applydept = applydept;
	}
	public String getModifyremark() {
		return modifyremark;
	}
	public void setModifyremark(String modifyremark) {
		this.modifyremark = modifyremark;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getCorpname() {
		return corpname;
	}
	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getCorptel() {
		return corptel;
	}
	public void setCorptel(String corptel) {
		this.corptel = corptel;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getAvailabilitydate() {
		return availabilitydate;
	}
	public void setAvailabilitydate(Date availabilitydate) {
		this.availabilitydate = availabilitydate;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getAvailabilitydateEnd() {
		return availabilitydateEnd;
	}
	public void setAvailabilitydateEnd(Date availabilitydateEnd) {
		this.availabilitydateEnd = availabilitydateEnd;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getInputdateEnd() {
		return inputdateEnd;
	}
	public void setInputdateEnd(Date inputdateEnd) {
		this.inputdateEnd = inputdateEnd;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}
