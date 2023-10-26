package behzad.aws_ip_filter.service;

import behzad.aws_ip_filter.Exceptions.ServerNotReachableException;
import behzad.aws_ip_filter.Region;
import behzad.aws_ip_filter.repository.IpRepository;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.Set;

@Service
public class IpService {

    private final IpRepository ipRepository;

    public IpService(IpRepository ipRepository) {
        this.ipRepository = ipRepository;
    }

    public String getFilteredIpsAsString(Region region) throws URISyntaxException, ServerNotReachableException {
        Set<String> ipsFilteredByRegion = ipRepository.getIpsFilteredByRegion(region);
        return ipRepository.formatSetToString(ipsFilteredByRegion);
    }
}
