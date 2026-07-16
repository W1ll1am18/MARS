package org.application.mars.MarketData.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.client.FinnhubClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PeerService {
    private final FinnhubClient finnhubClient;

    public List<String> getPeers(String ticker, String grouping) {
        if (ticker == null || grouping == null) {
            return new ArrayList<>();
        }

        //DB query
        //Check db first
        //if there exists at least one similar ticker then get and return
        //else query finnhub

        //Placeholder, should be enum if I want to expand
        StringBuilder url = new StringBuilder();
        url.append(ticker).append("&grouping=").append(grouping).append("&");

        List<String> results = finnhubClient.getPeers(url.toString());
        if (results == null) {
            return new ArrayList<>();
        }

        //Add to db
        //If tickers dont exists initally, then call MASSIVE ticker service to create one in the db
        //then add to related tickers table

        return results;
    }
}
