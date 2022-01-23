package cybersoft.javabackend.game.doanso.servlet;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import cybersoft.javabackend.game.doanso.model.GameRecord;
import cybersoft.javabackend.game.doanso.model.Player;
import cybersoft.javabackend.game.doanso.service.GameService;
import cybersoft.javabackend.game.doanso.util.JspConst;
import cybersoft.javabackend.game.doanso.util.UrlConst;

@WebServlet({ UrlConst.GAME_ROOT, UrlConst.GAME_PLAY, })
public class GameServlet extends HttpServlet {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private GameService service;

	@Override
	public void init() throws ServletException {
		super.init();
		service = new GameService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Player curPlayer = (Player) req.getSession().getAttribute("player");
		GameRecord record = service.loadGame(curPlayer.getUsername(), curPlayer.getPassword());
		req.setAttribute("record", record);
		if (record.getIsFinish()) {
			req.getRequestDispatcher(JspConst.GAME_PLAY).forward(req, resp);
		}else {
			String message = "Lần đầu tiên hãy nhập con số mà bạn cảm thấy may mắn!";
			req.setAttribute("message", message);
			req.getRequestDispatcher(JspConst.GAME_PLAY).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String path = req.getServletPath();
		String message;
		int recordID, tryNumber;
		GameRecord record;
		switch (path) {
		case UrlConst.GAME_PLAY:
			recordID =Integer.parseInt(req.getParameter("recordId"));
			tryNumber =Integer.parseInt(req.getParameter("tryNumber"));
			record = service.playgame(recordID, tryNumber);
			if(record.getIsFinish()) {
				message="Chúc mừng bạn đã đoán chính xác";
			}else {
				message=getHint(record.getNumber(), tryNumber);
			}
			req.setAttribute("message", message);
			req.setAttribute("record", record);
			req.getRequestDispatcher(JspConst.GAME_PLAY).forward(req, resp);
			break;
		default:
			break;
		}
	}
	private String getHint(int number, int tryNumber) {
		if(tryNumber>number) 
			return "Số vừa đoán lớn hơn kết quả";
		return "Số vừa đoán nhỏ hơn kết quả";
	}
}
