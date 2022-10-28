<%@ page import="java.util.Arrays" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Coron-checkout</title>
        <meta name="description" content="">
        <jsp:include page="/layout/frontend/meta_css.jsp"></jsp:include>
    </head>
    <body>
            <!-- Add your site or application content here -->
            
            <!--pos page start-->
            <div class="pos_page">
                <div class="container">
                    <!--pos page inner-->
                    <div class="pos_page_inner">  
                       <!--header area -->
                        <jsp:include page="/layout/frontend/header_area.jsp"></jsp:include>
                        <!--header end -->
                         <!--breadcrumbs area start-->
                        <div class="breadcrumbs_area">
                            <div class="row">
                                <div class="col-12">
                                    <div class="breadcrumb_content">
                                        <ul>
                                            <li><a href="index.html">home</a></li>
                                            <li><i class="fa fa-angle-right"></i></li>
                                            <li>checkout</li>
                                        </ul>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--breadcrumbs area end-->


                        <!--Checkout page section-->
                        <div class="Checkout_section">

                            <div class="checkout_form">
                                    <div class="row">
                                        <div class="col-lg-6 col-md-6">
                                            <form action="#">
                                                <h3>Billing Details</h3>
                                                <div class="row">

                                                    <div class="col-12 mb-30">
                                                        <label>Fullname</label>
                                                        <input type="text" id="coFullName">
                                                    </div>
                                                    <div class="col-12 mb-30">
                                                        <label>Phone</label>
                                                        <input type="text" id="coPhone">
                                                    </div>
                                                    <div class="col-12 mb-30">
                                                        <label>Street address  <span>*</span></label>
                                                        <input placeholder="House number and street name" id="coAddress" type="text">
                                                    </div>
                                                </div>
                                            </form>    
                                        </div>
                                        <div class="col-lg-6 col-md-6">
                                            <form action="#">    
                                                <h3>Your order</h3> 
                                                <div class="order_table table-responsive mb-30">
                                                    <table>
                                                        <thead>
                                                            <tr>
                                                                <th>Product</th>
                                                                <th>Total</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                        </tbody>
                                                        <tfoot>


                                                        </tfoot>
                                                    </table>     
                                                </div>
                                                <div class="payment_method">
                                                    <div class="order_button">
                                                        <button type="button" >CHECKOUT</button>
                                                    </div>    
                                                </div> 
                                            </form>         
                                        </div>
                                    </div> 
                                </div>        
                        </div>
                        <!--Checkout page section end-->

                    </div>
                    <!--pos page inner end-->
                </div>
            </div>
            <!--pos page end-->
            
            <!--footer area start-->
            <jsp:include page="/layout/frontend/footer_area.jsp"></jsp:include>
            <!--footer area end-->
            
            
            <script>
                window.onload = ()=>{
                    if(getDataLocalStorage(app.KEY_CART)!=null){
                        cart = getDataFromCartInStorage(getDataLocalStorage(app.KEY_CART));
                    }else{
                        cart = new Cart();
                        cart.setIdCart(1);
                        cart.setCartItems([])
                        setDataLocalStorage(app.KEY_CART, cart);
                    }
                    console.log(cart)
                    let orderTableHtml = document.querySelector(".order_table");
                    renderOrderTableCheckoutPage(orderTableHtml, cart);
                    let btnCheckoutProcess = document.querySelector(".payment_method > .order_button > button");
                    btnCheckoutProcess.addEventListener("click", ()=>{
                        cart.name = document.getElementById("coFullName").value;
                        cart.address = document.getElementById("coAddress").value;
                        cart.phone = document.getElementById("coPhone").value;
                        handleCheckoutProcess(cart);
                    });
                }
            </script>
            
      
		
		<!-- all js here -->
            <jsp:include page="/layout/frontend/footer_js.jsp"></jsp:include>
    </body>
</html>
