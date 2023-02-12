package tw.kiwi.coindesk.usecase.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import tw.kiwi.coindesk.entity.MappingData;
import tw.kiwi.coindesk.usecase.repository.MappingRepository;

@Service
@Primary
public class MappingRepositoryInMemory implements MappingRepository{

    private Map<String , MappingData> memoryData = new HashMap<>();
    
    @Override
    public List<MappingData> findAll() {
        List<MappingData> result = new ArrayList<>(memoryData.values());
        return  result;
    }

    @Override
    public boolean save(MappingData mappingData) {
        memoryData.put(mappingData.getId(), mappingData);
        return true;
    }

    @Override
    public MappingData findById(String id) {
        return memoryData.get(id);
    }

    @Override
    public boolean remove(MappingData mappingData) {
        if(memoryData.containsKey(mappingData.getId())) {
            memoryData.remove(mappingData.getId());
            return true;
        }else {
            return false;
        }
    }

}
