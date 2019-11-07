package android.bignerdranch.a277test;

public class Transaction {
    private String ACCOUNTID;
    private String INCOME_EXPENSE;

    private String DATE;
    private String TYPE;
    private String VALUE;



    public String getACCOUNTID() {
        return ACCOUNTID;
    }

    public void setACCOUNTID(String ACCOUNTID) {
        this.ACCOUNTID = ACCOUNTID;
    }

    public String getINCOME_EXPENSE() {
        return INCOME_EXPENSE;
    }

    public void setINCOME_EXPENSE(String INCOME_EXPENSE) {
        this.INCOME_EXPENSE = INCOME_EXPENSE;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getVALUE() {
        return VALUE;
    }

    public void setVALUE(String VALUE) {
        this.VALUE = VALUE;
    }
}
