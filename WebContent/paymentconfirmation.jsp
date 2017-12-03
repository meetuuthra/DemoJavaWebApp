<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>

<!--div "main" contains the whole content(sidebar at left-for navigation and other contents at right ) of page except header and footer.-->
<div id="main" class="shell">
    <%-- Include tag is used to import sidebar --%>
    <%@ include file="sidebar.jsp" %>
    
<%-- div to display Order confirmation and order summary. --%>
    <div id="content">
        <h3>Order Confirmation</h3>
        <div class="divInline">

            <section class="shipAddr">
                <p1>Thank You for your order.</p1> <br/>
                Your order number is <c:out value='${orderConfrmNum}'/>. Kindly note this order no for your future<br/> communication and tracking your order.
                <br/>
                <h5><c:out value='${cnfrmEmail}'/></h5>
                <br/><b> Your order amount is <c:out value='${requestScope.cart.totalAmountFormat}'/>.</b>
            </section>
            <section class="bottomborder"></section>
            <section class="shipAddr">
                <p1>Your Shipping Address</p1>           
                
                <p2><c:out value='${theUser.name}'/>,<br/> 
                    <c:out value='${theUser.addr1}'/>,<br/> 
                    <c:out value='${theUser.addr2}'/></p2>
                <p3>

                </p3>

            </section>

        </div>
        <div class="ckdiv2">
            <section class="Summary">
                
                <p1><b>Order Summary</b></p1> 
                <section class="bottomborder"></section>
                <br/>
                <p2>Item</p2><p2>Quantity</p2><p2>Amount</p2>

                <c:forEach var="item" items="${requestScope.cart.items}">
                    <br/><br/>

                    <span><c:out value='${item.product.title}'/></span><span><c:out value='${item.quantity}'/></span><span><c:out value='${item.totalCurrencyFormat}'/></span>
                </c:forEach>
                    <br/>
                   <section class="bottomborder"></section>
                   <br/>
                   <span></span><p2></p2>
                   <p1><b>Total: <c:out value='${requestScope.cart.totalAmountFormat}'/></b></p1><br/>
                <br/>    
            </section>
        </div>
    </div>
    <div class="cl">&nbsp;</div>
</div>
<!-- End Main -->   
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>