package behzad.aws_ip_filter.service;

import behzad.aws_ip_filter.Region;
import behzad.aws_ip_filter.repository.IpRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;

@Service
public class IpService {

    private final IpRepository ipRepository;

    public IpService(IpRepository ipRepository) {
        this.ipRepository = ipRepository;
    }

    public HashSet<String> getFilteredIps(Region region) throws URISyntaxException, IOException {
        return ipRepository.getIpsFilteredByRegion(region);
    }
}
