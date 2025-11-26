package com;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@WebServlet("/save")
@MultipartConfig
public class SaveStudent extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManager em = Connection.getEntityManager();
		EntityTransaction et = em.getTransaction();
		 String name = request.getParameter("name");
	        String age = request.getParameter("age");
	        //String gender = request.getParameter("gender");

	        Part photoPart = request.getPart("photo");
	        
	        byte[] image = null;
	        if (photoPart != null && photoPart.getSize() > 0) {
	            InputStream is = photoPart.getInputStream();
	            image= is.readAllBytes();
	        }

	        Student student = new Student();
	        student.setName(name);
	        student.setAge(age);
	       // student.setGender(gender);
	        student.setImage(image);
	        
	        et.begin();
	        em.persist(student);
	        et.commit();
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view");
	        requestDispatcher.forward(request, response);
	}
}
