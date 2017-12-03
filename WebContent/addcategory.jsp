<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<!--div "main" contains the whole content(sidebar at left-for navigation and other contents at right ) of page except header and footer.-->
<div id="main" class="shell">
    <%-- Include tag is used to import sidebar --%>    
    <%@ include file="adminsidebar.jsp" %>

    <%-- div to create new category --%>
    <div id="content">
        <div class="products">
            <h3>Add New Book Category</h3>
            <section class="addcategSection">
                <form id="newStudyForm" action="BooksAddUpdateController" method="post" >
                    <input type="hidden" name="actionParam" id="action" value="<%=CommonConstants.ADD_CATEGORY%>">
                    <label>Category*</label>
                    <input type="text" name="categName" required /><br>
                    <label>Description *</label>  
                    <textarea name="description" required></textarea><br>

                    <button type="submit"  id="submit_button" >ADD Category</button>
                </form>
            </section>
            <div class="cl">&nbsp;</div>
        </div>
    </div>
</div>
    <%-- Include tag is used to import footer page --%>
    <%@ include file="footer.jsp" %>