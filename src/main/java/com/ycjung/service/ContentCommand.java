package com.ycjung.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.ycjung.dao.DAO;
import com.ycjung.dto.DTO;

public class ContentCommand implements Command{

	@Override
	public void execute(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String bCd = request.getParameter("bCd");
		int Cd = Integer.parseInt(bCd);
		DAO dao = new DAO();
		DTO dto = dao.contentView(Cd);
		model.addAttribute("dto", dto);
	}
}
