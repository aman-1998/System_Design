package personal.learning.url.service;

public class Base62Encoder {
	
	private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = CHAR_SET.length();

    public static String encode(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(CHAR_SET.charAt((int) (num % BASE)));
            num = num / BASE;
        }
        return sb.reverse().toString();
    }

    public static long decode(String str) {
        long num = 0;
        for (int i = 0; i < str.length(); i++) {
            num = num * BASE + CHAR_SET.indexOf(str.charAt(i));
        }
        return num;
    }
}
