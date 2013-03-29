package com.sc.auth.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sc.auth.action.service.ArticleManageService;
import com.sc.auth.core.Action;
import com.sc.auth.core.ActionForward;
import com.sc.auth.util.ParamUtils;
import com.sc.auth.vo.ArticleVo;
import com.sc.auth.vo.BaseUser;

public class ArticleManageAction extends Action {

	private ArticleManageService articleManageService = ArticleManageService.getInstance();
	@Override
	public String excute(HttpServletRequest request,
			HttpServletResponse response, ActionForward forward) throws IOException {
		String action = ParamUtils.getString(request, "action", "");
		if("add".equals(action)){
			return addArticle(request, response,forward);
		}else if("delete".equals(action)){
			return deleteArticle(request, response,forward);
		}else if("edit".equals(action)){
			return showArticle(request, response, forward);
		}else{
			return listArticle(request, response, forward);
		}
		
	}

	private String listArticle(HttpServletRequest request,
			HttpServletResponse response, ActionForward forward) {
		try {
			List<ArticleVo> articles = articleManageService.queryArticles();
			request.setAttribute("articles", articles);
			return forward.findForward("list");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return forward.findForward("inexistence");
		}
	}

	/**
	 * 查看文章
	 * @param request
	 * @param response
	 * @param forward
	 * @return
	 */
	private String showArticle(HttpServletRequest request,
			HttpServletResponse response, ActionForward forward) {
		try {
			int id = ParamUtils.getInt(request, "articleId", 0);
			ArticleVo article = articleManageService.findArticle(id);
			if(null == article){
				return forward.findForward("inexistence");
			}
			request.setAttribute("article", article);
			return forward.findForward("show");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除文章
	 * @param request
	 * @param response
	 * @param forward 
	 * @return
	 */
	private String deleteArticle(HttpServletRequest request,
			HttpServletResponse response, ActionForward forward) {
		try {
			int id = ParamUtils.getInt(request, "articleId", 0);
			articleManageService.deleteArticle(id);
			return_out(response, PROCESS_RESULT_SUCCESS, "文章删除成功");
		} catch (SQLException e) {
			e.printStackTrace();
			return_out(response, PROCESS_RESULT_FAILURE, "文章删除失败, 错误原因: " +e.getMessage());
		}
		return null;
	}

	/**
	 * 新增文章
	 * @param request
	 * @param response
	 * @param forward 
	 * @return
	 */
	private String addArticle(HttpServletRequest request,
			HttpServletResponse response, ActionForward forward) {
		try {
			String title = ParamUtils.getString(request, "title", "");
			String content = ParamUtils.getString(request, "articleContent", "");
			int authorId = ((BaseUser)request.getSession().getAttribute("logonUser")).getId();
	//		String createTime = ParamUtils.getString(request, "content", "");
			int articleType = ParamUtils.getInt(request, "articleType", 0);
			ArticleVo article = new ArticleVo();
			article.setAuthorId(authorId);
			article.setTitle(title);
			article.setContent(content);
			article.setCreateTime(new Date());
			article.setArticleType(articleType);
			articleManageService.addArticle(article);
			return_out(response, PROCESS_RESULT_SUCCESS, "文章新增成功");
		} catch (SQLException e) {			
			e.printStackTrace();
			return_out(response, PROCESS_RESULT_FAILURE, "文章新增失败, 错误原因: " +e.getMessage());
		}
		return null;
	}

}
