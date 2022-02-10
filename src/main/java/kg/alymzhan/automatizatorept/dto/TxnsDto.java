package kg.alymzhan.automatizatorept.dto;

public class TxnsDto {

    private long idTxn;
    private long idPayer;
    private String payerPhone;
    private long idPayee;
    private String payeePhone;
    private String currency;
    private String text;
    private String amount;
    private int useCase;
    private String orderNum;
    private String creationDate;

    public TxnsDto() {
    }

    public TxnsDto(long idTxn, long idPayer, String payerPhone, long idPayee, String payeePhone, String currency, String text, String amount, int useCase, String orderNum, String creationDate) {
        this.idTxn = idTxn;
        this.idPayer = idPayer;
        this.payerPhone = payerPhone;
        this.idPayee = idPayee;
        this.payeePhone = payeePhone;
        this.currency = currency;
        this.text = text;
        this.amount = amount;
        this.useCase = useCase;
        this.orderNum = orderNum;
        this.creationDate = creationDate;
    }

    public long getIdTxn() {
        return idTxn;
    }

    public void setIdTxn(long idTxn) {
        this.idTxn = idTxn;
    }

    public long getIdPayer() {
        return idPayer;
    }

    public void setIdPayer(long idPayer) {
        this.idPayer = idPayer;
    }

    public String getPayerPhone() {
        return payerPhone;
    }

    public void setPayerPhone(String payerPhone) {
        this.payerPhone = payerPhone;
    }

    public long getIdPayee() {
        return idPayee;
    }

    public void setIdPayee(long idPayee) {
        this.idPayee = idPayee;
    }

    public String getPayeePhone() {
        return payeePhone;
    }

    public void setPayeePhone(String payeePhone) {
        this.payeePhone = payeePhone;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getUseCase() {
        return useCase;
    }

    public void setUseCase(int useCase) {
        this.useCase = useCase;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Txns {" +
                "idTxn=" + idTxn +
                ", idPayer=" + idPayer +
                ", payerPhone='" + payerPhone + '\'' +
                ", idPayee=" + idPayee +
                ", payeePhone='" + payeePhone + '\'' +
                ", currency='" + currency + '\'' +
                ", text='" + text + '\'' +
                ", amount=" + amount +
                ", useCase=" + useCase +
                ", orderNum='" + orderNum + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
