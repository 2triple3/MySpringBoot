package com.shanwen.select.web;

import com.shanwen.select.entity.Category;
import com.shanwen.select.entity.Decision;

import com.shanwen.select.entity.DecisionClass;
import com.shanwen.select.service.ICategoryService;
import com.shanwen.select.service.IDecisionClassService;
import com.shanwen.select.service.IDecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 决策参数基本信息维护
 */
@Controller
@RequestMapping("/decision")
public class DecisionController {
    @Autowired
    IDecisionService decisionService;
    @Autowired
    IDecisionClassService decisionClassService;

    /**
     * 列表
     *
     * @param model
     * @param decision
     * @return
     */
    @RequestMapping("/list")
    public String decisionList(Model model, Decision decision, Integer categoryId) {
        List<Decision> decisionList = decisionService.selectAll(decision);

        model.addAttribute("decisionList", decisionList);

        return "decisionclass/detaillist";
    }

    /**
     * 新增
     */
 /*   @RequestMapping(value = "add", method = RequestMethod.GET)
    public String AddView(Model model, Decision decision, DecisionClass decisionClass) {
        model.addAttribute("decisionlist", decisionClassList);
        return "decisionclass/detailadd";
    }
*/
 /*   @RequestMapping(value = "add", method = RequestMethod.POST)
    public String Add(Model model, Decision decision, DecisionClass decisionClass, Integer decisionClassId) {
        decisionClass.setDecisionClassId(decisionClassId);
        decision.setDecisionClass(decisionClass);
        decisionService.insertAll(decision);
        return "redirect:/decisionclass/list";
    }
*/
    /**
     * 修改
     */
   /* @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String EditView(Model model, Decision decision, DecisionClass decisionClass, Integer decisionId) {
        List<DecisionClass> decisionClassList = decisionClassService.selectAll(decisionClass);
        model.addAttribute("decisionlist", decisionClassList);
        decision = decisionService.selectMyNeed(decisionId);
        model.addAttribute("decision", decision);
        return "decisionclass/detailedit";
    }*/

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String Edit(Model model, Decision decision, DecisionClass decisionClass, Integer decisionClassId) {
        decisionClass.setDecisionClassId(decisionClassId);
        decision.setDecisionClass(decisionClass);
        decisionService.updateAll(decision);
        return "redirect:/decisionclass/list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(Integer decisionId) {
        decisionService.updateByMyId(decisionId);
        return "redirect:/decisionclass/list";
    }

    /**
     * 处理时间
     *
     * @param request
     * @param binder
     */
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
