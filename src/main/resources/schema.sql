
ALTER TABLE stocks DROP FOREIGN KEY FKff7be959jyco0iukc1dcjj9qm;
ALTER TABLE cart_items DROP FOREIGN KEY FK1re40cjegsfvw58xrkdp6bac6;


ALTER TABLE stocks
ADD CONSTRAINT FKff7be959jyco0iukc1dcjj9qm
FOREIGN KEY (product_id)
REFERENCES products(id)
ON DELETE CASCADE;

ALTER TABLE cart_items
ADD CONSTRAINT FK1re40cjegsfvw58xrkdp6bac6
FOREIGN KEY (product_id)
REFERENCES products(id)
ON DELETE CASCADE; 