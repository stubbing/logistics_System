package web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import bean.Admin;
import bean.DistrictCenter;
import bean.ProvinceCenter;
import bean.Goods;
import bean.GoodsStatus;
import bean.GoodsStatusId;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

import service.IAdminService;
import service.IConditionsService;
import service.IDistrictCenterService;
import service.IProvinceCenterService;
import service.IGoodsService;
import service.IGoodsStatusService;

public class AllAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1405926533311347411L;
	ActionContext context = ActionContext.getContext();
	protected HttpServletRequest servletRequest = null;
	Logger logger = Logger.getLogger(AllAction.class);
	private IDistrictCenterService districtCenterService;
	private IProvinceCenterService provinceCenterService;
	private IAdminService adminService;
	private IGoodsService goodsService;
	private IGoodsStatusService goodsStatusService;
	private IConditionsService conditionsService;

	private String userid;
	private String password;
	private int type;
	private String newpassword;
	private String goodsId;
	private String senderName;
	private String senderPhone;
	private String senderProvince;
	private String senderCity;
	private String senderAddress;
	private String receiverName;
	private String receiverPhone;
	private String receiverProvince;
	private String receiverCity;
	private String receiverAddress;
	private String senderDistrict;
	private String receiverDistrict;

	public String login() throws Exception {
		// LoginInfo login = null;
		try {
			switch (type) {
			/**
			 * districtCenter login
			 */
			case 1: {
				DistrictCenter districtCenter = districtCenterService.getDistrictCenterByIDAndPwd(userid, password);
				if (districtCenter == null) {
					addActionError("账号或密码输入有误");
					return INPUT;
				} else {
					context.getSession().put("login", districtCenter);
					context.getSession().put("type", "1");
					return "districtCenter";
				}
			}
			/**
			 * provinceCenter login
			 */
			case 2: {
				ProvinceCenter provinceCenter = provinceCenterService.getProvinceCenterByIDAndPwd(userid, password);
				if (provinceCenter == null) {
					addActionError("账号或密码输入有误");
					return INPUT;
				} else {
					context.getSession().put("login", provinceCenter);
					context.getSession().put("type", "2");
					return "provinceCenter";
				}
			}
			/**
			 * admin login
			 */
			case 3: {
				Admin admin = adminService.getAdminByLoginAndPassword(userid, password);
				if (admin == null) {
					addActionError("账号或密码输入有误");
					return INPUT;
				} else {
					context.getSession().put("login", admin);
					context.getSession().put("type", "3");
					return "admin";
				}
			}
			default:
				return INPUT;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return INPUT;
	}

	public String modifyPwd() {
		String type = (String) context.getSession().get("type");
		try {
			switch (type) {
			/**
			 * DistrictCenter modifypwd
			 */
			case "1": {
				DistrictCenter user = (DistrictCenter) context.getSession().get("login");
				if (user.getPwd().equals(password)) {
					user.setPwd(newpassword);
					districtCenterService.save(user);
					return "districtCenter";
				} else {
					addActionError("旧密码输入错误");
					return "inputdistrictCenter";
				}
			}
			/**
			 * ProvinceCenter modifypwd
			 */
			case "2": {
				ProvinceCenter user = (ProvinceCenter) context.getSession().get("login");
				if (user.getPwd().equals(password)) {
					user.setPwd(newpassword);
					provinceCenterService.save(user);
					return "provinceCenter";
				} else {
					addActionError("旧密码输入错误");
					return "inputprovinceCenter";
				}
			}
			/**
			 * Admin modifypwd
			 */
			case "3": {
				Admin user = (Admin) context.getSession().get("login");
				if (user.getPwd().equals(password)) {
					user.setPwd(newpassword);
					adminService.save(user);
					return "admin";
				} else {
					addActionError("旧密码输入错误");
					return "inputadmin";
				}
			}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return SUCCESS;
	}

	@SuppressWarnings("deprecation")
	public String getGoodsID() {
		SimpleDateFormat f = new SimpleDateFormat("MMddHHmmss");
		Date date = new Date();
		String s = f.format(date);
		s += (int) (Math.random() * 10) + "";
		s += (int) (Math.random() * 10) + "";
		s += (int) (Math.random() * 10) + "";
		s += (int) (Math.random() * 10) + "";
		System.out.println(s);
		if (s != null) {
			context.getSession().put("goodsID", s);
			return "getSuccess";
		} else
			return "getfalse";
	}

	public String addGoods() throws Exception {
		Goods goods = new Goods();
		goods.setGoodsId((String) context.getSession().get("goodsID"));
		goods.setSenderName(senderName);
		goods.setSenderPhone(senderPhone);
		goods.setSenderProvince(senderProvince);
		goods.setSenderCity(senderCity);
		goods.setSenderAddress(senderAddress);
		goods.setReceiverName(receiverName);
		goods.setReceiverPhone(receiverPhone);
		goods.setReceiverProvince(receiverProvince);
		goods.setReceiverCity(receiverCity);
		goods.setReceiverAddress(receiverAddress);
		goods.setReceiverDistrict(receiverDistrict);
		goods.setSenderDistrict(senderDistrict);
		goodsService.save(goods);
		GoodsStatusId goodsStatusId = new GoodsStatusId();
		goodsStatusId.setGoodsId(goods.getGoodsId());
		goodsStatusId.setConditionId("1");
		GoodsStatus goodsStatus = new GoodsStatus();
		goodsStatus.setId(goodsStatusId);
		goodsStatus.setGoods(goods);
		goodsStatus.setConditions(conditionsService.getConditionsByConditonsId("1"));
		goodsStatusService.save(goodsStatus);
		if (goods != null) {
			context.getSession().put("goodsinfo", goods);
			return "addSuccess";
		}
		return "addFalse";
	}
	
	public String logout() {
		context.getSession().remove("login");
		context.getSession().remove("type");
		return SUCCESS;
	}

	public ActionContext getContext() {
		return context;
	}

	public void setContext(ActionContext context) {
		this.context = context;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public IDistrictCenterService getDistrictCenterService() {
		return districtCenterService;
	}

	public void setDistrictCenterService(IDistrictCenterService districtCenterService) {
		this.districtCenterService = districtCenterService;
	}

	public IProvinceCenterService getProvinceCenterService() {
		return provinceCenterService;
	}

	public void setProvinceCenterService(IProvinceCenterService provinceCenterService) {
		this.provinceCenterService = provinceCenterService;
	}

	public IAdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the newpassword
	 */
	public String getNewpassword() {
		return newpassword;
	}

	/**
	 * @param newpassword
	 *            the newpassword to set
	 */
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	/**
	 * @return the goodsService
	 */
	public IGoodsService getGoodsService() {
		return goodsService;
	}

	/**
	 * @param goodsService
	 *            the goodsService to set
	 */
	public void setGoodsService(IGoodsService goodsService) {
		this.goodsService = goodsService;
	}

	/**
	 * @return the goodsId
	 */
	public String getGoodsId() {
		return goodsId;
	}

	/**
	 * @param goodsId
	 *            the goodsId to set
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * @return the senderName
	 */
	public String getSenderName() {
		return senderName;
	}

	/**
	 * @param senderName
	 *            the senderName to set
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 * @return the senderPhone
	 */
	public String getSenderPhone() {
		return senderPhone;
	}

	/**
	 * @param senderPhone
	 *            the senderPhone to set
	 */
	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}

	/**
	 * @return the senderProvince
	 */
	public String getSenderProvince() {
		return senderProvince;
	}

	/**
	 * @param senderProvince
	 *            the senderProvince to set
	 */
	public void setSenderProvince(String senderProvince) {
		this.senderProvince = senderProvince;
	}

	/**
	 * @return the senderCity
	 */
	public String getSenderCity() {
		return senderCity;
	}

	/**
	 * @param senderCity
	 *            the senderCity to set
	 */
	public void setSenderCity(String senderCity) {
		this.senderCity = senderCity;
	}

	/**
	 * @return the senderAddress
	 */
	public String getSenderAddress() {
		return senderAddress;
	}

	/**
	 * @param senderAddress
	 *            the senderAddress to set
	 */
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	/**
	 * @return the receiverName
	 */
	public String getReceiverName() {
		return receiverName;
	}

	/**
	 * @param receiverName
	 *            the receiverName to set
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	/**
	 * @return the receiverPhone
	 */
	public String getReceiverPhone() {
		return receiverPhone;
	}

	/**
	 * @param receiverPhone
	 *            the receiverPhone to set
	 */
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	/**
	 * @return the receiverProvince
	 */
	public String getReceiverProvince() {
		return receiverProvince;
	}

	/**
	 * @param receiverProvince
	 *            the receiverProvince to set
	 */
	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}

	/**
	 * @return the receiverCity
	 */
	public String getReceiverCity() {
		return receiverCity;
	}

	/**
	 * @param receiverCity
	 *            the receiverCity to set
	 */
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	/**
	 * @return the receiverAddress
	 */
	public String getReceiverAddress() {
		return receiverAddress;
	}

	/**
	 * @param receiverAddress
	 *            the receiverAddress to set
	 */
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	/**
	 * @return the senderDistrict
	 */
	public String getSenderDistrict() {
		return senderDistrict;
	}

	/**
	 * @param senderDistrict
	 *            the senderDistrict to set
	 */
	public void setSenderDistrict(String senderDistrict) {
		this.senderDistrict = senderDistrict;
	}

	/**
	 * @return the receiverDistrict
	 */
	public String getReceiverDistrict() {
		return receiverDistrict;
	}

	/**
	 * @param receiverDistrict
	 *            the receiverDistrict to set
	 */
	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	/**
	 * @return the goodsStatusService
	 */
	public IGoodsStatusService getGoodsStatusService() {
		return goodsStatusService;
	}

	/**
	 * @param goodsStatusService the goodsStatusService to set
	 */
	public void setGoodsStatusService(IGoodsStatusService goodsStatusService) {
		this.goodsStatusService = goodsStatusService;
	}

	/**
	 * @return the conditionsService
	 */
	public IConditionsService getConditionsService() {
		return conditionsService;
	}

	/**
	 * @param conditionsService the conditionsService to set
	 */
	public void setConditionsService(IConditionsService conditionsService) {
		this.conditionsService = conditionsService;
	}
	
	

}
