package com.yedam.board;

import java.util.List;
import java.util.Scanner;

import com.yedam.user.UserDao;

public class BoardMain {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		BoardDao dao = new BoardDao();
		UserDao udao = new UserDao();
		int menu = 0;
		String id = "";
		boolean log =true;
		while(log) {
			System.out.println("===============LOGIN===============");
			System.out.println("1.로그인 2.종료");
			System.out.print("선택>");
			try{
				menu = Integer.parseInt(scan.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("입력 오류: 주어진 숫자를 입력하세요.");
				continue;
			}
			switch(menu) {
			case 1:
				System.out.print("ID: ");
				id = scan.nextLine();
				System.out.print("PW: ");
				String pw = scan.nextLine();
				if(udao.Login(id, pw)) {
					System.out.println("로그인 성공");
					log = false;
				}else
					System.out.println("등록 정보가 없습니다.");
				break;
			case 2:
				return;
			}
		}
		
		while(true) {
			System.out.println("===============MENU===============");
			System.out.println("1.등록 2.삭제 3.수정 4.목록 5.상세 6.종료");
			System.out.print("선택>");
			try{
				menu = Integer.parseInt(scan.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("입력 오류: 주어진 숫자를 입력하세요.");
				continue;
			}
			switch(menu) {
			case 1: 
				System.out.println("<게시글 작성>===================");
				System.out.print("게시글 제목: ");
				String title = scan.nextLine();
				String writer = id; //현재 로그인된 사용자
				System.out.print("게시글 내용: ");
				String content = scan.nextLine();
				System.out.print("게시글 작성일: ");
				String date = scan.nextLine();
				BoardVO vo = new BoardVO(0, title, writer, content, date, 0);
				
				if(dao.insert(vo))
					System.out.println("등록 완료");
				else
					System.out.println("등록 실패");
				break;
			case 2: 
				System.out.println("<게시글 삭제>===================");
				System.out.print("게시글 번호: ");
				int no = Integer.parseInt(scan.nextLine());
				if(dao.delete(no))
					System.out.println("삭제 완료");
				else
					System.out.println("삭제 실패: 게시글 번호를 확인해주세요.");
				break;
			case 3: 
				System.out.println("<게시글 수정>===================");
				System.out.print("게시글 번호: ");
				no = Integer.parseInt(scan.nextLine());
				System.out.print("수정내용: ");
				content= scan.nextLine();
				if(dao.update(no, content)) {
					System.out.println("수정 완료");
				}else {
					System.out.println("게시글 번호를 확인해주세요.");
				}
				break;
			case 4: 
				System.out.println("<게시글 목록>===================");
				List<BoardVO> list = dao.showAll();
				for(BoardVO b : list) {
					System.out.println("[ INDEX: " +b.getNo() +" | TITLE: "+b.getTitle() +" | WRITER: " + 
				b.getWriter() +" | VIEW: "+ b.getCount()+" ]");
				}
				break;
			case 5: 
				System.out.println("<게시글 조회>===================");
				System.out.print("게시글 번호: ");
				no = Integer.parseInt(scan.nextLine());
				BoardVO b = dao.showOne(no);
				if(b == null)
					System.out.println("번호를 확인해주세요.");
				else
					System.out.println(b.toString());
				break;
			case 6: 
				System.out.println("프로그램 종료.");
				return;
			}
			
		}
	}
}
