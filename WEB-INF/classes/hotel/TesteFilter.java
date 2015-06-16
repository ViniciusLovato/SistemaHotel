package hotel;

import java.io.IOException;  
import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
import javax.servlet.*;  
import javax.servlet.http.*;
  
  
public class TesteFilter implements Filter{  
      
    public void destroy() {  
    }  
  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {  
        HttpServletRequest req = (HttpServletRequest) request;  
        HttpServletResponse res = (HttpServletResponse) response;  
          
        HttpSession session = req.getSession();  

        //String user = (String) session.getAttribute("user");  
        Boolean isAdmin = (Boolean) session.getAttribute("adminLogado");
        String url = "";
        System.out.println("Filtro passou por aqui!\n\n");
        
        if(isAdmin !=null && isAdmin == true){  
            System.out.println("Administrador esta logado e pode acessar de boas!\n");
            url = "/admin/index.jsp";

            //   return;  
        }  
        else {
            url  = "/login/index.html";
            // res.sendRedirect("/SistemaHotel/login/index.html");  
        }

        try{

            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);

        }catch(Exception e){
            e.printStackTrace();
        }
    }  
  
    public void init(FilterConfig arg0) throws ServletException {  

    }  
}  