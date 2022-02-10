package kg.alymzhan.automatizatorept.controller;

import kg.alymzhan.automatizatorept.dto.TxnsDto;
import kg.alymzhan.automatizatorept.service.AutomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class MainController {

    @Autowired
    private AutomationService registerService;

    @GetMapping("/txns")
    public @ResponseBody
    List<TxnsDto> getAllTxns(@RequestParam("start") String startDay, @RequestParam("end") String endDay) {
        return registerService.getAllTxns(startDay, endDay);
    }
}
