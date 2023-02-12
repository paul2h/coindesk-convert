package tw.kiwi.coindesk.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RateData {

    private String coinCode;
    
    private String coinName;
    
    private Double rate;
}
