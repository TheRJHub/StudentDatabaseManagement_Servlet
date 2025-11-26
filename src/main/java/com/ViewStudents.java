package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@WebServlet("/view")
public class ViewStudents extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        EntityManager em = Connection.getEntityManager();

        try {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
            List<Student> students = query.getResultList();

            // Get writer
            PrintWriter out = response.getWriter();

            // Start HTML
            out.println("<html>");
            out.println("<head><title>Student Records</title></head>");
            out.println("<body>");
            out.println("<h2>Student Records</h2>");
            out.println("<table border='1' cellpadding='10'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Age</th><th>Photo</th></tr>");

            // Iterate students
            for (Student s : students) {
                out.println("<tr>");
                out.println("<td>" + s.getId() + "</td>");
                out.println("<td>" + s.getName() + "</td>");
                out.println("<td>" + s.getAge() + "</td>");

                if (s.getImage() != null && s.getImage().length > 0) {
                    String base64Image = java.util.Base64.getEncoder().encodeToString(s.getImage());
                    out.println("<td><img src='data:image/jpeg;base64," + base64Image + "' width='100' height='100'/></td>");
                } else {
                    out.println("<td>No Image</td>");
                }

                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error fetching students: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
