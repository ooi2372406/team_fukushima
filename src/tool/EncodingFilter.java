package tool;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns={"/*"})  //全てのURLパターンに適用
public class EncodingFilter implements Filter {  //インターフェースの実装

	public void doFilter(   // Filterインターフェースのdo Filter
		ServletRequest request, ServletResponse response,
		FilterChain chain
	) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		System.out.println("フィルタの前処理");

		//FilterChainインターフェースのdo Filter
		chain.doFilter(request, response);
		System.out.println("フィルタの後処理");
	}


	// インターフェースなので全てをオーバライドしないといけない
	public void init(FilterConfig filterConfig) {}
	public void destroy() {}
}
