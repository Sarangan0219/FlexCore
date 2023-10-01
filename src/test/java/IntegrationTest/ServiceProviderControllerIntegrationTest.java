package IntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flexPerk.flexCore.FlexCoreApplication;
import com.flexPerk.flexCore.controller.ServiceProviderController;
import com.flexPerk.flexCore.model.ServiceProvider;
import com.flexPerk.flexCore.service.ServiceProviderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = FlexCoreApplication.class)
public class ServiceProviderControllerIntegrationTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private ServiceProviderService serviceProviderService;
//
//    @InjectMocks
//    private ServiceProviderController serviceProviderController;
//
//    private ServiceProvider serviceProvider;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    private String token;
//
//    @BeforeEach
//    public void setup() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        registerUserAndGenerateToken();
//        serviceProvider = new ServiceProvider();
//        serviceProvider.setServiceProviderID(1L);
//        serviceProvider.setName("Test Provider");
//        serviceProvider.setEligible(true);
//    }
//
//    public void registerUserAndGenerateToken() {
//        String userRegistrationJson = "{"
//                + "\"username\":\"testuser34\","
//                + "\"password\":\"testpass\","
//                + "\"roles\":\"SERVICE_PROVIDER\""
//                + "}";
//
//        try {
//            MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
//                            .post("http://localhost:8080/api/v1/auth/register")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(userRegistrationJson)
//                    )
//                    .andExpect(status().isOk())
//                    .andReturn().getResponse();
//
//
//            ObjectMapper mapper = new ObjectMapper();
//            Map<String, String> responseMap = mapper.readValue(response.getContentAsString(), Map.class);
//
//            token = responseMap.get("access_token");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testRegisterServiceProvider() throws Exception {
//        String serviceProviderJson = "{"
//                + "\"name\":\"Farringdon GYM\","
//                + "\"email\":\"may@gmail.com\","
//                + "\"phoneNumber\":\"77347823584\","
//                + "\"perk_type\":\"GYM\","
//                + "\"perkDescription\":\"GYM Provider from may\","
//                + "\"password\":\"watford@2023\""
//                + "}";
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/serviceProvider")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(serviceProviderJson)
//                        .header("Authorization", "Bearer " + token)
//                )
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
//                        .value("Service Provider Farringdon GYM submitted for registration, Awaiting approval"));
//    }
//
//
//    @Test
//    public void testGetServiceProvider() throws Exception {
//        when(serviceProviderService.getServiceProvider(1L)).thenReturn(serviceProvider);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/serviceProvider/1")
//                        .header("Authorization", "Bearer " + token))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Provider"));
//    }
//
//    @Test
//    public void testUpdateServiceProvider() throws Exception {
//        when(serviceProviderService.updateServiceProvider(any())).thenReturn(serviceProvider);
//
//
//        String jsonContent = "{"
//                + "\"name\": \"Farringdon GYM\","
//                + "\"email\": \"may@gmail.com\","
//                + "\"phoneNumber\": \"77347823584\","
//                + "\"perk_type\": \"GYM\","
//                + "\"perkDescription\": \"GYM Provider from may\","
//                + "\"password\": \"watford@2023\""
//                + "}";
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/serviceProvider")
//                        .header("Authorization", "Bearer " + token)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonContent)
//                )
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Provider"));
//    }
//
//    @Test
//    public void testDeleteServiceProvider() throws Exception {
//        when(serviceProviderService.deleteServiceProvider(1L)).thenReturn(serviceProvider);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/serviceProvider/1"))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("Service Provider Test Provider has been deleted"));
//    }
//
//    @Test
//    public void testApproveServiceProvider() throws Exception {
//        when(serviceProviderService.approveServiceProvider(1L)).thenReturn(serviceProvider);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/serviceProvider/1/approve-service-provider")
//                        .header("Authorization", "Bearer " + token)  )
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("Service Provider Test Provider has been approved"));
//    }
//
//    @Test
//    public void testUploadServiceProviderImage() throws Exception {
//        MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());
//
//        doNothing().when(serviceProviderService).uploadImage(1L, file);
//
//        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/serviceProvider/1/profileImage").file(file))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testGetServiceProviderImage() throws Exception {
//        byte[] image = "Hello, World!".getBytes();
//        when(serviceProviderService.getProfileImage(1L)).thenReturn(image);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/serviceProvider/1/profileImage")
//                        .header("Authorization", "Bearer " + token)  )
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().bytes(image));
//    }
//
//    @Test
//    public void testSearchServiceProvider() throws Exception {
//        when(serviceProviderService.searchServiceProviders("Test Provider", null)).thenReturn(Arrays.asList(serviceProvider));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/serviceProvider/search")
//                        .param("name", "Test Provider"))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Test Provider"));
//    }
}
