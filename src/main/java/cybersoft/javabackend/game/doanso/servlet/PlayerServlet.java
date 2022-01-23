package cybersoft.javabackend.game.doanso.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.javabackend.game.doanso.model.Player;
import cybersoft.javabackend.game.doanso.service.PlayerService;
import cybersoft.javabackend.game.doanso.service.StoreService;
import cybersoft.javabackend.game.doanso.util.JspConst;
import cybersoft.javabackend.game.doanso.util.UrlConst;

@WebServlet(name = "PlayerServlet", urlPatterns = { UrlConst.PLAYER_LOGIN, UrlConst.PLAYER_REGISTER })
public class PlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Player> players;

	@Override
	public void init() throws ServletException {
		super.init();
		players = StoreService.players;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case UrlConst.PLAYER_REGISTER:
			req.getRequestDispatcher(JspConst.PLAYER_REGISTER).forward(req, resp);
			break;
		case UrlConst.PLAYER_LOGIN:
			Cookie[] cookies = req.getCookies();
			String username = null;
			if (cookies != null) {
				Optional<Cookie> usernameCookie = Arrays.asList(cookies).stream()
						.filter(t -> t.getName().equals("username")).findFirst();

				if (usernameCookie.isPresent()) {
					username = usernameCookie.get().getValue();
				}
			}
			if (username != null) {
				req.setAttribute("lastUsername", username);
			}
			req.getRequestDispatcher(JspConst.PLAYER_LOGIN).forward(req, resp);
			break;
		default:
			resp.getWriter().print("Nhầm đường rồi a ơi");
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		String username, password, message;
		req.setCharacterEncoding("UTF-8");
		switch (path) {
		case UrlConst.PLAYER_LOGIN:
			username = req.getParameter("username");
			password = req.getParameter("password");
			if (players.size() > 0) {
				Optional<Player> curPlayer = players.stream().filter(t -> t.getUsername().equals(username))
						.filter(t -> t.getPassword().equals(password)).findFirst();
				if (curPlayer.isPresent()) {
					HttpSession session = req.getSession();
					session.setAttribute("player", curPlayer.get());
					Cookie cookie = new Cookie("username", curPlayer.get().getUsername());
					cookie.setMaxAge(60 * 60);
					resp.addCookie(cookie);
					resp.sendRedirect(req.getContextPath() + UrlConst.GAME_ROOT);
				} else {
					message = "Username or password is incorrect";
					req.setAttribute("message", message);
					req.getRequestDispatcher(JspConst.PLAYER_LOGIN).forward(req, resp);
				}
			} else {
				message = "Username or password is incorrect";
				req.setAttribute("message", message);
				req.getRequestDispatcher(JspConst.PLAYER_LOGIN).forward(req, resp);
			}

			break;
		case UrlConst.PLAYER_REGISTER:
			username = req.getParameter("username");
			password = req.getParameter("password");
			boolean isExistedUsername = false;
			if (players.size() > 0) {
				isExistedUsername = players.stream().anyMatch(t -> t.getUsername().equalsIgnoreCase(username));
			}
			if (isExistedUsername) {
				message = "Username is existed !";
				req.setAttribute("message", message);
				req.getRequestDispatcher(JspConst.PLAYER_REGISTER).forward(req, resp);
			} else {
				Player player = new Player(username, password);
				players.add(player);
				Cookie cookie = new Cookie("username", player.getUsername());
				cookie.setMaxAge(60 * 60);
				resp.addCookie(cookie);
				resp.sendRedirect(req.getContextPath() + UrlConst.PLAYER_LOGIN);
			}
			break;
		default:
			resp.getWriter().print("Sai đường rồi anh ơi!!");
			break;
		}
	}
}
