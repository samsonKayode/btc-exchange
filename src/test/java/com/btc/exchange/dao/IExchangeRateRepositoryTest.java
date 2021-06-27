package com.btc.exchange.dao;

import com.btc.exchange.entity.ExchangeRate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class IExchangeRateRepositoryTest {

    @Autowired
    private IExchangeRateRepository underTest;

    /*
    This test checks that the timestamp exists in the memory..
    */
    @Test
     void checkIfTimestampExists() {

        //given
        long timestamp = 1624600698;
        ExchangeRate exchangeRate = new ExchangeRate(1, new Date(timestamp*1000), timestamp, "USD", 31897.9009);
        underTest.save(exchangeRate);
        //when
        boolean exists = underTest.existsByTimestamp(timestamp);
        //then
        assertThat(exists).isTrue();
    }

    /*
    This test checks that the timestamp does not exist in the memory..
     */
    @Test
    void checkIfTimestampDoesNotExist() {

        //given
        long timestamp = 1624600698;
        //when
        boolean exists = underTest.existsByTimestamp(timestamp);
        //then
        assertThat(exists).isFalse();
    }

    /*
        This test checks if data is saved...
     */
    @Test
    void save() {

        //given
        long timestamp = 1624600698;
        ExchangeRate exchangeRate = new ExchangeRate(1, new Date(timestamp*1000), timestamp, "USD", 31897.9009);
        //when
        ExchangeRate result = underTest.save(exchangeRate);
        //then
        assertThat(result.getTimestamp()).isEqualTo(timestamp);
    }

    @Test
    void findTopByOrderByTimestampDesc() {
        //given
        long timestamp = 1624711825;
        long timestamp2 = 1624713222;
        ExchangeRate exchangeRate = new ExchangeRate(1, new Date(timestamp*1000), timestamp, "USD", 31897.9009);
        ExchangeRate exchangeRate2 = new ExchangeRate(2, new Date(timestamp2*1000), timestamp2, "USD", 32897.9009);

        underTest.save(exchangeRate);
        underTest.save(exchangeRate2);

        //when
        ExchangeRate result = underTest.findTopByOrderByTimestampDesc();

        //then
        assertThat(result.getTimestamp()).isEqualTo(timestamp2);
    }

    @Test
    void findByDateBetweenOrderByDateDesc() throws ParseException {

        String sDate1="2021/01/01";
        Date sDate=new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);

        String sDate21="2021/12/31";
        Date sDate2=new SimpleDateFormat("yyyy/MM/dd").parse(sDate21);

        //given
        long timestamp = 1624611825;
        long timestamp2 = 1624713222;
        Date date1 = new Date(timestamp*1000);
        Date date2 = new Date(timestamp2*1000);

        ExchangeRate exchangeRate = new ExchangeRate(1, date1, timestamp, "USD", 31897.9009);
        ExchangeRate exchangeRate2 = new ExchangeRate(2, date2, timestamp2, "USD", 32897.9009);

        underTest.save(exchangeRate);
        underTest.save(exchangeRate2);

        //when
        List<ExchangeRate> exResult = underTest.findByDateBetweenOrderByDateDesc(sDate, sDate2);

        //then
        assertThat(exResult.size()).isEqualTo(2);
    }
}