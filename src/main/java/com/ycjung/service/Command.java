package com.ycjung.service;

import org.springframework.ui.Model;

public interface Command {
	public void execute(Model model);
}
