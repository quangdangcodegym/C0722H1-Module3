<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Basic Tables | Zircos - Responsive Bootstrap 4 Admin Dashboard</title>
        <jsp:include page="/WEB-INF/admin/layout/meta_css.jsp"></jsp:include>

    </head>

    <body data-layout="horizontal">

        <!-- Begin page -->
        <div id="wrapper">

            <jsp:include page="/WEB-INF/admin/layout/topnav.jsp"></jsp:include>

            <!-- ============================================================== -->
            <!-- Start Page Content here -->
            <!-- ============================================================== -->

            <div class="content-page">
                <div class="content">

                    <!-- Start Content-->
                    <div class="container-fluid">

                        <!-- start page title -->
                        <div class="row">
                            <div class="col-12">
                                <div class="page-title-box">
                                    <div class="page-title-right">
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">Zircos</a></li>
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">Tables</a></li>
                                            <li class="breadcrumb-item active">Basic Tables</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Basic Tables</h4>
                                </div>
                            </div>
                        </div>
                        <!-- end page title -->
                        <div class="row justify-content-end">
                            <nav class="navbar navbar-light bg-light">
                                <form class="form-inline" action="user">
                                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" value="${requestScope.q}" name="q">
                                    <select name="idcountry" class="form-control mr-1" >
                                        <c:forEach items="${applicationScope.listCountry}" var="country">
                                            <c:choose>
                                                <c:when test="${country.getId()==requestScope.idcountry}">
                                                    <option
                                                            value="${country.getId()}">${country.getName()}
                                                    </option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option
                                                            value="${country.getId()}">${country.getName()}
                                                    </option>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:forEach>
                                    </select>
                                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                                </form>
                            </nav>
                        </div>
                        <div class="row">
                            <table class="table m-0">

                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Country</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="user" items="${requestScope.listUser}">
                                    <tr>
                                        <th scope="row"><c:out value="${user.getId()}"/></th>
                                        <td><c:out value="${user.getName()}"/></td>
                                        <td><c:out value="${user.getEmail()}"/></td>

                                        <c:forEach items="${applicationScope.listCountry}" var="country">
                                            <c:if test="${user.getIdCountry() == country.getId()}">
                                                <td>${country.getName()}</td>
                                            </c:if>
                                        </c:forEach>
                                        <td>
                                            <a href="/user?action=edit&id=${user.id}">
                                                <i class="mdi mdi-account-edit-outline"></i>
                                            </a>
                                            <a href="/user?action=delete&id=${user.id}">
                                                <i class="mdi mdi-delete"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- end row -->

                    </div>
                    <!-- end container-fluid -->

                </div>
                <!-- end content -->

                

                <jsp:include page="/WEB-INF/admin/layout/footer.jsp"></jsp:include>

            </div>

            <!-- ============================================================== -->
            <!-- End Page content -->
            <!-- ============================================================== -->
            <div class="row justify-content-center">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">Next</a></li>
                    </ul>

                </nav>
            </div>
        </div>
        <!-- END wrapper -->
        <jsp:include page="/WEB-INF/admin/layout/rightbar.jsp"></jsp:include>

        <jsp:include page="/WEB-INF/admin/layout/footer_js.jsp">
            <jsp:param name="page" value="list"/>
        </jsp:include>

    </body>

</html>