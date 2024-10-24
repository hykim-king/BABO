package com.pcwk.ehr.user;

import java.util.Objects;

public class AccountVO {
	public static AccountVO userLoginVO = null; // 로그인 유저 객체
	public static AccountVO adminLoginVO = null; // 관리자 유저 객체
	public static AccountVO targetAccountVO = null; // 송금 기능 구현 시 상대방 객체
	private String AccountNo; // 계좌번호
	private String userName; // 소유주명
	private String userPw; // 계좌 비밀번호
	private String userDob; // 생년월일
	private String regDt; // 가입일
	private String roleName; // 권한
	private double balance; // 잔고

	public AccountVO() {

	}

	public AccountVO(String accountNo, String userName, String userPw, String userDob, String regDt, String roleName,double balance) {
		this.AccountNo = accountNo;
		this.userName = userName;
		this.userPw = userPw;
		this.regDt = regDt;
		this.roleName = roleName;
		this.balance = balance;
		this.userDob = userDob;
	}
	

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountNo() {
		return AccountNo;
	}

	public String getUserDob() {
		return userDob;
	}

	public void setUserDob(String userDob) {
		this.userDob = userDob;
	}

	public void setAccountNo(String accountNo) {
		AccountNo = accountNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(AccountNo, balance, regDt, roleName, userDob, userName, userPw);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountVO other = (AccountVO) obj;
		return Objects.equals(AccountNo, other.AccountNo)
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& Objects.equals(regDt, other.regDt) && Objects.equals(roleName, other.roleName)
				&& Objects.equals(userDob, other.userDob) && Objects.equals(userName, other.userName)
				&& Objects.equals(userPw, other.userPw);
	}

	@Override
	public String toString() {
		return "AccountVO [AccountNo=" + AccountNo + ", userName=" + userName + ", userPw=" + userPw + ", userDob="
				+ userDob + ", regDt=" + regDt + ", roleName=" + roleName + ", balance=" + balance + "]";
	}

}
