
alter table order_items drop column delivery_status;

alter table order_items change order_count order_quantity tinyint not null;