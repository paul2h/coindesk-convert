package tw.kiwi.coindesk.usecase;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import tw.kiwi.coindesk.entity.MappingData;
import tw.kiwi.coindesk.usecase.api.DeskCoinApi;
import tw.kiwi.coindesk.usecase.io.UCCoinDeskConvertInput;
import tw.kiwi.coindesk.usecase.io.UCCoinDeskConvertOutput;
import tw.kiwi.coindesk.usecase.repository.MappingRepository;

@SpringBootTest
@Slf4j
public class UCCoinDeskConvertTest {

    @Autowired
    UCCoinDeskConvert ucCoinDeskConvert;
    
    @Autowired
    DeskCoinApi deskCoinApi;
    
    @Autowired
    MappingRepository mappingRepository;
    
    @Test
    void test() {
        
        String coinCode1 = "USD";
        String coinName1 = "美元";
        String coinCode2 = "EUR";
        String coinName2 = "歐元";;
        String coinCode3 = "GBP";
        String coinName3 = "英鎊";

        // init test datas
        mappingRepository.findAll().forEach(oldData -> mappingRepository.remove(oldData));
        mappingRepository.save(
                MappingData.builder()
                .id(UUID.randomUUID().toString())
                .coinCode(coinCode1)
                .coinName(coinName1)
                .build()
                );
        
        mappingRepository.save(
                MappingData.builder()
                .id(UUID.randomUUID().toString())
                .coinCode(coinCode2)
                .coinName(coinName2)
                .build()
                );
        
        mappingRepository.save(
                MappingData.builder()
                .id(UUID.randomUUID().toString())
                .coinCode(coinCode3)
                .coinName(coinName3)
                .build()
                );
        
        UCCoinDeskConvertOutput output = ucCoinDeskConvert.execute(UCCoinDeskConvertInput.builder().build());
        
        assertTrue(output.getUpdateTimeString() != null);
        assertTrue(output.getRateDatas() != null);
        assertTrue(output.getRateDatas().size() == 3);
        assertTrue(output.getRateDatas().stream().allMatch(rateData -> rateData.getCoinName() != null));
        assertTrue(output.getRateDatas().stream().allMatch(rateData -> rateData.getRate() > 0));
        
        log.info("[*][0][UCCoinDeskConvertTest][ Test Success , result : {}]" , output);
        
    }
}
