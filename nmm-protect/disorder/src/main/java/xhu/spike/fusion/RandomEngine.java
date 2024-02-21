package xhu.spike.fusion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomEngine {
    private static final String alphabets = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String numbers = "0123456789";
    private static final String codes = alphabets + numbers;
    private static final String chars = alphabets + numbers + ".:/-=+_";
    private static final Pattern RANDOM_PATTERN = Pattern.compile("ran_(int_\\d+_\\d+|name_\\d+)_dom");

    private static RandomEngine sIns;

    public static RandomEngine getIns() {
        if (sIns == null) {
            sIns = new RandomEngine();
        }
        return sIns;
    }

    public static void reset() {
        if (sIns != null) {
            sIns = new RandomEngine();
        }
    }

    private final Random random;
    private final List<String> names;
    private RandomEngine() {
        random = new Random();
        names = new ArrayList<>();
    }

    protected String translate(String content) {
        StringBuilder res = new StringBuilder();
        Matcher m = RANDOM_PATTERN.matcher(content);
        int start = 0;
        while (m.find()) {
            String[] values = m.group(1).split("_");
            res.append(content, start, m.start());
            if (values[0].equals("int")) {
                res.append(randomInt(Integer.parseInt(values[1]), Integer.parseInt(values[2])));
            } else if (values[0].equals("name")) {
                res.append(randomName(Integer.parseInt(values[1])));
            } else {
                res.append("ERROR_RANDOM");
            }
            start = m.end();
        }
        res.append(content.substring(start));
        return res.toString();
    }

    /**
     * Returns a pseudorandom, uniformly distributed {@code int} value
     * between start (inclusive) and the specified value (exclusive)
     * */
    public int randomInt(int start, int end) {
        return random.nextInt(end - start) + start;
    }

    public boolean randomBool() {
        return random.nextBoolean();
    }

    public String randomName(int len) {
        String name = null;
        while (name == null || names.contains(name)) {
            name = randomString(1, alphabets) + randomString(len - 1, alphabets + numbers);
        }
        names.add(name);
        return name;
    }

    public String randomCodes(int len) {
        return randomString(len, codes);
    }

    public String randomChars(int len) {
        return randomString(len, chars);
    }

    private String randomString(int len, String candidates) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            sb.append(candidates.charAt(random.nextInt(candidates.length())));
        }
        return sb.toString();
    }

    @SafeVarargs
    public final <T> T pick(T... args) {
        return args[random.nextInt(args.length)];
    }

    public int[] pickNumbers(int base, int count) {
        if (base < count || count == 0) {
            return new int[0];
        }
        int[] result = new int[count];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < base; i++) {
            list.add(i);
        }
        for (int i = 0; i < count; i++) {
            result[i] = list.remove(randomInt(0, list.size()));
        }
        return result;
    }

    public boolean hit(int base) {
        return base <= 1 || random.nextInt(base) == 0;
    }

    public <T> T pick(List<T> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(randomInt(0, list.size()));
    }
}
