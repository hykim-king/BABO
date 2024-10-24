package com.pcwk.ehr.user;

import java.util.Scanner;
public class CatchMethod {
    public void check_Input(String input, String title, String function) {
        CreateAccount cre = new CreateAccount();
        Scanner sc = new Scanner(System.in);
        try {
            if (function == "계좌생성") {
                if (title == "이름") {
                    if (!input.matches("^[가-힣a-zA-Z]+$")) {
                        System.err.println(title + "은 한글과 영어로 입력해주세요.");
                        if (function == "계좌생성") {
                            System.out.println("확인하셨으면 엔터키를 눌러주세요.");
                            sc.nextLine();
                        }
                    } else if (input.length() < 2) {
                        System.err.println(title + "은 최소 2글자 이상을 입력해주세요.");
                        if (function == "계좌생성") {
                            System.out.println("확인하셨으면 엔터키를 눌러주세요.");
                            sc.nextLine();
                        }
                    } else if (input.length() > 20) {
                        System.err.println(title + "은 최대 20글자 까지 입력할 수 있습니다.");
                        if (function == "계좌생성") {
                            System.out.println("확인하셨으면 엔터키를 눌러주세요.");
                            sc.nextLine();
                        }
                    }
                }
                if (title == "비밀번호")
                    if (!input.matches("^[0-9]+$")) {
                        System.err.println(title + "는 숫자만 입력해주세요.");
                        if (function == "계좌생성") {
                            System.out.println("확인하셨으면 엔터키를 눌러주세요.");
                            sc.nextLine();
                        }
                    } else if (input.length() != 4) {
                        System.err.println(title + "는 4자 입니다.");
                        if (function == "계좌생성") {
                            System.out.println("확인하셨으면 엔터키를 눌러주세요.");
                            sc.nextLine();
                        }
                    }
                if (title == "생년월일") {

                        if (input.length() != 8) {
                            System.err.println("생년월일은 8자리 입니다.");
                            if (function == "계좌생성") {
                                System.out.println("확인하셨으면 엔터키를 눌러주세요.");
                                sc.nextLine();
                            }
                        }
                        if (title.length() == 8) {
                            String year = input.substring(0, 4);
                            String month = input.substring(4, 6);
                            String day = input.substring(6, 8);
                            if (!input.matches("^[0-9]+$")) {
                                System.err.println(title + "은 숫자만 입력해주세요.");
                                if (function == "계좌생성") {
                                    System.out.println("확인하셨으면 엔터키를 눌러주세요.");
                                    sc.nextLine();
                                }
                            } else if (!year.matches("^(19[0-9]{2}|200[0-9])$")) {
                                System.err.println("혹시 나이가 몇살이신가요...");
                                if (function == "계좌생성") {
                                    System.out.println("확인하셨으면 엔터키를 눌러주세요.");
                                    sc.nextLine();
                                }
                            } else if (!month.matches("^(0[1-9]|1[0-2])$")) {
                                System.err.println(month + "월은 존재하지 않습니다. 1월부터12월중 입력해주세요.");
                                if (function == "계좌생성") {
                                    System.out.println("확인하셨으면 엔터키를 눌러주세요.");
                                    sc.nextLine();
                                }
                            } else if (!day.matches("^(0[1-9]|[12][0-9]|3[01])$")) {
                                System.err.println(day + "일은 존재하지 않습니다. 1일부터31일중 입력해주세요");
                                if (function == "계좌생성") {
                                    System.out.println("확인하셨으면 엔터키를 눌러주세요.");
                                    sc.nextLine();
                                }
                            }
                        }
                    if (!input.matches("^[0-9]+$")) {
                        System.err.println("생년월일은 숫자만 입력해주세요.");
                        System.out.println("확인하셨으면 엔터키를 눌러주세요.");
                        sc.nextLine();
                    }
                }
                if (title == "금액") {
                    if (input.length() > 8) {
                        System.err.println(input + "원은 큰금액이라 신분증을 제출해주세요");
                        if (function == "계좌생성") {
                            System.out.println("확인하셨으면 엔터키를 눌러주세요.");
                            sc.nextLine();
                        }
                    } else if (!input.matches("^[1-9][0-9]{0,7}$")) {
                        System.err.println(input + "원은 존재하지 않는 금액입니다. 금액은 0부터 시작할수 없습니다");
                        if (function == "계좌생성") {
                            System.out.println("확인하셨으면 엔터키를 눌러주세요.");
                            sc.nextLine();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("입력후 초기 화면으로 돌아갑니다.");
            System.out.println("======================================");
        }
    }
}