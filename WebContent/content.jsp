<!--Page to display content on main page-->
<%-- taglib to use jstl --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- page import to use java constants class --%>
<%@page import="com.bs.constants.CommonConstants" %>

<!-- div to show Contents -->
<div id="content">
    <div class="products">
        <h3><c:out value='${pageHeading}'/></h3>
        ${msg}
        <ul>
            <c:forEach var="book" items="${bList}"> 
                <li>
                    <div class="product">

                        <form id="product<c:out value='${book.bookId}'/>" method="post">

                            <input type="hidden" id="<%=CommonConstants.BOOKID%>" name="<%=CommonConstants.BOOKID%>" value="<c:out value='${book.bookId}'/>">
                            <input type="hidden" id="actionParam" name="actionParam" value="">
                            <input type="hidden" name="newItem" value="true">

                            <a href="#"  class="info" onclick="javascript:submitForms('product<c:out value='${book.bookId}'/>', '<%=CommonConstants.SELECT_TUNE%>', 'PageFlowController')">
                                <span class="holder">
                                    <c:set var="imgurlF" value="images\\sidebarimages\\"></c:set>
                                    <c:set var="imgurl" value="${imgurlF}${book.cover}"></c:set>
                                    <img src="${imgurl}" title="<c:out value='${book.title}'/> by <c:out value='${book.author}'/>" alt="<c:out value='${book.title}'/>" />
                                    <span class="book-name"><c:out value='${book.titleShort}'/></span>
                                    <span class="author">by <c:out value='${book.authorShort}'/></span>
                                    <span class="description"><c:out value='${book.descriptionShort}'/></span>
                                </span>
                            </a>
                            <c:choose>
                                <c:when test="${theUser != null}">
                                    <c:if test="${book.quantity == 0}">
                                        <span class="buy-btn">Not Available </span>
                                    </c:if>
                                    <c:if test="${book.quantity > 0}">
                                        <a href="#" class="buy-btn" onclick="javascript:submitForms('product<c:out value='${book.bookId}'/>', '<%=CommonConstants.CART%>', 'Cart')">
                                            Add To Cart <span class="price"><span class="low">$</span><c:out value='${book.priceFirst}'/><span class="high"><c:out value='${book.priceLast}'/></span></span></a>
                                            </c:if>
                                        </c:when>  
                                    <c:otherwise>
                                         <span class="buy-btn"> Book Price<span class="price"><span class="low">$</span><c:out value='${book.priceFirst}'/><span class="high"><c:out value='${book.priceLast}'/></span></span> </span>
                                    </c:otherwise>
                                    </c:choose>


                        </form>

                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="cl">&nbsp;</div>
</div>
<!-- End Content -->