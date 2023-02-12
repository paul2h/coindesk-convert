package tw.kiwi.coindesk.usecase.io;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UCCoinDeskGetOutput {

    private String updateTimeString;
    
    private Map<String, Double> coinRates;
    
}
