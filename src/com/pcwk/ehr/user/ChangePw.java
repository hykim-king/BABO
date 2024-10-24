package com.pcwk.ehr.user;

import java.util.Scanner;

public class ChangePw {
	Scanner scanner = new Scanner(System.in);
	AccountDao dao = new AccountDao();

	public ChangePw() {

	}

	public void userChangePw() { // 사용자가 자신 소유의 비밀번호 변경
		if (AccountVO.userLoginVO != null) {
			System.out.println("===== 계좌 비밀번호 변경 =====");
			System.out.println("계좌 비밀번호 변경을 위해서 정보를 입력해주세요.");
			System.out.print("계좌 소유주명을 입력해주세요: ");
			String inputName = scanner.nextLine();

			System.out.print("생년월일을 입력하세요: ");
			String inputDob = scanner.nextLine();

			System.out.print("현재 비밀번호를 입력하세요: ");
			String inputPw = scanner.nextLine();

			System.out.print("새 비밀번호를 입력하세요: ");
			String newPw = scanner.nextLine();

			if (inputName.equals(AccountVO.userLoginVO.getUserName())
					&& inputDob.equals(AccountVO.userLoginVO.getUserDob())
					&& inputPw.equals(AccountVO.userLoginVO.getUserPw())) {
				AccountVO.userLoginVO.setUserPw(newPw);
				System.out.println("비밀번호 변경 성공!");
				dao.doUpdate();
			} else {
				System.out.println("입력하신 정보가 일치하지 않습니다. 다시 시도해주세요.");
			}
			
		} else {
			System.out.println("로그인 되어있는 계좌가 없습니다.");
		}
	}

	public void adminChangePw() { // 관리자가 특이 상황이 있을 때, 사용자의 비밀번호를 변경

		System.out.println("===== 계좌 비밀번호 변경 =====");
		System.out.println("Admin is not allowed to change user's password in his / her own intention!!! ");
		dao.displayAllAccInfo();

		System.out.print("비밀번호를 변경할 계좌번호를 입력하세요: ");
		String inputAccNo = scanner.nextLine();

		System.out.print("새 비밀번호를 입력하세요: ");
		String inputPw = scanner.nextLine();

		for (AccountVO account : AccountDao.accounts) {
			if (account.getAccountNo().equals(inputAccNo)) {
				account.setUserPw(inputPw);
				System.out.println("비밀번호 변경 성공!");
				dao.doUpdate();
				break;
			} 
			
			System.out.println("비밀번호 변경 실패!");
		}
		

	}

}
