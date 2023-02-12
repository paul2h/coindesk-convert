package tw.kiwi.coindesk.usecase;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tw.kiwi.coindesk.entity.MappingData;
import tw.kiwi.coindesk.usecase.io.UCMappingUpdateInput;
import tw.kiwi.coindesk.usecase.io.UCMappingUpdateOutput;
import tw.kiwi.coindesk.usecase.repository.MappingRepository;

@Service
@AllArgsConstructor
public class UCMappingUpdate {
    
    private MappingRepository mappingRepository;

    public UCMappingUpdateOutput execute(UCMappingUpdateInput input) {
        UCMappingUpdateOutput output = UCMappingUpdateOutput.builder().build();
        MappingData mappingData = mappingRepository.findById(input.getId());
        
        if(mappingData != null) {
            
            mappingData.setCoinCode(input.getCoinCode());
            mappingData.setCoinName(input.getCoinName());
            mappingData.setUpdatedAt(Timestamp.from(Instant.now()));
            
            mappingRepository.save(mappingData);
            
            output.setSuccess(mappingRepository.save(mappingData));
        } else {
            output.setSuccess(false);
        }
        
        return output;
    }
}
