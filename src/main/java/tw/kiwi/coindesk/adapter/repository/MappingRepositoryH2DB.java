package tw.kiwi.coindesk.adapter.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.kiwi.coindesk.adapter.repository.dao.MappingDataDao;
import tw.kiwi.coindesk.entity.MappingData;
import tw.kiwi.coindesk.usecase.repository.MappingRepository;

@Service
public class MappingRepositoryH2DB implements MappingRepository {

    @Autowired
    MappingDataDao mappingDataDao;
    
    @Override
    public List<MappingData> findAll() {
        List<MappingData> result = new ArrayList<MappingData>();
        mappingDataDao.findAll().forEach(mappingDataVo -> {
            
            result.add(
                    MappingData.builder()
                    .coinCode(mappingDataVo.getCoinCode())
                    .coinName(mappingDataVo.getCoinName())
                    .id(mappingDataVo.getId())
                    .createdAt(mappingDataVo.getCreatedAt())
                    .updatedAt(mappingDataVo.getUpdatedAt())
                    .build());
        });
        return result;
    }

    @Override
    public MappingData findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean save(MappingData mappingData) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean remove(MappingData mappingData) {
        // TODO Auto-generated method stub
        return false;
    }
}
