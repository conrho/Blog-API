package net.atos.wl.blog.web.controller.Processes;

import java.time.Duration;
import java.time.LocalDateTime;

import net.atos.wl.blog.common.dto.UserDto;

public class InactivityTimer {
	
	public static LocalDateTime getTime() {
		return LocalDateTime.now();
	}
	
	public static String convertTimeToString(LocalDateTime ldt) {
		return ldt.toString();
	}
	
	public static LocalDateTime convertStringToTime(String ldt) {
		return LocalDateTime.parse(ldt);
	}
	
	public static long compareTimes(LocalDateTime ldt1, LocalDateTime ldt2) {
		return Duration.between(ldt1, ldt2).toMinutes();
	}
	
//	public static UserDto checkUserActivityTimer(UserDto user) {
//		if(user.equals(null)) {
//			return null;
//		}
//		LocalDateTime userTime = convertStringToTime(user.getActivity_timer());
//		if(compareTimes(userTime, getTime()) > 30) {
//			
//		}
//	}
	
}
