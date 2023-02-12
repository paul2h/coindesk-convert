package tw.kiwi.coindesk.usecase;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.kiwi.coindesk.entity.MappingData;
import tw.kiwi.coindesk.usecase.io.UCMappingDeleteInput;
import tw.kiwi.coindesk.usecase.io.UCMappingDeleteOutput;
import tw.kiwi.coindesk.usecase.repository.MappingRepository;

@SpringBootTest
public class UCMappingDeleteTest {

    @Autowired
    UCMappingDelete ucMappingDelete;

    @Autowired
    MappingRepository mappingRepository;

    @Test
    void mappingDeleteTest() {
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
                        .build());
        mappingRepository.save(
                MappingData.builder()
                        .id(id2)
                        .coinCode(coinCode2)
                        .coinName(coinName2)
                        .build());

        // start test - delete by id which not exist
        UCMappingDeleteOutput output = ucMappingDelete.execute(
                UCMappingDeleteInput.builder()
                        .id("ID_NOT_EXIST")
                        .build());

        assertTrue(!output.isSuccess());
        assertTrue(mappingRepository.findById(id2) != null);
        assertTrue(mappingRepository.findById(id1) != null);
        
        // start test - delete by id
        output = ucMappingDelete.execute(
                UCMappingDeleteInput.builder()
                        .id(id2)
                        .build());

        assertTrue(output.isSuccess());
        assertTrue(mappingRepository.findById(id2) == null);
        assertTrue(mappingRepository.findById(id1) != null);


    }
}
