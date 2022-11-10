<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Form Elements | Zircos - Responsive Bootstrap 4 Admin Dashboard</title>
        <jsp:include page="/WEB-INF/layout/meta_css.jsp"></jsp:include>

    </head>

    <body data-layout="horizontal">

        <!-- Begin page -->
        <div id="wrapper">

            <jsp:include page="/WEB-INF/layout/topnav.jsp"></jsp:include>

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
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">Forms</a></li>
                                            <li class="breadcrumb-item active">Form elements</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Form elements</h4>
                                </div>
                            </div>
                        </div>
                        <!-- end page title -->

                        <div class="row justify-content-center">

                                <div class="col-8">
                                    <form method="post">
                                        <div class="row mb-2">
                                            <div class="col-2">
                                                <label>Name: </label>
                                            </div>
                                            <div class="col-10">
                                                <input class="form-control" type="text" name="name">
                                            </div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-2">
                                                <label>Email: </label>
                                            </div>
                                            <div class="col-10">
                                                <input class="form-control" type="text" name="email">
                                            </div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-2">
                                                <label>Country: </label>
                                            </div>
                                            <div class="col-10">
                                                <select name="idcountry" class="form-control">
                                                    <c:forEach items="${applicationScope.listCountry}" var="country">
                                                        <option
                                                                value="${country.getId()}">${country.getName()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row mb-2">

                                            <div class="col-10 offset-2">
                                                <button class="btn btn-success">Create</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                        </div>
                        <!-- end row -->

                    </div>
                    <!-- end container-fluid -->

                </div>
                <!-- end content -->



                <jsp:include page="/WEB-INF/layout/footer.jsp"></jsp:include>

            </div>

            <!-- ============================================================== -->
            <!-- End Page content -->
            <!-- ============================================================== -->

        </div>
        <!-- END wrapper -->

        <jsp:include page="/WEB-INF/layout/rightbar.jsp"></jsp:include>

        <jsp:include page="/WEB-INF/layout/footer_js.jsp">
            <jsp:param name="page" value="create"/>
        </jsp:include>

    </body>

</html>