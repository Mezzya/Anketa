import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Anketa extends HttpServlet {

    List<Human> list = Collections.synchronizedList(new ArrayList<Human>());

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String srcPage = req.getCharacterEncoding();

        resp.setContentType("text.html");
        PrintWriter pw = resp.getWriter();
        pw.println("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Anketa result</title></head><body>");

//        Провреим валидность полей как сможем.
        boolean corectFilds=true;
        if (req.getParameter("firstname").equals(""))
        {
            pw.println("is not a valid first name<br>");
            corectFilds=false;
        }
        if (req.getParameter("lastname").equals("")){
            pw.println("is not a valid last name<br>");
            corectFilds=false;
        }
        int age=0;
        try{
            age = Integer.parseInt(req.getParameter("age"));
        } catch (NumberFormatException e)
        {
            pw.println("age is not a integer<br>");
            corectFilds =false;
        }

        if ((age>100)||(age<0)){
            pw.println("is not a valid range of age<br>");
            corectFilds=false;
        }

        if ((req.getParameter("qOne")==null)||req.getParameter("qTwo")==null){
            pw.println("answers to the questions must be chosen<br>");
            corectFilds=false;
        }

        if (corectFilds)
        {
//            Все поля вроде как коректные
//            Добавим проголосовавшего в наш лист;

            boolean answerOne =false;
            boolean answerTwo = false;
            if (req.getParameter("qOne").equals("yes")) answerOne=true;
            if (req.getParameter("qTwo").equals("yes")) answerTwo=true;

            list.add(new Human(req.getParameter("firstname"),req.getParameter("lastname"), age, answerOne, answerTwo));

            pw.println("Statistic:<br>");
            pw.print("Col humans - "+Human.getCol()+"<br>");

//            Общее количество положительныъ ответов
            int colQoneYes=0;
            int colQtwoYes=0;


            for (Human human: list) {
                if (human.getqOne()) colQoneYes++;
                if (human.getqTwo()) colQtwoYes++;

            }

            pw.println("Question #1  Yes: "+colQoneYes+" No: "+(Human.getCol()-colQoneYes)+"<br>");
            pw.println("Question #2  Yes: "+colQtwoYes+" No: "+(Human.getCol()-colQtwoYes)+"<br><br>");


            pw.println("<table cellpadding=\"5\" cellspacing=\"0\" border=\"1\"><tr><td>First Name</td><td>Last Name</td><td>Age</td><td>Question #1</td><td>Question #2</td></tr>");
            pw.println("");
            for (Human human: list ) {
                pw.println("<tr>");

                pw.println("<td>"+human.getFirstName()+"</td><td>"+human.getLastName()+"</td><td>"+human.getAge()+"</td><td>");
                if (human.getqOne()) pw.print("Yes");
                else pw.print("No");
                pw.print("</td><td>");
                if (human.getqTwo()) pw.print("Yes");
                else pw.print("No");
                pw.print("</td>");


                pw.println("</tr>");





            }
            pw.println("</table><a href=\"index.html\">Return</a>");



        } else {
            pw.println("<a href=\"index.html\">try again</a>");
        }



        pw.println("</body></html>");
    }


}
