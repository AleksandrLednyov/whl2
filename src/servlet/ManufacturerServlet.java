package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ManufacturerService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * Класс ManufacturerServlet обеспечивает взаимодействие HTML-страницы /manufacturers с сервисным уровнем.
 * Метод doGet обращается к экземпляру сервисного слоя, который в свою очередь вызывает метод findAll, получающий все
 * экземпляры DTO, обрабатывает и создаёт ответ в виде HTML-страницы, где выводятся все производители (manufacturers).
 */
@WebServlet("/manufacturers")
public class ManufacturerServlet extends HttpServlet {
    private final ManufacturerService manufacturerService = ManufacturerService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.write("<h1>Список производителей</h1>");
            printWriter.write("<ul>");
            manufacturerService.findAll().forEach(manufacturerDto -> {
                printWriter.write("""
                        <li>
                            <a href="/products?id=%s">%s</a>
                        </li>
                        """.formatted(manufacturerDto.getId(), manufacturerDto.getName()));
            });
            printWriter.write("</ul>");
        }
    }
}
