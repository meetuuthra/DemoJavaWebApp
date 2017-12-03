<%-- taglib to use jstl --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- page import to refer java file which contains constant variables --%>
<%@page import="com.bs.constants.CommonConstants" %>
<!--div to display sidebar for admin-->
<div id="sidebar">
    <ul class="categories">
        <li>
            <h4>Add/Update</h4>
            <ul>
                <li><a href="#"  onclick="javascript:submitForms('linksForm', '<%=CommonConstants.GOTO_ADD_CATEGORY%>', 'PageFlowController')">
                        <span <c:if test="${adminLinkSel eq 'gotoAddCateg'}"> class="currentLink" </c:if>>Add New Category</span></a></li>
                <li><a href="#"  onclick="javascript:submitForms('linksForm', '<%=CommonConstants.GOTO_EDIT_CATEGORY%>', 'PageFlowController')">
                        <span <c:if test="${adminLinkSel eq 'gotoEditCateg'}">class="currentLink"</c:if>>View/Update Category</span></a></li>
                <li><a href="#"  onclick="javascript:submitForms('linksForm', '<%=CommonConstants.GOTO_ADD_TUNE%>', 'PageFlowController')">
                        <span <c:if test="${adminLinkSel eq 'gotoAddBook'}">class="currentLink"</c:if>>Add New Book</span></a></li>
                <li><a href="#"  onclick="javascript:submitForms('linksForm', '<%=CommonConstants.GOTO_EDIT_TUNE%>', 'PageFlowController')">
                        <span <c:if test="${adminLinkSel eq 'gotoEditBook'}">class="currentLink"</c:if>>View/Update Book Details</span></a></li>
                </ul>
            </li>
        </ul>

        <ul class="categories">
            <li>
                <h4>Categories</h4>
                <ul>
                <c:forEach var="cl" items="${categList}">
                    <li>
                        <form id="adminSBForm<c:out value='${cl.categCode}'/>" method="post">
                            <input type="hidden" name="actionParam" id="actionParam" value="" />
                            <input type="hidden" name="categName" value="<c:out value='${cl.categName}'/>">
                            <input type="hidden" name="<%=CommonConstants.CATEGCODE%>" value="<c:out value='${cl.categCode}'/>" />
                        </form>
                        <a href="#"  onclick="javascript:submitForms('adminSBForm<c:out value='${cl.categCode}'/>', '<%=CommonConstants.GOTO_EDIT_TUNE%>', 'PageFlowController')">
                            <c:if test="${requestScope.categCodeSelected eq cl.categCode}"> 
                                <span class="currentLink"><c:out value="${cl.categName}"></c:out></span>
                            </c:if>  
                            <c:if test="${requestScope.categCodeSelected ne cl.categCode}"> 
                                <span><c:out value="${cl.categName}"></c:out></span>
                            </c:if>    
                        </a>
                    </li>
                </c:forEach>

            </ul>
        </li>
    </ul>
</div>


