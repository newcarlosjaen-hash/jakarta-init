package es.daw.jakartainit;

import java.io.*;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/init-demo")
public class InitDemoServlet extends HttpServlet {

    private String horaInicializacion;
    private int contadorPeticiones = 0;

    @Override
    public void init() throws ServletException {
        horaInicializacion = LocalDateTime.now().toString();
        System.out.println(">>> init() ejecutado, instancia " + this.hashCode());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        contadorPeticiones++;
        System.out.println(">>> doGet() num " + contadorPeticiones + ", instancia " + this.hashCode());

        request.setAttribute("horaInit", horaInicializacion);
        request.setAttribute("contador", contadorPeticiones);
        request.setAttribute("instancia", this.hashCode());

        request.getRequestDispatcher("/resultado.jsp").forward(request, response);
    }
}
