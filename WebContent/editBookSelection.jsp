<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<!--div "main" contains the whole content(sidebar at left-for navigation and other contents at right ) of page except header and footer.-->
<div id="main" class="shell">
    <%-- Include tag is used to import sidebar --%>    
    <%@ include file="adminsidebar.jsp" %>

    <div id="content">
        <div class="products">

            <h3><c:out value='${pageHeading}'/></h3>
            <%-- Section to display all categories. Category name and description can be updated. --%>
            <section class="sectionTable">

                <table  >
                    <tr>
                        <th>Book-Name</th>
                        <th>Author</th>                
                        <th>Edition</th>
                        <th>Category</th>
                        <th>Action</th>
                    </tr>
                    <c:set var="isListEmpty" value="${true}"></c:set>
                    <c:forEach var="book" items="${booksList}">
                        <c:set var="isListEmpty" value="${false}"></c:set>
                            <tr>
                            <form id="BookEditSelForm<c:out value='${book.bookId}'/>" action="BooksAddUpdateController" method="post">
                            <input type="hidden" id="<%=CommonConstants.BOOKID%>" name="<%=CommonConstants.BOOKID%>" value="<c:out value='${book.bookId}'/>">
                            <input type="hidden" id="actionParam" name="actionParam" value="">
                            <td><c:out value="${book.title}"></c:out></td>
                            <td><c:out value="${book.author}"></c:out></td>
                            <td><c:out value="${book.edition}"></c:out></td>
                            <td><c:out value="${book.categName}"></c:out></td>
                            <td>
                                <button class="button" type="submit" onclick="javascript:submitForms('BookEditSelForm<c:out value='${book.bookId}'/>', '<%=CommonConstants.EDIT_SEL_BOOK%>', 'BooksAddUpdateController')">Edit</button></td>
                        </form>
                        </tr>


                    </c:forEach>

                    <c:if test="${isListEmpty}">
                        <tr>
                            <td colspan="5">No Book found.</td>

                        </tr>
                    </c:if>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td> 
                        <td></td><td></td>
                    </tr>
                </table>

            </section>

            <!--    <section class="how">
                    <h3>Company Information</h3>
            
                </section>-->
            <div class="cl">&nbsp;</div>
        </div></div></div>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>