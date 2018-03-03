package pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストから名前を取得
		String name = (String) request.getAttribute("userName");
		// リクエストからログイン日時を取得
		String loginTime = (String) request.getAttribute("loginTime");
		// リクエストから処理結果を取得
		String result = (String) request.getAttribute("result");

		// 名前が空の場合Guestを設定
		if (name == null || "".equals(name)) {
			request.setAttribute("userName", "Guest");
		}

		// 処理結果が空の場合初回処理
		if (result == null || "".equals(result)) {
			// JSPにforward
			String view = "/WEB-INF/view/index.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);

			dispatcher.forward(request, response);
		} else if ("success".equals(result)) {
			// JSPにforward
			String view = "/WEB-INF/view/hello.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);

			dispatcher.forward(request, response);
		} else {
			// JSPにforward
			String view = "/WEB-INF/view/error.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);

			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = "";
		String loginTime = "";
		// 文字コードの指定
		request.setCharacterEncoding("utf-8");
		// formから値を取得
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		// コネクションを作成
		Connection conn = helloDriverManeger();
		try {
			// Statementの作成
			java.sql.Statement stmt = conn.createStatement();
			// 問い合わせの実行
			ResultSet rset = stmt.executeQuery(
					"select USER_NAME, LOGIN_TIME from T_USER where USER_ID = '" + userid + "' and PASSWORD = '" + password + "'");

			System.out.println("select USER_NAME, LOGIN_TIME from T_USER where USER_ID = '" + userid + "' and PASSWORD = '" + password + "'");
			// 取得したデータを出力する
			while (rset.next()) {
				name = rset.getString("USER_NAME");
				loginTime = rset.getString("LOGIN_TIME");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(name);
		System.out.println(loginTime);

		// 名前が空の場合、resultにfalseを設定
		if (name == null || "".equals(name)) {
			request.setAttribute("result", "false");
		} else {
			// "userName"に設定。
			request.setAttribute("userName", name);
			// "loginTime"に設定。
			request.setAttribute("loginTime", loginTime);
			// resultにsuccess設定。
			request.setAttribute("result", "success");
		}

		// doGetメソッドを呼び出す。
		doGet(request, response);
	}

	@SuppressWarnings("finally")
	private Connection helloDriverManeger() {

		Connection conn = null;

		try {
			// JDBCドライバクラスのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connectionの作成
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "CSC_USER", "CSC_USER");
		} catch (Exception e) {
			throw e;
		} finally {
			return conn;
		}
	}
}
