package behzad.aws_ip_filter.repository;

import behzad.aws_ip_filter.Region;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


class IpRepositoryTest {
    @ParameterizedTest
    @EnumSource(Region.class)
    public void getIpRangeFromAWS_receivingAnyData(Region region) throws IOException, URISyntaxException {
        IpRepository ipRepository = new IpRepository();
        Set<String> ipsByCountry = ipRepository.getIpsFilteredByRegion(region);

        System.out.println(ipsByCountry);
        System.out.println(ipsByCountry.size());
    }

    @Test
    public void formatOutputToString_receiveStringWithOneValueInEachLine() {
        IpRepository ipRepository = new IpRepository();
        Set<String> givenHashSet = createGivenHashSet();

        String expected = createExpectedString(givenHashSet);
        String result = ipRepository.formatSetToString(givenHashSet);

        assertEquals(expected, result);
    }

    private Set<String> createGivenHashSet() {
        Set<String> ips = new TreeSet<>();
        for (int i = 0; i < 100; i++) {
            ips.add(createRandomIp());
        }
        return ips;
    }

    private String createExpectedString(Set<String> ips) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String ip : ips) {
            stringBuilder.append(ip).append(",\n");
        }
        String string = stringBuilder.toString();
        return string.substring(0, string.length() - 2);
    }

    private String createRandomIp() {
        Random r = new Random();
        return r.nextInt(256) + "."
                + r.nextInt(256) + "."
                + r.nextInt(256) + "."
                + r.nextInt(256) + "/"
                + r.nextInt(32);
    }
}