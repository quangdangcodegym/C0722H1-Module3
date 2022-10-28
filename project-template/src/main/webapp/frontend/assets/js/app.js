
const app = {
    KEY_CART: "key_cart"
}
class Product{
    constructor(id, name, imgUrl, price) {
        this.idProduct = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.price = price;
    }

    getIdProduct() {
        return this.idProduct;
    }

    setIdProduct(idProduct) {
        this.idProduct = idProduct;
    }
    setName(name) {
        this.name = name;
    }

    getName() {
        return this.name;
    }

    setImgUrl(imgUrl) {
        this.imgUrl = imgUrl;
    }

    getImgUrl() {
        return this.imgUrl;
    }

    setPrice(price) {
        this.price = price;
    }

    getPrice() {
        return this.price;
    }
}

class CartItem{
    constructor(idCartItem, product, quantity, price) {
        this.idCartItem  = idCartItem;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    setIdCartItem(idCartItem) {
        this.idCartItem = idCartItem;
    }

    getIdCartItem() {
       return this.idCartItem;
    }
    setProduct(product){
        this.product = product;
    }

    getProduct() {
        return this.product;
    }

    setQuantity(quantity) {
        this.quantity = quantity;
    }

    getQuantity() {
        return this.quantity;
    }

    setPrice(price) {
        this.price = price;
    }

    getPrice() {
        return this.price;
    }
}
class Cart{

    constructor(idCart, fullName, address, phone, email,total, cartItems) {
        this.idCart = idCart;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.total = total;
        this.cartItems = cartItems;
    }
    setCartItems(cartItems){
        this.cartItems = cartItems;
    }

    getCartItems() {
        return this.cartItems;
    }
    setIdCart(idCart){
        this.idCart = idCart;
    }

    getIdCart() {
        return this.idCart;
    }
    setFullName(fullName)
    {
        this.fullName = fullName;
    }
    getFullName(){
        return this.fullName;
    }
    setAddress(address){
        this.address = address;
    }
    getAddress(){
        return this.address;
    }
    setEmail(email){
        this.email = email;
    }
    getEmail(){
        return this.email;
    }
    setPhone(phone){
        this.phone = phone;
    }
    getPhone(){
        return this.phone;
    }
    setTotal(total){
        this.total = total;
    }
    getTotal(){
        return this.total;
    }
    getCartItems(){
        if(this.cartItems ==null){
            return null;
        }
        return  this.cartItems;
    }

    getCartItemByIdProduct(idProduct) {
        if(this.cartItems ==null){
            return null;
        }else{
            for(let i=0;i<this.cartItems.length;i++){
                if(this.cartItems[i].getProduct().getIdProduct() == idProduct){
                    return this.cartItems[i];
                }
            }
        }

    }

    addProductToCart(product){
        let cartItem = cart.getCartItemByIdProduct(product.getIdProduct());
        if(cartItem==null){
            cartItem = new CartItem(product.getIdProduct(), product, 1, product.getPrice());
            this.cartItems.push(cartItem);
        }else{
            for(let i=0;i<this.cartItems.length;i++){
                if(this.cartItems[i].getProduct().getIdProduct() == product.getIdProduct()){
                    return this.cartItems[i].setQuantity(this.cartItems[i].getQuantity() +1);
                }
            }
        }
        this.updateTotal();
    }
    updateProductToCart(product, number){
        for(let i=0;i<this.cartItems.length;i++){
            if(this.cartItems[i].product.idProduct == product.idProduct){
                return this.cartItems[i].setQuantity(number);
            }
        }
        this.updateTotal();
    }

    removeProductFromCart(product) {
        for(let i=0;i<this.cartItems.length;i++){
            if(this.cartItems[i].getIdCartItem() == product.getIdProduct()){
                this.cartItems.splice(i, 1);
                this.updateTotal();
                return;
            }
        }

    }

