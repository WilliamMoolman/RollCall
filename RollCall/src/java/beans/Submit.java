/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moolm
 */
public class Submit extends HttpServlet{
    @Override
    
    public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
        Cookie[] cookies = req.getCookies();
        String house,grade,present = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("house")) {
                    house = cookie.getValue();
                }
                if (cookie.getName().equals("grade")) {
                    grade = cookie.getValue();
                }
                if (cookie.getName().equals("present")) {
                    present = cookie.getValue();
                }
            }
        }
        
        Scanner sc = new Scanner(present).useDelimiter(",");
        PrintWriter out = res.getWriter();
        while(sc.hasNext()){
            
        }
    }
}
