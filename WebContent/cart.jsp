<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>

<!--div "main" contains the whole content(sidebar at left-for navigation and other contents at right ) of page except header and footer.-->
<div id="main" class="shell">
    <%-- Include tag is used to import sidebar --%>
    <%@ include file="sidebar.jsp" %>
    <%-- div to display cart items. --%>
    <div id="content">
        <div class="products">
            <h3>Cart</h3>
            <section>
                <table class="cartTable">
                    <tr>
                        <th>Book</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Amount</th>
                        <th>Remove</th>
                    </tr>  
                    <c:set var="emptyCart" value="true"/>
                    <c:forEach var="item" items="${cart.items}">
                        <c:set var="emptyCart" value="false"/>
                        <tr>
                            <td >
                                <span> <c:out value='${item.product.title}'/></span>
                            </td>
                            <td >

                                <form id="productUpdate" action="Cart" method="post">
                                    <input type="hidden" id="actionParam" name="actionParam" value="<%=CommonConstants.CART%>">
                                    <input type="hidden" name="bookId" value="<c:out value='${item.product.bookId}'/>">
                                    <select name="quantity" class="selBox">
                                        <c:forEach begin="1" end="${item.product.quantity}" var="counter">
                                            <option value="<c:out value='${counter}'/>"
                                                    <c:if test="${item.quantity == counter}">selected</c:if>
                                                    ><c:out value='${counter}'/></option>
                                        </c:forEach>
                                    </select>
                                    <input class="cartbtn" type="submit" value="Update" >
                                </form>
                            </td>

                            <td >
                                $<c:out value='${item.product.priceString}'/>
                            </td>
                            <td ><c:out value='${item.totalCurrencyFormat}'/></td>
                            <td >
                                <form action="Cart" method="post">
                                    <input type="hidden" id="actionParam" name="actionParam" value="<%=CommonConstants.CART%>">
                                    <input type="hidden" name="bookId" 
                                           value="<c:out value='${item.product.bookId}'/>">
                                    <input type="hidden" name="quantity" 
                                           value="0">
                                    <input class="cartbtn" type="submit" value="Remove">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${!emptyCart}">   
                        <tr>
                            <td colspan="3">
                                <p><b>Total Amount:</b></p>                        
                            </td>
                            <td colspan="2" class="tdTextLeft">
                                <c:out value='${cart.totalAmountFormat}'/>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td colspan="5" class="tdTextRight">
                            <c:if test="${emptyCart}"> <span>No item added in cart.<span></c:if>
                            <a href="#" class="button" onclick="javascript:submitForms('linksForm', 'home', 'PageFlowController')"> Continue Shopping </a>
                            &nbsp;&nbsp;&nbsp;
                            <c:if test="${!emptyCart}">   <a href="Cart?actionParam=checkout" class="button"> Checkout </a>  </c:if>                 
                            </td>

                        </tr>
                    </table> 

                </section>
            </div>

        </div>
        <!-- End Content -->

        <div class="cl">&nbsp;</div>
    </div>
    <!-- End Main -->   
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>