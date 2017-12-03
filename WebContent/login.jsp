<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<!-- Main -->
<div id="main" class="shell">
    <!--    <div id="content">
            <div class="products">-->
    <!--<h3>Login</h3>-->
    <%-- Section to input login details --%>

    <section class="signup_form">
        <h1>Login Form</h1>
        <section>
            <%-- Code to create login form--%>
            <form id="myloginForm" method="post" action="#">

                <span id="errorMessage"><c:out value="${msg}" /></span>
                <br/>
                <input type="hidden" name="actionParam" value="login" />
                <input type="hidden" name="userButtonType" id="userButtonType" value="" />
                <label >Email Address *</label>
                <input type="email" name="email" onblur="javascript:validateEmailAddr(this.value)" onkeyup="javascript:validateEmailAddr(this.value)" required />
                <span id="emailError" class="inputErrorMessage"></span><br><br>
                <label >Password *</label>
                <input type="password" name="password" onblur="javascript:validatePassword(this.value)" required />
                <span id="pError" class="inputErrorMessage"></span><br>
                <label>&nbsp;</label>
                <%-- Code to call javascript method to go to Main page  --%>
                <input type="button" value="Log in" id="signup_button" onclick="javascript:submitLoginForm('myloginForm', 'Buyer', 'PageFlowController');">

                <%-- Code to call javascript method to go to Admin page  --%>
                <!--<input type="button" value="Admin" id="login_button" onclick="javascript:submitLoginForm('myloginForm', 'Admin','UserController');">-->
            </form>
                <br/><br/><br/>
            <%-- Code to go to Sign up for a new account  --%>
            <a href="#" id="sign_up_link" onclick="javascript:submitForms('linksForm', 'goToSignUp','PageFlowController')">Sign up for a new account</a>
        </section>
    </section>
    <!--        </div>
        </div>-->
</div>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>