package com.pcwk.ehr.user;

import java.util.Calendar;
import java.util.Scanner;

public class CreateAccount {
    CatchMethod catchMethod = new CatchMethod();
    AccountDao dao = new AccountDao();

    Scanner sc = new Scanner(System.in);

    public boolean check(String name, String dob, String pw, String money) {
        boolean type = false;
        if (name.matches("^[가-힣a-zA-Z]+$") && name.length() >= 2 && name.length() < 20 &&
                dob.matches("^[0-9]+$") && dob.length() == 8 && pw.matches("^[0-9]+$") && pw.length() == 4
                && money.matches("^[0-9]+$") && money.length() < 9) {
            type = true;
        }
        return type;
    }
    public AccountVO createAccount()  { // 계좌 생성
       AccountVO out = null;
       System.out.println("===== 회원가입 =====");
       //계좌번호
        int ranNo = 99991000 + ((int) (Math.random() * 100 + 1));
        String accNo = Integer.toString(ranNo); 

        //소유주명
        System.out.print("본인의 이름을 입력하세요: ");
        String userName=sc.nextLine();
        catchMethod.check_Input(userName,"이름","계좌생성");

        //생년월일
        System.out.print("본인의 생년월일 8자리를 입력하세요: ");
        String userDob = sc.nextLine();// 생년월일
        catchMethod.check_Input(userDob,"생년월일","계좌생성");
        
        //비밀번호
        System.out.print("설정하실 비밀번호 4자리를 입력하세요: ");
        String userPw = sc.nextLine();
        catchMethod.check_Input(userPw,"비밀번호","계좌생성");

        //가입일
        Calendar calendar =  Calendar.getInstance();
        String currentDate = String.format("%02d/%02d/%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH));
        String regDt = currentDate;

        //권한명
        String roleName = "사용자";
        
        //잔고
        System.out.print("초기 입금액을 입력하세요: ");
        String initBalance = sc.nextLine();
        catchMethod.check_Input(initBalance,"금액","계좌생성");
        double balance = 0;
		
		try {
			balance = Double.parseDouble(initBalance);
		}catch(NumberFormatException e) {
		}


        if(check(userName,userDob,userPw,initBalance) == true) { 
            out = new AccountVO(accNo, userName, userPw, userDob, regDt, roleName, balance);

            System.out.println("********** 계좌 생성 완료! **********");
            System.out.println("계좌번호: " + out.getAccountNo());
            System.out.println("소유주명: " + out.getUserName());
            System.out.println("비밀번호: " + out.getUserPw());
            System.out.println("생년월일: " + out.getUserDob());
            System.out.println("가입일: " + out.getRegDt());
            System.out.println("권한명: " + out.getRoleName());
            System.out.println("잔고: " + out.getBalance());
            System.out.println("********************************");
            dao.doSave(out);
            dao.doUpdate();
        }else {
            System.err.println("올바르지 못한 입력값이 있어 메인 메뉴로 돌아갑니다. 다시 시도해주세요.");
        }
       return out;
    }
    
    
    
}