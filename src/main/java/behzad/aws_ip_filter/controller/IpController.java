package behzad.aws_ip_filter.controller;

import behzad.aws_ip_filter.Region;
import behzad.aws_ip_filter.service.IpService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("api/v1")
public class IpController {

    final IpService ipService;

    public IpController(IpService ipService) {
        this.ipService = ipService;
    }

    @GetMapping(value = "/ips", produces = MediaType.TEXT_PLAIN_VALUE)
    public String findAllIpsByRegion(@RequestParam("region") Region region) throws URISyntaxException, IOException {
        return ipService.getFilteredIpsAsString(region);
    }
}
