package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("<title>Welcome to Jason's simple User App</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<a href=\"http://localhost:8080/SimpleApp/ServletTestRunner?suite=com.wickedhobo.test.SimpleDAOTestServlet&transform=yes\">Go to the Simple JDBC Tests</a><br/>\n");
      out.write("<a href=\"http://localhost:8080/SimpleApp/ServletTestRunner?suite=com.wickedhobo.test.HibernateDAOTestServlet&transform=yes\">Go to the DAO Hibernate Tests</a><br/>\n");
      out.write("<a href=\"http://localhost:8080/SimpleApp/ServletTestRunner?suite=com.wickedhobo.test.HibernateSpringDAOTestServlet&transform=yes\">Go to the DAO Hibernate Spring Tests</a><br/>\n");
      out.write("<a href=\"addUser.jsp\">Add a Person using Spring/Hibernate stuff.</a><br/>\n");
      out.write("<a href=\"updateUser.jsp\">Update a Person using Spring/Hibernate stuff.</a><br/>\n");
      out.write("<a href=\"removeUser.jsp\">Remove a Person using Spring/Hibernate stuff.</a><br/>\n");
      out.write("</body>\n");
      out.write("</html>\n");
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
