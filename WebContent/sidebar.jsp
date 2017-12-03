<%@page import="com.bs.constants.CommonConstants" %>
<!-- Sidebar -->

<div id="sidebar">
    <ul class="searchPanel">  
        <h4>Search Tunes</h4>
        <ul>
            <form id="searchForm" method="POST">
                <input type="hidden" id="actionParam" name="actionParam" value="">
                <input type="search" id="search" class="searchbox" name="searchbox" placeholder="Search music by name..." />

                <select name="category" class="searchbox">
                    <option value="NoSelection" selected>Select A Category</option>
                    <option value="All" >All</option>
                    <c:forEach var="categ" items="${categList}" varStatus="loop">
                        <option value="${categ.categCode}">${categ.categName}</option>
                    </c:forEach>    
                </select>


                <input type="button" value="Search" onclick="javascript:searchForm('searchForm', '<%=CommonConstants.SEARCH_BOX%>', 'PageFlowController')">
            </form>
        </ul>


    </ul>   
    <ul class="categories">


        <li>
            <h4>Tune Categories</h4>
            <ul>
            
                 <c:forEach var="cl" items="${categList}">
                    <li>
                        <form id="userSBForm${cl.categCode}" method="post">
                            <input type="hidden" name="actionParam" id="actionParam" value="" />
                            <input type="hidden" name="<%=CommonConstants.CATEGCODE%>" value="${cl.categCode}" />
                            <input type="hidden" name="categName" value="${cl.categName}">
                        </form>
                        <a href="#"  onclick="javascript:submitForms('userSBForm${cl.categCode}', '<%=CommonConstants.SEARCH_BY_CATEG%>', 'PageFlowController')">
                            <c:if test="${requestScope.categCodeSelected eq cl.categCode}"> 
                                <span class="currentLink"><c:out value="${cl.categName}"></c:out></span>
                            </c:if>  
                            <c:if test="${requestScope.categCodeSelected ne cl.categCode}"> 
                                <c:out value="${cl.categName}"></c:out>
                            </c:if> 
                        </a>
                    </li>
                </c:forEach> 
                <!--						<li><a href="#">Lorem ipsum dolor</a></li>
                                                                <li><a href="#">Morbi eget</a></li>
                                                                <li><a href="#">Nulla egestas</a></li>
                                                                <li><a href="#">Curabitur venenatis</a></li>
                                                                <li><a href="#">Ut dictum purus</a></li>
                                                                <li><a href="#">Curabitur imperdiet</a></li>
                                                                <li><a href="#">Aliquam elementum</a></li>-->
            </ul>
        </li>
        <!--        <li>
                                                        <h4>Authors</h4>
                                                        <ul>
                                                                <li><a href="#">Lorem ipsum dolor</a></li>
                                                                <li><a href="#">Morbi eget</a></li>
                                                                <li><a href="#">Nulla egestas</a></li>
                                                                <li><a href="#">Curabitur venenatis</a></li>
                                                                <li><a href="#">Ut dictum purus</a></li>
                                                                <li><a href="#">Curabitur imperdiet</a></li>
                                                                <li><a href="#">Lorem ipsum dolor</a></li>
                                                                <li><a href="#">Morbi eget</a></li>
                                                                <li><a href="#">Nulla egestas</a></li>
                                                                <li><a href="#">Curabitur venenatis</a></li>
                                                                <li><a href="#">Ut dictum purus</a></li>
                                                                <li><a href="#">Curabitur imperdiet</a></li>
                                                        </ul>
                                                </li>-->
    </ul>
</div>
<!-- End Sidebar -->


