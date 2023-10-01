package IntegrationTest;

import com.flexPerk.flexCore.model.Quote;
import com.flexPerk.flexCore.service.QuoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class QuoteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuoteService quoteService;

    private Quote quote;

    @BeforeEach
    public void setup() {
        quote = new Quote();
        quote.setQuoteID(1L);
    }

    @Test
    public void testGetQuote() throws Exception {
        given(quoteService.getQuote(1L)).willReturn(quote);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/quote/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateQuote() throws Exception {
        // Given
//        given(quoteService.createQuote(any(Quote.class))).willReturn(quote);

        String quoteJson = "{"
                + "\"quoteID\": 1,"
                + "\"status\": \"PENDING\","
                // ... other fields
                + "}";

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/quote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(quoteJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteQuote() throws Exception {
        // Given
        given(quoteService.deleteQuote(1L)).willReturn(quote);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/quote/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testApproveQuote() throws Exception {
        // Given
        given(quoteService.approveQuote(1L)).willReturn(quote);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/quote/1/approve-Quote"))
                .andExpect(status().isOk());
    }

}
