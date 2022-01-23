package cybersoft.javabackend.game.doanso.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateUtils {
private static DateTimeFormatter formmatter= DateTimeFormatter.ofPattern("dd-mm-yyyy");
public static String toString(LocalDateTime date) {
		return formmatter.format(date);
}
}
