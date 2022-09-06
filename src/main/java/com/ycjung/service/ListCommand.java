package com.ycjung.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.ycjung.dao.DAO;
import com.ycjung.dto.DTO;
import com.ycjung.paging.Paging;
import com.ycjung.paging.PagingVo;

public class ListCommand implements Command{
	
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		DAO dao = new DAO();
		ArrayList<DTO> dtos = dao.list();
		model.addAttribute("list", dtos);
		
		Paging paging = new Paging();
		PagingVo pv = paging.membersAboutList();
		model.addAttribute("pv", pv);
		
	}
}