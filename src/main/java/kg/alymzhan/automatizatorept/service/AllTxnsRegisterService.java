package kg.alymzhan.automatizatorept.service;

import kg.alymzhan.automatizatorept.dto.TxnsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllTxnsRegisterService {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public AllTxnsRegisterService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TxnsDto> getAllTxns() {
        String sql =
                "SELECT T.ID_TXN,\n" +
                        "       T.ID_PAYER,\n" +
                        "       I.STR_IDENTIFICATION as payerPhone,\n" +
                        "       T.ID_PAYEE,\n" +
                        "       II.STR_IDENTIFICATION as payeePhone,\n" +
                        "       T.ID_CURRENCY,\n" +
                        "       T.STR_TEXT,\n" +
                        "       T.AMNT_AMOUNT,\n" +
                        "       T.ID_USE_CASE,\n" +
                        "       T.STR_SPARE_1,\n" +
                        "       T.DAT_CREATION\n" +
                        "FROM (\n" +
                        "         SELECT ID_TXN,\n" +
                        "                ID_PAYER,\n" +
                        "                ID_PAYEE,\n" +
                        "                ID_CURRENCY,\n" +
                        "                STR_TEXT,\n" +
                        "                AMNT_AMOUNT,\n" +
                        "                ID_USE_CASE,\n" +
                        "                STR_SPARE_1,\n" +
                        "                DAT_CREATION\n" +
                        "         FROM mobr5.mobr5.MOB_TXNS\n" +
                        "         WHERE STR_SPARE_2 = 'prinyata'\n" +
                        "           AND ID_TXN_STATUS = 30\n" +
                        "           AND ID_ERROR_CODE = 0\n" +
                        "           AND DAT_CREATION between '20220101' and '20220201' \n" +
                        "     ) AS T\n" +
                        "         LEFT JOIN mobr5.mobr5.MOB_CUSTOMERS_IDENTIFICATIONS AS I\n" +
                        "                   ON I.ID_CUSTOMER = T.ID_PAYER AND I.ID_IDENTIFICATION_TYPE = 101\n" +
                        "         LEFT JOIN mobr5.mobr5.MOB_CUSTOMERS_IDENTIFICATIONS AS II\n" +
                        "                   ON II.ID_CUSTOMER = T.ID_PAYEE AND II.ID_IDENTIFICATION_TYPE = 101";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new TxnsDto(
                        rs.getLong("ID_TXN"),
                        rs.getLong("ID_PAYER"),
                        rs.getString("payerPhone"),
                        rs.getLong("ID_PAYEE"),
                        rs.getString("payeePhone"),
                        rs.getString("ID_CURRENCY"),
                        rs.getString("AMNT_AMOUNT"),
                        rs.getString("STR_TEXT"),
                        rs.getInt("ID_USE_CASE"),
                        rs.getString("STR_SPARE_1"),
                        rs.getString("DAT_CREATION"))
        );
    }
}
