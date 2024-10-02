@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("txtemail");
            String password = request.getParameter("txtpassword");
            String save = request.getParameter("savelogin");

            try {
                if (isNullOrEmpty(email) || isNullOrEmpty(password)) {
                    handleAutoLogin(request, response);
                } else {
                    handleLogin(request, response, email, password, save);
                }
            } catch (IOException e) {
                e.printStackTrace(); // Log the exception
            }
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.equals("");
    }

    private void handleAutoLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = getTokenFromCookies(request.getCookies());
        if (!token.isEmpty()) {
            response.sendRedirect("personalPage.jsp");
        } else {
            response.sendRedirect("errorpage.html");
        }
    }

    private String getTokenFromCookies(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("selector".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response, String email, String password, String save)
            throws IOException {
        Account acc = AccountDAO.getAccount(email, password);
        if (acc != null) {
            initializeSession(request, acc);
            if (save != null) {
                createLoginCookie(response, email);
            }
            redirectUser(response, acc);
        } else {
            response.sendRedirect("errorpage.html");
        }
    }

    private void initializeSession(HttpServletRequest request, Account acc) {
        HttpSession session = request.getSession(true);
        session.setAttribute("name", acc.getFullname());
        session.setAttribute("email", acc.getEmail());
    }

    private void createLoginCookie(HttpServletResponse response, String email) {
        String token = "ABC123";
        AccountDAO.updateToken(token, email);
        Cookie cookie = new Cookie("selector", token);
        cookie.setMaxAge(60 * 2);
        response.addCookie(cookie);
    }

    private void redirectUser(HttpServletResponse response, Account acc) throws IOException {
        if (acc.getRole() == 1) {
            response.sendRedirect("AdminIndex.jsp");
        } else {
            response.sendRedirect("personalPage.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
