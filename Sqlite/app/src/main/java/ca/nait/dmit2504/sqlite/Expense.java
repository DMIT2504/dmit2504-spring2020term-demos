package ca.nait.dmit2504.sqlite;

public class Expense {

    private Integer mId;
    private String mDescription;
    private String mAmount;
    private String mDate;

    // Generate getters+setters for each data field

    public Integer getId() {
        return mId;
    }

    public void setId(final Integer id) {
        mId = id;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(final String description) {
        mDescription = description;
    }

    public String getAmount() {
        return mAmount;
    }

    public void setAmount(final String amount) {
        mAmount = amount;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(final String date) {
        mDate = date;
    }
}
