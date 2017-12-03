<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<!--div "main" contains the whole content(sidebar at left-for navigation and other contents at right ) of page except header and footer.-->
<div id="main" class="shell">
    <%-- Include tag is used to import sidebar --%>
    <c:choose>
        <c:when test="${theUser != null}">
            <%@ include file="sidebar.jsp" %>
        </c:when>
        <c:when test="${theAdmin != null}"><%@ include file="adminsidebar.jsp" %></c:when>
        <c:otherwise><%@ include file="sidebar.jsp" %></c:otherwise>

    </c:choose>
    <%-- div to create contactus page --%>
    <div id="content">
        <div class="products">
            <h3>Contact Us</h3>
            <section class="addcategSection">
                <br/><p1>Email: abcbookstorenbad@gmail.com</p1>
                <p1>Phone: +1-980-789-0987</p1>
                <br/>
                <p2>
                    Should you experience any problems or wish to share any comments, suggestions, or feedback for this site, please let us know using the form below.<br>

                    Your email address will only be used to reply to your inquiry. We will never send you unsolicited email or share your address with any third-party.
                </p2>

                <br/><br/>
                <form method="post" id="contact-form" role="form" action="PageFlowController">
                    <div>
                        <input type="hidden" name="actionParam" value="query" />

                        <div >
                            <label >Your Name*</label>
                            <input type="text"  placeholder="Full Name" name="name" required>
                        </div>

                        <div >
                            <label >Email Address*</label>
                            <input type="email"  placeholder="email address" name="email" required>
                        </div>

                        <div >
                            <label >Comments*</label>
                            <textarea  name="comments" rows="5" placeholder="Type your message here...Max length 300." required maxlength="300"></textarea>
                        </div>

                        <div><br>
                            <label >What is 2 + 2</label>

                            <input type="text"  name="sum">
                        </div>

                        <div>
                            <input type="submit" value="Send Email" class="submitButtonClass" id="contact-form-btn" />
                        </div>
                    </div>

                </form>

            </section>
            <!-- End Content -->
            <div class="cl">&nbsp;</div>
        </div></div></div>
    <!-- End Main -->   
    <%-- Include tag is used to import footer page --%>
    <%@ include file="footer.jsp" %>      