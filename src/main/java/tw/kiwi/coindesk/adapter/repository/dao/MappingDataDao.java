package tw.kiwi.coindesk.adapter.repository.dao;

import org.springframework.data.repository.CrudRepository;

import tw.kiwi.coindesk.adapter.repository.vo.MappingDataVo;

public interface MappingDataDao extends CrudRepository<MappingDataVo, String>{

}
