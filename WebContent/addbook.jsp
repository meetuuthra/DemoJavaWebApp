<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<!--div "main" contains the whole content(sidebar at left-for navigation and other contents at right ) of page except header and footer.-->
<div id="main" class="shell">
    <%-- Include tag is used to import sidebar for admin --%>    
    <%@ include file="adminsidebar.jsp" %>

    <%-- div to Add new book --%>
    <div id="content">
        <div class="products">
            <h3>Add New Book</h3>
            <section class="addcategSection">
                <form id="newStudyForm" action="BooksAddUpdateController?actionParam=<%=CommonConstants.ADD_BOOK%>" method="post" enctype="multipart/form-data" >
                    <input type="hidden" name="actionParam" id="action" value="<%=CommonConstants.ADD_BOOK%>">
                    <label>Title *</label>
                    <input type="text" name="title" required /><br>
                    <label>Author *</label>
                    <input type="text" name="author" required/><br>
                    <label>Category *</label>
                    <select name="category">
                        <c:forEach var="categ" items="${cList}" varStatus="loop">
                            <c:if test="${loop.first}">
                                <option value="<c:out value='${categ.categCode}'/>" selected><c:out value="${categ.categName}"/></option>
                            </c:if>
                            <c:if test="${!loop.first}">
                                <option value="<c:out value='${categ.categCode}'/>"><c:out value="${categ.categName}"/></option>
                            </c:if>
                        </c:forEach>    
                    </select>   
                    <br>
                    <label>Cover *</label>
                    <input type="file" id="image" name="image" onchange="javascript:setImgSource(this.value)" class="hidden"/>
                    <img src ="images/placeholder.jpg" id="imagePreview" alt="upload book's cover page" class="imgPreviewClass" onclick="javascript:ImageUploadClick('newStudyForm', 'image')"/>
                    <br>
                    <br>
                    <label>Isbn# *</label>  
                    <input type="text" name="isbn" maxlength="13" required/><br>
                    <label>Quantity# *</label>  
                    <input type="text" name="quantity" maxlength="4" required/><br>
                    <label class="">Price $ *</label>           
                    <input type="text" name="price" maxlength="5" required/>
                    <br>
                    <label>Publisher *</label>  
                    <input type="text" name="publisher" required/><br>
                    <label>Edition*</label>  
                    <input type="text" name="edition" required/><br>
                    <label>Description *</label>  
                    <textarea name="description" required></textarea><br>
                    <button type="submit"  id="submit_button" >Add Book</button>
                </form>
            </section><br/><br/>
            <div class="cl">&nbsp;</div>
        </div>
    </div>
</div> 
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>