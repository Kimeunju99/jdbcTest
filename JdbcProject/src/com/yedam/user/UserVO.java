package com.yedam.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //기본 생성자
@AllArgsConstructor //모든 필드가 들어있는 생성자
public class UserVO {
	private String id;
	private String pw;
	private String name;
	private String addr;
	private String phone;
	private String birth;
}
