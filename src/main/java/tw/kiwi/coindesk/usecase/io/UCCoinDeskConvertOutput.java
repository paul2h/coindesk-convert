package tw.kiwi.coindesk.usecase.io;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import tw.kiwi.coindesk.entity.RateData;

@Builder
@Data
public class UCCoinDeskConvertOutput {

    private String updateTimeString;
    
    private List<RateData> rateDatas;
    
}
