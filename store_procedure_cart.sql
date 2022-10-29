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
delimiter //
CREATE PROCEDURE `sp_removeProductInCart`(
	in pCartId bigint,
    in pProductId bigint,
    out pMessage varchar(200)
)
begin
	declare pDeleteCart varchar(200);
    set pDeleteCart = "";
	-- Nếu sản phẩm trong bảng cart_item
	if(exists (select `productId`, `cartId` from `cart_item` where `productId` = pProductId and `cartId` =pCartId)) then
		DELETE FROM `cart_item` WHERE (`productId` = pProductId and `cartId` = pCartId);
        set pMessage = concat("Delete product in cart productId:", pProductId, "cartId:",pCartId);
    end if;
    -- Nếu trong bảng cart_item không còn sản phẩm nào thì xóa cart trong bảng cart
    if(not exists (SELECT * FROM `cart_item` where `cartId` = pCartId)) then
		DELETE FROM `cart` WHERE (`id` = pCartId);
        set pMessage = concat("Delete cart","cartId:",pCartId, "productId:", pProductId);
    end if;
end //

-- Kiểm tra số lượng sản phẩm
drop procedure if exists sp_checkProductQuantity;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_checkProductQuantiy`(
	in pProductId integer,
    in pQuantity integer,
    out pValid boolean
)
BEGIN
	declare pTotal integer;
    set pValid = false;
    set pTotal  = (select `quantity` from `product` where `id` = pProductId);
    if(pQuantity <= pTotal) then
		set pValid = true;
    end if;
END

-- Cải tiến thêm sản phẩm vào giỏ hàng - VẬN DỤNG Tham số INOUT
/**
	Lần đầu: truyền các thông tin về giỏ hàng lên: sessionid, token, idstatus, productId, quantity
    Nếu cartId chưa có (=NULL) thì tạo mới và gửi về MÃ CARDID được tạo
    Nếu cartId đã có thì thêm và cập nhật số lượng cho sản phẩm
    
    Lưu ý: 
		khi sử dụng store procedure với transaction thì lúc rollback nó vẫn chạy các lệnh ở bên dưới, 
        nên ta có thể đưa về cuối để nó rollback
		Nếu sử dụng với SQL Exception thì ok
**/
DROP PROCEDURE IF EXISTS sp_addToCartAdvance;
delimiter //
create procedure sp_addToCartAdvance(
	inout pCartId bigint,
	in pSessionId varchar(100),
    in pTokenId varchar(100),
    in pStatus SMALLINT,
    in pProductId bigint,
    in pQuantity SMALLINT,
    in pPrice float,
    out pMessage varchar(200)
)
begin
    declare bAmount SMALLINT;
    declare bTempTotal SMALLINT;
    declare bAddCartItem boolean;
    
    START TRANSACTION;
	set @bCheckCartExists = (not exists (select * from cart where `id` = pCartId)) or pCartId = NULL;
	if(@bCheckCartExists) then
			INSERT INTO `cart` (`userId`,`sessionId`, `token`, `status`, `createdAt`) 
			VALUES ('1', pSessionId, pTokenId, '1', now());
			set pCartId = (SELECT max(id) from cart);
	end if;
    set @bAmount = (SELECT `quantity` FROM `cart_item` where `productId`  = pProductId and `cartId` = pCartId);
	set @bTempTotal = @bAmount + pQuantity;
	
    set @bValid = 0;
   -- select concat(pCartId, @bValid, pMessage, "----");
    if(exists (SELECT `productId` FROM `cart_item` where `productId`  = pProductId and `cartId` = pCartId)) then
            UPDATE `cart_item` SET `quantity` = @bTempTotal  WHERE (`productId`  = pProductId and `cartId` = pCartId);
			set pMessage = concat("Update quantity success ","cartId:", pCartId,"productId:",pProductId,"quantity:", @bTempTotal);
            
            call sp_checkProductQuantiy(pProductId, @bTempTotal, @bValid);	
			set @bQuantity = (select `quantity` from `product` where `id` = pProductId);
			UPDATE `product` SET `quantity` = (@bQuantity - @bTempTotal) WHERE (`id` = pProductId);
		else
			INSERT INTO `cart_item` (`productId`, `cartId`, `sku`, `price`, `discount`, 
			`quantity`, `active`, `createdAt`, `updatedAt`) VALUES 
			(pProductId, pCartId, 'sp2', pPrice, '0', pQuantity, '1', now(), now());
            
            call sp_checkProductQuantiy(pProductId, 1, @bValid);	
            set @bQuantity = (select `quantity` from `product` where `id` = pProductId);
            UPDATE `product` SET `quantity` = (@bQuantity - 1) WHERE (`id` = pProductId);
			set pMessage = concat("Add item to cart success ","cartId:", pCartId,"productId:", pProductId, "quantity:" , 1);
        end if;
	
	
	if(@bValid = false) then
		set pMessage = "Update fail quantity not valid....";
        select concat(pCartId, @bValid, pMessage, "vvvv");
		ROLLBACK;
	end if;
	COMMIT;
end; //

