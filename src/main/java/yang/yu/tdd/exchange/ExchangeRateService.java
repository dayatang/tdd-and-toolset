package yang.yu.tdd.exchange;

import java.math.BigDecimal;

public interface ExchangeRateService {

    BigDecimal exchangeOf(Currency from, Currency to);
}
