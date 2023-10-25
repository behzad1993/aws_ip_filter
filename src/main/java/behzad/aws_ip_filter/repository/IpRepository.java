package behzad.aws_ip_filter.repository;

import behzad.aws_ip_filter.Region;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

@Repository
public class IpRepository {

    private final static String IP_RANGE_STRING = "https://ip-ranges.amazonaws.com/ip-ranges.json";

    private JsonNode receiveIpsFromAWS() throws URISyntaxException, IOException {
        URL ipRangeURL = new URI(IP_RANGE_STRING).toURL();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(ipRangeURL);
        return jsonNode.get("prefixes");
    }

    public HashSet<String> getIpsFilteredByRegion(Region regionToFilter) throws URISyntaxException, IOException {
        Iterator<JsonNode> elements = receiveIpsFromAWS().elements();
        HashSet<String> ipSet = new HashSet<>();

        while (elements.hasNext()) {
            JsonNode value = elements.next();
            String region = value.get("region").asText();
            if (regionToFilter.equals(Region.ALL) || region.startsWith(regionToFilter.region)) {
                JsonNode ip = elements.next().get("ip_prefix");
                ipSet.add(ip.asText());
            }
        }
        return ipSet;
    }
}
