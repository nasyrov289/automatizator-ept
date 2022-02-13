package kg.alymzhan.automatizatorept.service;

import kg.alymzhan.automatizatorept.dto.TxnDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class TxnService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TxnDto> getAllTxns(String startDay, String endDay) {
        String sql = String.format(
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
                        "           AND DAT_CREATION between \'%s\' and \'%s\' \n" +
                        "     ) AS T\n" +
                        "         LEFT JOIN mobr5.mobr5.MOB_CUSTOMERS_IDENTIFICATIONS AS I\n" +
                        "                   ON I.ID_CUSTOMER = T.ID_PAYER AND I.ID_IDENTIFICATION_TYPE = 101\n" +
                        "         LEFT JOIN mobr5.mobr5.MOB_CUSTOMERS_IDENTIFICATIONS AS II\n" +
                        "                   ON II.ID_CUSTOMER = T.ID_PAYEE AND II.ID_IDENTIFICATION_TYPE = 101", startDay, endDay);

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new TxnDto(
                        rs.getLong("ID_TXN"),
                        rs.getLong("ID_PAYER"),
                        rs.getString("payerPhone"),
                        rs.getLong("ID_PAYEE"),
                        rs.getString("payeePhone"),
                        rs.getString("ID_CURRENCY"),
                        rs.getString("STR_TEXT"),
                        rs.getDouble("AMNT_AMOUNT"),
                        rs.getInt("ID_USE_CASE"),
                        rs.getString("STR_SPARE_1"),
                        rs.getTimestamp("DAT_CREATION"))
        );
    }

    public void exportToXlsx(String startDay, String endDay) throws IOException {
        List<TxnDto> allTxns = getAllTxns(startDay, endDay);
        String path = String.format("/home/mimquick/Documents/transactions/%s_старый_МБ.xlsx", startDay);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Транзакции");
        writeHeaderLine(sheet);

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("Calibri");

        writeDataLines(allTxns, workbook, sheet);

        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
        workbook.close();
    }

    private void writeHeaderLine(XSSFSheet sheet) {


        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("ID_TXN МБ");
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("ID_ПЛАТЕЛЬЩИКА");
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("ID_ПЛАТЕЛЬЩИКА РБС");
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("ID_ПОЛУЧАТЕЛЯ");
        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("ID_ПОЛУЧАТЕЛЯ РБС");
        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("ВАЛЮТА");
        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("НАЗНАЧЕНИЕ");
        headerCell = headerRow.createCell(7);
        headerCell.setCellValue("СУММА");
        headerCell = headerRow.createCell(8);
        headerCell.setCellValue("ID_USE_CASE");
        headerCell = headerRow.createCell(9);
        headerCell.setCellValue("ПАЧКА ДОКУМЕНТА В РБС");
        headerCell = headerRow.createCell(10);
        headerCell.setCellValue("ДАТА ПЛАТЕЖА");
    }

    private void writeDataLines(List<TxnDto> txns, XSSFWorkbook workbook,
                                XSSFSheet sheet) {
        int rowCount = 1;

        for (TxnDto txn : txns) {
            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(txn.getIdTxn());

            cell = row.createCell(columnCount++);
            cell.setCellValue(txn.getIdPayer());

            cell = row.createCell(columnCount++);
            cell.setCellValue(txn.getPayerPhone());

            cell = row.createCell(columnCount++);
            cell.setCellValue(txn.getIdPayee());

            cell = row.createCell(columnCount++);
            cell.setCellValue(txn.getPayeePhone());

            cell = row.createCell(columnCount++);
            cell.setCellValue(txn.getCurrency());

            cell = row.createCell(columnCount++);
            cell.setCellValue(txn.getText());

            cell = row.createCell(columnCount++);
            cell.setCellValue(txn.getAmount());

            cell = row.createCell(columnCount++);
            cell.setCellValue(txn.getUseCase());

            cell = row.createCell(columnCount++);
            cell.setCellValue(txn.getOrderNum());

            cell = row.createCell(columnCount);
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
            cell.setCellStyle(cellStyle);
            cell.setCellValue(txn.getCreationDate());
        }
    }
}


