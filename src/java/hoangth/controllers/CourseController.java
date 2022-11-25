/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangth.controllers;

import config.Config;
import hoangth.db.courses.CourseDAO;
import hoangth.db.courses.CourseDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ACER
 */
@WebServlet(name = "CourseController", urlPatterns = {"/course"})
public class CourseController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = (String) request.getAttribute("action");
        String controller = (String) request.getAttribute("controller");
        action = action.toLowerCase();
        System.out.println("Option : " + action);
        switch (action) {
            case "listall":
                listAll(request, response);
                break;
            case "listinactive":
                listInactive(request, response);
                break;
            case "listpiano":
                listOneType(request, response, 1);
                break;
            case "listguitar":
                listOneType(request, response, 2);
                break;
            case "listdrawing":
                listOneType(request, response, 3);
                break;
            case "search":
                search(request, response);
                break;
            case "update":
                update(request, response);
                break;
            case "inactive":
                inactive(request, response);
                break;
            case "active":
                active(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
        }
    }

    public void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int page = Integer.parseInt(request.getParameter("page"));
            CourseDAO pf = new CourseDAO();
            System.out.println("List all 1");
            List<CourseDTO> listCourse = pf.selectAll();
            System.out.println("List all 2");
            request.setAttribute("list", listCourse);
