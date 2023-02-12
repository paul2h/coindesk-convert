package tw.kiwi.coindesk.adapter.controller.vo;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MappingDataVo {

    private String id;
    
    private Timestamp createdAt;
    
    private Timestamp updatedAt;
    
    private String coinCode;
    
    private String coinName;
}
