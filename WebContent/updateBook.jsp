<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<div id="main" class="shell">
    <%-- Include tag is used to import sidebar --%>    
    <%@ include file="adminsidebar.jsp" %>


    <div id="content">
        <div class="products">

            <h3>Update Book Details</h3>
            <section class="addcategSection">
                <form id="newStudyForm" action="BooksAddUpdateController?actionParam=editBook" method="post" enctype="multipart/form-data" >
                    <input type="hidden" name="actionParam" id="action" value="<%=CommonConstants.EDIT_BOOK%>">
                    <input type="hidden" name="<%=CommonConstants.BOOKID%>" id="<%=CommonConstants.BOOKID%>" value="<c:out value='${book.bookId}'/>">
                    <input type="hidden" name="oldImage"  value="<c:out value='${book.cover}'/>">
                    <label>Title *</label>
                    <input type="text" name="title" value="<c:out value='${book.title}'/>" required /><br>
                    <label>Author *</label>
                    <input type="text" name="author" value="<c:out value='${book.author}'/>" required/><br>
                    <label>Category *</label>
                    <select name="category">
                        <c:forEach var="categ" items="${cList}" varStatus="loop">
                            <c:if test="${categ.categCode eq book.categCode}">
                                <option value="<c:out value='${categ.categCode}'/>" selected><c:out value='${categ.categName}'/></option>
                            </c:if>
                            <c:if test="${!(categ.categCode eq book.categCode)}">
                                <option value="<c:out value='${categ.categCode}'/>"><c:out value='${categ.categName}'/></option>
                            </c:if>
                        </c:forEach>    
                    </select>   
                    <br>
                    <c:set var="imgurlF" value="images\\sidebarimages\\"></c:set>
                    <c:set var="imgurl" value="${imgurlF}${book.cover}"></c:set>
                        <label>Cover *</label>
                        <input type="file"  id="image" name="image" onchange="javascript:setImgSource(this.value)" class="hidden"/>
                        <!--            <br/>
                                    <label>Cover *</label>
                                    <input type="file" class="hidden" id="uploadFile"/>-->
                        <!--<div class="buttonImg" id="uploadTrigger" onclick="javascript:ImageUploadClick('newStudyForm','image')">Upload File</div>-->


                        <img src ='<c:out value='${imgurl}'/>' id="imagePreview" alt="upload book's cover page" class="imgPreviewClass" onclick="javascript:ImageUploadClick('newStudyForm', 'image')"/>
                    <br>
                    <br>
                    <label>Isbn# *</label>  
                    <input type="text" name="isbn" maxlength="13" value="<c:out value='${book.isbn}'/>" required/><br>
                    <label>Quantity# *</label>  
                    <input type="text" name="quantity" maxlength="4" value="<c:out value='${book.quantity}'/>" required/><br>
                    <label class="">Price $ *</label>           
                    <input type="text" name="price" maxlength="5" value="<c:out value='${book.price}'/>" required/>
                    <br>
                    <label>Publisher *</label>  
                    <input type="text" name="publisher" value="<c:out value='${book.publisher}'/>" required/><br>
                    <!--            <label>Published *</label>  
                                <input type="text" name="published" required/><br>-->
                    <label>Edition*</label>  
                    <input type="text" name="edition" value="<c:out value='${book.edition}'/>" required/><br>
                    <label>Description *</label>  
                    <textarea name="description"  required><c:out value='${book.descritpion}'/></textarea><br>

                    <button type="submit"  id="submit_button" >Update</button>
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