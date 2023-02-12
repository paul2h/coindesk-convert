package tw.kiwi.coindesk.usecase.io;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UCMappingUpdateInput {

    private String id;
    
    private String coinCode;
    
    private String coinName;
}
