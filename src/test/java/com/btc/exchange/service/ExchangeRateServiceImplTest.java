package com.btc.exchange.service;

import com.btc.exchange.dao.IExchangeRateRepository;
import com.btc.exchange.entity.ExchangeRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceImplTest {

    @Mock
    private IExchangeRateRepository exchangeRateRepository;
    ExchangeRateServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new ExchangeRateServiceImpl(exchangeRateRepository);
    }

    @Test
    void findTopByOrderByTimestampDesc() {
        //when
        exchangeRateRepository.findTopByOrderByTimestampDesc();
        //then
        verify(exchangeRateRepository).findTopByOrderByTimestampDesc();
    }

    @Test
    void findByDateBetweenOrderByDateDesc() throws ParseException {
        String sDate1="2021/01/01";
        Date sDate=new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);

        String sDate21="2021/12/31";
        Date sDate2=new SimpleDateFormat("yyyy/MM/dd").parse(sDate21);

        //when
        exchangeRateRepository.findByDateBetweenOrderByDateDesc(sDate, sDate2);
        //then
        verify(exchangeRateRepository).findByDateBetweenOrderByDateDesc(sDate, sDate2);
    }

    @Test
    void saveExchangeRate() {

        long timestamp = 1624600698;
        ExchangeRate exchangeRate = new ExchangeRate(1, new Date(timestamp*1000), timestamp, "USD", 31897.9009);

        //when
        underTest.saveExchangeRate(exchangeRate);

        //then
        ArgumentCaptor<ExchangeRate> exchangeRateArgumentCaptor = ArgumentCaptor.forClass(ExchangeRate.class);
        verify(exchangeRateRepository).save(exchangeRateArgumentCaptor.capture());

        ExchangeRate capturedExchangeRate = exchangeRateArgumentCaptor.getValue();
        assertThat(capturedExchangeRate).isEqualTo(exchangeRate);

    }
}