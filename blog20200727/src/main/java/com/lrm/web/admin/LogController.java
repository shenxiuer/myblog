package com.lrm.web.admin;

import com.lrm.po.Log;
import com.lrm.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by limi on 2017/10/16.
 */

@Controller
@RequestMapping("/admin")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/logs")
    public String logs(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                               Pageable pageable, Model model) {
        model.addAttribute("page",logService.listLog(pageable));
        return "admin/logs";
    }

    @GetMapping("/logs/input")
    public String input(Model model) {
        model.addAttribute("log", new Log());
        return "admin/logs-input";
    }

    @GetMapping("/logs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("log", logService.getLog(id));
        return "admin/logs-input";
    }

    @PostMapping("/logs")
    public String post(@Valid Log log, BindingResult result, RedirectAttributes attributes) {

        Log t = logService.saveLog(log);
        if (t == null ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/logs";
    }



    @PostMapping("/logs/{id}")
    public String editPost(@Valid Log log, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            return "admin/logs-input";
        }
        Log t = logService.updateLog(id,log);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/logs";
    }
                                    
    @GetMapping("/logs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        logService.deleteLog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/logs";
    }


}
