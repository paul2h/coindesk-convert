package tw.kiwi.coindesk.usecase;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tw.kiwi.coindesk.usecase.io.UCMappingQueryInput;
import tw.kiwi.coindesk.usecase.io.UCMappingQueryOutput;
import tw.kiwi.coindesk.usecase.repository.MappingRepository;

@Service
@AllArgsConstructor
public class UCMappingQuery {
    
    private MappingRepository mappingRepository;

    public UCMappingQueryOutput execute(UCMappingQueryInput input) {
        
        UCMappingQueryOutput output = UCMappingQueryOutput.builder().build();
        
        if(input.getCoinCode() != null && !"".equals(input.getCoinCode().strip())) {
            // Query by coin code
            output.setQueryResult(
                    mappingRepository.findAll().stream()
                    .filter(
                            mappingData -> mappingData.getCoinCode().equals(input.getCoinCode())).collect(Collectors.toList())
                    );
        } else {
            // Query all
            output.setQueryResult(mappingRepository.findAll());
        }
        
        
        return output;
    }
}
