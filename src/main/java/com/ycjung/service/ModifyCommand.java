package com.ycjung.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.ycjung.dao.DAO;

public class ModifyCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String,Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int bCd = Integer.parseInt(request.getParameter("bCd"));
		String bAuthor = request.getParameter("bAuthor");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		DAO dao = new DAO();
		dao.modify(bCd, bAuthor, bTitle, bContent);
	}
}
