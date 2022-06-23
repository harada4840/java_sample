package controller.task;
import model.category.Category;
import model.task.Task;
import model.user.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet("/task/insert")
public class Insert extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // セッションスコープを取得
        HttpSession session = req.getSession();
        // セッションスコープにcurrentUserとして保存してあるUser情報を取得
        User user = (User) session.getAttribute("currentUser");
        // 取得したUser情報をもとに該当するUserの登録したCategory一覧を取得
        ArrayList<Category> categories = Category.indexCategories(user);
        // 取得したCategory一覧をcategoriesとしてリクエストスコープに保存
        req.setAttribute("categories", categories);
        // /WEB-INF/task/insert.jspにフォワード
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/task/insert.jsp");
        dispatcher.forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String memo = req.getParameter("memo");
        Date deadLine = Date.valueOf(req.getParameter("deadLine"));
        Integer category = Integer.valueOf(req.getParameter("categoryId"));

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");

        Task task = new Task(
                null,
                name,
                null,
                null,
                deadLine,
                memo,
                false,
                user.getId(),
                category
        );

        task.insert();

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/top.jsp");
        dispatcher.forward(req, resp);
    }
}