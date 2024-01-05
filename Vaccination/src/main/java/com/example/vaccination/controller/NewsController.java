package com.example.vaccination.controller;

import com.example.vaccination.exception.NotFoundException;
import com.example.vaccination.model.entity.News;
import com.example.vaccination.service.NewsServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class NewsController {
    @Autowired
    private final NewsServices NewsServices;

    public NewsController(NewsServices newsServices) {
        NewsServices = newsServices;
    }


    //Print list on screen
    @GetMapping(value = "/newslist")
    public String findAll(Model model) {
        List<News> newsList = NewsServices.findAllByOrderByPostdateDesc();
        model.addAttribute("newsList", newsList);
        return "newsList";
    }

    //Create News
    @GetMapping(value = "/createnews")
    public String createnews(Model model) {
        model.addAttribute("news", new News());
        return "createNews";
    }

    @PostMapping(value = "/createnews")
    public String saveNews(@Valid @ModelAttribute("news") News news,
                           BindingResult bindingResult,
                           Model model,
                           RedirectAttributes red) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("news", news);
            return "/createnews";
        }
        model.addAttribute("news", news);
        NewsServices.createNews(news);
        red.addFlashAttribute("message", "Save Succcessfull");
        return "redirect:/newslist";
    }

    //Update news
    @GetMapping(value = "/update")
    public String updateNews(@RequestParam Integer newsId, Model model, RedirectAttributes red) {
        try {
            News existingNews = NewsServices.findbyId(newsId);
            model.addAttribute("update", existingNews);
            return "updateNews";
        }catch(NotFoundException e){
            red.addFlashAttribute("messageError", e.getMessage());
            return "redirect:/newslist";
        }
    }

    @PostMapping(value = "/update")
    public String updateNews(@Valid @ModelAttribute("update") News news,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes red) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("update", news);
            return "/updateNews";
        }
        Integer newsId = news.getNewsId();
        news.setNewsId(newsId); // Set the newsId from the URL path
        model.addAttribute("update", news);
        NewsServices.updateNews(news);
        red.addFlashAttribute("message", "Update Succcessfull");
        return "redirect:/newslist";
    }

    //checkbox delete
    @GetMapping(value = "/delete/{ids}")
    public String deleteNews(@PathVariable("ids") String ids, Model model, RedirectAttributes red) {
        String[] idArray = ids.split(",");

        for (String idStr : idArray) {
            int id = Integer.parseInt(idStr);  // Convert each ID string to an integer
            NewsServices.deleteNews(id);  // Delete the news item with the given ID
            red.addFlashAttribute("message", "Delete Succcessfull");

        }
        return "redirect:/newslist";
    }

    // News details
    @GetMapping(value = "/news")
    public String updateNews2(@RequestParam Integer newsId, Model model, RedirectAttributes red) {
        try {
            News existingNews = NewsServices.findbyId(newsId);
            model.addAttribute("newspage", existingNews);
            return "newsPage";
        }catch(NotFoundException e){
            red.addFlashAttribute("messageError", e.getMessage());
            return "redirect:/newslist";
        }
    }


}