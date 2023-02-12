package tw.kiwi.coindesk.usecase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tw.kiwi.coindesk.entity.MappingData;
import tw.kiwi.coindesk.entity.RateData;
import tw.kiwi.coindesk.usecase.api.DeskCoinApi;
import tw.kiwi.coindesk.usecase.io.UCCoinDeskConvertInput;
import tw.kiwi.coindesk.usecase.io.UCCoinDeskConvertOutput;
import tw.kiwi.coindesk.usecase.repository.MappingRepository;

@Service
@AllArgsConstructor
@Slf4j
public class UCCoinDeskConvert {

    @Autowired
    DeskCoinApi deskCoinApi;
    
    @Autowired
    MappingRepository mappingRepository;
    
    public UCCoinDeskConvertOutput execute(UCCoinDeskConvertInput input) {
        
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

        // mapping coin names and create rate datas
        List<RateData> rateDatas = new ArrayList<>();
        List<MappingData> mappingDatas = mappingRepository.findAll();
        fetchResult.get("bpi").forEach(coinRateData -> {
            String coinCode = coinRateData.get("code").asText();
            Double coinRate = coinRateData.get("rate_float").asDouble();
            String coinName = mappingDatas.stream().filter(mappingData -> mappingData.getCoinCode().equals(coinCode))
                    .findFirst()
                    .orElse(MappingData.builder().coinName("無對應幣別名稱").build())
                    .getCoinName();
            
            rateDatas.add(
                    RateData.builder()
                    .coinCode(coinCode)
                    .coinName(coinName)
                    .rate(coinRate)
                    .build()
                    );
        });
        
        return UCCoinDeskConvertOutput.builder()
                .updateTimeString(updateTimeString)
                .rateDatas(rateDatas)
                .build();
    }
}
