<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<!--div "main" contains the whole content(sidebar at left-for navigation and other contents at right ) of page except header and footer.-->
<div id="main" class="shell">
    <%-- Include tag is used to import sidebar --%>
    <%@ include file="sidebar.jsp" %>
<%-- div to create contactus confirmation page --%>
    <div id="content">
        <h3>Contact Us</h3>
        <div>
            <div id="newstudy_form">
                <h1>Thank you for contacting us.</h1>
                <p1>&nbsp;&nbsp;Your query number is <c:out value='${queryNum}'/></p1>
            </div>
        </div>
        <!-- End Content -->
        <div class="cl">&nbsp;</div>
    </div>
    <!-- End Main -->   
    <%-- Include tag is used to import footer page --%>
    <%@ include file="footer.jsp" %>      
</html>
