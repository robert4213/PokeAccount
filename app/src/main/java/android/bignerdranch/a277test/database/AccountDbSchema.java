package android.bignerdranch.a277test.database;

public class AccountDbSchema {
    public static final class AccountTable{
        public static final String NAME = "accounts";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String Type = "type";
            public static final String COMPANY = "company";
            public static final String BALANCE = "balance";
        }
    }
}
