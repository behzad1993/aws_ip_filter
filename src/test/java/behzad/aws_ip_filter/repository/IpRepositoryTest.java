package behzad.aws_ip_filter.repository;

import behzad.aws_ip_filter.Exceptions.ServerNotReachableException;
import behzad.aws_ip_filter.Region;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.net.URISyntaxException;
import java.util.Set;

import static behzad.aws_ip_filter.Helper.createExpectedString;
import static behzad.aws_ip_filter.Helper.createGivenHashSet;
import static org.junit.jupiter.api.Assertions.*;


class IpRepositoryTest {

    private IpRepository ipRepository;

    @BeforeEach
    void setup() {
        this.ipRepository = new IpRepository();
    }

    @Test
    public void getIpRangeFromAWS_AWSServerIsReachable() {
        assertDoesNotThrow(() -> {
            this.ipRepository.getIpsFilteredByRegion(Region.AP);
        });
    }

    @ParameterizedTest
    @EnumSource(Region.class)
    public void getIpRangeFromAWS_receivingAnyData(Region region) throws URISyntaxException, ServerNotReachableException {
        Set<String> ipsByCountry = this.ipRepository.getIpsFilteredByRegion(region);
        assertFalse(ipsByCountry.isEmpty());
    }

    @Test
    public void formatOutputToString_receiveStringWithOneValueInEachLine() {
        Set<String> givenHashSet = createGivenHashSet();

        String expected = createExpectedString(givenHashSet);
        String result = this.ipRepository.formatSetToString(givenHashSet);

        assertEquals(expected, result);
    }

}