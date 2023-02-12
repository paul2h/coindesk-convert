package tw.kiwi.coindesk.usecase;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import tw.kiwi.coindesk.entity.MappingData;
import tw.kiwi.coindesk.usecase.io.UCMappingUpdateInput;
import tw.kiwi.coindesk.usecase.io.UCMappingUpdateOutput;
import tw.kiwi.coindesk.usecase.repository.MappingRepository;

@SpringBootTest
@Slf4j
public class UCMappingUpdateTest {

    @Autowired
    UCMappingUpdate ucMappingUpdate;
    
    @Autowired
    MappingRepository mappingRepository;
    
    @Test
    void mappingUpdateTest() {
        
        // init test datas
        mappingRepository.findAll().forEach(oldData -> mappingRepository.remove(oldData));
        String id = UUID.randomUUID().toString();
        String coinCode = "TEST";
        String coinName = "測試幣別";
        mappingRepository.save(MappingData.builder().id(id).coinCode(coinCode).coinName(coinName).build());
        assertTrue(mappingRepository.findById(id).getCoinCode().equals(coinCode));
        assertTrue(mappingRepository.findById(id).getCoinName().equals(coinName));
        
        // start test usecase
        String newCoinCode = "TEST2";
        String newCoinName = "測試幣別2";
        UCMappingUpdateOutput output = ucMappingUpdate.execute(
                UCMappingUpdateInput.builder()
                .id(id)
                .coinCode(newCoinCode)
                .coinName(newCoinName)
                .build()
                );
        
        assertTrue(output.isSuccess());
        assertTrue(mappingRepository.findById(id).getCoinCode().equals(newCoinCode));
        assertTrue(mappingRepository.findById(id).getCoinName().equals(newCoinName));
        
        log.info("[*][0][UCMappingUpdateTest][ Test Success , result : {}]" , mappingRepository.findById(id));
    }
}
