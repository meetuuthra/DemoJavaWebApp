<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>
<!-- Main -->
<div id="main" class="shell">
    <!--<div id="content">-->
        <!--<div class="products">-->
            <!--<h3>SignUp</h3>-->
            <%--Code to signup form --%>
            <section  class="signup_form">
                <h1>SignUp Form</h1>
                <section>
                    <span id="errorMessage"><c:out value="${msg}" /></span>
                    <form id="signUpForm" method="post"">
                        <input type="hidden" name="actionParam" value="create" />
                        <label>Name *</label>
                        <input type="text" name="name" required/> 
                        <span id="nError" class="inputErrorMessage"></span><br><br>
                        <label>Email *</label>
                        <input type="email" name="email" onchange="javascript:validateEmailAddr(this.value)" required/>
                        <span id="emailError" class="inputErrorMessage"></span> <br><br>
                        <label>Password *</label>
                        <input type="password" name="password" required/>
                        <span id="pError" class="inputErrorMessage"></span><br><br>
                        <label>Confirm Password *</label>
                        <input type="password" name="confirm_password" required /> 
                        <span id="cpError" class="inputErrorMessage"></span> <br><br>
                        <label>Address1 *</label>
                        <input type="text" name="addr1" required maxlength="60"/> 
                        <span id="addr1Error" class="inputErrorMessage"></span> <br><br>
                        <label>Address2 *</label>
                        <input type="text" name="addr2" maxlength="60" required /> 
                        <span id="addr2Error" class="inputErrorMessage"></span> <br>
                        <input type="button" value="Create Account" id="signup_button" onclick="javascript:validateSignUpForm('signUpForm', 'PageFlowController')">
                        <br>
                    </form>
                </section>
            </section>
        <!--</div>-->
    <!--</div>-->
</div>
<%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp" %>