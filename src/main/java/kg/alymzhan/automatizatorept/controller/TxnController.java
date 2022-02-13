package kg.alymzhan.automatizatorept.controller;

import kg.alymzhan.automatizatorept.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/api/v1")
public class TxnController {

    @Autowired
    private TxnService registerService;

    @GetMapping("/txns")
    public @ResponseBody
    String getAllTxns(@RequestParam("start") String startDay, @RequestParam("end") String endDay) throws SQLException, IOException {
        registerService.exportToXlsx(startDay, endDay);
        return "Успешно выгружено";
    }
}
