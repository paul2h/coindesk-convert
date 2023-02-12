package tw.kiwi.coindesk.adapter.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.AllArgsConstructor;
import tw.kiwi.coindesk.adapter.controller.vo.MappingDataVo;
import tw.kiwi.coindesk.entity.MappingData;
import tw.kiwi.coindesk.usecase.UCCoinDeskConvert;
import tw.kiwi.coindesk.usecase.UCCoinDeskGet;
import tw.kiwi.coindesk.usecase.UCMappingAdd;
import tw.kiwi.coindesk.usecase.UCMappingDelete;
import tw.kiwi.coindesk.usecase.UCMappingQuery;
import tw.kiwi.coindesk.usecase.UCMappingUpdate;
import tw.kiwi.coindesk.usecase.io.UCCoinDeskConvertInput;
import tw.kiwi.coindesk.usecase.io.UCCoinDeskConvertOutput;
import tw.kiwi.coindesk.usecase.io.UCCoinDeskGetInput;
import tw.kiwi.coindesk.usecase.io.UCCoinDeskGetOutput;
import tw.kiwi.coindesk.usecase.io.UCMappingAddInput;
import tw.kiwi.coindesk.usecase.io.UCMappingDeleteInput;
import tw.kiwi.coindesk.usecase.io.UCMappingQueryInput;
import tw.kiwi.coindesk.usecase.io.UCMappingUpdateInput;

@RestController
@AllArgsConstructor
public class MainController {

    private UCMappingAdd ucMappingAdd;
    
    private UCMappingDelete ucMappingDelete;
    
    private UCMappingUpdate ucMappingUpdate;
    
    private UCMappingQuery ucMappingQuery;
    
    private UCCoinDeskGet ucCoinDeskGet;
    
    private UCCoinDeskConvert ucCoinDeskConvert;
    
    @GetMapping("/mapping/{coinCode}")
    public ResponseEntity<List<MappingData>> queryMappingDatas(@PathVariable String coinCode){
        
        List<MappingData> result = ucMappingQuery.execute(UCMappingQueryInput.builder().coinCode(coinCode).build()).getQueryResult();
        
        return ResponseEntity.ok(result);
        
    }
    
    @GetMapping("/mapping")
    public ResponseEntity<List<MappingData>> queryMappingDatasAll(){
        
        List<MappingData> result = ucMappingQuery.execute(UCMappingQueryInput.builder().build()).getQueryResult();
        
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/mapping")
    public void updateMappingData(@RequestBody MappingDataVo request) throws Exception{
        if (ucMappingUpdate.execute(UCMappingUpdateInput.builder().coinCode(request.getCoinCode()).coinName(request.getCoinName()).build())
                .isSuccess()) {

            throw new Exception("Add failed!");
        }
    }
    
    @PutMapping("/mapping")
    public void addMappingData(@RequestBody MappingDataVo request) throws Exception {
        if (ucMappingAdd.execute(UCMappingAddInput.builder().coinCode(request.getCoinCode()).coinName(request.getCoinName()).build())
                .isSuccess()) {

            throw new Exception("Add failed!");
        }

    }
    
    @DeleteMapping("/mapping")
    public void deleteMappingDate(@RequestBody MappingDataVo request) throws Exception{
        if(ucMappingDelete.execute(UCMappingDeleteInput.builder().build()).isSuccess()) {
            throw new Exception("Delete failed!");
        }
    }
    
    @GetMapping("/test")
    public ResponseEntity<JsonNode> test(){
        return null;
    }
    
    @GetMapping("/coindesk/fetch")
    public ResponseEntity<UCCoinDeskGetOutput> fetchCoinDeskData(){
        return ResponseEntity.ok(ucCoinDeskGet.execute(UCCoinDeskGetInput.builder().build()));
        
    }
    
    @GetMapping("/coindesk/convert")
    public ResponseEntity<UCCoinDeskConvertOutput> convertCoinDeskData(){
        return ResponseEntity.ok(ucCoinDeskConvert.execute(UCCoinDeskConvertInput.builder().build()));
        
    }
}
