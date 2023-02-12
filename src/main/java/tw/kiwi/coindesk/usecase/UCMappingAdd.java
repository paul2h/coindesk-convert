package tw.kiwi.coindesk.usecase;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tw.kiwi.coindesk.entity.MappingData;
import tw.kiwi.coindesk.usecase.io.UCMappingAddInput;
import tw.kiwi.coindesk.usecase.io.UCMappingAddOutput;
import tw.kiwi.coindesk.usecase.repository.MappingRepository;

@Service
@AllArgsConstructor
public class UCMappingAdd {
    
    private MappingRepository mappingRepository;

    public UCMappingAddOutput execute(UCMappingAddInput input) {
        
        String id = UUID.randomUUID().toString();
        
        mappingRepository.save(
                MappingData.builder()
                .id(id)
                .createdAt(Timestamp.from(Instant.now()))
                .coinCode(input.getCoinCode())
                .coinName(input.getCoinName())
                .build()
                );
        
        UCMappingAddOutput output = UCMappingAddOutput.builder().success(true).newId(id).build();
        
        return output;
    }
    
}
