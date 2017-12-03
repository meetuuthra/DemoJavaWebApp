<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<div id="main" class="shell">
    <%-- Include tag is used to import sidebar --%>    
    <%@ include file="adminsidebar.jsp" %>

    <%-- Section to create new category --%>
    <div id="content">
        <div class="products">
            <h3>Update Category</h3>
            <section class="addcategSection">
                <form id="newStudyForm" action="BooksAddUpdateController" method="post" >
                    <input type="hidden" name="actionParam" id="action" value="<%=CommonConstants.UPDATE_CATEGORY%>">
                    <input type="hidden" id="categCode" name="<%=CommonConstants.CATEGCODE%>" value="<c:out value='${categ.categCode}'/>">
                    <label>Category*</label>
                    <input type="text" name="categName" value="<c:out value='${categ.categName}'/>" required /><br>
                    <label>Description *</label>  
                    <textarea name="description" value="" required><c:out value='${categ.description}'/></textarea><br>
                    <button type="submit"  id="submit_button" >Update Category</button>
                </form>
            </section>

            <!--    <section class="how">
                    <h3>Company Information</h3>
            
                </section>-->
            <div class="cl">&nbsp;</div>
        </div>
    </div>
</div>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>