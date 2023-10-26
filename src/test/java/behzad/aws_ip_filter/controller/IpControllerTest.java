package behzad.aws_ip_filter.controller;

import behzad.aws_ip_filter.Region;
import behzad.aws_ip_filter.service.IpService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(IpController.class)
class IpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IpService ipService;

    @ParameterizedTest
    @EnumSource(Region.class)
    void findAllIpsByRegion_shouldAcceptALlValuesFromRegion(Region region) throws Exception {
        // GIVEN
        String expectedString = "Random IP";

        // WHEN
        when(ipService.getFilteredIpsAsString(any())).thenReturn(expectedString);

        // THEN
        this.mockMvc.perform(get("/api/v1/ips?region=" + region))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")));
    }

    @Test
    void findAllIpsByRegion_shouldNotAcceptValuesNotInRegion() throws Exception {
        // GIVEN
        String expectedString = "Random IP";

        // WHEN
        when(ipService.getFilteredIpsAsString(any())).thenReturn(expectedString);

        // THEN
        this.mockMvc.perform(get("/api/v1/ips?region=ALLL"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}