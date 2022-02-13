package kg.alymzhan.automatizatorept.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TxnDto {

    private long idTxn;
    private long idPayer;
    private String payerPhone;
    private long idPayee;
    private String payeePhone;
    private String currency;
    private String text;
    private Double amount;
    private int useCase;
    private String orderNum;
    private Timestamp creationDate;
}
