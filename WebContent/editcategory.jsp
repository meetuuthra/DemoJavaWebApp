<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<!--div "main" contains the whole content(sidebar at left-for navigation and other contents at right ) of page except header and footer.-->
<div id="main" class="shell">
    <%-- Include tag is used to import sidebar --%>    
    <%@ include file="adminsidebar.jsp" %>

    <div id="content">
        <!-- Products -->

        <div class="products">

            <h3>Update Category</h3>
            <%-- Section to display all categories. Category name and description can be updated. --%>
            <section class="sectionTable">

                <table  >
                    <tr>
                        <th>Category Name</th>
                        <th>Description</th>                
                        <th>Action</th>
                    </tr>
                    <c:set var="isListEmpty" value="${true}"></c:set>
                    <c:forEach var="categ" items="${categList}">
                        <c:set var="isListEmpty" value="${false}"></c:set>
                            <tr>
                            <form id="CategEditSelForm${categ.categCode}" action="BooksAddUpdateController" method="post">
                            <input type="hidden" id="categCode" name="<%=CommonConstants.CATEGCODE%>" value="<c:out value='${categ.categCode}'/>">
                            <input type="hidden" id="" name="categName" value="<c:out value='${categ.categName}'/>">
                            <input type="hidden" id="" name="description" value="<c:out value='${categ.description}'/>">
                            <input type="hidden" id="actionParam" name="actionParam" value="default">
                            <td><c:out value="${categ.categName}"></c:out></td>
                            <td><c:out value="${categ.description}"></c:out></td>

                            <%-- Code to display edit page --%>
                            <td>
                                <button class="button" type="submit" onclick="javascript:submitForms('CategEditSelForm${categ.categCode}', '<%=CommonConstants.EDIT_CATEGORY%>', 'BooksAddUpdateController')">Edit</button></td>
                        </form>
                        </tr>


                    </c:forEach>
                    <c:if test="${isListEmpty}">
                        <tr>
                            <td colspan="5">No Category found.</td>

                        </tr>
                    </c:if>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td> 

                    </tr>
                </table>

            </section>

            <!--    <section class="how">
                    <h3>Company Information</h3>
            
                </section>-->
            <div class="cl">&nbsp;</div>
        </div>
    </div></div>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>