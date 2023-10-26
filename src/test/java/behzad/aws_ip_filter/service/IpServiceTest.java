package behzad.aws_ip_filter.service;

import behzad.aws_ip_filter.Exceptions.ServerNotReachableException;
import behzad.aws_ip_filter.Region;
import behzad.aws_ip_filter.repository.IpRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.Set;

import static behzad.aws_ip_filter.Helper.createExpectedString;
import static behzad.aws_ip_filter.Helper.createGivenHashSet;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class IpServiceTest {

    private IpService ipService;
    private IpRepository ipRepository;

    @BeforeEach
    public void initMocks() {
        this.ipRepository = mock(IpRepository.class);
        this.ipService = new IpService(this.ipRepository);
    }

    @Test
    public void getFilteredIpsAsString_test() throws URISyntaxException, ServerNotReachableException {
        // GIVEN
        Set<String> givenIpSet = createGivenHashSet();
        String givenStringOutput = createExpectedString(givenIpSet);
        Region givenRegion = Region.EU;

        // WHEN
        when(this.ipRepository.getIpsFilteredByRegion(givenRegion)).thenReturn(givenIpSet);
        when(this.ipRepository.formatSetToString(givenIpSet)).thenReturn(givenStringOutput);
        String filteredIpsAsString = this.ipService.getFilteredIpsAsString(givenRegion);

        // THEN
        assertTrue(givenIpSet.stream().allMatch(filteredIpsAsString::contains));
    }
}