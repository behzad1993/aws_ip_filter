package behzad.aws_ip_filter.repository;

import behzad.aws_ip_filter.Region;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;


class IpRepositoryTest {
    @ParameterizedTest
    @EnumSource(Region.class)
    public void getIpRangeFromAWS_receivingAnyData(Region region) throws IOException, URISyntaxException {
        IpRepository ipRepository = new IpRepository();
        HashSet<String> ipsByCountry = ipRepository.getIpsFilteredByRegion(region);

        System.out.println(ipsByCountry);
        System.out.println(ipsByCountry.size());
    }
}