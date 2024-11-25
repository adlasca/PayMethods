package classes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/payDashboard")
public class PayMethods extends HttpServlet {
    public static final Map<String, String> PAYMENT_DESCRIPTION = new HashMap<>();
    static {
        PAYMENT_DESCRIPTION.put("Credit Card", "A method that use any Credit Card type like Visa, MasterCard, etc.");
        PAYMENT_DESCRIPTION.put("Transfer","Use this for an easy transfer from an application bank.");
        PAYMENT_DESCRIPTION.put("PayPal","If you are from exterior, use this.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String[] payMethods = (String[]) session.getAttribute("userPayMethod");

        if(payMethods == null || payMethods.length == 0) {
            handleNoPayMethod(resp);
            return;
        }
        PrintWriter out = resp.getWriter();
        out.println("""
                <html>
                <body>
                <h1>Pay Methods</h1>    
                """);
        for(String payMethod : payMethods) {
            String additionalInfo = PAYMENT_DESCRIPTION.get(payMethod);
                out.println("""
                        <h2>Recommended %s</h2>
                        <p>Here are some %s you might use</p>
                        %s
                        """.formatted(payMethod,payMethod, additionalInfo));
        }
        out.println("""
                    </body>
                </html>
                """);
    }

    private void handleNoPayMethod( HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("""
                <html>
                    <body>
                        <h1>No Pay Methods found</h1>
                        <p>Please select a pay method.</p>
                    </body>
                </html>
                """);
    }
}
