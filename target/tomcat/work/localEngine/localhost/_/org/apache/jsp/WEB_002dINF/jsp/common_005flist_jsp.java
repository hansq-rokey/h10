package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class common_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fshiro_005fhasRole_0026_005fname;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fshiro_005fhasRole_0026_005fname = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fshiro_005fhasRole_0026_005fname.release();
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta charset=\"UTF-8\">\r\n");
      out.write("\t<meta charset=\"UTF-8\">\r\n");
      out.write("\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" >\r\n");
      out.write("\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no\"> \r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"/plug/bootstrap/bootstrap.min.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/index_new.css\">\r\n");
      out.write("\t<!-- <link href=\"/css/style.css\" rel=\"stylesheet\" type=\"text/css\"> -->\r\n");
      out.write("\t<!-- <link href=\"http://fe.ibaixiong.com/bbs/css/index.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\t<link href=\"http://fe.ibaixiong.com/bbs/css/prods.css\" rel=\"stylesheet\" type=\"text/css\"> -->\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"/plug/swiper/swiper.css\">\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"/plug/jQuery/jquery-1.9.1.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"/plug/swiper/swiper.jquery.min.js\"></script>\r\n");
      out.write("\t<script src=\"http://fe.ibaixiong.com/bbs/js/qrcode.js\"></script>\r\n");
      out.write("\t<script src=\"http://fe.ibaixiong.com/bbs/js/head.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t<script src=\"/js/base.js\" type=\"text/javascript\"></script>\r\n");
      out.write(" \t<script src=\"/js/index.js\" type=\"text/javascript\" ></script>\r\n");
      out.write("\t<title>白熊资讯</title>\r\n");
      out.write("   <script>\r\n");
      out.write("    $(document).ready(function(){\r\n");
      out.write("        $('.banner').css('height','428px');\r\n");
      out.write("        var Rand = Math.random();  \r\n");
      out.write("        $.ajax({\r\n");
      out.write("  \t\t   url: \"/u/user/getUserInfo.html\",\r\n");
      out.write("  \t\t   type: \"get\",\r\n");
      out.write("  \t\t   dataType:\"json\",\r\n");
      out.write("  \t\t   cache:false,\r\n");
      out.write("  \t\t   success: function(obj){\r\n");
      out.write("\t\t\t \tif ( obj.code == 0 ) {\r\n");
      out.write("\t\t\t\t\t//获取登陆用户成功之后给值\r\n");
      out.write("\t\t\t\t\t$(\"#yBtn\").show();\r\n");
      out.write("\t\t\t\t\t$(\"#loginBtn\").hide();\r\n");
      out.write("\t\t\t\t\t$(\"#regBtn\").hide();\r\n");
      out.write("\t\t\t\t\tvar nickName=obj.result.user.nickName;\r\n");
      out.write("\t\t\t\t\tif(nickName==''||nickName==null){\r\n");
      out.write("\t\t\t\t\t\tnickName=obj.result.user.bxNum;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t$(\"#userName\").text(nickName);\r\n");
      out.write("\t\t\t\t\t$(\"#headImg\").attr(\"src\",obj.result.user.headImg);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("  \t\t   }\r\n");
      out.write("  \t\t});\r\n");
      out.write("  \t\t/*$.ajax({\r\n");
      out.write("    \t\turl: '/u/user/getUserInfo.html?callbackName=getHeadUserInfo',\r\n");
      out.write("    \t\tdataType:'jsonp',\r\n");
      out.write("    \t\tjsonp:'callback'\r\n");
      out.write("    \t});*/\r\n");
      out.write("    })\r\n");
      out.write("    function getHeadUserInfo( obj ) {\r\n");
      out.write("\t\tif ( obj.code == 0 ) {\r\n");
      out.write("\t\t\t$(\"#yBtn\").show();\r\n");
      out.write("\t\t\t$(\"#loginBtn\").hide();\r\n");
      out.write("\t\t\t$(\"#regBtn\").hide();\r\n");
      out.write("\t\t\t$(\"#userName\").text(obj.result.user.nickName);\r\n");
      out.write("\t\t\t$(\".headImg\").attr(\"src\",obj.result.user.headImg);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("    \r\n");
      out.write("   /*发帖*/\r\n");
      out.write("   function toPublishArticle(){\r\n");
      out.write("\t   window.location.href='/u/article/toArticlePublish.html?headType=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${headType}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("   }\r\n");
      out.write("  </script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write(" \t<div class=\"avbar-fixed-top\">\r\n");
      out.write("\t    <div class=\"new_header\">\r\n");
      out.write("\t        <a href=\"http://www.ibaixiong.com/\"><img src=\"http://fe.ibaixiong.com/common/image/logo01.png\" class=\"bbs-logo\"> </a>\r\n");
      out.write("\t        <ul class=\"new_nav nav-pills nav-justified \">\r\n");
      out.write("\t            <li id=\"bxhp\"><a class=\"navlink\" href=\"http://www.ibaixiong.com\">首页</a></li>\r\n");
      out.write("\t            <li id=\"bxhp\"><a class=\"navlink\" target=\"_blank\" href=\"http://www.ibaixiong.com/attract.html\">合作伙伴</a></li>\r\n");
      out.write("\t           \t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t        </ul>\r\n");
      out.write("\t    </div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("    <!--content-->\r\n");
      out.write("\t<div class=\"_new_content\">\r\n");
      out.write("\t\t<div class=\"_new_box\">\r\n");
      out.write("\t\t<div class=\"_new_left\">\r\n");
      out.write("\t\t\t<div class=\"swiper-container\">\r\n");
      out.write("\t\t\t    <div class=\"swiper-wrapper\">\r\n");
      out.write("\t\t\t        <div class=\"swiper-slide\"><a href=\"http://bbs.ibaixiong.com/detail/444.html\"><img src=\"../../image/a1.jpg\"></a></div>\r\n");
      out.write("\t\t\t        <div class=\"swiper-slide\"><a href=\"http://bbs.ibaixiong.com/detail/445.html\"><img src=\"../../image/a2.jpg\"></a></div>\r\n");
      out.write("\t\t\t        <div class=\"swiper-slide\"><a href=\"http://bbs.ibaixiong.com/detail/438.html\"><img src=\"../../image/a3.jpg\"></a></div>\r\n");
      out.write("\t\t\t        <div class=\"swiper-slide\"><a href=\"http://bbs.ibaixiong.com/detail/435.html\"><img src=\"../../image/a4.jpg\"></a></div>\r\n");
      out.write("\t\t\t        \r\n");
      out.write("\t\t\t    </div>\r\n");
      out.write("                 <!-- 如果需要分页器 -->\r\n");
      out.write("                 <div class=\"swiper-pagination\"></div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"_new_section\">\r\n");
      out.write("            \t");
      if (_jspx_meth_c_005fforEach_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t<!--page-->\r\n");
      out.write("\t\t        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "include/pages.jsp" + (("include/pages.jsp").indexOf('?')>0? '&': '?') + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("pageUrl", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("/${headType }.html?", java.lang.String.class, (PageContext)_jspx_page_context, null, false), request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t\t    </div>\r\n");
      out.write("\t\t    <div class=\"loading_more\">浏览更多<span class=\"icon-arrow-right\"></span></div>\r\n");
      out.write("\t   \t</div>\r\n");
      out.write("\t\t<div class=\"_new_right\">\r\n");
      out.write("\t\t\t<div class=\"_new_wx\">\r\n");
      out.write("\t\t\t  <p class=\"_new_wx1\"><img src=\"/image/weixin_papabear.jpg\"></p>\r\n");
      out.write("              <p class=\"_new_wx2\">关注官方微信</p>          \r\n");
      out.write("              <p class=\"_new_wx3\">实时获取最新资讯</p>          \r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"_new_ig\">\r\n");
      out.write("\t\t\t\t<a href=\"http://www.ibaixiong.com/attract.html\"><img src=\"/image/new3.jpg\"></a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div id=\"publishDiv\" style=\"display: block;\">\r\n");
      out.write("\t        \t");
      if (_jspx_meth_shiro_005fhasRole_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t        </div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "include/footer.jsp", out, false);
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar mySwiper = new Swiper ('.swiper-container', {\r\n");
      out.write("        pagination: '.swiper-pagination',\r\n");
      out.write("        slidesPerView: 1,\r\n");
      out.write("        autoplay :3000,\r\n");
      out.write("        speed:300,\r\n");
      out.write("        paginationClickable: true,\r\n");
      out.write("        spaceBetween: 30,\r\n");
      out.write("        loop: true\r\n");
      out.write("  }) \r\n");
      out.write("\t $(function(){\r\n");
      out.write("\t\t$('.iconlist').hover(function() {\r\n");
      out.write("\t\t\t$(this).children('.sharebox').show();\r\n");
      out.write("\t\t}, function() {\r\n");
      out.write("\t\t\t$(this).children('.sharebox').removeClass(\"bigPic1\").hide();\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$('.wx').mouseover(function(){\r\n");
      out.write("\t        $(this).parent().parent('.sharebox').css('width','217');\r\n");
      out.write("\t    });\r\n");
      out.write("\t    $('.wx').mouseout(function(){\r\n");
      out.write("\t        $(this).parent().parent('.sharebox').css({\"width\":\"55\",\"border-right\":\"0\"});\r\n");
      out.write("\t    });\r\n");
      out.write("\t}) \r\n");
      out.write("</script>\r\n");
      out.write("<style>\r\n");
      out.write("#publishDiv{\r\n");
      out.write("    text-align: center;\r\n");
      out.write("}\r\n");
      out.write(".publishbtn{\r\n");
      out.write("    width: 210px;\r\n");
      out.write("    margin: 0 auto;\r\n");
      out.write("    height: 55px;\r\n");
      out.write("    background: #32a5e7;\r\n");
      out.write("    color: #fff;\r\n");
      out.write("    border: 0;\r\n");
      out.write("    font-size: 24px;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("</html>");
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

  private boolean _jspx_meth_c_005fforEach_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /WEB-INF/jsp/common_list.jsp(81,13) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/jsp/common_list.jsp(81,13) '${commonFormList }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${commonFormList }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/jsp/common_list.jsp(81,13) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("item");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t           \t\t<li class='");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.permissionsTag==headType?\"currentTop\":\"\" }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("'><a class='navlink' href='");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.url }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write('\'');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</a></li>\r\n");
          out.write("\t           \t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f1.setParent(null);
    // /WEB-INF/jsp/common_list.jsp(105,13) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/jsp/common_list.jsp(105,13) '${bbsList }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${bbsList }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/jsp/common_list.jsp(105,13) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVar("item");
    int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
      if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t<div class=\"_new_wrap row\">\r\n");
          out.write("\t                    <div class=\"col-lg-4 col-md-4 col-sm-4\">\r\n");
          out.write("\t                    \t<div class=\"_new_img\"><a href=\"/detail/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write(".html\"><img title=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.title }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" src=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.picUrl }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\"></a></div>\r\n");
          out.write("\t                    </div>\r\n");
          out.write("\t                    \r\n");
          out.write("\t                    <div class=\"col-lg-8 col-md-8 col-sm-8\">\r\n");
          out.write("\t\t                    <div class=\"_new_text\">\r\n");
          out.write("\t\t\t\t\t\t\t\t<h3 class=\"_new_tag\"><a href=\"/detail/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write(".html\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.title }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</a></h3>\r\n");
          out.write("\t\t\t\t\t\t\t\t<p class=\"_new_brief\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.summary }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</p>\r\n");
          out.write("\t\t\t\t\t\t\t\t<span class=\"_new_time\">");
          if (_jspx_meth_fmt_005fformatDate_005f0(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
            return true;
          out.write("</span>\r\n");
          out.write("\t\t\t\t\t\t\t    <div class=\"titleline\">\r\n");
          out.write("\t\t\t\t\t\t\t    \t<!--浏览次数-->\r\n");
          out.write("\t\t\t\t\t\t\t    \t<span class=\"iconlist\">\r\n");
          out.write("\t\t\t\t\t\t\t    \t\t<i class=\"icon read\"></i>\r\n");
          out.write("\t\t\t\t\t\t\t    \t\t");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.viewCount }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t    \t</span>\r\n");
          out.write("\t\t\t\t\t\t\t    \t<!--评论-->\r\n");
          out.write("\t\t\t\t\t\t\t    \t<span class=\"iconlist\">\r\n");
          out.write("\t\t\t\t\t\t\t    \t\t<i class=\"icon reply\"></i>\r\n");
          out.write("\t\t\t\t\t\t\t    \t\t");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.replyCount }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t    \t</span>\r\n");
          out.write("\t\t\t\t\t\t\t    \t<!--点赞-->\r\n");
          out.write("\t\t\t\t\t\t\t    \t<span class=\"iconlist\">\r\n");
          out.write("\t\t\t\t\t\t\t    \t\t");
          if (_jspx_meth_c_005fif_005f0(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
            return true;
          out.write("\r\n");
          out.write("\t\t\t\t\t                    ");
          if (_jspx_meth_c_005fif_005f1(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
            return true;
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t    \t</span>\r\n");
          out.write("\t\t\t\t\t\t\t    \t<!--分享-->\r\n");
          out.write("\t\t\t\t\t\t\t    \t<div class=\"iconlist\">\r\n");
          out.write("\t\t\t\t\t\t\t    \t\t<i class=\"icon share\"></i>分享\r\n");
          out.write("\t\t\t\t\t\t\t    \t\t<div class=\"sharebox\">\r\n");
          out.write("\t\t\t\t                            <a class=\"hover_\" href=\"###\"> <span data-id=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" data-value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.title }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" class=\"shareacon wx\"></span></a>\r\n");
          out.write("\t\t\t\t                            <a href=\"http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=http://bbs.ibaixiong.com/share/detail/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write(".html\" target=\"_blank\"> <span data-id=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" data-value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.title }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" class=\"shareacon kj\"></span></a>\r\n");
          out.write("\t\t\t\t                            <a href=\"http://connect.qq.com/widget/shareqq/index.html?title=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.title}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("&url=http://bbs.ibaixiong.com/share/detail/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write(".html\" target=\"_blank\"> <span data-id=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" data-value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.title }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" class=\"shareacon qq\"></span></a>\r\n");
          out.write("\t\t\t\t                            <a href=\"http://v.t.sina.com.cn/share/share.php?url=http://bbs.ibaixiong.com/share/detail/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write(".html&title='");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.title }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("'\" target=\"_blank\"> <span data-id=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" data-value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.title }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" class=\"shareacon wb\"></span></a>\r\n");
          out.write("\t\t\t\t                            <div data-id=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" class=\"shareImgBox\">\r\n");
          out.write("\t\t\t\t                            \t<p class=\"shareWeixin\">扫一扫分享到微信</p>\r\n");
          out.write("\t\t\t\t                            \t<div class=\"shareWeixinImgBox\">\r\n");
          out.write("\t\t\t\t                            \t\t<img src=\"#\" id=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" class=\"shareWeixinImg\">\r\n");
          out.write("\t\t\t\t                            \t</div>\r\n");
          out.write("\t\t\t\t                            </div>\r\n");
          out.write("\t\t\t\t                        </div>\r\n");
          out.write("\t\t\t\t\t\t\t    \t</div>\r\n");
          out.write("\t\t\t\t\t\t\t    </div>\r\n");
          out.write("\t\t\t\t\t\t\t</div>\r\n");
          out.write("\t                    </div>\r\n");
          out.write("\t                    \r\n");
          out.write("\t\t\t\t\t</div>\r\n");
          out.write("            \t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f1.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f1);
    }
    return false;
  }

  private boolean _jspx_meth_fmt_005fformatDate_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatDate
    org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag _jspx_th_fmt_005fformatDate_005f0 = (org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag) _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag.class);
    _jspx_th_fmt_005fformatDate_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fformatDate_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /WEB-INF/jsp/common_list.jsp(115,32) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f0.setValue((java.util.Date) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.createDateTime}", java.util.Date.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/common_list.jsp(115,32) name = pattern type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f0.setPattern("yyyy.MM.dd");
    int _jspx_eval_fmt_005fformatDate_005f0 = _jspx_th_fmt_005fformatDate_005f0.doStartTag();
    if (_jspx_th_fmt_005fformatDate_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody.reuse(_jspx_th_fmt_005fformatDate_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody.reuse(_jspx_th_fmt_005fformatDate_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /WEB-INF/jsp/common_list.jsp(129,13) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.pariseyes == 1 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t                    \t<i class=\"icon praise\" style=\"background-position:-12px -133px;cursor:auto\"></i><span>");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.pariseCount }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</span>\r\n");
        out.write("\t\t\t\t\t                    ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /WEB-INF/jsp/common_list.jsp(132,25) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.pariseyes == null }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t                    \t<i class=\"icon praise\" onclick=\"parise(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\"></i><span id=\"pariseCount ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write('"');
        out.write('>');
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.pariseCount }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</span>\r\n");
        out.write("\t\t\t\t\t                    ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_shiro_005fhasRole_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  shiro:hasRole
    org.apache.shiro.web.tags.HasRoleTag _jspx_th_shiro_005fhasRole_005f0 = (org.apache.shiro.web.tags.HasRoleTag) _005fjspx_005ftagPool_005fshiro_005fhasRole_0026_005fname.get(org.apache.shiro.web.tags.HasRoleTag.class);
    _jspx_th_shiro_005fhasRole_005f0.setPageContext(_jspx_page_context);
    _jspx_th_shiro_005fhasRole_005f0.setParent(null);
    // /WEB-INF/jsp/common_list.jsp(175,10) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasRole_005f0.setName("administrator");
    int _jspx_eval_shiro_005fhasRole_005f0 = _jspx_th_shiro_005fhasRole_005f0.doStartTag();
    if (_jspx_eval_shiro_005fhasRole_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t        \t\t<input type=\"button\" id=\"publishBtn\" class=\"publishbtn\" value=\"发      帖\" onclick=\"toPublishArticle()\">\r\n");
        out.write("\t        \t");
        int evalDoAfterBody = _jspx_th_shiro_005fhasRole_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_shiro_005fhasRole_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fshiro_005fhasRole_0026_005fname.reuse(_jspx_th_shiro_005fhasRole_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fshiro_005fhasRole_0026_005fname.reuse(_jspx_th_shiro_005fhasRole_005f0);
    return false;
  }
}
