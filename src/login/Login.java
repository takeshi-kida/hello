package login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.StartLogin;

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
		String userId = request.getParameter("userid");
		String password = request.getParameter("password");

		StartLogin startLogin = new StartLogin();

		try {
			String[] tUserData = startLogin.getUserData(userId, password);
			
			name = tUserData[0];
			loginTime = tUserData[1];

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


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
}
