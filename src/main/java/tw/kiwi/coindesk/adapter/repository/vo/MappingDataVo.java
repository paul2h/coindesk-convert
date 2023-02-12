package tw.kiwi.coindesk.adapter.repository.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class MappingDataVo {

    @Id
    private String id;
    
    @Column
    private Timestamp createdAt;
    
    @Column
    private Timestamp updatedAt;
    
    @Column
    private String coinCode;
    
    @Column
    private String coinName;
    
}
