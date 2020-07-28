package com.lrm.web;

import com.lrm.po.Log;
import com.lrm.service.BlogService;
import com.lrm.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by limi on 2017/10/23.
 */
@Controller
public class LogShowController {

    @Autowired
    private LogService logService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/blossom")
    public String logs(@PageableDefault(size = 8, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        Page<Log> logs = logService.listLog(pageable);
        model.addAttribute("logs", logs);
        
        return "blossom";
    }
}
