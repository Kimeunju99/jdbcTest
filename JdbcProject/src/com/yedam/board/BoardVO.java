package com.yedam.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
	private int no;
	private String title;
	private String writer;
	private String content;
	private String date;
	private int count;
}
