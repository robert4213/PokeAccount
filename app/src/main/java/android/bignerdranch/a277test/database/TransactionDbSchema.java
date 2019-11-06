package android.bignerdranch.a277test.database;


import java.util.Date;

public class TransactionDbSchema {
        public static final class Transactions{
            public static final String NAME = "Transactions";

            public static final class Cols{
                public static final String ACCOUNTID = "accountid";
                public static final String INCOME_EXPENSE = "Income_Expense";
                public static final String CARD_NUMBER = "CardNumber";
                public static final String Date="date";
                public static final String Type="type";
                public static final String Value="value";
            }
        }
    }

