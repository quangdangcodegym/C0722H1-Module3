<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Basic Tables | Zircos - Responsive Bootstrap 4 Admin Dashboard</title>
        <jsp:include page="/layout/admin/meta_css.jsp"></jsp:include>

    </head>

    <body data-layout="horizontal">

        <!-- Begin page -->
        <div id="wrapper">

            <!-- Navigation Bar-->
            <jsp:include page="/layout/admin/topnav.jsp"></jsp:include>
                <!-- End Navigation Bar-->

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

                        <div class="row">
                            <div class="col-sm-6">
                            </div>
                            <div class="col-sm-6">
                                <form action="/user" method="get" class="form-horizontal form-group row" >
                                    <div class="col-5" style="padding-right: 5px">
                                        <input class="form-control" type="text" placeholder="search.." name="q" value="${requestScope.q}">
                                    </div>
                                    <div class="col-5" style="padding-right: 5px">
                                        <select  name="idcountry"class="form-control" >
                                            <option value="-1">All</option>
                                            <c:forEach items="${applicationScope.listCountry}" var="country">
                                                <option <c:if test="${requestScope.idcountry == country.getId()}">selected</c:if> value="${country.getId()}">${country.getName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-2" style="padding-left: 0px;">
                                        <input style="padding-right: 12px" type="submit" value="Search" class="form-control bg-primary"/>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="table m-0">

                                    <thead class="thead-light">
                                    <tr>
                                        <th>#</th>
                                        <th>Username</th>
                                        <th>Email</th>
                                        <th>Country</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope.listUser}" var="user">
                                        <tr>
                                            <th scope="row">${user.getId()}</th>
                                            <td>
                                                <a href="/user?action=edit&id=${user.getId()}">${user.getName()}</a>
                                            </td>
                                            <td>${user.getEmail()}</td>
                                            <td>
                                                <c:forEach items="${applicationScope.listCountry}" var="country">
                                                    <c:if test="${user.getIdCountry()==country.getId()}">
                                                        ${country.getName()}
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                                <!-- end card-box -->
                            </div>
                            <!-- end col -->
                        </div>
                        <div>
                            <ul class="pagination pagination-split justify-content-end">

                                <c:if test="${currentPage != 1}">
                                    <li class="page-item disabled">
                                        <a href="/user?page=${currentPage - 1}&q=${requestScope.q}&idcountry=${requestScope.idcountry}" class="page-link"><i class="fa fa-angle-left"></i></a>
                                    </li>
                                </c:if>
                                <c:forEach begin="1" end="${noOfPages}" var="i">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <li class="page-item active">
                                                <a class="page-link">${i}</a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item">
                                                <a href="/user?page=${i}&q=${requestScope.q}&idcountry=${requestScope.idcountry}" class="page-link">${i}</a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:if test="${currentPage lt noOfPages}">
                                    <li class="page-item">
                                        <a href="/user?page=${currentPage + 1}&q=${requestScope.q}&idcategory=${requestScope.idcountry}" class="page-link"><i class="fa fa-angle-right"></i></a>
                                    </li>
                                </c:if>

                            </ul>

                        </div>
                        <!-- end row -->

                    </div>
                    <!-- end container-fluid -->

                </div>
                <!-- end content -->

                

                <!-- Footer Start -->
                <jsp:include page="/layout/admin/footer.jsp"></jsp:include>
                <!-- end Footer -->

            </div>

            <!-- ============================================================== -->
            <!-- End Page content -->
            <!-- ============================================================== -->

        </div>
        <!-- END wrapper -->

        <!-- Right Sidebar -->
        <jsp:include page="/layout/admin/rightbar.jsp"></jsp:include>

        <jsp:include page="/layout/admin/footer_js.jsp"></jsp:include>

    </body>

</html>