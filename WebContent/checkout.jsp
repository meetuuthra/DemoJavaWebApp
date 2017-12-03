<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>

<!--div "main" contains the whole content(sidebar at left-for navigation and other contents at right ) of page except header and footer.-->
<div id="main" class="shell">
    <%-- Include tag is used to import sidebar --%>
    <%@ include file="sidebar.jsp" %>
    <%-- div to create payment page. --%>
    <div id="content">
        <h3>Payment</h3>
        <div class="divInline">
            <section class="shipAddr">
                <p1>1. Shipping Address</p1>            
                <p2><c:out value='${theUser.name}'/>,<br/> 
                   <c:out value='${theUser.addr1}'/>,<br/> 
                    <c:out value='${theUser.addr2}'/></p2>
            </section>
            <section class="bottomborder"></section>
            <section class="shipAddr">
                <p1>2. Payment Details</p1>           
                <br/><br/>
                <p3>
                    <form id="checkout" method="post" action="Cart">
                        <input type="hidden" name="actionParam" id="actionParam" value="payment" />
                        <label>Cardholder Name:</label><input type="text" name="nameOfBuyer" required><br/>
                        <label>Credit Card Number:</label><input type="number" minlength="14" maxlength="16" name="ccNum" required><br/>
                        <label>CVV:</label><input type="number" name="cvv" min="100" max="999" required><br/>
                        <label>Expiry Date:</label><input type="month" name="expDate" min="2017-05" required><br/>
                        <label></label><input type="submit"  value="Pay" />
                        <!--onclick="javascript:validatePaymentForm('checkout', 'payment','Cart')"-->
                    </form>
                </p3>

            </section>

        </div>

        <div class="ckdiv2">
            <section class="Summary">
                
                <p1><b>Summary</b></p1> 
                <section class="bottomborder"></section>
                <br/>
                <p1><b>Total Amount to pay: <c:out value='${cart.totalAmountFormat}'/></b></p1> <br/>
                
                <section class="bottomborder"></section>
                <br/>
                <p2>Item</p2><p2>Quantity</p2><p2>Amount</p2>

                <c:forEach var="item" items="${cart.items}">
                    <br/><br/>

                    <span><c:out value='${item.product.title}'/></span><span><c:out value='${item.quantity}'/></span><span><c:out value='${item.totalCurrencyFormat}'/></span>
                </c:forEach>
                    <br/>
                   <section class="bottomborder"></section>
                   <br/>
                   <span></span><p2></p2>
                   <p1><b>Total: <c:out value='${cart.totalAmountFormat}'/></b></p1><br/>
                <br/>    


            </section>



        </div>

    </div>
    <!-- End Content -->
    <div class="cl">&nbsp;</div>
</div>
<!-- End Main -->   
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>