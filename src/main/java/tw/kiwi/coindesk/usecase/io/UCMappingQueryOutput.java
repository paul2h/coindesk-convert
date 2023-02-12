package tw.kiwi.coindesk.usecase.io;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import tw.kiwi.coindesk.entity.MappingData;

@Builder
@Data
public class UCMappingQueryOutput {

    private List<MappingData> queryResult;
    
}
