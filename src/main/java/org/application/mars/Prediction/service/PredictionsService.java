package org.application.mars.Prediction.service;

import lombok.RequiredArgsConstructor;
import org.application.mars.MarketData.dtos.OHLCVBarDTO;
import org.application.mars.MarketData.dtos.OHLCVDTO;
import org.application.mars.MarketData.entities.TickerEntity;
import org.application.mars.MarketData.models.Massive.enums.Input.Timespan;
import org.application.mars.Prediction.client.MarsInferenceClient;
import org.application.mars.Prediction.models.*;
import org.application.mars.MarketData.repository.TickerRepository;
import org.application.mars.MarketData.service.PeerService;
import org.application.mars.MarketData.service.PriceChartService;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.application.mars.Common.utils.Constant.VOO;

/*
THIS WILL ONLY BE USED IN THE UNLIMITED API CALL VERSION
OTHERWISE THIS COULD EASILY OVERLOAD THE API LIMITS
 */

@Service
@RequiredArgsConstructor
public class PredictionsService {
    private final PeerService peerService;
    private final PriceChartService priceChartService;
    private final TickerRepository tickerRepository;
    private final MarsInferenceClient marsInferenceClient;

    public PredictionResponse predict(String ticker, Integer horizon) {
        if (ticker == null || horizon == null) {
            throw new NullPointerException("Ticker or horizon is null");
        }

        ticker.toUpperCase();
        //Get peers and their price data, placeholder industry
        List<String> peers = peerService.getPeers(ticker, "industry");
        List<BarRequest> bars = new ArrayList<>();
        List<BarRequest> vooBars = new ArrayList<>();
        Map<Long, String> sectors = new HashMap<>();

        //bars and sectors
        for (String peer : peers) {
            //Will implicity create a ticker that doesnt exist yet

            //CARE if you do decide to implement different multipliers

            Optional<OHLCVDTO> ohlcvdto = priceChartService.getPrices(peer, 1L, Timespan.DAY, null,null,null,null,null);
            Optional<TickerEntity> tickerEntity = tickerRepository.findByTicker(peer);
            if (ohlcvdto.isPresent() && tickerEntity.isPresent()) {
                bars.addAll(formatToBarDTO(ohlcvdto.orElse(null), tickerEntity.get()));
                sectors.put(tickerEntity.get().getTickerId(), "Technology");
            }
        }

        //voo_bars
        Optional<OHLCVDTO> ohlcvdto = priceChartService.getPrices(VOO, 1L, Timespan.DAY, null,null,null,null,null);
        Optional<TickerEntity> voo = tickerRepository.findByTicker(VOO);
        if (ohlcvdto.isPresent() && voo.isPresent()) {
            vooBars = formatToBarDTO(ohlcvdto.orElse(null), voo.get());
        }

        //Send request to mars-ml
        PredictionRequest predictionRequest = new PredictionRequest();
        predictionRequest.setHorizon(horizon);
        predictionRequest.setSectors(sectors);
        predictionRequest.setBars(bars);
        predictionRequest.setVooBars(vooBars);

        PredictionResponse response = marsInferenceClient.predict(predictionRequest);
        if (!response.getResults().isEmpty()) {
            PredictionResult result = response.getResults().getFirst();
            System.out.println(result);

            ModelInfo modelInfo = response.getModelInfo();
            System.out.println(modelInfo);
        } else {
            System.out.println("No results — check errors: " + response.getErrors());
        }

        return response;
    }

    private List<BarRequest> formatToBarDTO(OHLCVDTO ohlcvdto, TickerEntity tickerEntity) {
        List<BarRequest> bars = new ArrayList<>();
        Long tickerId = tickerEntity.getTickerId();

        for (OHLCVBarDTO price : ohlcvdto.getResults()) {
            BarRequest barRequest = new BarRequest();

            barRequest.setTickerId(tickerId);
            barRequest.setTradeDate(price.getTime());
            barRequest.setOpen(price.getOpen());
            barRequest.setHigh(price.getHigh());
            barRequest.setLow(price.getLow());
            barRequest.setClose(price.getClose());
            barRequest.setVolume(price.getVolume());

            bars.add(barRequest);
        }

        return bars;
    }

}
