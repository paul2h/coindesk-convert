package tw.kiwi.coindesk.usecase.api.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import tw.kiwi.coindesk.usecase.api.DeskCoinApi;

@Service
@Primary
public class DeskCoinApiLocal implements DeskCoinApi{

    @Override
    public JsonNode fetchCoinDeskData() {
        try {
            return new ObjectMapper().readValue(new File("./test_data/currentprice.json") , JsonNode.class);
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    
}
