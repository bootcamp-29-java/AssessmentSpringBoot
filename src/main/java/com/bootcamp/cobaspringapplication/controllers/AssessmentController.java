/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.controllers;

import com.bootcamp.cobaspringapplication.entities.Assessment;
import com.bootcamp.cobaspringapplication.entities.AssessmentDetail;
import com.bootcamp.cobaspringapplication.entities.Batch;
import com.bootcamp.cobaspringapplication.entities.BatchClass;
import com.bootcamp.cobaspringapplication.entities.Employee;
import com.bootcamp.cobaspringapplication.entities.EmployeeRole;
import com.bootcamp.cobaspringapplication.entities.LessonCriteria;
import com.bootcamp.cobaspringapplication.entities.Participant;
import com.bootcamp.cobaspringapplication.entities.Sylabus;
import com.bootcamp.cobaspringapplication.services.IAssessmentDetailService;
import com.bootcamp.cobaspringapplication.services.IAssessmentService;
import com.bootcamp.cobaspringapplication.services.IBatchClassService;
import com.bootcamp.cobaspringapplication.services.IBatchService;
import com.bootcamp.cobaspringapplication.services.ISylabusService;
import com.bootcamp.cobaspringapplication.services.IParticipantService;
import com.bootcamp.cobaspringapplication.services.IClassesService;
import com.bootcamp.cobaspringapplication.services.ICriteriaService;
import com.bootcamp.cobaspringapplication.services.IEmployeeRoleService;
import com.bootcamp.cobaspringapplication.services.IEmployeeService;
import com.bootcamp.cobaspringapplication.services.ILessonCriteriaService;
import com.bootcamp.cobaspringapplication.services.ILessonService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author arman
 */
@Controller
public class AssessmentController {

    @Autowired
    IBatchClassService ibcs;
    @Autowired
    IParticipantService ips;
    @Autowired
    ISylabusService iss;
    @Autowired
    IBatchService ibs;
    @Autowired
    IClassesService ics;
    @Autowired
    ICriteriaService ics2;
    @Autowired
    IEmployeeService ies;
    @Autowired
    IEmployeeRoleService iers;
    @Autowired
    ILessonService ils;
    @Autowired
    ILessonCriteriaService ilcs;
    @Autowired
    IAssessmentService ias;
    @Autowired
    IAssessmentDetailService iads;
    
    @RequestMapping("/adminHome")
    public String home(){
        return "adminhome";
    }
    
    @RequestMapping("/trainerHome")
    public String home2(){
        return "trainerhome";
    }

    @GetMapping("/managebatchclass")
    public String manageBatchClass(Model model) {
        model.addAttribute("batchClasses", ibcs.getAll());
        model.addAttribute("id", ibs.genId());
        model.addAttribute("classes", ics.getAll());
        List<Employee> trainers = new ArrayList<>();
        for (EmployeeRole employeeRole : iers.getAll()) {
            if (employeeRole.getRole().getName().equals("Trainer")) {
                trainers.add(employeeRole.getEmployee());
            }
        }
        model.addAttribute("trainers", trainers);
        return "managebatchclass";
    }

    @PostMapping("/addbatchclass")
    public String addBatchClass(Model model, @ModelAttribute("id") String id, @RequestParam("class2") List<String> class2, @ModelAttribute("trainer") String trainer) {
        ibs.save(new Batch(id));
        for (String string : class2) {
            ibcs.save(new BatchClass(ibs.getById(id), ics.getById(string), ies.getById(trainer)));
        }
        return "redirect:/managebatchclass";
    }
    
    @GetMapping("deleteBatchClass")
    public String deleteBatchClass(String id){
        ibcs.delete(id);
        return "redirect:/managebatchclass";
    }
    
    @RequestMapping("/manageparticipant")
    public String manageParticipant(Model model) {
        model.addAttribute("batchClasses", ibcs.getAll());
        List<Employee> participants = new ArrayList<>();
        for (EmployeeRole employeeRole : iers.getAll()) {
            if (employeeRole.getRole().getName().equals("Participant") && employeeRole.getEmployee().getParticipant() == null) {
                participants.add(employeeRole.getEmployee());
            }
        }
        model.addAttribute("participants", participants);
        return "manageparticipant";
    }

    @PostMapping("/addparticipant")
    public String addParticipant(Model model, @ModelAttribute("batchClass") String batchClass, @RequestParam("participants") List<String> participants) {
        for (String participant : participants) {
            ips.save(new Participant(participant, ibcs.getById(batchClass)));
        }
        return "redirect:/manageparticipant";
    }

    @GetMapping("/inputnilai")
    public String inputNilai(Model model) {
        model.addAttribute("batchClasses", ibcs.getAll());
        return "inputnilai";
    }
    @PostMapping("/inputnilai")
    public String inputNilai(@RequestParam("nilai") List<String> nilai, @ModelAttribute("criteria") String criteria, @RequestParam("id") List<String> id) {
        for (int i = 0; i < id.size(); i++) {
            if (!nilai.get(i).equals("")) {
                Assessment assessment = null;
                for (Assessment assessment1 : ias.getAll()) {
                    if (assessment1.getParticipant().getId().equals(id.get(i)) && assessment1.getSylabus().getId().equals(ilcs.getById(criteria).getSylabus().getId()) ) {
                        assessment = assessment1;
                    }
                }
                iads.save(new AssessmentDetail(Float.parseFloat(nilai.get(i)), ilcs.getById(criteria), assessment));
            }
        }
        return "redirect:/inputnilai";
    }

    @GetMapping("/loadform")
    public String loadForm(Model model, String id) {
        List<Participant> participants = new ArrayList<>();
        for (Participant participant : ips.getAll()) {
            if (participant.getBatchClass().getId().equals(id)) {
                participants.add(participant);
            }
        }
        List<Sylabus> sylabuses = new ArrayList<>();
        for (Sylabus sylabus : iss.getAll()) {
            if (sylabus.getClass1().getId().equals(ibcs.getById(id).getClass1().getId())) {
                sylabuses.add(sylabus);
            }
        }
        model.addAttribute("participants", participants);
        model.addAttribute("sylabuses", sylabuses);
        return "content :: form";
    }

    @GetMapping("/loadcriterias")
    public String loadCriterias(Model model, String id) {
        List<LessonCriteria> criterias = new ArrayList<>();
        for (LessonCriteria criteria : ilcs.getAll()) {
            if (criteria.getSylabus().getId().equals(id)) {
                criterias.add(criteria);
            }
        }
        model.addAttribute("criterias", criterias);
        return "content :: criterias2";
    }
    
//    jasper report
//    @RequestMapping(value = "report", method = RequestMethod.GET)
//	public void report(HttpServletResponse response) throws Exception {
//		response.setContentType("text/html");
//		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ProductService);
//		InputStream inputStream = this.getClass().getResourceAsStream("/reports/reportAssessment.jrxml");
//		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
//		HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
//		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
//		exporter.exportReport();
//	}
}
