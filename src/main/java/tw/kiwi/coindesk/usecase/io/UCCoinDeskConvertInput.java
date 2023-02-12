package tw.kiwi.coindesk.usecase.io;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UCCoinDeskConvertInput {

    private String coinCode;
}
