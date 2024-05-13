import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "jdbc:postgresql://localhost:5432/chess";
        String username = "postgres";
        String password = "12345678";

        List<User> usersList = new ArrayList<>();
        User firstUser = new User();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String selectUsersQuery = "SELECT * FROM user_credentials";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectUsersQuery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nickname = resultSet.getString("nickname");
                        String email = resultSet.getString("email");

                        User user = new User(id, nickname, email);
                        firstUser = user;
                        usersList.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> lol = new ArrayList<>();
        lol.add(4);
        lol.add(2);
        lol.add(3);
        request.setAttribute("numbers",lol);
        request.setAttribute("user", firstUser);
        request.setAttribute("users", usersList);
        request.setAttribute("chel",5);
        System.out.println(usersList.get(0).getEmail());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
