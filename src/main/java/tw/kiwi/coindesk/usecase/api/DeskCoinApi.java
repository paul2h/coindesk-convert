package tw.kiwi.coindesk.usecase.api;

import com.fasterxml.jackson.databind.JsonNode;

public interface DeskCoinApi {

    JsonNode fetchCoinDeskData();
}
