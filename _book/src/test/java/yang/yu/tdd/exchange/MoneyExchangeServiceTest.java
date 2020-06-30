package yang.yu.tdd.exchange;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MoneyExchangeServiceTest {

    @Mock
    private ExchangeRateService exchangeRateService;

    private MoneyExchangeService instance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        //exchangeRateService = mock(ExchangeRateService.class);
        instance = new MoneyExchangeService(exchangeRateService);
    }

    @Test
    void fromCnyToUsd() {
        when(exchangeRateService.exchangeOf(Currency.CNY, Currency.USD)).thenReturn(new BigDecimal("0.1413"));
        Money cny = Money.of(new BigDecimal("10000"), Currency.CNY);
        Money usd = Money.of(new BigDecimal("1413.0000"), Currency.USD);
        assertThat(instance.exchangeTo(cny, Currency.USD)).isEqualTo(usd);
    }
}