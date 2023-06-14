package com.yedam.user;

import java.util.List;
import java.util.Scanner;

public class UserMain {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		UserDao dao = new UserDao();
		int menu = 0;
		
		while(true) {
			System.out.println("===========<DBMS>=============");
			System.out.println("1.추가 2.조회 3.수정 4.삭제 5.목록 6.종료");
			System.out.print("선택>");
			try {
			menu = Integer.parseInt(scan.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("입력 오류: 주어진 숫자를 입력하세요.");
				continue;
			}
			
			if(menu == 1) {
				System.out.println("<추가>=========================");
				System.out.print("id>");
				String id = scan.nextLine();
				System.out.print("pw>");
				String pw = scan.nextLine();
				System.out.print("name>");
				String name = scan.nextLine();
				System.out.print("birth>");
				String birth = scan.nextLine();
				System.out.print("phone>");
				String phone = scan.nextLine();
				System.out.print("addr>");
				String addr = scan.nextLine();
				UserVO user =new UserVO();
				user.setId(id); 	  user.setPw(pw);
				user.setName(name);	  user.setBirth(birth);
				user.setPhone(phone); user.setAddr(addr);	
				
				if(dao.add(user))
					System.out.println("처리 성공");
				else
					System.out.println("처리 실패");
				
			}else if(menu == 2) {
				System.out.println("<조회>=========================");
				System.out.print("id>");
				String id = scan.nextLine();
				UserVO user = dao.search(id);
				if(user == null) {
					System.out.println("조회 결과가 없습니다.");
				}else {
					System.out.println(user.toString());
				}
				
			}else if(menu == 3) {
				System.out.println("<수정>=========================");
				System.out.print("대상 id>");
				String id = scan.nextLine();
				
				System.out.print("pw>");
				String pw = scan.nextLine();
				System.out.print("name>");
				String name = scan.nextLine();
				System.out.print("birth>");
				String birth = scan.nextLine();
				System.out.print("phone>");
				String phone = scan.nextLine();
				System.out.print("addr>");
				String addr = scan.nextLine();
	
				UserVO user =new UserVO();
				user.setId(id); 	  user.setPw(pw);
				user.setName(name);	  user.setBirth(birth);
				user.setPhone(phone); user.setAddr(addr);	
				
				if(dao.modify(user))
					System.out.println("수정 완료");
				else
					System.out.println("수정 실패");
				
			}else if(menu == 4) {
				System.out.println("<삭제>=========================");
				System.out.print("id>");
				String id = scan.nextLine();
				if(dao.remove(id)) {
					System.out.println("삭제 완료");
				}else {
					System.out.println("삭제 실패");
				}
			}else if(menu == 5){
				System.out.println("<목록>=========================");
				List<UserVO> list = dao.list();
				for(UserVO user : list) {
					System.out.println(user.toString());
				}
			}else if(menu == 6) {
				break;
			}
			
		}
		System.out.println("System Close");
	}
}
