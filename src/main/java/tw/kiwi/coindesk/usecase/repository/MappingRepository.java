package tw.kiwi.coindesk.usecase.repository;

import java.util.List;

import tw.kiwi.coindesk.entity.MappingData;

public interface MappingRepository{

    public List<MappingData> findAll();
    
    public MappingData findById(String id);
    
    public boolean save(MappingData mappingData);
    
    public boolean remove(MappingData mappingData);
    
}
