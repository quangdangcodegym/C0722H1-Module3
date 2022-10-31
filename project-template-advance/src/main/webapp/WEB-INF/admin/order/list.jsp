<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Order Pages</title>
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
                            <div class="col-sm-12">
                                <table class="table m-0">

                                    <thead class="thead-light">
                                    <tr>
                                        <th>#</th>
                                        <th>Name</th>
                                        <th>Phone</th>
                                        <th>Address</th>
                                        <th>Status</th>
                                        <th>Total</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope.orderDTOList}" var="orderDTO">
                                        <tr>
                                            <th scope="row">${orderDTO.getId()}</th>
                                            <td>
                                                <a href="/order?action=edit&id=${orderDTO.getId()}">${orderDTO.getName()}</a>
                                            </td>
                                            <td>${orderDTO.getPhone()}</td>
                                            <td>${orderDTO.getAddress()}</td>
                                            <td>
                                                <c:forEach items="${applicationScope.orderStatusList}" var="orderStatus">
                                                    <c:if test="${orderStatus.getId() == orderDTO.getIdStatus()}">
                                                        ${orderStatus.getName()}
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>${orderDTO.getTotal()}</td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                                <!-- end card-box -->
                            </div>
                            <!-- end col -->
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