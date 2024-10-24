package com.pcwk.ehr.user;

import java.util.Scanner;

public class Login {

	private boolean isUserLogin = false; // 사용자로그인 상태값 // T - 로그인 상태 F - 로그아웃 상태
	private boolean isAdminLogin = false; // 관리자 로그인 상태값 // T - 로그인 상태 F - 로그아웃 상태
	private String inputAN = ""; // 로그인 계좌의 계좌번호 입력값
	private String inputPw = ""; // 로그인 계좌의 계좌 비밀번호 입력값
	Scanner scanner = new Scanner(System.in);
	AccountDao dao = new AccountDao();

	public Login() {
		
	}
	
	
	public boolean isAdminLogin() {
		return isAdminLogin;
	}


	public void setAdminLogin(boolean isAdminLogin) {
		this.isAdminLogin = isAdminLogin;
	}


	public boolean IsUserLogin() {
		return isUserLogin;
	}

	public void setUserLogin(boolean isUserLogin) {
		this.isUserLogin = isUserLogin;
	}
	
	public void userLogin() { // 사용자 로그인 
		System.out.println("===== 로그인 =====");
		System.out.print("계좌번호를 입력하세요: ");
		inputAN = scanner.nextLine();

		System.out.print("비밀번호를 입력하세요: ");
		inputPw = scanner.nextLine();
		if(inputAN.equals("99999999")) {
			System.out.println("관리자 계좌로는 로그인할 수 없습니다.");
			return;
		}
		for (AccountVO account : AccountDao.accounts) {
			if (account.getAccountNo().equals(inputAN) && account.getUserPw().equals(inputPw)) {
				
				setUserLogin(true);
				AccountVO.userLoginVO = account;
				dao.displayLoginAccInfo();
				System.out.println("로그인 성공!");
				return;
			} 
		}
		
		
		System.out.println("계좌번호 혹은 비밀번호가 일치하지 않습니다.");
	} // userLogin() end
	
	public void adminLogin() { // 관리자 로그인
		System.out.println("===== 관리자 로그인 ======");
		System.out.print("계좌번호를 입력하세요: ");
		inputAN = scanner.nextLine();
		
		System.out.print("비밀번호를 입력하세요: ");
		inputPw = scanner.nextLine();
		
		for(AccountVO account : AccountDao.accounts) {
			if(account.getAccountNo().equals("99999999") && account.getUserPw().equals("9999")) {
				if(account.getAccountNo().equals(inputAN) && account.getUserPw().equals(inputPw)) {
					System.out.println("'관리자' 로그인 성공!");
					setAdminLogin(true);
					AccountVO.adminLoginVO = account;
					return;
				}
			}
		}
		System.out.println("계좌번호 혹은 비밀번호가 일치하지 않습니다.");
	} //adminLogin() end
}
