<%@ page import="java.util.List" %>
<%@ page import="com.example.servlet_customer.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <thead>
    <%
     <tr>firstname</tr><tr>lastname</tr><tr>phone</tr><tr>country</tr><tr>city</tr><tr>postcode</tr>
     <tr>email</tr><tr>dateofbirth</tr><tr>instagram</tr><tr>pet</tr><tr>kids</tr><tr>gender</tr>
     <tr>maritalstatus</tr><tr>linkedin</tr><tr>job</tr> %>
    </thead>

    <tbody>
        <%
        List<Customer> customers = (List<Customer>) request.getAttribute("customers");
        response.getWriter().println("<table>")

         response.getWriter().println("<tr><th>" + "FirstName" + "</th><th>" + "LastName" + "</th><th>" + "Phone"
                    + "</th><th>" + "Country" + "</th><th>" + "City" + "</th><th>" + "Postcode" + "</th><th>" + "Email"
                    + "</th><th>" + "DateOfBirth" + "</th><th>" + "Instagram" + "</th><th>" + "Pet" + "</th><th>" + "Kids"
                    + "</th><th>" + "Gender" + "</th><th>" + "MaritalStatus" + "</th><th>" + "Linkedin" + "</th><th>" + "Job"
                    + </th></tr>);


        for (Customer customer:customers){
            response.getWriter().println("<tr><td>" + customer.getFirstname() + " " +customer.getLastname() + " " + customer.getPhone() + " "
                    + customer.getCountry() + " " + customer.getCity() + " "
                    + customer.getPostcode() + " " + customer.getEmail() + " " + customer.getDateofbirth() + " "
                    + customer.getInstagram() + " " + customer.getPet() + " " + customer.getKids() + " " + customer.getGender() + " "
                    + customer.getMaritalstatus() + " " + customer.getLinkedin() + " " + customer.getJob() + "</tr></td>"));
        }
        response.getWriter().println("</table");
    %>
    </tbody>
</table>

</body>
</html>
