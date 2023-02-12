package tw.kiwi.coindesk.usecase;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import tw.kiwi.coindesk.usecase.api.DeskCoinApi;
import tw.kiwi.coindesk.usecase.io.UCCoinDeskGetInput;
import tw.kiwi.coindesk.usecase.io.UCCoinDeskGetOutput;

@SpringBootTest
@Slf4j
public class UCCoinDeskGetTest {

    @Autowired
    UCCoinDeskGet ucCoinDeskGet;
    
    @Autowired
    DeskCoinApi deskCoinApi;
    
    @Test
    void test() {
        
        UCCoinDeskGetOutput output = ucCoinDeskGet.execute(UCCoinDeskGetInput.builder().build());
        
        assertTrue(output.getCoinRates() != null);
        assertTrue(output.getCoinRates().size() == 3);
        log.info("[*][0][UCCoinDeskGetTest][ Test Success , result : {}]" , output);
        
    }
}
