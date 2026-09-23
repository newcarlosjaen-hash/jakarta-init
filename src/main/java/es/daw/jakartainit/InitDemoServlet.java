package es.daw.jakartainit;

import java.io.*;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

// PASO 3: El servidor intercepta la petición a "/init-demo" y se la entrega a esta clase, gracias a la anotación @WebServlet.
@WebServlet("/init-demo")
public class InitDemoServlet extends HttpServlet {

    // Variables de estado del Servlet.
    // OJO: El servidor crea UNA sola instancia de este Servlet. Por tanto, estas variables
    // mantienen su valor y son compartidas entre todas las peticiones de los distintos clientes.

    private int contadorPeticiones = 0;
    // PASO 4: El metodo init() se ejecuta UNA ÚNICA VEZ entodo el ciclo de vida del Servlet,
    // justo en el momento en que el servidor crea la instancia.
    @Override
    public void init() throws ServletException {
        // Congelamos la hora en la que se instanció
        System.out.println(">>> init() ejecutado, instancia " + this.hashCode());
    }

    // PASO 5: El metodo doGet() se ejecuta CADA VEZ que alguien hace una petición GET a "/init-demo" (cada clic o cada F5).
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String horaInicializacion = LocalDateTime.now().toString();
        contadorPeticiones++; // Aumenta en 1 por cada nueva petición recibida
        System.out.println(">>> doGet() num " + contadorPeticiones + ", instancia " + this.hashCode());

        // PASO 6: Preparamos la información que queremos pasar a la vista (el HTML).
        // Usamos setAttribute para inyectar nuestros datos dentro del objeto 'request'.
        request.setAttribute("horaInit", horaInicializacion);
        request.setAttribute("contador", contadorPeticiones);
        request.setAttribute("instancia", this.hashCode());

        // PASO 7: Hacemos un "forward" (redirección interna en el servidor).
        // Pasamos el control al archivo "resultado.jsp", enviándole el 'request' que contiene nuestros datos.
        request.getRequestDispatcher("/resultado.jsp").forward(request, response);
    }
}