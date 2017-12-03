<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>

<!--div "main" contains the whole content(sidebar at left-for navigation and other contents at right ) of page except header and footer.-->
<div id="main" class="shell">
    <%-- Include tag is used to import sidebar --%>
    <%@ include file="sidebar.jsp" %>
    <%-- Include tag is used to import content --%>
    <%@ include file="content.jsp" %>
    <div class="cl">&nbsp;</div>
</div>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>