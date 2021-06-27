package com.btc.exchange.restcontroller;

import com.btc.exchange.entity.ExchangeRate;
import com.btc.exchange.service.IExchangeRateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(IExchangeRateRestController.class)
class IExchangeRateRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IExchangeRateService exchangeRateService;

    @Test
    void getLatest() throws Exception {
        long timestamp = 1624600698;
        ExchangeRate exchangeRate = new ExchangeRate(1, new Date(timestamp*1000), timestamp, "USD", 31897.9009);

        when(exchangeRateService.findTopByOrderByTimestampDesc()).thenReturn(exchangeRate);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/exchange-rate/latest"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").value(timestamp))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("USD"));
    }

    @Test
    void getByDates() throws Exception {

        long timestamp = 1614600698;
        ExchangeRate exchangeRate = new ExchangeRate(1, new Date(timestamp*1000), timestamp, "USD", 31897.9009);

        long timestamp2 = 1624600690;
        ExchangeRate exchangeRate2 = new ExchangeRate(2, new Date(timestamp*1000), timestamp, "USD", 31597.1218);

        List<ExchangeRate> exchangeRateList = new ArrayList<>();

        exchangeRateList.add(exchangeRate);
        exchangeRateList.add(exchangeRate2);

        String sDate1="2021/01/01";
        Date sDate=new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);

        String sDate21="2021/12/31";
        Date sDate2=new SimpleDateFormat("yyyy/MM/dd").parse(sDate21);

        when(exchangeRateService.findByDateBetweenOrderByDateDesc(sDate, sDate2)).thenReturn(exchangeRateList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/exchange-rate/list?startDate=2021/01/01&endDate=2021/12/31"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].timestamp").value(timestamp))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currency").value("USD"));
    }
}