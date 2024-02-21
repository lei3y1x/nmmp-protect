package xhu.spike.fusion;

import java.util.Calendar;
import java.util.TimeZone;

public class AdjustCode extends LineAction {
    private static final String INT_MARK = "static final int RANDOM_SEED_";
    private static final String STR_MARK = "RANDOM_STRING_";
    private static final String PLUS_TODAY_MARK = "static final int PLUS_TODAY";

    public AdjustCode(JavaLoader javaLoader) {
        super(javaLoader);
    }

    @Override
    public String handleLine(String line) {
        if (line.contains(INT_MARK)) {
            return randomInt(line);
        }
        if (line.contains(STR_MARK)) {
            return randomString(line);
        }
        if (line.contains(PLUS_TODAY_MARK)) {
            return plusToday(line);
        }
        return line;
    }

    private String plusToday(String line) {
        markRefresh();
        int equals = line.indexOf('=') + 1;
        int end = line.indexOf(';', equals);
        int delta = Integer.parseInt(line.substring(equals, end).trim());
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Calendar calendar = Calendar.getInstance(timeZone);
        int target = calendar.get(Calendar.DAY_OF_YEAR) + delta;
        String newLine = line.substring(0, equals) + " " + target + ";";

        log("plusToday:");
        log(line);
        log(newLine);

        return newLine;
    }

    private String randomString(String line) {
        markRefresh();
        int start = line.indexOf(STR_MARK);
        int end = line.indexOf('"', start);
        String tag = line.substring(start, end);
        int count = Integer.parseInt(tag.substring(STR_MARK.length()));
        String newLine = line.replace(tag, random.randomCodes(count));

        log("randomString:");
        log(line);
        log(newLine);

        return newLine;
    }

    private String randomInt(String line) {
        markRefresh();
        int start = line.indexOf(INT_MARK) + INT_MARK.length();
        int end = -1;
        for (int i = start; i < line.length(); i++) {
            char c = line.charAt(i);
            if (Character.isDigit(c) || c == '_') {
                continue;
            }
            end = i;
            break;
        }
        String[] seeds = line.substring(start, end).split("_");
        int min = Integer.parseInt(seeds[0]);
        int max = Integer.parseInt(seeds[1]);
        int seed = random.randomInt(min, max);
        int equals = line.indexOf('=') + 1;
        String newLine = line.substring(0, equals) + " " + seed + ";";

        log("randomInt:");
        log(line);
        log(newLine);

        return newLine;
    }
}
