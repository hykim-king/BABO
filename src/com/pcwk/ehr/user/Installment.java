package com.pcwk.ehr.user;

import java.util.Calendar;
import java.util.Scanner;

public class Installment {
	static Scanner scanner = new Scanner(System.in);
	AccountDao dao = new AccountDao();
	Calendar calendar = Calendar.getInstance();
	boolean isWonInstallment = false;
	boolean runSuccessful =false;
	
	public boolean isRunSuccessful() {
		return runSuccessful;
	}

	public void setRunSuccessful(boolean runSuccessful) {
		this.runSuccessful = runSuccessful;
	}

	public boolean isWonInstallment() {
		return isWonInstallment;
	}

	public boolean isSuperInstallment() {
		return isSuperInstallment;
	}

	public void setWonInstallment(boolean isWonInstallment) {
		this.isWonInstallment = isWonInstallment;
	}

	public void setSuperInstallment(boolean isSuperInstallment) {
		this.isSuperInstallment = isSuperInstallment;
	}

	boolean isSuperInstallment = false;

	public Installment() {

	}

	public void installmentMenu() { //적금 메뉴

		int input = 0;
		do {
			System.out.println("===== 적금 =====");
			System.out.println("어떤 적금을 드실 건지 고르세요: ");
			System.out.println("1. WON적금");
			System.out.println("2. 일반정기적금");
			System.out.println("0. 적금 메뉴 종료");
			System.out.print("입력: ");
			input = scanner.nextInt();
			switch (input) {
			case 1:
				wonInstallment();
				break;
			case 2:
				superInstallment();
				break;

			case 0:
				System.out.println("적금 메뉴 종료!");
				break;
			}

		} while (input != 0);
	}

	public void runInstallment(int monthlyInput) { //적금 신청

		int input = 3;
		do {
			System.out.println("적금을 신청하시겠습니까? ");
			System.out.println("'예'를 선택할 시, 첫 달의 월불입액이 납입됩니다.");
			System.out.println("1. 예");
			System.out.println("0. 아니오");
			System.out.print("입력: ");
			input = scanner.nextInt();

			switch (input) {

			case 1:
				if (AccountVO.userLoginVO.getBalance() >= monthlyInput) {
					System.out.println("적금 신청이 완료되었습니다.");

					System.out.println("매월 납입 날짜는 " + calendar.get(Calendar.DAY_OF_MONTH) + "일 입니다.");
					System.out.println(
							AccountVO.userLoginVO.getUserName() + "님의 납입 전 잔고는: " + AccountVO.userLoginVO.getBalance());
					AccountVO.userLoginVO.setBalance(AccountVO.userLoginVO.getBalance() - monthlyInput);
					System.out.println(
							AccountVO.userLoginVO.getUserName() + "님의 납입 후 잔고는: " + AccountVO.userLoginVO.getBalance());
					System.out.println("첫 달의 월불입액이 납입되었습니다!");
					dao.doUpdate();
					setRunSuccessful(true);
				} else {
					System.out.println("잔고를 확인해주세요. 돌아갑니다.");
				}
				break;

			case 0:
				System.out.println("적금 신청이 취소되었습니다!");
				break;
			}
		
			break;
		} while (input != 0);
	}

	public void wonInstallment() { // 1번 적금
		if(isWonInstallment == false) {
		System.out.println("===== WON 적금 =====");
		System.out.println("WON적금은 1년 기준 이율이 3.7%입니다. (최소 1년, 비과세)");
		double interestRate = 3.7;
		System.out.print("기간을 입력해주세요 [ ex) 1 ] : ");
		int year = scanner.nextInt();
		if (year >= 1) {
			System.out.print("월불입액을 입력해주세요: ");
			int monthlyInput = scanner.nextInt();

			double totalInterest = interestRate * 0.55; // 2.035
			double totalAmount = ((totalInterest * (monthlyInput * year * 12)) / 100) + (monthlyInput * year * 12);

			System.out.println(year + "년 후 만기 시 수령 금액: " + totalAmount);
			runInstallment(monthlyInput);
			if(runSuccessful == true) {
			setWonInstallment(true);
			}
		} else {
			System.out.println("다시 확인해주세요.");
		}
		}else {
			System.out.println("이미 가입된 적금 상품입니다.");
		}
	}

	public void superInstallment() { // 2번 적금
		if(isSuperInstallment == false) {
		System.out.println("===== SUPER 적금 =====");
		System.out.println("SUPER적금은 1년 기준 이율이 4.2%입니다. (최소 3년, 비과세)");
		double interestRate = 4.2;
		System.out.print("기간을 입력해주세요 [ ex) 3 ] : ");
		int year = scanner.nextInt();
		if(year >= 3) {
			System.out.print("월불입액을 입력해주세요: ");
			int monthlyInput = scanner.nextInt();
			
			double totalInterest = interestRate * 0.55;
			double totalAmount = ((totalInterest * (monthlyInput * year * 12))/100) + (monthlyInput * year * 12);
			
			System.out.println(year + "년 후 만기 시 수령 금액: " + totalAmount);
			runInstallment(monthlyInput);
			if(runSuccessful == true) {
			setSuperInstallment(isSuperInstallment);
			}
		}else {
			System.out.println("다시 확인해주세요.");
		}
	
	}else {
		System.out.println("이미 가입된 적금 상품입니다.");
	}
	} 
	//참고 링크: https://spot.wooribank.com/pot/Dream?withyou=PODEP0021#none

}
