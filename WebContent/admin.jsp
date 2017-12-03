<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<!--div "main" contains the whole content(sidebar at left-for navigation and other contents at right ) of page except header and footer.-->
<section id="main" class="shell">
    <%-- Include tag is used to import sidebar for admin screen --%>    
    <%@ include file="adminsidebar.jsp" %>
    <%-- Include tag is used to import content --%>
    <%@ include file="aboutcontent.jsp" %>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>