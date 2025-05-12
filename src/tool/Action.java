package tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Action {
    public abstract String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception;

	public String execute(HttpServletRequest request) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}