    updateTotal() {
        let total = 0;
        for(let i=0;i<this.cartItems.length;i++){
            total += this.cartItems[i].getQuantity()*this.cartItems[i].getPrice();
        }
        this.setTotal(total);
    }

}


let cart;
function getDataLocalStorage(key){
    return JSON.parse(localStorage.getItem(key));

}
function getDataFromCartInStorage(cartData){
    let cart = Object.assign(new Cart(), JSON.parse(data));
    let cartItems = [];
    for(let i=0;i<cart.getCartItems().length;i++){
        let product = Object.assign(new Product(), cart.getCartItems()[i].product);
        let cartItem = Object.assign(new CartItem(), cart.getCartItems()[i]);
        cartItem.setProduct(product);
        cartItems.push(cartItem);
    }
    cart.setCartItems(cartItems)
    return cart;
}
function setDataLocalStorage(key, value) {
    localStorage.setItem(key, JSON.stringify(value));
}


function handleSumaryCartClick(){
    ///*mini cart slideToggle*/
    $(".shopping_cart").on("click", function() {
        if(cart.getCartItems().length!=0){
            $('.mini_cart').slideToggle('medium');
        }

    });
}
function renderSumaryCart(cart){
    let sumaryCartHtml = document.querySelector(".shopping_cart > a");
    let value = " " + cart.getCartItems().length +" Items - " + cart.getTotal() +"vnd";
    let str = `
    <i class="fa fa-shopping-cart"></i>${value}<i class="fa fa-angle-down"></i>`
    sumaryCartHtml.innerHTML = str;
}
function renderMiniCart(cart){
    let cartHtml = document.querySelector(".mini_cart");
    /**
     <div class="cart_item">
     <div class="cart_img">
     <a href="#"><img src="/frontend/assets\img\cart\cart.jpg" alt=""></a>
     </div>
     <div class="cart_info">
     <a href="#">lorem ipsum dolor</a>
     <span class="cart_price">$115.00</span>
     <span class="quantity">Qty: 1</span>
     </div>
     <div class="cart_remove">
     <a title="Remove this item" href="#"><i class="fa fa-times-circle"></i></a>
     </div>
     </div>
     <div class="cart_item">
     <div class="cart_img">
     <a href="#"><img src="/frontend/assets\img\cart\cart2.jpg" alt=""></a>
     </div>
     <div class="cart_info">
     <a href="#">Quisque ornare dui</a>
     <span class="cart_price">$105.00</span>
     <span class="quantity">Qty: 1</span>
     </div>
     <div class="cart_remove">
     <a title="Remove this item" href="#"><i class="fa fa-times-circle"></i></a>
     </div>
     </div>
     <div class="shipping_price">
     <span> Shipping </span>
     <span>  $7.00  </span>
     </div>
     <div class="total_price">
     <span> total </span>
     <span class="prices">  $227.00  </span>
     </div>
     <div class="cart_button">
     <a href="checkout.jsp"> Check out</a>
     </div>
     */

    let cartItems = cart.getCartItems();
    let cartItemsHtml = cartItems.map((cartItem)=>{
        return `<div class="cart_item" id="idcartitem${cartItem.getIdCartItem()}">
                <div class="cart_img">
                    <a href="#"><img src="${cartItem.getProduct().getImgUrl()}" alt=""></a>
                </div>
                <div class="cart_info">
                    <a href="#">lorem ipsum dolor</a>
                    <span class="cart_price">${cartItem.getPrice()}</span>
                    <span class="quantity">${cartItem.getQuantity()}</span>
                </div>
                <div class="cart_remove">
                    <a title="Remove this item" href="#"><i class="fa fa-times-circle"></i></a>
                </div>
            </div>`
    })

    let totalHtml =
        `<div class="total_price">
            <span> total </span>
            <span class="prices">  ${cart.getTotal()}  </span>
        </div>`;
    let checkoutHtml =
        `<div class="cart_button">
         <a href="checkout.jsp"> Check out</a>
         </div>`;
    cartHtml.innerHTML = cartItemsHtml.join("") + totalHtml + checkoutHtml;
    handleRemoveCartItem();

}
function handleRemoveCartItem() {
        let cartItemsHtml = document.querySelectorAll(".cart_remove > a > i.fa-times-circle");
        cartItemsHtml.forEach((cartItem)=>{
            cartItem.addEventListener("click", ()=>{
                let cartItemParentHtml = cartItem.parentNode.parentNode.parentNode;
                let idCartItem = cartItemParentHtml.id.substring(10, cartItemParentHtml.id.length);

                let p = new Product();
                p.setIdProduct(idCartItem)
                cart.removeProductFromCart(p);
                renderMiniCart(cart);
                renderSumaryCart(cart);
                handleSumaryCartClick();
                setDataLocalStorage(app.KEY_CART, cart);
            });

        })
}


function handleOnchangeNumber(cart) {
    let numbersHtml = document.querySelectorAll(".cart_page .product_quantity > input");
    numbersHtml.forEach((numberItem)=>{
        numberItem.addEventListener("change", ()=>{
            let parentNumberHtml = numberItem.parentNode.parentNode;
            let number = numberItem.value;
            if(number<0){
                alert("Số phải lớn hơn không!")
            }else{
                let product = new Product();
                let idProduct = parentNumberHtml.id.substring(10, parentNumberHtml.id.length);
                product.idProduct = Number(idProduct);
                cart.updateProductToCart(product, number);
                setDataLocalStorage(app.KEY_CART, cart);
                let cartPageHtml  = document.querySelector(".cart_page tbody");
                renderCartPage(cartPageHtml, cart);
            }
        })

    })
}

function renderCartPage(cartPageHtml, cart){
        let cartItemsHtml = cart.cartItems.map((cartItem)=>{
            return `
                <tr id="idCartItem${cartItem.idCartItem}">
                   <td class="product_remove"><a href="#"><i class="fa fa-trash-o"></i></a></td>
                    <td class="product_thumb"><a href="#"><img src="${cartItem.product.imgUrl}" alt=""></a></td>
                    <td class="product_name"><a href="#">${cartItem.product.name}</a></td>
                    <td class="product-price">${cartItem.price}</td>
                    <td class="product_quantity"><input min="0" max="100" value="${cartItem.quantity}" type="number"></td>
                    <td class="product_total">${cartItem.price*cartItem.quantity}</td>
                </tr>
            `
        })
        cartPageHtml.innerHTML = cartItemsHtml.join("");
        document.querySelector(".cart_subtotal > .cart_subtotal").innerText = cart.total;
        handleOnchangeNumber(cart);
}


function renderOrderTableCheckoutPage(orderTableHtml, cart){
        let orderTableTBody = orderTableHtml.querySelector("tbody");
        let innerOrderTableTBody = cart.cartItems.map((cartItem)=>{
            return `
                <tr>
                    <td> ${cartItem.product.name} <strong> × ${cartItem.quantity}</strong></td>
                    <td> ${cartItem.price*cartItem.quantity}</td>
                </tr>
            `
        })
        orderTableTBody.innerHTML = innerOrderTableTBody.join("");
        let orderTableTFoot = orderTableHtml.querySelector("tfoot");
        let innerOrderTableTFoot = `
            <tr class="order_total">
                <th>Order Total</th>
                <td><strong>${cart.total}</strong></td>
            </tr>
        `;
        orderTableTFoot.innerHTML = innerOrderTableTFoot;

}

function handleCheckoutProcess(cart){
    console.log("handleCheckoutProcess......");
    console.log(cart);
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/cart?action=order");

    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onload = () => console.log(xhr.responseText);
    let orderItems = [];
    for(let i=0;i<cart.cartItems.length;i++){
        let objProduct = {
            id: cart.cartItems[i].product.idProduct
        }
        orderItems.push({
            product: objProduct
        })
    }

    let objData = {
        name: cart.name,
        phone: cart.phone,
        address: cart.address,
        total: cart.total,
        orderItemDTOList: orderItems
    }
    console.log(objData);
    xhr.send(JSON.stringify(objData));
}





