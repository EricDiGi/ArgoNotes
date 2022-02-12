CREATE DEFINER=`root`@`localhost` TRIGGER `users_BEFORE_DELETE` BEFORE DELETE ON `users` FOR EACH ROW BEGIN
	delete from accounts where user_acc= old.uid;
END