package net.atos.wl.blog.web.controller.Processes;

import net.atos.wl.blog.business.service.UserService;
import net.atos.wl.blog.common.dto.UserDto;
import net.atos.wl.blog.common.enums.UserType;

public class Processes {
	
	public static String makeid() {
		String text = "";
		String possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		for (int i = 0; i < 26; i++)
			text += possible.charAt((int) Math.floor(Math.random() * possible.length()));

		return text;
	}

	public static double round(double value, int precision) {
		int scale = (int) Math.pow(10, precision);
		return (double) Math.round(value * scale) / scale;
	}

}
