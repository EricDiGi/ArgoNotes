CREATE DEFINER=`root`@`localhost` TRIGGER `users_AFTER_INSERT` AFTER INSERT ON `users` FOR EACH ROW BEGIN
	insert into user_state values (new.uid, current_timestamp(), TRUE);
    -- insert into accounts values (new.uid, uuid());
END