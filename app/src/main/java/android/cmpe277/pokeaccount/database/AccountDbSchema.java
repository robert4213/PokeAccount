package android.cmpe277.pokeaccount.database;

public class AccountDbSchema {
    public static final class AccountTable{
        public static final String NAME = "accounts";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String CASH = "cash";
            public static final String PAYPAL = "paypal";
            public static final String BALANCE = "balance";
        }
    }
}
