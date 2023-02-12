package tw.kiwi.coindesk.usecase;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.kiwi.coindesk.usecase.io.UCMappingAddInput;
import tw.kiwi.coindesk.usecase.io.UCMappingAddOutput;
import tw.kiwi.coindesk.usecase.repository.MappingRepository;

@SpringBootTest
public class UCMappingAddTest {

    @Autowired
    UCMappingAdd ucMappingAdd;
    
    @Autowired
    MappingRepository mappingRepository;
    
    @Test
    void mappingAddTest() {
        // init test datas
        mappingRepository.findAll().forEach(oldData -> mappingRepository.remove(oldData));
        String coinCode = "TEST";
        String coinName = "測試幣別";
        
        // start test
        UCMappingAddOutput output = ucMappingAdd.execute(
                UCMappingAddInput.builder()
                .coinCode(coinCode)
                .coinName(coinName)
                .build()
                );
        
        assertTrue(output.isSuccess());
        assertTrue(mappingRepository.findAll().size() > 0);
        assertTrue(mappingRepository.findById(output.getNewId()) != null);
        assertTrue(mappingRepository.findById(output.getNewId()).getCoinCode().equals(coinCode));
        assertTrue(mappingRepository.findById(output.getNewId()).getCoinName().equals(coinName));
        
    }
}
