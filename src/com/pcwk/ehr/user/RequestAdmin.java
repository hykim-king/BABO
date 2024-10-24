package com.pcwk.ehr.user;

import java.util.Scanner;

public class RequestAdmin {
	Scanner scanner = new Scanner(System.in);

	boolean deleteAccRequest = false;

//	public boolean isDeleteAccRequest() {
//		return deleteAccRequest;
//	}
//
//	public void setDeleteAccRequest(boolean deleteAccRequest) {
//		this.deleteAccRequest = deleteAccRequest;
//	}

	public RequestAdmin() {

	}

	public boolean isDeleteAccRequest() {
		return deleteAccRequest;
	}

	public void setDeleteAccRequest(boolean deleteAccRequest) {
		this.deleteAccRequest = deleteAccRequest;
	}

	public void requestAdmin() {
		int input = 0;

		do {
			System.out.println("계좌를 정말 삭제하시려면 1번을 입력하세요.");
			System.out.println("1. YES");
			System.out.println("0. NO");
			System.out.print("입력: ");
			input = scanner.nextInt();

			switch (input) {
			case 1:
				reqDeleteAcc();
				input = 0;
				break;

			case 0:
				System.out.println("사용자 모드로 돌아갑니다.");
				break;
			}

		} while (input != 0);

	}

	public void reqDeleteAcc() {
		if (AccountVO.userLoginVO != null) {
			System.out.println("===== 계좌 삭제 =====");
			System.out.println("계좌를 삭제하기 위해서 정보를 입력해주세요.");
			scanner.nextLine();
			System.out.print("계좌 소유주명을 입력해주세요: ");
			String inputName = scanner.nextLine();

			System.out.print("생년월일을 입력해주세요: ");
			String inputDob = scanner.nextLine();

			System.out.print("계좌 비밀번호를 입력해주세요: ");
			String inputPw = scanner.nextLine();

			if (inputName.equals(AccountVO.userLoginVO.getUserName())
					&& inputDob.equals(AccountVO.userLoginVO.getUserDob())
					&& inputPw.equals(AccountVO.userLoginVO.getUserPw())) {
				System.out.println("모든 정보가 입력되었습니다. 관리자 승인까지 기다려주세요.");
				setDeleteAccRequest(true);
			} else {
				System.out.println("잘못된 값이 입력되었습니다. 다시 시도해주세요.");
				setDeleteAccRequest(false);
			}

		} else {
			System.out.println("로그인 되어있는 계좌가 없습니다.");
		}
	}
}
