package tw.kiwi.coindesk.usecase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tw.kiwi.coindesk.usecase.api.DeskCoinApi;
import tw.kiwi.coindesk.usecase.io.UCCoinDeskGetInput;
import tw.kiwi.coindesk.usecase.io.UCCoinDeskGetOutput;

@Service
@AllArgsConstructor
@Slf4j
public class UCCoinDeskGet {

    private DeskCoinApi deskCoinApi;
    
    
    
    public UCCoinDeskGetOutput execute(UCCoinDeskGetInput input) {
        SimpleDateFormat isoDateSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        SimpleDateFormat outputDateSdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String updateTimeString = null;

        // call api fetch coin desk
        JsonNode fetchResult = deskCoinApi.fetchCoinDeskData();
        
        // read update time
        String originalUpdateTimeText = fetchResult.get("time").get("updatedISO").asText();
        try {
            updateTimeString = outputDateSdf.format(isoDateSdf.parse(originalUpdateTimeText));
        } catch (ParseException e) {
            log.error("[0][*][UCCoinDeskGet.execute][ format update time error : {}]" , originalUpdateTimeText , e);
        }
        

        // read rate datas
        Map<String, Double> coinRates = new HashMap<>();
        fetchResult.get("bpi").forEach(coinRateData -> {
            coinRates.put(coinRateData.get("code").asText(), coinRateData.get("rate_float").asDouble());
        });
        
        return UCCoinDeskGetOutput.builder()
                .updateTimeString(updateTimeString)
                .coinRates(coinRates)
                .build();
    };
    
    
}
