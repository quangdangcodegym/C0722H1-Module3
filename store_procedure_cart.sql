delimiter //
DROP PROCEDURE IF EXISTS sp_addToCart;
create procedure sp_addToCart(
	in sSessionId varchar(200),
    in sTokenId varchar(200),
    in sStatus integer,
    in sProductId SMALLINT,
    in sQuantity SMALLINT,
    in sPrice integer,
    out sMessage varchar(200)
)
begin
	declare cartId integer;
    declare amount integer;
    declare tempTotal SMALLINT;
    if(not exists (SELECT * FROM cart where sessionId  = sSessionId)) then
		-- Chưa tồn tại thì thêm 1 dòng vào bảng cart
		INSERT INTO `cart` (`userId`,`sessionId`, `token`, `status`, `createdAt`) 
		VALUES ('1', sSessionId, sTokenId, '1', '2022-10-20 00:00:00');
        set cartId = (SELECT max(id) from cart);
	else
		-- Nếu đã tồn tại thì lấy idCart ra
		set cartId = (SELECT id FROM cart where sessionId  = sSessionId);
	end if;
		
    if(exists (SELECT productId FROM cart_item where productId  = sProductId and `cartId` = cartId)) then
			set amount = (SELECT `quantity` FROM `cart_item` where `productId`  = sProductId and `cartId` = cartId);
            set tempTotal = amount + sQuantity;
			UPDATE `cart_item` SET `quantity` = tempTotal  WHERE (`id`  = sProductId);
            set sMessage = "Update so luong thanh cong";
		else
			select 'test';
            INSERT INTO `shoppingcart`.`cart_item` (`productId`, `cartId`, `sku`, `price`, `discount`, 
			`quantity`, `active`, `createdAt`, `updatedAt`) VALUES 
			(sProductId, cartId, 'sp2', sPrice, '0', sQuantity, '1', now(), now());
            set sMessage = "Them item moi vao cart thanh cong";
        end if;
end;
-- collate utf8mb4_0900_ai_ci

