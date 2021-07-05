package com.tech.spring_tx_board.controller;

import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tech.spring_tx_board.dao.IDao;
import com.tech.spring_tx_board.dao.IDao2;
import com.tech.spring_tx_board.dto.BoardDto;
import com.tech.spring_tx_board.util.Constant;
import com.tech.spring_tx_board.vopage.SearchVO;

@Controller
public class BController {
	
	public JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template=this.template;
	}
	@Autowired
	private SqlSession sqlSession;
	
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,
			SearchVO searchVO,Model model) {
		System.out.println("pass by list()");
//		commandInp=new BListService();
//		commandInp.execute(model);
		//search
		String btitle="";
		String bcontent="";
		
		String[] brdtitle=request.getParameterValues("searchType");
		
		if(brdtitle!=null) {
			for (int i = 0; i < brdtitle.length; i++) {
				System.out.println("brdtitle : "+brdtitle[i]);
			}
		}
		//검색유지
		if (brdtitle!=null) {
			for (String val : brdtitle) {
				if (val.equals("btitle")) {
					model.addAttribute("btitle","true");
					btitle="btitle";
				}else if (val.equals("bcontent")) {
					model.addAttribute("bcontent","true");
					bcontent="bcontent";
				}
			}
		}
		
		String searchKeyword=request.getParameter("sk");
		if(searchKeyword==null)
			searchKeyword="";
	
		String strPage=request.getParameter("page");
		System.out.println("strPage : "+strPage);
		
		
//		mybatis에서 작업
		IDao dao=sqlSession.getMapper(IDao.class);
		
		if (strPage==null || strPage.equals("")) {
			strPage="1";
		}
		System.out.println("strPage : "+strPage);
		
		int page=Integer.parseInt(strPage);
		searchVO.setPage(page);
		
		//totcnt
		int total=0;
		if (btitle.equals("btitle") && bcontent.equals("")) {
			total=dao.selectBoardTotCount(searchKeyword,"1");
			System.out.println("list1~~~~~~~~~~~~");
		}else if (btitle.equals("") && bcontent.equals("bcontent")) {
			total=dao.selectBoardTotCount(searchKeyword,"2");
			System.out.println("list2~~~~~~~~~~~~");
		}else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			total=dao.selectBoardTotCount(searchKeyword,"3");
			System.out.println("list3~~~~~~~~~~~~");
		}else if (btitle.equals("") && bcontent.equals("")) {
			total=dao.selectBoardTotCount(searchKeyword,"0");
			System.out.println("list0~~~~~~~~~~~~");
		}
		
		model.addAttribute("searchKeyword",searchKeyword);
		
		
		searchVO.pageCalculate(total);
		
		
		System.out.println("total : "+total);
		System.out.println("clickPage : "+strPage);
		System.out.println("pageStart : "+searchVO.getPageStart());
		System.out.println("pageEnd : "+searchVO.getPageEnd());
		System.out.println("pageTot : "+searchVO.getTotPage());
		System.out.println("rowStart : "+searchVO.getRowStart());
		System.out.println("rowEnd : "+searchVO.getRowEnd());
		
		int rowStart=searchVO.getRowStart();
		int rowEnd=searchVO.getRowEnd();
		
		System.out.println("=============================");
		
		if (btitle.equals("btitle") && bcontent.equals("")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"1"));
			model.addAttribute("totRowCnt",dao.selectBoardTotCount(searchKeyword,"1"));
			System.out.println("list1~~~~~~~~~~~~");
		}else if (btitle.equals("") && bcontent.equals("bcontent")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"2"));
			model.addAttribute("totRowCnt",dao.selectBoardTotCount(searchKeyword,"2"));
			System.out.println("list2~~~~~~~~~~~~");
		}else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"3"));
			model.addAttribute("totRowCnt",dao.selectBoardTotCount(searchKeyword,"3"));
			System.out.println("list3~~~~~~~~~~~~");
		}else if (btitle.equals("") && bcontent.equals("")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"0"));
			model.addAttribute("totRowCnt",dao.selectBoardTotCount(searchKeyword,"0"));
			System.out.println("list0~~~~~~~~~~~~");
		}
		
		model.addAttribute("searchVo",searchVO);
		return "list";
	}

	
	@RequestMapping("/write_view")
	public String write_view() {
		System.out.println("pass by write_view()");
		
		return "write_view";
	}
	@RequestMapping("/write")
	public String write(HttpServletRequest request,Model model) 
			throws Exception {
		System.out.println("pass by write()");
		
//		upload
		String attachPath="resources\\upload\\";
		String uploadPath=request.getSession().getServletContext()
				.getRealPath("/");
		
		System.out.println("uploadPath : "+uploadPath);
		String path=uploadPath+attachPath;
//		직접내컴폴더에 넣는 방법
//		내가 직접관리하는 서버를 가지고있다면가능, 서버를 위탁해 사용한다면 아래 내용으로 제한이 많을듯
//		String path="C:\\springSet\\springwork\\spring_tx_board8\\src\\main\\webapp\\resources\\upload";
		
		//multipart request구성
		MultipartRequest req=
				new MultipartRequest(request, path,1024*1024*20,"utf-8",
						new DefaultFileRenamePolicy());
		
		String bname=req.getParameter("bName");
		String btitle=req.getParameter("bTitle");
		String bcontent=req.getParameter("bContent");
		String fName=req.getFilesystemName("file");
		if (fName==null) {
			fName="";
		}
		
//		mybatis에서 작업
		IDao2 dao=sqlSession.getMapper(IDao2.class);
		dao.write(bname, btitle, bcontent,fName);
		
		return "redirect:list";
	}
	@RequestMapping("/download")
	public String download(HttpServletRequest request,
			HttpServletResponse response,Model model) throws Exception {
		System.out.println("pass by download()");
		String path=request.getParameter("p");
		String fname=request.getParameter("f");
		String bid=request.getParameter("bid");
		
		System.out.println("~~~"+path+":"+fname+":"+bid);
		response.setHeader("Content-Disposition", 
				"Attachment;filename="
		+URLEncoder.encode(fname,"utf-8"));//첨부처리, 한글처리
		//down
		String attachPath="resources\\upload\\";
		String realPath=request.getSession().getServletContext()
				.getRealPath(attachPath)+"\\"+fname;
		System.out.println("realPath : "+realPath);
		
		FileInputStream fin=new FileInputStream(realPath);
		ServletOutputStream sout=response.getOutputStream();
		
		byte[] buf=new byte[1024];
		int size=0;
		while ((size=fin.read(buf, 0, 1024))!=-1) {
			sout.write(buf, 0, size);
		}
		fin.close();
		sout.close();
		return "content_view?bid="+bid;	
	}
	
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request,Model model) {
		System.out.println("pass by content_view()");	
//		model.addAttribute("request",request);
//		commandInp=new BContentViewService();
//		commandInp.execute(model);
		String bid=request.getParameter("bid");
		
//		mybatis에서 작업
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.upHit(bid);
		
		BoardDto dto=dao.contentView(bid);
	
		model.addAttribute("content_view",dto);
		return "content_view";
	}
	@RequestMapping(method=RequestMethod.POST,value="/modify")
	public String modify(HttpServletRequest request,Model model) {
		System.out.println("pass by modify()");

//		model.addAttribute("request",request);
//		commandInp=new BModifyService();
//		commandInp.execute(model);
		
		String bid=request.getParameter("bid");
		String bname=request.getParameter("bName");
		String btitle=request.getParameter("bTitle");
		String bcontent=request.getParameter("bContent");

//		mybatis에서 작업
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.modify(bid,bname,btitle,bcontent);
	
		return "redirect:list";
	}
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,Model model) {
		System.out.println("pass by delete()");
//		model.addAttribute("request",request);
//		commandInp=new BDeleteService();
//		commandInp.execute(model);
		
		String bid=request.getParameter("bid");
//		mybatis에서 작업
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.delete(bid);

		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request,Model model) {
		System.out.println("pass by reply_view()");
//		model.addAttribute("request",request);
//		commandInp=new BReplyViewService();
//		commandInp.execute(model);
//		
		String bid=request.getParameter("bid");
//		mybatis에서 작업
		IDao dao=sqlSession.getMapper(IDao.class);
		BoardDto dto=dao.replyView(bid);
		model.addAttribute("reply_view",dto);
		
		return "reply_view";
	}
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request,Model model) {
		System.out.println("pass by reply()");

//		model.addAttribute("request",request);
//		commandInp=new BReplyService();
//		commandInp.execute(model);
		String bid=request.getParameter("bid");
		String bName=request.getParameter("bName");
		String bTitle=request.getParameter("bTitle");
		String bContent=request.getParameter("bContent");
		String bgroup=request.getParameter("bgroup");
		String bstep=request.getParameter("bstep");
		String bindent=request.getParameter("bindent");
		
//		mybatis에서 작업
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.replyShape(bgroup, bstep);
		dao.reply(bid, bName, bTitle,
				bContent, bgroup, bstep, bindent);
		
		return "redirect:list";
	}
}
