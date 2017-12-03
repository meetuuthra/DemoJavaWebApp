/* Login.jsp:
 * Function to validate login credentials and to submit login form.
 *  */
function submitLoginForm(formId, buttonType, url) {
    var formObj = document.getElementById(formId);
    document.getElementById("userButtonType").value = buttonType;
    var bPass = true;
    var bEmail = validateEmailAddr(formObj.email.value);
    // alert(bEmail);
    if (formObj.password == null || formObj.password.value.length == 0)
    {
        document.getElementById("pError").innerHTML = "Password cannot be empty.";
        bPass = false;

    } else {
        document.getElementById("pError").innerHTML = "";
    }

    if (bEmail && bPass)
    {
        formObj.action = url;
        formObj.submit();
    }
}

//function to validate email address format.
function validateEmail(email) {
    //alert(email);
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

// function called when emailId text box changed(onchange)on Login page and signup page.
function validateEmailAddr(email) {
    if (!validateEmail(email)) {
        document.getElementById("emailError").innerHTML = "Email id is not valid.";
        return false;
    } else {
        document.getElementById("emailError").innerHTML = "";
        return true;
    }
}
//function to display message for empty password on login page.
function validatePassword(password) {
    if (password == null || password.length == 0)
    {
        document.getElementById("pError").innerHTML = "Password cannot be empty.";

    } else {
        document.getElementById("pError").innerHTML = "";
    }
}

//Signup.jsp: validate email id and password length.
function validateSignUpForm(formId, url) {
    var formObj = document.getElementById(formId);
    var bName = true, bPass = true, bCpass = true;
    var bEmail = validateEmailAddr(formObj.email.value);
    if (formObj.name == null || formObj.name.value.length == 0)
    {
        document.getElementById("nError").innerHTML = "Enter username.";
        bName = false;
    } else {
        document.getElementById("nError").innerHTML = "";
    }

    if (formObj.password == null || formObj.password.value.length == 0)
    {
        document.getElementById("pError").innerHTML = "Enter a password.";
        document.getElementById("cpError").innerHTML = "";
        bPass = false;
    } else if (formObj.password.value.length < 8) {
        document.getElementById("pError").innerHTML = "Password should be atleast 8 characters long.";
        document.getElementById("cpError").innerHTML = "";
        bPass = false;
    } else {
        document.getElementById("pError").innerHTML = "";
        if (formObj.confirm_password == null || formObj.confirm_password.value.length == 0 || formObj.confirm_password.value != formObj.password.value)
        {
            document.getElementById("cpError").innerHTML = "Password doesn't match.";
            bCpass = false;
        } else {
            document.getElementById("cpError").innerHTML = "";
            bCpass = true;
        }
    }
    var aPass = false;
    if (formObj.addr1.value.length == 0) {
        document.getElementById("addr1Error").innerHTML = "Address is a required field.";

    } else {
        var aPass = true;
        document.getElementById("addr1Error").innerHTML = "";
    }

    if (bName && bEmail && bPass && bCpass && aPass)
    {
        formObj.action = url;
        formObj.submit();
    }

}

//Header.jsp: To submit the form and set hidden parameter value.
function submitActionForm(formId, paramVal) {
    var formObj = document.getElementById(formId);
    formObj.actionParam.value = paramVal;
    formObj.submit();
}
// Used in multiple JSPs to submit form to set hidden param value and to go to servlet url passed.
function submitStudyControllerForm(formId, paramVal, actionUrl) {
    var formObj = document.getElementById(formId);
    formObj.actionParam.value = paramVal;
    formObj.action = actionUrl;
    formObj.submit();
}
// Used in multiple JSPs to submit form to set hidden param value and to go to servlet url passed.
function submitForms(formId, paramVal, actionUrl) {
    var formObj = document.getElementById(formId);
    formObj.actionParam.value = paramVal;
    formObj.action = actionUrl;
    formObj.submit();
}

// function to validate search box used for searching books.
function searchForm(formId, paramVal, actionUrl) {
//    alert(formId);
    var formObj = document.getElementById(formId);
//    alert(formObj.category.value);
    if (formObj.category.value == "NoSelection") {
        alert("Please select a category to search.");
        return false;
    }
    formObj.actionParam.value = paramVal;
    formObj.action = actionUrl;
    formObj.submit();
}



// Addcategory.jsp: to upload image.
function ImageUploadClick(formId, elName) {
    var elem = document.getElementById(elName);
    if (elem && document.createEvent) {
        var evt = document.createEvent("MouseEvents");
        evt.initEvent("click", true, false);
        elem.dispatchEvent(evt);
    }
}

function setImgSource(value) {
    var indx = value.lastIndexOf("\\");
    document.getElementById("imagePreview").src = "images\\sidebarimages\\" + value.substr(indx + 1);
}

//checkout.jsp : payment form validation
//function validatePaymentForm(formId, paramVal, actionUrl) {
//    var formObj = document.getElementById(formId);
//    formObj.actionParam.value = paramVal;
//    var name = formObj.nameOfBuyer.value;
//    var ccnum = formObj.ccNum.value;
//    var cvv = formObj.cvv.value;
//    var expDate = formObj.expDate.value;
//    if ((name.length < 1) || (ccnum.length < 1) || (cvv.length < 1) || (expDate.length < 1)) {
//        alert("All payment fields are required fields.");
//        return false;
//    } else if (formObj.ccNum.value.length < 16)
//    {
//        alert("please enter 16 digit credit card number.");
//        return false;
//    } else {
//        formObj.action = actionUrl;
//        formObj.submit();
//    }
//}