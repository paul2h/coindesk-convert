package tw.kiwi.coindesk.adapter.api;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import tw.kiwi.coindesk.usecase.api.DeskCoinApi;

@Service
public class DeskCoinApiRemote implements DeskCoinApi{

    @Override
    public JsonNode fetchCoinDeskData() {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println();
        try {
            return new ObjectMapper().readValue(restTemplate.getForEntity("https://api.coindesk.com/v1/bpi/currentprice.json", String.class).getBody(), JsonNode.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