//            CartDAO cf = new CartDAO();
//            List<Cart> listCart = cf.selectAll();
//            request.setAttribute("list", listCart);
//            request.setAttribute("count", listCart.size());
            request.setAttribute("category", "listall");
            request.setAttribute("page", page);
            request.setAttribute("controller", "course");
            request.setAttribute("action", "category");
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void listInactive(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int page = Integer.parseInt(request.getParameter("page"));
            CourseDAO pf = new CourseDAO();
            System.out.println("List all 1");
            List<CourseDTO> listCourse = pf.selectInactive();
            System.out.println("List all 2");
            request.setAttribute("list", listCourse);
//            CartDAO cf = new CartDAO();
//            List<Cart> listCart = cf.selectAll();
//            request.setAttribute("list", listCart);
//            request.setAttribute("count", listCart.size());
            request.setAttribute("category", "listall");
            request.setAttribute("page", page);
            request.setAttribute("controller", "course");
            request.setAttribute("action", "inactive");
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int page = Integer.parseInt(request.getParameter("page"));
            String couId = request.getParameter("couId");
            CourseDAO pf = new CourseDAO();
            pf.activate(couId);
            System.out.println("List all 1");
            List<CourseDTO> listCourse = pf.selectInactive();
            System.out.println("List all 2");
            request.setAttribute("list", listCourse);
//            CartDAO cf = new CartDAO();
//            List<Cart> listCart = cf.selectAll();
//            request.setAttribute("list", listCart);
//            request.setAttribute("count", listCart.size());
            request.setAttribute("category", "listall");
            request.setAttribute("page", page);
            request.setAttribute("controller", "course");
            request.setAttribute("action", "inactive");
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void delete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int page = Integer.parseInt(request.getParameter("page"));
            String couId = request.getParameter("couId");
            CourseDAO pf = new CourseDAO();
            System.out.println("Delete 1");
            pf.delete(couId);
            System.out.println("List all 1");
            List<CourseDTO> listCourse = pf.selectInactive();
            System.out.println("List all 2");
            request.setAttribute("list", listCourse);
//            CartDAO cf = new CartDAO();
//            List<Cart> listCart = cf.selectAll();
//            request.setAttribute("list", listCart);
//            request.setAttribute("count", listCart.size());
            request.setAttribute("category", "listall");
            request.setAttribute("page", page);
            request.setAttribute("controller", "course");
            request.setAttribute("action", "inactive");
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void listOneType(HttpServletRequest request, HttpServletResponse response, int caId) throws ServletException, IOException {
        try {
            int page = Integer.parseInt(request.getParameter("page"));
            CourseDAO pf = new CourseDAO();
            List<CourseDTO> listCourse = pf.selectType(caId);
            request.setAttribute("list", listCourse);
//            CartDAO cf = new CartDAO();
//            List<Cart> listCart = cf.selectAll();
//            request.setAttribute("list", listCart);
//            request.setAttribute("count", listCart.size());
            String url = null;
            if (caId == 1) {
                request.setAttribute("category", "listpiano");
            } else if (caId == 2) {
                request.setAttribute("category", "listguitar");
            } else if (caId == 3) {
                request.setAttribute("category", "listdrawing");
            } else {
                request.setAttribute("category", "listall");
            }
//            request.setAttribute("category", category);
            request.setAttribute("page", page);
            request.setAttribute("controller", "course");
            request.setAttribute("action", "category");
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search").toLowerCase();
        int page = Integer.parseInt(request.getParameter("page"));
//        String search = (String) request.getAttribute("search");
        CourseDAO pf = new CourseDAO();
        List<CourseDTO> listCourse = null;
        try {
//            listCourse = pf.searchPage(search,page); 
            listCourse = pf.search(search);
            request.setAttribute("list", listCourse);
//            CartDAO cf = new CartDAO();
//            List<Cart> listCart = cf.selectAll();
//            request.setAttribute("list", listCart);
//            request.setAttribute("count", listCart.size());
            request.setAttribute("search", search);
            request.setAttribute("page", page);
            request.setAttribute("controller", "course");
            request.setAttribute("action", "search");
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
//            int page = Integer.parseInt(request.getParameter("page"));
            String category = request.getParameter("category").toLowerCase();
            System.out.println(category);
            
            HttpSession session = request.getSession();
            String user = (String) session.getAttribute("userName");
            String couId = request.getParameter("couId");
            String couName = request.getParameter("newName");
            double fee = Double.parseDouble(request.getParameter("fee"));
            int caId = Integer.parseInt(request.getParameter("newCaId"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(request.getParameter("newStartDate"));
            Date endDate = sdf.parse(request.getParameter("newEndDate"));
            System.out.println(couId + " " + couName + " " + fee + " " + caId + " " + startDate + " " + endDate );
            System.out.println("Update 1");
            CourseDAO pf = new CourseDAO();
            System.out.println(user);
            pf.update(user, couId, couName, fee, caId, startDate, endDate);
            System.out.println("Update 2");

            switch (category) {
                case "search":
                    search(request, response);
                    break;
                case "listall":
                    listAll(request, response);
                    break;
                case "listpiano":
                    listOneType(request, response, 1);
                    break;
                case "listguitar":
                    listOneType(request, response, 2);
                    break;
                case "listdrawing":
                    listOneType(request, response, 3);
                    break;
            }

//            CartDAO cf = new CartDAO();
//            List<Cart> listCart = cf.selectAll();
//            request.setAttribute("list", listCart);
//            request.setAttribute("count", listCart.size());
//            System.out.println("Update 2" + category);
//            request.setAttribute("page", page);
//            request.setAttribute("controller", "course");
//            request.setAttribute("action", "category");
//            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void inactive (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
//            int page = Integer.parseInt(request.getParameter("page"));
            String category = request.getParameter("category").toLowerCase();
            System.out.println(category);

            String couId = request.getParameter("couId");
            System.out.println("Update 1");
            CourseDAO pf = new CourseDAO();
            pf.deactivate(couId);
            System.out.println("Update 2");

            switch (category) {
                case "search":
                    search(request, response);
                    break;
                case "listall":
                    listAll(request, response);
                    break;
                case "listpiano":
                    listOneType(request, response, 1);
                    break;
                case "listguitar":
                    listOneType(request, response, 2);
                    break;
                case "listdrawing":
                    listOneType(request, response, 3);
                    break;
            }

//            CartDAO cf = new CartDAO();
//            List<Cart> listCart = cf.selectAll();
//            request.setAttribute("list", listCart);
//            request.setAttribute("count", listCart.size());
//            System.out.println("Update 2" + category);
//            request.setAttribute("page", page);
//            request.setAttribute("controller", "course");
//            request.setAttribute("action", "category");
//            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
