package tw.kiwi.coindesk.usecase.io;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UCMappingAddOutput {

    private boolean success;
    
    private String newId;
}
