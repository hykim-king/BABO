package com.pcwk.ehr.user;

import java.util.Scanner;

public class Menu {
	CreateAccount createAccount = new CreateAccount(); // 계좌 생성
	Login login = new Login(); // 로그인
	Deposit deposit = new Deposit(); // 입금
	RequestAdmin reqAdmin = new RequestAdmin(); // 계좌 삭제 관리자에게 요청
	ChangePw changePw = new ChangePw(); // 비밀번호 변경 (사용자)
	DeleteAcc deleteAcc = new DeleteAcc(); // 관리자가 사용자 계좌 삭제
	Transfer transfer = new Transfer(); // 이체
	Withdraw withdraw = new Withdraw(); // 출금
	Installment installment = new Installment();

	AccountDao dao = new AccountDao();
	Scanner scanner = new Scanner(System.in);

	public void menu0() { // 처음 화면 (메인메뉴) // select 관리자 모드 or 사용자 모드

		int input1 = 3;
		while (input1 != 0) {
			System.out.println("====================================");
			System.out.println("┌────────────────────────┐");
			System.out.println("│ WELCOME TO             │");
			System.out.println("│ BABO BANKING SYSTEM !!!│");
			System.out.println("└────────────────────────┘");
			System.out.println("1. 관리자모드");
			System.out.println("2. 사용자모드");
			System.out.println("0. 종료");
			System.out.println("====================================");
			System.out.print("입력: ");

			input1 = scanner.nextInt();
			switch (input1) {
			case 1: // 관리자 모드
				if (login.isAdminLogin() == false) {
					login.adminLogin();
				}
				if (login.isAdminLogin() == true) {
					adminMenu();
				}
				break;
			case 2: // 사용자 모드
				userMenu();
				break;
			case 0:
				System.out.println("프로그램 종료!");
			default:
				System.out.println("잘못된 입력입니다.");
			}
		}
	}

	public void adminMenu() { // 관리자 모드 메뉴

		int input2 = 0;
		do {
			System.out.println("====================================");
			System.out.println("┌────────────────────────────────┐");
			System.out.println("│  BABO BANK ADMINISTRATOR MODE  │");
			System.out.println("└────────────────────────────────┘");

			System.out.println("1. 모든 계좌 정보 조희");
			System.out.println("2. 계좌 비밀번호 변경 ");
			System.out.println("3. 계좌 삭제");
			System.out.println("0. 관리자 모드 종료");
			System.out.println("====================================");
			System.out.print("입력: ");

			input2 = scanner.nextInt();
			switch (input2) {
			case 1: // 모든 계좌 정보 조회
				dao.displayAllAccInfo();
				break;
			case 2: // 로그인 계좌의 비밀번호 변경
				if (login.isAdminLogin() == true) {
					changePw.adminChangePw();
				} else {
					System.out.println("로그인 먼저 해주세요.");
				}
				break;
			case 3: // 로그인 계좌의 계정 삭제
				if (login.isAdminLogin() == true) {
					if (reqAdmin.isDeleteAccRequest() == true) {
						deleteAcc.deleteMenu();
						break;
					} else {
						System.out.println("계좌 삭제 요청이 없습니다.");
					}
					break;
				} else {
					System.out.println("로그인 먼저 해주세요.");
				}
			case 0:
				System.out.println("관리자 모드 종료!");
				break;
			default:
				System.out.println("잘못된 입력입니다.");
			}

		} while (input2 != 0);
	}

	public void userMenu() { // 사용자 모드
		int input = 0;

		do {
			System.out.println("====================================");
			System.out.println("┌────────────────────────┐");
			System.out.println("│ WELCOME TO             │");
			System.out.println("│ BABO BANKING SYSTEM !!!│");
			System.out.println("└────────────────────────┘");

			System.out.println("1. 계좌 생성");
			System.out.println("2. 로그인");
			System.out.println("3. 입금");
			System.out.println("4. 출금");
			System.out.println("5. 송금");
			System.out.println("6. 적금");
			System.out.println("7. 계좌 정보 조회");
			System.out.println("8. 계좌 비밀번호 변경");
			System.out.println("9. 계좌 삭제 (관리자에게 요청)");
			System.out.println("0. 사용자 모드 종료");
			System.out.println("====================================");
			System.out.print("입력: ");
			input = scanner.nextInt();

			switch (input) {
			case 1: // 계좌 생성
				if(AccountVO.userLoginVO == null) {
				createAccount.createAccount();
				} else {
					System.out.println("로그인 되어있는 상태에서는 계좌를 생성할 수 없습니다.");
				}
				break;

			case 2: // 로그인
				if (AccountVO.userLoginVO != null) {
					System.out.println("이미 로그인 되어있습니다.");
				} else if (AccountVO.userLoginVO == null) {
					login.userLogin();
					break;
				} else {
					System.out.println("잘못된 시도입니다.");
				}
				break;

			case 3: // 입금
				if (login.IsUserLogin() == true) {
					deposit.deposit();
				} else {
					System.out.println("로그인 먼저 해주세요.");
				}
				break;

			case 4: // 출금
				if (login.IsUserLogin() == true) {
					withdraw.withdraw();
				} else {
					System.out.println("로그인 먼저 해주세요.");
				}
				break;

			case 5: // 송금 함수
				if (login.IsUserLogin() == true) {
					transfer.trasnfer();
				} else {
					System.out.println("로그인 먼저 해주세요.");
				}
				break;

			case 6: // 적금
				if (login.IsUserLogin() == true) {
					installment.installmentMenu();
				} else {
					System.out.println("로그인 먼저 해주세요.");
				}
				break;

			case 7: // 계좌 정보 조회 함수
				if (login.IsUserLogin() == true) {
					dao.displayLoginAccInfo();
				} else {
					System.out.println("로그인 먼저 해주세요.");
				}
				break;

			case 8: // 비밀번호 변경 (사용자)
				if (login.IsUserLogin() == true) {
					changePw.userChangePw();
				} else {
					System.out.println("로그인 먼저 해주세요.");
				}
				break;

			case 9: // 계좌 삭제 (관리자에게 요청)
				if (login.IsUserLogin() == true) {
					reqAdmin.requestAdmin();
				} else {
					System.out.println("로그인 먼저 해주세요.");
				}
				break;

			case 0: // 사용자 모드 종료
				System.out.println("메인 메뉴로 돌아갑니다.");
				break;
				
				default:
					System.out.println("잘못된 값입니다.");
			}
			

		} while (input != 0);
	}
}
