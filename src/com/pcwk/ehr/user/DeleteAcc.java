package com.pcwk.ehr.user;

import java.util.Iterator;
import java.util.Scanner;

public class DeleteAcc {

	RequestAdmin reqAdmin = new RequestAdmin();
	AccountDao dao = new AccountDao();
	Scanner scanner = new Scanner(System.in);

	public DeleteAcc() {

	}

	public void deleteMenu() { // 계좌 삭제 재차 물어보기 위한 메뉴
		System.out.println("===== 계좌 삭제 =====");
		int input = 100;
		while (input != 0) {

			System.out.println();
			dao.displayLoginAccInfo();

			System.out.println("==================");
			System.out.println("삭제 하시겠습니까?");
			System.out.println("1번 - YES");
			System.out.println("0번 - NO");
			input = scanner.nextInt();

			switch (input) {
			case 1:
				deleteAcc();

				break;
			case 0:
				break;
			}
			break;
		}

	}

	public void deleteAcc() { // 계좌 삭제

		Iterator<AccountVO> iter = AccountDao.accounts.iterator();

		while (iter.hasNext()) {
			AccountVO delAcc = iter.next();
			if (delAcc.getAccountNo().equals(AccountVO.userLoginVO.getAccountNo())) {
				iter.remove();
				reqAdmin.setDeleteAccRequest(false);
				System.out.println("계좌 삭제 성공!");
				dao.doUpdate();
			}
		}
	}
}
