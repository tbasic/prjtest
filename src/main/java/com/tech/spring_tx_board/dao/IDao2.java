package com.tech.spring_tx_board.dao;

import java.util.ArrayList;

import com.tech.spring_tx_board.dto.BoardDto;

public interface IDao2 {
	public ArrayList<BoardDto> list(int start, int end,String searchKeyword,String selNum);
	
	
	public void write(String bname, 
			String btitle, String bcontent,String fName);
	
	
}
