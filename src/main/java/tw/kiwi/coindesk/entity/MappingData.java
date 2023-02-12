package tw.kiwi.coindesk.entity;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MappingData {

    private String id;
    
    private Timestamp createdAt;
    
    private Timestamp updatedAt;
    
    private String coinCode;
    
    private String coinName;
}
