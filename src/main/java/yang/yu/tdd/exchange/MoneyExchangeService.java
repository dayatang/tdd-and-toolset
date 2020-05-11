package yang.yu.tdd.exchange;

import java.math.BigDecimal;

public class MoneyExchangeService {
    private ExchangeRateService exchangeRateService;

    public MoneyExchangeService(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    public Money exchangeTo(Money from, Currency to) {
        BigDecimal amount = exchangeRateService.exchangeOf(from.getCurrency(), to)
                .multiply(from.getAmount());
        return Money.of(amount, to);
    }
}
