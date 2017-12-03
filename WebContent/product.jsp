<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.bs.constants.CommonConstants" %>
<!-- Content -->
<div id="content">
    <!-- Products -->

    <div class="products">
        <h3>Book Details</h3>
        <section>
            <table class="productTable">
                <tr>
                    <th colspan="2">Product Details</th>
                </tr>    
                <tr>
                    <td>
                        <c:set var="imgurlF" value="images\\sidebarimages\\"></c:set>
                        <c:set var="imgurl" value="${imgurlF}${book.cover}"></c:set>
                        <img src ='${imgurl}' id="imagePreview" class="imgPreviewClass" />
                    </td>
                    <td>
                        <form id="productDescForm" method = "POST">
                            <input type="hidden" id="actionParam" name="actionParam" value="">
                            <input type="hidden" name="newItem" value="true">
                            <input type="hidden" name="bookId" value="${book.bookId}">
                            <input type="hidden" name="title" value="${book.title}">
                            <span class="bookTitle"><c:out value="${book.title}"></c:out></span><br/>
                            <input type="hidden" name="author" value="${book.author}">
                            <span>By<c:out value="${book.author}"></c:out></span><br/>
                            <input type="hidden" name="price" value="${book.priceString}">
                            <span>Price: $<c:out value="${book.priceString}"></c:out></span><br/>
                            <input type="hidden" name="quantityAvailable" value="${book.quantity}">
                            <span>Quantity Available: <c:out value="${book.quantity}"></c:out></span><br/>
                                <br/>
                            <c:if test="${theUser != null}">  
                            <c:if test="${book.quantity == 0}">
                                <span class="cartbtn">Not Available </span>
                            </c:if>
                            <c:if test="${book.quantity > 0}">
                                <span><a href="#" class="cartbtn" onclick="javascript:submitForms('productDescForm', '<%=CommonConstants.CART%>', 'Cart')">Add to Shopping Cart</a></span>
                                <br/><br/><span><b>Availability:</b> Usually ships in 2 to 4 weeks.</span>
                            </c:if>
                            </c:if>
                                <!--<input type="submit" value="Add to Shopping Cart" onclick="javascript:submitForms('searchForm', '<%=CommonConstants.SEARCH_BOX%>', 'Cart')">-->
                        </form>

                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <p><c:out value="${book.descritpion}"></c:out></p>                        
                    </td>
                </tr>    
            </table> 

        </section>

    </div>

</div>
<!-- End Content -->