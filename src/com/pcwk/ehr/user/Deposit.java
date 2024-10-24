package com.pcwk.ehr.user;

import java.util.Scanner;

public class Deposit {
	AccountDao dao = new AccountDao();
	Scanner scanner = new Scanner(System.in);
	public Deposit() {
		
	}

	public void deposit() { // 입금
		
		if(AccountVO.userLoginVO != null) {
		System.out.println("===== 입금 =====");
		System.out.print("계좌에 입금하실 금액을 입력해주세요: ");
		double depositAmount = scanner.nextDouble();
		
		if(depositAmount>0) {
		System.out.println("입금 전 잔고: " + AccountVO.userLoginVO.getBalance());
		AccountVO.userLoginVO.setBalance(depositAmount + AccountVO.userLoginVO.getBalance());
		System.out.println("소유주명: " + AccountVO.userLoginVO.getUserName());
		System.out.println("입금 후 잔고: " + AccountVO.userLoginVO.getBalance());
		dao.doUpdate();
		} else {
			System.out.println("잘못된 값입니다.");
		}
		} else {
			System.out.println("로그인 되어있는 계좌가 없습니다.");
		}
		
		
	}
}
