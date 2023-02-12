package tw.kiwi.coindesk.usecase;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import tw.kiwi.coindesk.entity.MappingData;
import tw.kiwi.coindesk.usecase.io.UCMappingQueryInput;
import tw.kiwi.coindesk.usecase.io.UCMappingQueryOutput;
import tw.kiwi.coindesk.usecase.repository.MappingRepository;

@SpringBootTest
@Slf4j
public class UCMappingQueryTest {
    @Autowired
    UCMappingQuery ucMappingQuery;
    
    @Autowired
    MappingRepository mappingRepository;
    
    @Test
    void mappingQueryTest() {
        // init test datas
        mappingRepository.findAll().forEach(oldData -> mappingRepository.remove(oldData));
        String id1 = UUID.randomUUID().toString();
        String coinCode = "TEST";
        String coinName = "測試幣別";
        String id2 = UUID.randomUUID().toString();
        String coinCode2 = "TEST2";
        String coinName2 = "測試幣別2";
        mappingRepository.save(
                MappingData.builder()
                .id(id1)
                .coinCode(coinCode)
                .coinName(coinName)
                .build()
                );
        mappingRepository.save(
                MappingData.builder()
                .id(id2)
                .coinCode(coinCode2)
                .coinName(coinName2)
                .build()
                );
        
        // start test - query all
        UCMappingQueryOutput output = ucMappingQuery.execute(
                UCMappingQueryInput.builder()
                .build()
                );
        assertTrue(output.getQueryResult().size() == 2);
        log.info("[*][0][UCMappingQueryTest][ Test Success , result : {}]" , output);
        
        // start test - query by coin code
        output = ucMappingQuery.execute(
                UCMappingQueryInput.builder()
                .coinCode(coinCode)
                .build()
                );
        assertTrue(output.getQueryResult().size() == 1);
        assertTrue(output.getQueryResult().get(0).getCoinCode().equals(coinCode));
        log.info("[*][0][UCMappingQueryTest][ Test Success , result : {}]" , output);
        
        // start test - query by coin code which not exist
        output = ucMappingQuery.execute(
                UCMappingQueryInput.builder()
                .coinCode("NOT_EXIST")
                .build()
                );
        assertTrue(output.getQueryResult().size() == 0);
        log.info("[*][0][UCMappingQueryTest][ Test Success , result : {}]" , output);
        
        
    }
}
