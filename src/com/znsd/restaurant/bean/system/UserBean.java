package com.znsd.restaurant.bean.system;

//用户
public class UserBean {
	private int userId;//用户id
	private String userName;//用户名
	private String password;//密码
	private String createTime;//创建时间
	private String trueName;//真实姓名
	private String phone;//预留号码
	private String email;//电子邮件
	private String address;//收货地址
	private double money;//消费总金额
	private String role;//系统角色

	public UserBean(int userId, String userName, String password, String createTime, String trueName, String phone, String email, String address, double money) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.createTime = createTime;
		this.trueName = trueName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.money = money;
	}

	public UserBean(int userId, String userName, String role) {
		this.userId = userId;
		this.userName = userName;
		this.role = role;
	}

	public UserBean(int userId, String userName, String createTime, String trueName, String phone, String email, String address, double money) {
		this.userId = userId;
		this.userName = userName;
		this.createTime = createTime;
		this.trueName = trueName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.money = money;
	}

	public UserBean(String userName, String password, String phone) {
		this.userName = userName;
		this.password = password;
		this.phone = phone;
	}

    public UserBean(String userName, String trueName, String phone, String email, String address) {
        this.userName = userName;
        this.trueName = trueName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public UserBean(int userId, String userName, String trueName, String phone, String email, String address) {
        this.userId = userId;
        this.userName = userName;
        this.trueName = trueName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public UserBean(int userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}



	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "UserBean{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", createTime='" + createTime + '\'' +
				", trueName='" + trueName + '\'' +
				", phone='" + phone + '\'' +
				", email='" + email + '\'' +
				", address='" + address + '\'' +
				", money=" + money +
				", role='" + role + '\'' +
				'}';
	}
}
