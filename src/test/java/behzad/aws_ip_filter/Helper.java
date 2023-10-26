package behzad.aws_ip_filter;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Helper {

    public static Set<String> createGivenHashSet() {
        Set<String> ips = new TreeSet<>();
        for (int i = 0; i < 100; i++) {
            ips.add(createRandomIp());
        }
        return ips;
    }

    public static String createExpectedString(Set<String> ips) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String ip : ips) {
            stringBuilder.append(ip).append(",\n");
        }
        String string = stringBuilder.toString();
        return string.substring(0, string.length() - 2);
    }

    public static String createRandomIp() {
        Random r = new Random();
        return r.nextInt(256) + "."
                + r.nextInt(256) + "."
                + r.nextInt(256) + "."
                + r.nextInt(256) + "/"
                + r.nextInt(32);
    }
}
