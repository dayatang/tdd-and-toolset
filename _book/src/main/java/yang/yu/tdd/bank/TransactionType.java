package yang.yu.tdd.bank;

/**
 * 事务类型
 */
public enum TransactionType {
    DEBIT,      //取款
    CREDIT,     //存款
    FREEZE,     //冻结
    UNFREEZE    //解冻
}
