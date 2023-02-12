package tw.kiwi.coindesk.usecase;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tw.kiwi.coindesk.entity.MappingData;
import tw.kiwi.coindesk.usecase.io.UCMappingDeleteInput;
import tw.kiwi.coindesk.usecase.io.UCMappingDeleteOutput;
import tw.kiwi.coindesk.usecase.repository.MappingRepository;

@Service
@AllArgsConstructor
public class UCMappingDelete {

    private MappingRepository mappingRepository;

    public UCMappingDeleteOutput execute(UCMappingDeleteInput input) {
        UCMappingDeleteOutput output = UCMappingDeleteOutput.builder().build();
        
        MappingData removeTarget = mappingRepository.findById(input.getId());
        if(removeTarget != null) {
            mappingRepository.remove(removeTarget);
            output.setSuccess(true);
        } else {
            output.setSuccess(false);
        }
        
        return output;
    }
}
