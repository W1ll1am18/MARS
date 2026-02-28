package org.application.mars.MarketData.models.Massive.Fundamentals;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShortVolume {
    @JsonProperty("adf_short_volume")
    private Long adfShortVolume;

    @JsonProperty("adf_short_volume_exempt")
    private Long adfShortVolumeExempt;

    @JsonProperty("date")
    private String date;

    @JsonProperty("exempt_volume")
    private Double exemptVolume;

    @JsonProperty("nasdaq_carteret_short_volume")
    private Long nasdaqCarteretShortVolume;

    @JsonProperty("nasdaq_carteret_short_volume_exempt")
    private Long nasdaqCarteretShortVolumeExempt;

    @JsonProperty("nasdaq_chicago_short_volume")
    private Long nasdaqChicagoShortVolume;

    @JsonProperty("nasdaq_chicago_short_volume_exempt")
    private Long nasdaqChicagoShortVolumeExempt;

    @JsonProperty("non_exempt_volume")
    private Double nonExemptVolume;

    @JsonProperty("nyse_short_volume")
    private Long nyseShortVolume;

    @JsonProperty("nyse_short_volume_exempt")
    private Long nyseShortVolumeExempt;

    @JsonProperty("short_volume")
    private Double shortVolume;

    @JsonProperty("short_volume_ratio")
    private Double shortVolumeRatio;

    @JsonProperty("ticker")
    private String ticker;

    @JsonProperty("total_volume")
    private Double totalVolume;
}
