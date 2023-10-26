package behzad.aws_ip_filter.repository;

import behzad.aws_ip_filter.Exceptions.ServerNotReachableException;
import behzad.aws_ip_filter.Region;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Repository
public class IpRepository {

    private final static String IP_RANGE_STRING = "https://ip-ranges.amazonaws.com/ip-ranges.json";

    private JsonNode receiveIpsFromAWS() throws URISyntaxException, ServerNotReachableException {
        try {
            URL ipRangeURL = new URI(IP_RANGE_STRING).toURL();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(ipRangeURL);
            return jsonNode.get("prefixes");
        } catch (IOException e) {
            throw new ServerNotReachableException("AWS server " + IP_RANGE_STRING + "is not reachable.");
        }
    }

    public Set<String> getIpsFilteredByRegion(Region regionToFilter) throws URISyntaxException, ServerNotReachableException {
        Iterator<JsonNode> elements;
        elements = receiveIpsFromAWS().elements();
        Set<String> ipSet = new TreeSet<>();

        while (elements.hasNext()) {
            JsonNode value = elements.next();
            String region = value.get("region").asText();

            if (regionToFilter.equals(Region.ALL) || region.startsWith(regionToFilter.region)) {
                JsonNode ip = value.get("ip_prefix");
                ipSet.add(ip.asText());
            }
        }
        return ipSet;
    }

    public String formatSetToString(Set<String> ipSet) {
        return ipSet.stream().map(String::toString).collect(Collectors.joining(",\n"));
    }
}
