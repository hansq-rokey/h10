package org.apache.jsp.WEB_002dINF.jsp.include;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class footer_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write(" <meta charset=\"UTF-8\">\r\n");
      out.write("<footer>\r\n");
      out.write("    <div class=\"row footor\">\r\n");
      out.write("      <div class=\"footer_content\">\r\n");
      out.write("            <a  href=\"http://www.ibaixiong.com/about.html\" target=\"_black\">关于白熊</a>\r\n");
      out.write("\t  \t\t<a  href=\"http://www.ibaixiong.com/join.html\" target=\"_black\">工作机会</a>\r\n");
      out.write("\t  \t\t<a  href=\"http://www.ibaixiong.com/contact.html\" target=\"_black\">联系我们</a>\r\n");
      out.write("\t  \t\t<a href=\"http://user.ibaixiong.com/agreement.html\" target=\"_black\">法律声明</a>\n");
      out.write("            <a class=\"new_nb\" href=\"\">服务热线:400-175-008</a>\r\n");
      out.write("        </div>    \r\n");
      out.write("        <p style=\"color:#999;\"><span>© 2015 ibaixiong.com All rights reserved.</span> <span>杭州白熊科技有限责任公司 备案号:浙ICP备15024007号-1. </span><a target=\"_blank\" class=\"mobile-gan\"\r\n");
      out.write("         href=\"http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=33010402000428\" style=\"display:inline-block;text-decoration:none;height:20px;line-height:20px;\"><img src=\"http://fe.ibaixiong.com/common/image/ghs.png\" style=\"float:left;\"/>浙公网安备 33010402000428号</a></p>\r\n");
      out.write("    </div>\r\n");
      out.write("</footer>\n");
      out.write("<style>\r\n");
      out.write(".footor{\r\n");
      out.write("    text-align: center;\r\n");
      out.write("    font-size:12px;\r\n");
      out.write("    height:130px;\r\n");
      out.write("    width: 100%;\r\n");
      out.write("    border-top:1px solid #f2f2f2;\r\n");
      out.write("    padding-top:40px;\r\n");
      out.write("    color:#999;\r\n");
      out.write("    overflow:hidden;\r\n");
      out.write("}\r\n");
      out.write(".footor a{\r\n");
      out.write("    display: inline-block;\r\n");
      out.write("    padding:5px;\r\n");
      out.write("    color:#999;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</style>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
