DROP PROCEDURE IF EXISTS sp_addToCart;
delimiter //
create procedure sp_addToCart(
	in sSessionId varchar(100),
    in sTokenId varchar(100),
    in sStatus SMALLINT,
    in sProductId bigint,
    in sQuantity SMALLINT,
    in sPrice float,
    out sMessage varchar(200)
)
begin
	declare pCartId bigint;
    declare pAmount SMALLINT;
    declare pTempTotal SMALLINT;
    if(not exists (SELECT * FROM cart where sessionId  = sSessionId)) then
		-- Chưa tồn tại thì thêm 1 dòng vào bảng cart
		INSERT INTO `cart` (`userId`,`sessionId`, `token`, `status`, `createdAt`) 
		VALUES ('1', sSessionId, sTokenId, '1', '2022-10-20 00:00:00');
        set pCartId = (SELECT max(id) from cart);
	else
		-- Nếu đã tồn tại thì lấy idCart ra
		set pCartId = (SELECT id FROM cart where sessionId  = sSessionId);
	end if;
    -- if(exists (SELECT `productId` FROM `cart_item` where `productId`  = sProductId and `cartId` = cartId)
    -- Nếu tên biến là cartId trùng với tên trường `cartId` dẫn dến câu lệnh exists luôn đúng
    if(exists (SELECT `productId` FROM `cart_item` where `productId`  = sProductId and `cartId` = pCartId)) then
            set pAmount = (SELECT `quantity` FROM `cart_item` where `productId`  = sProductId and `cartId` = pCartId);
            set pTempTotal = pAmount + sQuantity;
			UPDATE `cart_item` SET `quantity` = pTempTotal  WHERE (`productId`  = sProductId and `cartId` = pCartId);
            set sMessage = concat("Update quantity success ","cartId:", pCartId,"productId:",sProductId,"quantity:", pTempTotal);
		else
            INSERT INTO `cart_item` (`productId`, `cartId`, `sku`, `price`, `discount`, 
			`quantity`, `active`, `createdAt`, `updatedAt`) VALUES 
			(sProductId, pCartId, 'sp2', sPrice, '0', sQuantity, '1', now(), now());
            set sMessage = concat("Add item to cart success ","cartId:", pCartId,"productId:", sProductId, "quantity:" , 1);
        end if;
end; //
-- collate utf8mb4_0900_ai_ci

-- Xóa 1 sản phẩm trong cart của người dung
DROP PROCEDURE IF EXISTS sp_removeProductInCart;
delimiter //
create procedure sp_removeProductInCart(
	in sCartId bigint,
    in sProductId bigint,
    out sMessage varchar(200)
)
begin
	declare sDeleteCart varchar(200);
    set sDeleteCart = "";
	-- Nếu sản phẩm trong bảng cart_item
	if(exists (select `productId`, `cartId` from `cart_item` where `productId` = sProductId and `cartId` =sCartId)) then
		DELETE FROM `cart_item` WHERE (`productId` = sProductId and `cartId` = sCartId);
        set sMessage = concat("Delete product in cart productId:", sProductId, "cartId:",sCartId);
    end if;
    -- Nếu trong bảng cart_item không còn sản phẩm nào thì xóa cart trong bảng cart
    if(not exists (SELECT * FROM `cart_item` where `cartId` = sCartId)) then
		DELETE FROM `cart` WHERE (`id` = sCartId);
        set sMessage = concat("Delete cart","cartId:",sCartId, "productId:", sProductId);
    end if;
end;
//

-- Cải tiến thêm sản phẩm vào giỏ hàng - VẬN DỤNG Tham số INOUT
/**
	Lần đầu: truyền các thông tin về giỏ hàng lên: sessionid, token, idstatus, productId, quantity
    Nếu cartId chưa có (=NULL) thì tạo mới và gửi về MÃ CARDID được tạo
    Nếu cartId đã có thì thêm và cập nhật số lượng cho sản phẩm
**/
DROP PROCEDURE IF EXISTS sp_addToCartAdvance;
delimiter //
create procedure sp_addToCartAdvance(
	inout sCartId bigint,
	in sSessionId varchar(100),
    in sTokenId varchar(100),
    in sStatus SMALLINT,
    in sProductId bigint,
    in sQuantity SMALLINT,
    in sPrice float,
    out sMessage varchar(200)
)
begin
    declare pAmount SMALLINT;
    declare pTempTotal SMALLINT;
    if((not exists (select * from cart where `id` = sCartId)) or sCartId = NULL) then
		INSERT INTO `cart` (`userId`,`sessionId`, `token`, `status`, `createdAt`) 
		VALUES ('1', sSessionId, sTokenId, '1', '2022-10-20 00:00:00');
        set sCartId = (SELECT max(id) from cart);
    end if;
    
    if(exists (SELECT `productId` FROM `cart_item` where `productId`  = sProductId and `cartId` = sCartId)) then
            set pAmount = (SELECT `quantity` FROM `cart_item` where `productId`  = sProductId and `cartId` = sCartId);
            set pTempTotal = pAmount + sQuantity;
			UPDATE `cart_item` SET `quantity` = pTempTotal  WHERE (`productId`  = sProductId and `cartId` = sCartId);
            set sMessage = concat("Update quantity success ","cartId:", sCartId,"productId:",sProductId,"quantity:", pTempTotal);
		else
            INSERT INTO `cart_item` (`productId`, `cartId`, `sku`, `price`, `discount`, 
			`quantity`, `active`, `createdAt`, `updatedAt`) VALUES 
			(sProductId, sCartId, 'sp2', sPrice, '0', sQuantity, '1', now(), now());
            set sMessage = concat("Add item to cart success ","cartId:", sCartId,"productId:", sProductId, "quantity:" , 1);
        end if;
end; //