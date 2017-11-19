
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class Test {

        public static void main(String[] args) throws SQLException {

            try {
                Class.forName("org.postgresql.Driver");

                Connection connection = null;

                //  "jdbc:postgresql://nazwahosta:port/nazwabazy","uzytkownik", "haslo"
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres","zenek", "123");

                connection.close();

            } catch (ClassNotFoundException e) {

                e.printStackTrace();
                System.out.append("Nie masz sterownika");
            }
            catch (SQLException e )
            {
                e.printStackTrace();
                System.out.append("Zle dane");
            }
        }
    }

