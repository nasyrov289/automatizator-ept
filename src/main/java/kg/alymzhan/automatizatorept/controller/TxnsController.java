package kg.alymzhan.automatizatorept.controller;

import kg.alymzhan.automatizatorept.dto.TxnsDto;
import kg.alymzhan.automatizatorept.service.AllTxnsRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class TxnsController {

    @Autowired
    private AllTxnsRegisterService registerService;

    @GetMapping("/txns")
    public List<TxnsDto> getAllTxns() {
        return registerService.getAllTxns();
    }
}
