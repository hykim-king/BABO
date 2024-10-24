package com.pcwk.ehr.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.WorkDiv;

public class AccountDao implements WorkDiv<AccountVO> {

	public static List<AccountVO> accounts = new ArrayList<AccountVO>(); // 계좌 정보를 담을 arraylist

	public AccountDao() {

	}

	public void displayAllAccInfo() { // 모든 계좌들의 정보 출력 (관리자 사용)
		if (accounts.isEmpty()) { // 컬렉션에 아무 객체 정보가 없을 때
			System.out.println("등록된 멤버가 없습니다.");
			return;
		}
		int i = 1;
		System.out.println("총 계좌 수: " + accounts.size()); // 총 계좌 수
		for (AccountVO account : accounts) {

			System.out.println(i + "번 계좌");
			System.out.println("계좌번호: " +   account.getAccountNo() + 
							   ", 이름: " +    account.getUserName() + 
					           ", 생년월일: " + account.getUserDob() +
					           ", 가입일: " +   account.getRegDt() + 
				           	   ", 잔액: " +    account.getBalance());
			System.out.println();
			i++;
		}
	}

	public void displayLoginAccInfo() { // 로그인 계좌 정보 출력
		if (accounts.isEmpty()) {
			System.out.println("로그인 된 계좌가 없습니다.");
			return;
		}
		System.out.println("===== 계좌 정보 =====");
		System.out.println("계좌번호: "  + AccountVO.userLoginVO.getAccountNo());
		System.out.println("소유주명: "  + AccountVO.userLoginVO.getUserName());
		System.out.println("생년월일: "  + AccountVO.userLoginVO.getUserDob());
		System.out.println("가입일: "   + AccountVO.userLoginVO.getRegDt());
		System.out.println("잔액: "    + AccountVO.userLoginVO.getBalance());
	}

	/**
	 * 1(성공)/0(실패)/2(memberId 중복)
	 */
	private boolean isExistsAccount(AccountVO account) { // 계좌 중복 처리 (컬렉션에 같은 계좌번호를 가진 계좌가 있으면 flag 0값
		boolean flag = false;

		for (AccountVO vo : accounts) {
			if (vo.getAccountNo().equals(account.getAccountNo())) {
				flag = true;
				return flag;
			}
		}
		return flag;
	}

	/**
	 * flag가 1이면 성공 flag가 0이면 실패 (else 처리 느낌) flag가 2면 accountNo 중복
	 */
	@Override
	public int doSave(AccountVO param) { // accounts(arraylist)에 객체를 저장하는 메소드
		// 1. 저장하기 전에 동일한 accountNo가 이미 있는지 확인
		// 2. 인자값을 통해 받은 데이터를 accounts에 추가.
		
		int flag = 0; // 상태값

		if (isExistsAccount(param) == true) { // 이미 저장되어있는 accountNo라면, flag값을 2를 반환하고 stop.
			flag = 2;
			return flag; // 교수님은 break 씀
		}

		boolean check = this.accounts.add(param); // 위에 if문에 걸리지 않았으면 객체를 accounts에 저장, 저장 되었으면 true, 아니라면 false
		flag = check == true ? 1 : 0; // true면 1을 주고 false면 0

		return flag;
	}

	@Override
	public void doUpdate() { //txt파일 실시간처럼 업데이트 되도록 설정

		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("accounts.txt"), "UTF-8"))) {
			for (AccountVO account : accounts) {
				String accStr = account.getAccountNo() + "," +
								account.getUserName() + "," +
								account.getUserPw()+ "," +
								account.getUserDob() + "," +
								account.getRegDt() + "," +
								account.getRoleName() + "," +
								account.getBalance();
				bw.write(accStr);
				bw.newLine();
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println("UnsupportedEncodingException: " + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
	}

	@Override
	public int doDelete(AccountVO param) { // accountNo를 가지고 회원 목록에서 찾아서 삭제 //return값이 1이 들어오면 삭제 성공, 0이 들어오면 삭제 실패
		int flag = 0; // 일단 0값 설정, 성공하면 1로 바꾸려고.

		for (AccountVO vo : accounts) { // list를 순회
			if (vo.getAccountNo().equals(param.getAccountNo())) { // 리스트의 계좌번호가 param값의 계좌번호가 같으면
				accounts.remove(param); // param 객체 삭제 후 flag에 1 할당(메인에서 콘솔 출력)
				flag = 1;
				return flag;
			}
		}
		return flag;
	}

	@Override
	public AccountVO doSelectOne(AccountVO param) {
		// accounts에서 계좌번호가 맞는 계좌의 정보 전체를 return

		AccountVO outVO = null;
		for (AccountVO vo : accounts) {
			if (vo.getAccountNo().equals(param.getAccountNo())) {
				outVO = vo;
				break;
			}
		}

		return outVO;
	}

	@Override
	public List<AccountVO> doRetrieve(DTO param) {
		return accounts;
	}

	public AccountVO stringToAccount(String data) { // accounts.txt에 있는 계좌 정보를 한 단어씩 떼어내서 객체화 시킴
		AccountVO out = null;

		String accountStr = data;
		String[] accArr = accountStr.split(",");
		if(accArr.length !=7) {
			System.out.println("잘못된 데이터 형식: " + data);
			return null;
		}
		String accountNo = accArr[0];
		String userName = accArr[1];
		String userPw = accArr[2];
		String regDt = accArr[3];
		String userDob = accArr[4];
		String roleName = accArr[5];
		double balance;
		
		try {
			balance = Double.parseDouble(accArr[6]);
		}catch(NumberFormatException e) {
			System.out.println("NumberFormatException: " + accArr[6]);
			return null;
		}

		out = new AccountVO(accountNo, userName, userPw, regDt, userDob, roleName, balance);
		return out;
	}

	public int readFile(String path) { // accounts.txt를 읽고, stringToAccount() 함수를 이용하여 만든 객체를 arraylist에 집어넣음

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String data = "";
			while ((data = br.readLine()) != null) {
				AccountVO outVO = stringToAccount(data);
				accounts.add(outVO);
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException: " + e.getStackTrace());
		} catch (IOException e) {
			System.out.println("IOException: " + e.getStackTrace());
			e.printStackTrace();
		}

		return accounts.size();
	}

	@Override
	public void writeFile(String path) { //프로그램이 종료됐을 때, 컬렉션에 있는 값을 txt파일에 저장.

		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"))) {
			for (AccountVO account : accounts) {
				String accStr = account.getAccountNo() + "," +
								account.getUserName() + "," +
								account.getUserPw()+ "," +
								account.getUserDob() + "," +
								account.getRegDt() + "," +
								account.getRoleName() + "," +
								account.getBalance();
				bw.write(accStr);
				bw.newLine();
			}

		} catch (UnsupportedEncodingException e) {
			System.out.println("UnsupportedEncodingException: " + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}

	}
}