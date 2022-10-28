<%@ page import="java.util.Arrays" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Edited Order</title>
        <jsp:include page="/layout/admin/meta_css.jsp">
            <jsp:param name="page" value="create"/>
        </jsp:include>

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
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">Forms</a></li>
                                            <li class="breadcrumb-item active">Form elements</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Form elements</h4>
                                </div>
                            </div>
                        </div>
                        <!-- end page title -->

                        <div class="row">
                            <div class="col-sm-12">
                                <c:if test="${!requestScope.errors.isEmpty()&&requestScope.errors!=null }">
                                    <div class="alert alert-warning" role="alert">
                                        <c:forEach items="${requestScope.errors}" var="item">
                                            ${item} <br>
                                        </c:forEach>
                                    </div>
                                </c:if>

                                <c:if test="${requestScope.message!=null}">
                                    <%
                                        String sMessage = request.getAttribute("message").toString();
                                    %>
                                    <script>
                                        let message = '<%= sMessage%>';
                                        window.onload = ()=>{
                                            toastr["success"](message)
                                        }
                                    </script>
                                </c:if>

                                <form class="form-horizontal" action="/order?action=edit&id=${requestScope.orderDTO.getId()}" method="post">
                                    <div class="form-group row">
                                        <label class="col-md-2 control-label">Username</label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" value="${requestScope.orderDTO.getName()}" name="name">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-2 control-label" for="example-phone">Phone</label>
                                        <div class="col-md-10">
                                            <input id="example-phone" name="phone" class="form-control" value="${requestScope.orderDTO.getPhone()}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-2 control-label" for="example-address">Address</label>
                                        <div class="col-md-10">
                                            <input id="example-address" name="address" class="form-control" value="${requestScope.orderDTO.getAddress()}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-2 control-label" for="example-total">Total</label>
                                        <div class="col-md-10">
                                            <input id="example-total" name="total" class="form-control" value="${requestScope.orderDTO.getTotal()}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-2 control-label">Status</label>
                                        <div class="col-md-10">
                                            <select name="orderstatus" class="form-control">
                                                <c:forEach items="${applicationScope.orderStatusList}" var="orderStatus">
                                                    <option <c:if test="${orderStatus.getId() == requestScope.orderDTO.getIdStatus()}">selected</c:if> value="${orderStatus.getId()}">${orderStatus.getName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <button type="submit" class="btn btn-primary waves-effect waves-light btn-md">
                                            Edit
                                        </button>

                                    </div>
                                </form>
                                <table class="table m-0">

                                    <thead class="thead-light">
                                    <tr>
                                        <th>#</th>
                                        <th>Product</th>
                                        <th>Quantity</th>
                                        <th>Price</th>
                                        <th>Total</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope.orderDTO.getOrderItemDTOList()}" var="orderItemDTO">
                                        <tr>
                                            <th scope="row">${orderItemDTO.getId()}</th>
                                            <td>${orderItemDTO.getProduct().getName()}</td>
                                            <td>${orderItemDTO.getQuantity()}</td>
                                            <td>${orderItemDTO.getPrice()}</td>
                                            <td>${orderItemDTO.getPrice()*orderItemDTO.getQuantity()}</td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>

                            </div>
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

        <jsp:include page="/layout/admin/footer_js.jsp">
            <jsp:param name="page" value="create"/>
        </jsp:include>

    </body>

</html>