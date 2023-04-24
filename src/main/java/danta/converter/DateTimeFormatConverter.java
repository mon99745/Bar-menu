package danta.converter;


import com.fasterxml.jackson.databind.util.StdConverter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date <-> String(yyyy-MM-dd HH:mm:ss) 형식 변환
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateTimeFormatConverter {
    /**
     * Date -> String 형식 변환
     */
    public static class DateToStringConverter
            extends StdConverter<Date, String> {
        @Override
        public String convert(Date value) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formatter.format(value);
        }
    }

    /**
     * String -> Date 형식 변환
     */
    public static class StringToDateConverter
            extends StdConverter<String, Date> {
        @Override
        public Date convert(String value) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return formatter.parse(value);
            } catch (ParseException e) {
                // 예외 처리
                return null;
            }
        }
    }
}
