package com.pcwk.ehr.user;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Withdraw {

	AccountDao dao = new AccountDao();
	Scanner scanner = new Scanner(System.in);
	private boolean running = true;
	public Withdraw() {

	}

	public void startTimer() {
		Random random = new Random();
		// 타이머 1분예정 테스트를 위해 10초 수정함
		int totalSeconds = 70;
		// 인증번호 생성
		int code = 100000 + random.nextInt(900000);

		System.out.println("출금 인증번호: (" + code + ")");

		// 타이머 실행
		for (int i = totalSeconds; i >= 0 && running; i--) {
			int minutesLeft = i / 60;
			int secondsLeft = i % 60;

			System.out.printf("\r남은 시간: %02d:%02d", minutesLeft, secondsLeft);

			try {
				// 1초 대기
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				System.err.println("에러가 발생했습니다: " + e.getMessage());
			}

			if (i == 0) {
				System.out.println();
				System.out.println("시간 초과로 메뉴화면으로 돌아갑니다.");
				System.out.println("엔터를 눌러주세요");
				break;
			}
		}
	}

	public void countdown() {
		//재실행을위해 불린값 초기화
		running = true;
		Scanner scanner = new Scanner(System.in);
		String input = "1";

		while (input != "") {
			//스레드에서 따로따로 실행
			Thread timerThread = new Thread(this::startTimer);
			timerThread.start();

			System.out.println("====================================");
			System.out.println("인증이 완료되었으면 엔터를 눌러주세요.");
			System.out.println("====================================");
			input = scanner.nextLine();

			if (input == "") {
				running = false;
			}
			try {
				//타이머 중 발생할수있는 예외처리
				timerThread.join();
			} catch (InterruptedException e) {
				System.err.println("에러가 발생 하였습니다: " + e.getMessage());
			}
		}
	}




	public void withdraw() {
		if (AccountVO.userLoginVO != null) {
			System.out.println("===== 출금 =====");
			System.out.print("계좌에서 출금하실 금액을 입력해주세요: ");
			double withdrawAmount = scanner.nextDouble();
			if (withdrawAmount > 0) {
				System.out.println("출금 전 잔고:" + AccountVO.userLoginVO.getBalance());
				AccountVO.userLoginVO.setBalance(AccountVO.userLoginVO.getBalance() - withdrawAmount);
				System.out.println("소유주명:" + AccountVO.userLoginVO.getUserName());
				System.out.println("출금 후 잔고:" + AccountVO.userLoginVO.getBalance());
				countdown();

				dao.doUpdate();
			} else {
				System.out.println("잘못된 값입니다.");
			}
		} else {
			System.out.println("로그인 되어있는 계좌가 없습니다.");
		}

	}

}
