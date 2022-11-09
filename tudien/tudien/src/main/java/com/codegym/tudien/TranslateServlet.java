package com.codegym.tudien;

import com.codegym.tudien.service.DiscService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "TranslateServlet", urlPatterns = "/translate")
public class TranslateServlet extends HttpServlet {

    private DiscService discService;
    @Override
    public void init() throws ServletException {
        discService = new DiscService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
//        if (action == null) {
//            action = "";
//        }
//        switch (action) {
//            case "dich":
//                String keyword = req.getParameter("keyword");
//                System.out.println(keyword);
//                break;
//            default:
//                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/translate.jsp");
//                requestDispatcher.forward(req, resp);
//        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/translate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        //
        String result = "";
        if (discService.getDisc().containsKey(keyword)) {
            result = discService.getDisc().get(keyword);
        }else{
            result = "Không tìm thấy";
        }

        req.setAttribute("ketqua", result);
        req.setAttribute("keyword", keyword);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/translate.jsp");
        requestDispatcher.forward(req, resp);


    }
}
